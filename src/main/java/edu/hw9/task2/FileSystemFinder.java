package edu.hw9.task2;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class FileSystemFinder {
    public static List<Path> findDirectoriesWithMoreThanNFiles(Path startDir, int n, int threads) {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool(threads)) {
            return forkJoinPool.invoke(new FindDirTask(startDir, n));
        }
    }

    public static List<Path> findFiles(Path startDir, Predicate<File> predicate, int threads) {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool(threads)) {
            return forkJoinPool.invoke(new FindFilesTask(startDir, predicate));
        }
    }

    private static class FindDirTask extends RecursiveTask<List<Path>> {
        private final List<Path> paths = new ArrayList<>();

        private final Path dir;

        private final int files;

        FindDirTask(Path dir, int files) {
            this.dir = dir;
            this.files = files;
        }

        @Override
        protected List<Path> compute() {
            var dirFiles = dir.toFile().listFiles(File::isFile);
            if (dirFiles != null && dirFiles.length > files) {
                paths.add(dir);
            }

            List<FindDirTask> recursiveTasks = new ArrayList<>();
            var dirs = dir.toFile().listFiles(File::isDirectory);
            if (dirs != null) {
                for (File subDir : dirs) {
                    FindDirTask recursiveTask = new FindDirTask(subDir.toPath(), files);
                    recursiveTask.fork();
                    recursiveTasks.add(recursiveTask);
                }
            }
            for (var task : recursiveTasks) {
                paths.addAll(task.join());
            }

            return paths;
        }
    }

    private static class FindFilesTask extends RecursiveTask<List<Path>> {
        private final List<Path> files = new ArrayList<>();

        private final Path dir;

        private final Predicate<File> predicate;

        FindFilesTask(Path dir, Predicate<File> predicate) {
            this.dir = dir;
            this.predicate = predicate;
        }

        @Override
        protected List<Path> compute() {
            var suitableFiles = dir.toFile().listFiles(predicate::test);
            if (suitableFiles != null) {
                for (var f : suitableFiles) {
                    files.add(f.toPath());
                }
            }

            List<FindFilesTask> recursiveTasks = new ArrayList<>();
            var dirs = dir.toFile().listFiles(File::isDirectory);
            if (dirs != null) {
                for (File subDir : dirs) {
                    FindFilesTask recursiveTask = new FindFilesTask(subDir.toPath(), predicate);
                    recursiveTask.fork();
                    recursiveTasks.add(recursiveTask);
                }
            }
            for (var task : recursiveTasks) {
                files.addAll(task.join());
            }

            return files;
        }

    }

    public static class SimpleFilePredicates {
        public static Predicate<File> sizeMoreThan(int bytes) {
            return (f) -> f.length() > bytes;
        }

        public static Predicate<File> sizeLessThan(int bytes) {
            return (f) -> f.length() < bytes;
        }

        public static Predicate<File> sizeExactly(int bytes) {
            return (f) -> f.length() == bytes;
        }

        public static Predicate<File> hasExtension(String extension) {
            return (f) -> {
                String fileName = f.getName();
                int dotIndex = fileName.lastIndexOf('.');
                String fileExtension = "";
                if (dotIndex != -1) {
                    fileExtension = fileName.substring(dotIndex);
                }
                return fileExtension.equalsIgnoreCase(extension);
            };
        }
    }
}
