package edu.hw4;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

public class Tasks {

    private static final int DANGEROUS_HEIGHT = 100;

    private Tasks() {
    }

    static List<Animal> sortByHeight(@NotNull List<Animal> animals) {
        return Objects.requireNonNull(animals).stream()
            .sorted(Comparator.comparing(Animal::height)).toList();

    }

    static List<Animal> pickTheHeaviest(@NotNull List<Animal> animals, int k) {
        if (k < 0) {
            throw new IllegalArgumentException();
        }

        return Objects.requireNonNull(animals).stream()
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    static Map<Animal.Type, Integer> countEachType(@NotNull List<Animal> animals) {
        return Objects.requireNonNull(animals).stream()
            .map(Animal::type)
            .collect(Collectors.toMap(
                type -> type,
                type -> 1,
                Integer::sum
            ));
    }

    static Animal getAnimalWithTheLongestName(@NotNull List<Animal> animals) {
        return Objects.requireNonNull(animals).stream()
            .max(Comparator.comparing(animal -> animal.name().length()))
            .orElse(null);
    }

    static Animal.Sex getTheMostCommonSex(@NotNull List<Animal> animals) {
        return Objects.requireNonNull(animals).stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet().stream()
            .max(Comparator.comparingLong(Map.Entry::getValue))
            .orElse(new AbstractMap.SimpleEntry<>(null, 0L))
            .getKey();

    }

    static Map<Animal.Type, Animal> getTheMostHeaviestAnimalOfEachType(@NotNull List<Animal> animals) {
        return Objects.requireNonNull(animals).stream()
            .collect(Collectors.toMap(
                    Animal::type,
                    Function.identity(),
                    (t, o) ->
                        (o.weight() > t.weight()) ? o : t
                )
            );

    }

    static Animal getKthOldestAnimal(@NotNull List<Animal> animals, int k) {
        if (k < 1) {
            throw new IllegalArgumentException();
        }
        return Objects.requireNonNull(animals).stream()
            .sorted(Comparator.comparing(Animal::age).reversed())
            .skip(k - 1).findFirst().orElse(null);
    }

    static Optional<Animal> findHeaviestAnimalBelowHeight(@NotNull List<Animal> animals, int height) {
        return Objects.requireNonNull(animals).stream()
            .filter(animal -> animal.height() < height)
            .max(Comparator.comparing(Animal::weight));
    }

    static Integer countPaws(@NotNull List<Animal> animals) {
        return Objects.requireNonNull(animals).stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    static List<Animal> getAnimalsMismatchAgeAndPaws(@NotNull List<Animal> animals) {
        return Objects.requireNonNull(animals).stream()
            .filter(animal -> animal.paws() != animal.age()).toList();
    }

    static List<Animal> getBitingAnimalsWithBigHeight(@NotNull List<Animal> animals) {
        return Objects.requireNonNull(animals).stream()
            .filter(Animal::bites)
            .filter(animal -> animal.height() > DANGEROUS_HEIGHT)
            .toList();
    }

    static Integer countWeightMoreThanHeight(@NotNull List<Animal> animals) {
        return Objects.requireNonNull(animals).stream()
            .filter(animal -> animal.weight() > animal.height())
            .mapToInt((animal) -> 1).sum();
    }

    static List<Animal> getAnimalsWithNamesConsistMoreThanTwoWords(@NotNull List<Animal> animals) {
        return Objects.requireNonNull(animals).stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .toList();
    }

    static boolean isContainDogHigher(@NotNull List<Animal> animals, int height) {
        return Objects.requireNonNull(animals).stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > height);

    }

    static Map<Animal.Type, Integer> getTotalWeightInAgeRange(@NotNull List<Animal> animals, int k, int l) {
        if (k > l || k < 0) {
            throw new IllegalArgumentException();
        }
        return Objects.requireNonNull(animals).stream()
            .filter(animal -> animal.age() > k && animal.age() < l)
            .collect(Collectors.toMap(
                Animal::type,
                Animal::weight,
                Integer::sum
            ));
    }

    static List<Animal> sortByTypeThanBySexThanByName(@NotNull List<Animal> animals) {
        return Objects.requireNonNull(animals).stream()
            .sorted(Comparator
                .comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    static boolean isSpidersBiteOftenThanDogs(@NotNull List<Animal> animals) {
        return Objects.requireNonNull(animals).stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG)
            && animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG || animal.type() == Animal.Type.SPIDER)
            .collect(Collectors.toMap(
                Animal::type,
                animal -> animal.bites() ? 1L : 0L,
                Long::sum
            ))
            .entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> (double) entry.getValue()
                    / animals.stream().filter(animal -> animal.type() == entry.getKey()).count()
            ))
            .entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .max(Map.Entry.comparingByValue())
            .orElse(new AbstractMap.SimpleEntry<>(Animal.Type.DOG, 0.0))
            .getKey() != Animal.Type.DOG;
    }

    static Animal getTheHeaviestFish(@NotNull List<Animal>... lists) {
        return Arrays.stream(Objects.requireNonNull(lists))
            .flatMap(list ->
                Objects.requireNonNull(list).stream().filter(animal -> animal.type() == Animal.Type.FISH))
            .max(Comparator.comparing(Animal::weight)).orElse(null);
    }

    static Map<String, Set<Validation.ValidationError>> getErrors(@NotNull List<Animal> animals) {
        return Objects.requireNonNull(animals).stream()
            .collect(Collectors.toMap(
                Animal::name,
                animal ->
                    Arrays.stream(Validation.ValidationError.values())
                        .filter(validation -> validation.getValidationFunction().apply(animal))
                        .collect(Collectors.toSet()),
                (set, oSet) ->
                    Stream.concat(set.stream(), oSet.stream()).collect(Collectors.toSet())
            ))
            .entrySet().stream()
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
            ));
    }

    static Map<String, String> getErrorsReadable(@NotNull List<Animal> animals) {
        return getErrors(Objects.requireNonNull(animals)).entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> {
                    StringBuilder sb = new StringBuilder();
                    for (Validation.ValidationError error : entry.getValue()) {
                        sb.append(error.toString().toLowerCase().replace('_', ' ')).append(", ");
                    }
                    sb.delete(sb.length() - 2, sb.length());
                    return sb.toString();
                }
            ));
    }
}
