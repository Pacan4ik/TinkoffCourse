package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteSyncPersonDataBase implements PersonDataBase {
    private final Map<Integer, Person> idsMap = new HashMap<>();
    private final Map<String, List<Person>> nameMap = new HashMap<>();
    private final Map<String, List<Person>> adressMap = new HashMap<>();
    private final Map<String, List<Person>> phoneMap = new HashMap<>();

    ReadWriteLock lock = new ReentrantReadWriteLock(true);

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            if (idsMap.containsKey(person.id())) {
                throw new IDAlreadyExistsException(String.format(
                    "DataBase already contains Person with id %d: %s",
                    person.id(),
                    idsMap.get(person.id())
                ));
            }
            idsMap.put(person.id(), person);
            nameMap.computeIfAbsent(person.name(), list -> new ArrayList<>()).add(person);
            adressMap.computeIfAbsent(person.address(), list -> new ArrayList<>()).add(person);
            phoneMap.computeIfAbsent(person.phoneNumber(), list -> new ArrayList<>()).add(person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person personToDelete = idsMap.remove(id);
            if (personToDelete != null) {
                removeByKey(nameMap, personToDelete.name(), personToDelete);
                removeByKey(adressMap, personToDelete.address(), personToDelete);
                removeByKey(phoneMap, personToDelete.phoneNumber(), personToDelete);
            } else {
                throw new NoSuchElementException(String.format("no Person with id %d found", id));
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public int getSize() {
        lock.readLock().lock();
        try {
            return idsMap.size();
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return getByKey(nameMap, name);
        } finally {
            lock.readLock().unlock();
        }

    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return getByKey(adressMap, address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return getByKey(phoneMap, phone);
        } finally {
            lock.readLock().unlock();
        }

    }

    @Override
    public Person getById(int id) {
        return idsMap.get(id);
    }

    private <K> void removeByKey(Map<K, List<Person>> map, K key, Person person) {
        List<Person> list = map.get(key);
        list.remove(person);
        if (list.isEmpty()) {
            map.remove(key);
        }
    }

    private <K> List<Person> getByKey(Map<K, List<Person>> map, K key) {
        List<Person> personList = map.get(key);
        if (personList == null) {
            return Collections.emptyList();
        } else {
            return List.copyOf(personList);
        }
    }
}
