package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class SyncPersonDataBase implements PersonDataBase {

    private final Map<Integer, Person> idsMap = new HashMap<>();
    private final Map<String, List<Person>> nameMap = new HashMap<>();
    private final Map<String, List<Person>> adressMap = new HashMap<>();
    private final Map<String, List<Person>> phoneMap = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
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
    }

    @Override
    public synchronized void delete(int id) {
        Person personToDelete = idsMap.remove(id);
        if (personToDelete != null) {
            removeByKey(nameMap, personToDelete.name(), personToDelete);
            removeByKey(adressMap, personToDelete.address(), personToDelete);
            removeByKey(phoneMap, personToDelete.phoneNumber(), personToDelete);
        } else {
            throw new NoSuchElementException(String.format("no Person with id %d found", id));
        }
    }

    @Override
    public synchronized int getSize() {
        return idsMap.size();
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return getByKey(nameMap, name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return getByKey(adressMap, address);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return getByKey(phoneMap, phone);
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
