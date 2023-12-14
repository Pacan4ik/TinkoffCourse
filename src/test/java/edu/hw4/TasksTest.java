package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TasksTest {

    private static final List<Animal> animalList1 = new ArrayList<>();
    private static final List<Animal> animalList2 = new ArrayList<>();


    @BeforeAll
    private static void initAnimalList1() {
        animalList1.add(new Animal("Lion", Animal.Type.CAT, Animal.Sex.M, 5, 120, 150, true));
        animalList1.add(new Animal("Golden Retriever", Animal.Type.DOG, Animal.Sex.M, 4, 65, 30, true));
        animalList1.add(new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 2, 30, 1, false));
        animalList1.add(new Animal("Goldfish", Animal.Type.FISH, Animal.Sex.F, 1, 5, 0, false));
        animalList1.add(new Animal("Tarantula", Animal.Type.SPIDER, Animal.Sex.F, 2, 1, 0, true));
        animalList1.add(new Animal("Tiger", Animal.Type.CAT, Animal.Sex.M, 7, 130, 200, true));
        animalList1.add(new Animal("Bulldog", Animal.Type.DOG, Animal.Sex.F, 3, 55, 25, true));
        animalList1.add(new Animal("Canary", Animal.Type.BIRD, Animal.Sex.M, 2, 12, 1, false));
        animalList1.add(new Animal("Salmon", Animal.Type.FISH, Animal.Sex.M, 1, 5, 2, false));
        animalList1.add(new Animal("Black Widow", Animal.Type.SPIDER, Animal.Sex.F, 1, 1, 0, true));
    }

    @BeforeAll
    private static void initAnimalList2() {
        animalList2.add(new Animal("Great White Shark", Animal.Type.FISH, null, -15, 400, 2000, true));
        animalList2.add(new Animal("Another Black Widow", Animal.Type.SPIDER, Animal.Sex.F, 1, 1, 0, true));
        animalList2.add(new Animal("Araneus", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, -1, false));
        animalList2.add(new Animal("Friendly Dog", Animal.Type.DOG, Animal.Sex.M, 4, 40, 20, false));
        animalList2.add(new Animal("Very Agressive Сhihuahua", Animal.Type.DOG, Animal.Sex.F, 150, 5200, -10, true));
    }

    @Test
    @DisplayName("Task1. Сортировка по росту")
    void shouldSortByHeight() {
        //given
        List<Animal> animals = animalList1;

        //when
        List<Animal> sorted = Tasks.sortByHeight(animals);

        //then
        Assertions.assertThat(sorted)
            .isSortedAccordingTo(Comparator.comparing(Animal::height));
    }

    @Test
    @DisplayName("Task2. Выбор 3 самых тяжелых")
    void shouldReturnTheHeaviest() {
        //given
        List<Animal> animals = animalList1;

        //when
        List<Animal> theHeaviest = Tasks.pickTheHeaviest(animals, 3);

        //then
        Assertions.assertThat(theHeaviest)
            .containsExactly(animals.get(5), animals.get(0), animals.get(1));
    }

    @Test
    @DisplayName("Task3. Подсчёт животных каждого вида")
    void shouldCountEachType() {
        //given
        List<Animal> animals = animalList1;

        //when
        Map<Animal.Type, Integer> types = Tasks.countEachType(animals);

        //then
        Map<Animal.Type, Integer> expected = new HashMap<>();
        expected.put(Animal.Type.BIRD, 2);
        expected.put(Animal.Type.FISH, 2);
        expected.put(Animal.Type.CAT, 2);
        expected.put(Animal.Type.DOG, 2);
        expected.put(Animal.Type.SPIDER, 2);
        Assertions.assertThat(types).containsExactlyInAnyOrderEntriesOf(expected);
    }

    @Test
    @DisplayName("Task4. Животное с самым длинным именем")
    void shouldReturnAnimalWithTheLongestName() {
        //given
        List<Animal> animals = animalList1;

        //when
        Animal animalWithLongestName = Tasks.getAnimalWithTheLongestName(animals);

        //then
        Assertions.assertThat(animalWithLongestName).isEqualTo(animals.get(1));
    }

    @Test
    @DisplayName("Task5. Каких животных больше: самцов или самок")
    void shouldReturnTheMostCommonSex() {
        //given
        List<Animal> animals = animalList1;

        //when
        Animal.Sex mostCommon = Tasks.getTheMostCommonSex(animals);

        //then
        assertEquals(mostCommon, Animal.Sex.M);

    }

    @Test
    @DisplayName("Task6. Самое тяжелое животное каждого вида")
    void shouldReturnTheMostHeaviestOfEachType() {
        //given
        List<Animal> animals = animalList1;

        //when
        Map<Animal.Type, Animal> map = Tasks.getTheMostHeaviestAnimalOfEachType(animals);

        //then
        Map<Animal.Type, Animal> expected = new HashMap<>();
        expected.put(Animal.Type.CAT, animals.get(5));
        expected.put(Animal.Type.DOG, animals.get(1));
        expected.put(Animal.Type.BIRD, animals.get(2));
        expected.put(Animal.Type.FISH, animals.get(8));
        expected.put(Animal.Type.SPIDER, animals.get(4));

        Assertions.assertThat(map).containsExactlyInAnyOrderEntriesOf(expected);

    }

    @Test
    @DisplayName("Task7. K-е самое старое животное")
    void shouldReturnKthOldestAnimal() {
        //given
        List<Animal> animals = animalList1;

        //when
        Animal oldest = Tasks.getKthOldestAnimal(animals, 3);

        //then
        assertEquals(animals.get(1), oldest);
    }

    @Test
    @DisplayName("Task8. Самое тяжелое животное среди животных ниже К")
    void shouldReturnTheHeaviestBelowHeight() {
        //given
        List<Animal> animals = animalList1;

        //when
        Optional<Animal> heaviest = Tasks.findHeaviestAnimalBelowHeight(animals, 60);

        //then
        assertTrue(heaviest.isPresent());
        assertEquals(heaviest.get(), animals.get(6));
    }

    @Test
    @DisplayName("Task8. Если такое животное нет -> Optional.empty")
    void shouldReturnEmpty() {
        //given
        List<Animal> animals = animalList1;

        //when
        Optional<Animal> heaviest = Tasks.findHeaviestAnimalBelowHeight(animals, 1);

        //then
        assertEquals(Optional.empty(), heaviest);
    }

    @Test
    @DisplayName("Task9. Сумма лап")
    void shouldCountPaws() {
        //given
        List<Animal> animals = animalList1;

        //when
        int paws = Tasks.countPaws(animals);

        //then
        assertEquals(36, paws);
    }

    @Test
    @DisplayName("Task9. В пустом списке 0 лап")
    void shouldReturnZeroWhenEmpty() {
        //given
        List<Animal> emptyList = new ArrayList<>();

        //when
        int paws = Tasks.countPaws(emptyList);

        //then
        assertEquals(0, paws);
    }

    @Test
    @DisplayName("Task10. Возраст не совпадает с количеством лап")
    void shouldReturnMismatchesAgeAndPaws() {
        //given
        List<Animal> animals = animalList1;

        //when
        List<Animal> mismatchesList = Tasks.getAnimalsMismatchAgeAndPaws(animals);

        //then
        Assertions.assertThat(mismatchesList).containsExactlyInAnyOrderElementsOf(
            List.of(
                animals.get(0),
                animals.get(3),
                animals.get(4),
                animals.get(5),
                animals.get(6),
                animals.get(8),
                animals.get(9)
            )
        );
    }

    @Test
    @DisplayName("Task11. Кусающиеся животные с ростом > 100")
    void shouldReturnBitingAnimalsWithHeightMoreThanOneHundred() {
        //given
        List<Animal> animals = animalList1;

        //when
        List<Animal> bitingAnimals = Tasks.getBitingAnimalsWithBigHeight(animals);

        //then
        Assertions.assertThat(bitingAnimals).containsExactlyInAnyOrderElementsOf(
            List.of(
                animals.get(0),
                animals.get(5)
            )
        );
    }

    @Test
    @DisplayName("Task12. Вес превышает рост")
    void shouldCountAnimalsWithWeightMoreThanHeight() {
        //given
        List<Animal> animals = animalList1;

        //when
        int amount = Tasks.countWeightMoreThanHeight(animals);

        //then
        assertEquals(2, amount);
    }

    @Test
    @DisplayName("Task13. Список животных, чьи имена состоят более чем из двух слов")
    void shouldReturnAnimalsWithNamesMoreThanTwoWords() {
        //given
        List<Animal> animals = animalList2;
        //when
        List<Animal> twoWordsAnimalList = Tasks.getAnimalsWithNamesConsistMoreThanTwoWords(animals);

        //then
        Assertions.assertThat(twoWordsAnimalList).containsExactlyInAnyOrderElementsOf(
            List.of(animals.get(0), animals.get(1), animals.get(4))
        );
    }

    @Test
    @DisplayName("Task13. Возвращает пустой список, если нет животных с именами более чем из двух слов")
    void shouldReturnEmptyListIfAllOfAnimalsHaveLessThanThreeWordsName() {
        //given
        List<Animal> animals = animalList1;

        //when
        List<Animal> twoWordsAnimalList = Tasks.getAnimalsWithNamesConsistMoreThanTwoWords(animals);

        //then
        Assertions.assertThat(twoWordsAnimalList).isEmpty();

    }

    @Test
    @DisplayName("Task14. Cписок содержит собаку ростом более 60")
    void shouldFindDogHigher() {
        //given
        List<Animal> animals = animalList1;

        //when
        boolean isContainDogHigher = Tasks.isContainDogHigher(animals, 60);

        //then
        assertTrue(isContainDogHigher);
    }

    @Test
    @DisplayName("Task14. Список не содержит собаку ростом более 65")
    void shouldntFindDogHigher() {
        //given
        List<Animal> animals = animalList1;

        //when
        boolean isContainDogHigher = Tasks.isContainDogHigher(animals, 65);

        //then
        assertFalse(isContainDogHigher);
    }

    @Test
    @DisplayName("Task14. Пустой список не содержит собаку любого роста")
    void shouldntFindDogHigherIfEmpty() {
        //given
        List<Animal> emptyList = new ArrayList<>();

        //when
        boolean isContainDogHigher = Tasks.isContainDogHigher(emptyList, 60);

        //then
        assertFalse(isContainDogHigher);
    }

    @Test
    @DisplayName("Task15. Суммарный вес животных каждого вида в пределах возраста")
    void shouldCountSummaryWeightByType() {
        //given
        List<Animal> animals = animalList1;

        //when
        var weightTypeMap = Tasks.getTotalWeightInAgeRange(animals, 1, 6);

        //then
        Map<Animal.Type, Integer> expected = new HashMap<>();
        expected.put(Animal.Type.DOG, 55);
        expected.put(Animal.Type.CAT, 150);
        expected.put(Animal.Type.BIRD, 2);
        expected.put(Animal.Type.SPIDER, 0);
        Assertions.assertThat(weightTypeMap).containsExactlyInAnyOrderEntriesOf(expected);
    }

    @Test
    @DisplayName("Task15. Суммарный вес животных каждого вида в пределах возраста - empty Map, если таких животных нет")
    void shouldReturnEmptyMapIfNoAnimalMatches() {
        //given
        List<Animal> animals = animalList1;

        //when
        var weightTypeMap = Tasks.getTotalWeightInAgeRange(animals, 1000, 1500);

        //then
        Assertions.assertThat(weightTypeMap).isEmpty();
    }

    @Test
    @DisplayName("Task15. Суммарный вес животных каждого вида. Неправильный интервал")
    void shouldThrowExceptionIfWrongInterval() {
        //given
        List<Animal> animals = animalList1;

        //then
        assertThrows(IllegalArgumentException.class, () -> Tasks.getTotalWeightInAgeRange(animals, 25, 20));
        assertThrows(IllegalArgumentException.class, () -> Tasks.getTotalWeightInAgeRange(animals, -5, 20));

    }

    @Test
    @DisplayName("Task16. Список животных, отсортированный по виду, затем по полу, затем по имени")
    void shouldSortByTypeThanBySexThanByName() {
        //given
        List<Animal> animals = animalList1;

        //when
        var sorted = Tasks.sortByTypeThanBySexThanByName(animals);

        //then
        Assertions.assertThat(sorted).isSortedAccordingTo(
            Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name));
    }

    @Test
    @DisplayName("Task17. Правда ли, что пауки кусаются чаще, чем собаки - правда")
    void shouldReturnTrueIfBitingSpidersMoreThanDogs() {
        //given
        List<Animal> animals = new ArrayList<>(animalList2);
        animals.add(new Animal("Another Biting Spider", Animal.Type.SPIDER, Animal.Sex.M, 2, 1, 0, true));

        //when
        boolean isSpidersBiteOften = Tasks.isSpidersBiteOftenThanDogs(animals);

        //then
        assertTrue(isSpidersBiteOften);
    }

    @Test
    @DisplayName("Task17. Правда ли, что пауки кусаются чаще, чем собаки - неправда")
    void shouldReturnFalseIfBitingDogsMoreThanSpiders() {
        //given
        List<Animal> animals = new ArrayList<>(animalList2);
        animals.add(new Animal("Another Biting Dog", Animal.Type.DOG, Animal.Sex.M, 2, 30, 8, true));

        //when
        boolean isSpidersBiteOften = Tasks.isSpidersBiteOftenThanDogs(animals);

        //then
        assertFalse(isSpidersBiteOften);
    }

    @Test
    @DisplayName("Task17. Правда ли, что пауки кусаются чаще, чем собаки - Одинаково")
    void shouldReturnFalseIfEqually() {
        //given
        List<Animal> animals = animalList2;

        //when
        boolean isSpidersBiteOften = Tasks.isSpidersBiteOftenThanDogs(animals);

        //then
        assertFalse(isSpidersBiteOften);
    }

    @Test
    @DisplayName("Task17. Правда ли, что пауки кусаются чаще, чем собаки - собак нет, нельзя судить (неправда)")
    void shouldReturnFalseIfNoDogs() {
        //given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Tarantula", Animal.Type.SPIDER, Animal.Sex.F, 2, 1, 0, true));
        animals.add(new Animal("Araneus", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, -1, false));
        animals.add(new Animal("Black Widow", Animal.Type.SPIDER, Animal.Sex.F, 1, 1, 0, true));
        animals.add(new Animal("Another Black Widow", Animal.Type.SPIDER, Animal.Sex.F, 1, 1, 0, true));

        //when
        boolean isSpidersBiteOften = Tasks.isSpidersBiteOftenThanDogs(animals);

        //then
        assertFalse(isSpidersBiteOften);
    }

    @Test
    @DisplayName("Task17. Правда ли, что пауки кусаются чаще, чем собаки - пауков нет, нельзя судить (неправда)")
    void shouldReturnFalseIfNoSpiders() {
        //given
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Friendly Dog", Animal.Type.DOG, Animal.Sex.M, 4, 40, 20, false));
        animals.add(new Animal("Very Agressive Сhihuahua", Animal.Type.DOG, Animal.Sex.F, 4200, 12, -10, true));
        animals.add(new Animal("Bulldog", Animal.Type.DOG, Animal.Sex.F, 3, 55, 25, true));
        animals.add(new Animal("Golden Retriever", Animal.Type.DOG, Animal.Sex.M, 4, 65, 30, true));

        //when
        boolean isSpidersBiteOften = Tasks.isSpidersBiteOftenThanDogs(animals);

        //then
        assertFalse(isSpidersBiteOften);
    }

    @Test
    @DisplayName("Task 18. Самая тяжелая рыба из нескольких списков")
    void shouldFindTheHeaviestFish() {
        //given
        List<Animal> list1 = animalList1;
        List<Animal> list2 = animalList2;

        //when
        Animal fish = Tasks.getTheHeaviestFish(list1, list2);

        //then
        assertSame(list2.get(0), fish);
    }

    @Test
    @DisplayName("Task 18. Самая тяжелая рыба из нескольких списков. Один из списков пустой")
    void shouldFindTheHeaviestFishDespiteTheEmptyList() {
        //given
        List<Animal> list1 = new ArrayList<>();
        List<Animal> list2 = animalList2;

        //when
        Animal fish = Tasks.getTheHeaviestFish(list1, list2);

        //then
        assertSame(list2.get(0), fish);
    }

    @Test
    @DisplayName("Task19. Список ошибок")
    void shouldFindErrors() {
        //given
        List<Animal> animals = animalList2;

        //when
        var namesErrorsMap = Tasks.getErrors(animals);

        //then
        Map<String, Set<Validation.ValidationError>> expected = new HashMap<>();
        expected.put(
            "Great White Shark",
            Stream.of(Validation.ValidationError.SEX_NULL, Validation.ValidationError.WRONG_AGE)
                .collect(Collectors.toSet())
        );
        expected.put(
            "Araneus",
            Stream.of(Validation.ValidationError.WRONG_WEIGHT)
                .collect(Collectors.toSet())
        );
        expected.put(
            "Very Agressive Сhihuahua",
            Stream.of(Validation.ValidationError.WRONG_AGE, Validation.ValidationError.WRONG_WEIGHT, Validation.ValidationError.WRONG_HEIGHT)
                .collect(Collectors.toSet())
        );

        Assertions.assertThat(namesErrorsMap).containsExactlyInAnyOrderEntriesOf(expected);
    }

    @Test
    @DisplayName("Task19. Нет ошибок")
    void shouldntFindErrors() {
        //given
        List<Animal> animals = animalList1;

        //when
        var namesErrorsMap = Tasks.getErrors(animals);

        //then
        Assertions.assertThat(namesErrorsMap).isEmpty();
    }

    @Test
    @DisplayName("Task20. Читаемый вывод ошибoк")
    void shouldReturnReadableErrorsString() {
        //given
        List<Animal> animals = animalList2;

        //when
        var namesErrorsStrMap = Tasks.getErrorsReadable(animals);

        //then
        Pattern pattern = Pattern.compile("[a-zA-Z ]+(, [a-zA-Z ]+)*");
        Assertions.assertThat(namesErrorsStrMap.values()).allMatch(s -> pattern.matcher(s).matches());

    }

    static Arguments[] functionsToNull() {
        return new Arguments[] {
            Arguments.of((Function<List<Animal>, ?>) Tasks::getAnimalWithTheLongestName),
            Arguments.of((Function<List<Animal>, ?>) Tasks::getTheMostCommonSex),
            Arguments.of((Function<List<Animal>, ?>) (list) -> Tasks.getKthOldestAnimal(list, 3)),
        };
    }

    @ParameterizedTest
    @MethodSource("functionsToNull")
    @DisplayName("Возвращается Null если список животных пустой")
    void shouldReturnNullIfEmpty(Function<List<Animal>, ?> func) {
        //given
        List<Animal> emptyList = new ArrayList<>();

        //when
        var res = func.apply(emptyList);

        //then
        assertNull(res);
    }

    static Arguments[] functionsToException() {
        return new Arguments[] {
            Arguments.of((Function<List<Animal>, List<Animal>>) Tasks::sortByHeight),
            Arguments.of((Function<List<Animal>, List<Animal>>) (list) -> Tasks.pickTheHeaviest(list, 3)),
            Arguments.of((Function<List<Animal>, Map<Object, Object>>) list ->
                new HashMap<>(Tasks.countEachType(list))),
            Arguments.of((Function<List<Animal>, ?>) Tasks::getAnimalWithTheLongestName),
            Arguments.of((Function<List<Animal>, ?>) Tasks::getTheMostCommonSex),
            Arguments.of((Function<List<Animal>, Map<Object, Object>>) list ->
                new HashMap<>(Tasks.getTheMostHeaviestAnimalOfEachType(list))),
            Arguments.of((Function<List<Animal>, ?>) (list) -> Tasks.getKthOldestAnimal(list, 3)),
            Arguments.of((Function<List<Animal>, ?>) Tasks::countPaws),
            Arguments.of((Function<List<Animal>, List<Animal>>) Tasks::getAnimalsMismatchAgeAndPaws),
            Arguments.of((Function<List<Animal>, List<Animal>>) Tasks::getBitingAnimalsWithBigHeight),
            Arguments.of((Function<List<Animal>, ?>) (Tasks::countWeightMoreThanHeight)),
            Arguments.of((Function<List<Animal>, ?>) (Tasks::getAnimalsWithNamesConsistMoreThanTwoWords)),
            Arguments.of((Function<List<Animal>, Object>) (list) -> Tasks.isContainDogHigher(list, 10)),
            Arguments.of((Function<List<Animal>, Object>) (list) -> Tasks.getTotalWeightInAgeRange(list, 0, 10)),
            Arguments.of((Function<List<Animal>, Object>) Tasks::sortByTypeThanBySexThanByName),
            Arguments.of((Function<List<Animal>, Object>) Tasks::isSpidersBiteOftenThanDogs),
            Arguments.of((Function<List<Animal>, Object>) Tasks::getTheHeaviestFish),
            Arguments.of((Function<List<Animal>, Object>) list -> Tasks.getTheHeaviestFish(animalList1, list)),
            Arguments.of((Function<List<Animal>, Object>) Tasks::getErrors),
            Arguments.of((Function<List<Animal>, Object>) Tasks::getErrorsReadable),
        };
    }

    @ParameterizedTest
    @MethodSource("functionsToException")
    @DisplayName("Выбрасывается исключение если список животных - null")
    void shouldThrowExceptionIfNull(Function<List<Animal>, ?> func) {
        assertThrows(Exception.class, () -> func.apply(null));
    }

}
