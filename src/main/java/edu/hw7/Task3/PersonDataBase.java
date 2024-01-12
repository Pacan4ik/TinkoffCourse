package edu.hw7.Task3;

import java.util.List;

public interface PersonDataBase {
    void add(Person person) throws IDAlreadyExistsException;

    void delete(int id);

    int getSize();

    List<Person> findByName(String name);

    List<Person> findByAddress(String address);

    List<Person> findByPhone(String phone);

    Person getById(int id);

    class IDAlreadyExistsException extends RuntimeException {
        IDAlreadyExistsException(String message) {
            super(message);
        }

        IDAlreadyExistsException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
