package edu.hw3;

import java.util.Iterator;
import java.util.SequencedCollection;

public class Task8 {

    public static class BackwartIterator<T> implements Iterator<T> {

        Iterator<T> iterator;

        public BackwartIterator(SequencedCollection<T> seqCol) {
            iterator = seqCol.reversed().iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public T next() {
            return iterator.next();
        }

    }
}
