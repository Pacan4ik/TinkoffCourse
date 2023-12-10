package edu.hw7;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDataBase;
import edu.hw7.Task3.ReadWriteSyncPersonDataBase;
import edu.hw7.Task3.SyncPersonDataBase;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {
    static Arguments[] dataBases() {
        return new Arguments[] {
            Arguments.of(new SyncPersonDataBase()),
            Arguments.of(new ReadWriteSyncPersonDataBase())
        };
    }

    private static final List<Person> PERSON_LIST = List.of(
        new Person(1, "Ivan", "улица Пушкина", "100-123"),
        new Person(2, "Dmitriy", "улица Советская", "345-134"),
        new Person(3, "Olga", "проспект Ленина", "567-789"),
        new Person(4, "Elena", "улица Пушкина", "890-234"),
        new Person(5, "Ivan", "переулок Горького", "456-567"),
        new Person(6, "Svetlana", "проспект Гагарина", "789-012"),
        new Person(7, "Nikolay", "улица Маяковского", "123-456"),
        new Person(8, "Maria", "переулок Горького", "234-567"),
        new Person(9, "Elena", "проспект Ленина", "890-123"),
        new Person(10, "Ekaterina", "улица Советская", "567-890"),
        new Person(11, "Andrey", "переулок Горького", "123-789"),
        new Person(12, "Natalia", "проспект Гагарина", "456-789"),
        new Person(13, "Sergey", "улица Маяковского", "234-890"),
        new Person(14, "Svetlana", "проспект Ленина", "789-345"),
        new Person(15, "Vladimir", "улица Пушкина", "012-345"),
        new Person(16, "Ivan", "бульвар Гагарина", "678-901"),
        new Person(17, "Igor", "переулок Горького", "345-678"),
        new Person(18, "Nikolay", "проспект Ленина", "901-234"),
        new Person(19, "Roman", "улица Советская", "567-123"),
        new Person(20, "Olga", "улица Маяковского", "890-567")
    );

    @ParameterizedTest
    @MethodSource("dataBases")
    void shouldAddPersons(PersonDataBase dataBase) throws InterruptedException, ExecutionException {
        //given
        var tasks = PERSON_LIST.stream().map(person -> (Callable<Void>) () -> {
            dataBase.add(person);
            return null;
        }).toList();

        //when
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            executorService.invokeAll(tasks);
        }

        //then
        Assertions.assertThat(PERSON_LIST).extracting(person -> dataBase.getById(person.id()))
            .noneMatch(Objects::isNull);
    }

    @ParameterizedTest
    @MethodSource("dataBases")
    void shouldDeletePersons(PersonDataBase dataBase) throws InterruptedException, ExecutionException {
        //given
        PERSON_LIST.forEach(dataBase::add);
        var tasks = PERSON_LIST.stream().map(person -> (Callable<Void>) () -> {
            dataBase.delete(person.id());
            return null;
        }).toList();

        //when
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            executorService.invokeAll(tasks);
        }

        //then
        assertEquals(0, dataBase.getSize());
    }

    @ParameterizedTest
    @MethodSource("dataBases")
    void shouldReturnEmptyLists(PersonDataBase dataBase) {
        //when
        var res = List.of(
            dataBase.findByName("name"),
            dataBase.findByAddress("adress"),
            dataBase.findByPhone("phone")
        );

        //then
        Assertions.assertThat(res).allMatch(List::isEmpty);
    }

    @ParameterizedTest
    @MethodSource("dataBases")
    void shouldThrowExceptionIfIDExists(PersonDataBase dataBase) {
        //given
        int sameID = 0;

        //when
        dataBase.add(new Person(sameID, "", "", ""));

        //then
        assertThrows(
            PersonDataBase.IDAlreadyExistsException.class,
            () -> dataBase.add(new Person(sameID, "", "", ""))
        );
    }

}
