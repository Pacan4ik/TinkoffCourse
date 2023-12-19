package edu.hw3;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Task8 {

    //    public static class BackwardIterator<T> implements Iterator<T> {
//
//        Iterator<T> iterator;
//
//        public BackwardIterator(SequencedCollection<T> seqCol) {
//            iterator = seqCol.reversed().iterator();
//        }
//
//        @Override
//        public boolean hasNext() {
//            return iterator.hasNext();
//        }
//
//        @Override
//        public T next() {
//            return iterator.next();
//        }
//
//
//    }
    public static class BackwardIterator<T> implements Iterator<T> {

        private final Collection<T> collection;
        private final List<T> elements;
        private int index;

        public BackwardIterator(Collection<T> collection) {
            this.collection = collection;
            this.elements = List.copyOf(collection);
            index = elements.size() - 1;
        }

        private void checkForComodification() {
            List<T> checkElements = List.copyOf(collection);
            if (checkElements.size() != elements.size()) {
                throw new ConcurrentModificationException();
            }

            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i) != checkElements.get(i)) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        @Override
        public boolean hasNext() {
            checkForComodification();
            return index >= 0;
        }

        @Override
        public T next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements.get(index--);
        }
    }

}
