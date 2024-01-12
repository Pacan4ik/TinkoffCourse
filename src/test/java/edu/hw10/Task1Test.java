package edu.hw10;

import edu.hw10.task1.Max;
import edu.hw10.task1.Min;
import edu.hw10.task1.MyClass;
import edu.hw10.task1.MyRecord;
import edu.hw10.task1.RandomObjectGenerator;
import java.util.Objects;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @Test
    void shouldReturnRandomPOJO() {
        //given
        RandomObjectGenerator rog = new RandomObjectGenerator();

        //when
        MyClass myClassObj = rog.nextObject(MyClass.class);

        //then
        Assertions.assertNotNull(myClassObj);
        Assertions.assertInstanceOf(MyClass.class, myClassObj);
    }

    @Test
    void shouldReturnRandomPOJOWithFabric() {
        //given
        RandomObjectGenerator rog = new RandomObjectGenerator();

        //when
        MyClass myClassObj = rog.nextObject(MyClass.class, "create");

        //then
        Assertions.assertNotNull(myClassObj);
        Assertions.assertInstanceOf(MyClass.class, myClassObj);
    }

    @Test
    void shouldReturnRandomRecord() {
        //given
        RandomObjectGenerator rog = new RandomObjectGenerator();

        //when
        MyRecord myRecord = rog.nextObject(MyRecord.class);

        //then
        Assertions.assertNotNull(myRecord);
        Assertions.assertInstanceOf(MyRecord.class, myRecord);
    }

    @RepeatedTest(100)
    void shouldGenerateValuesWithinRangeInAnnotation() {
        //given
        RandomObjectGenerator rog =
            new RandomObjectGenerator(-1000, 1000, true);
        var p = MyRecord.class.getConstructors()[0].getParameters()[0];
        int min = p.getAnnotation(Min.class).value();
        int max = p.getAnnotation(Max.class).value();

        //when
        MyRecord myRecord = rog.nextObject(MyRecord.class);

        //then
        short s = myRecord.shortValue();
        Assertions.assertTrue(s >= min && s < max);
    }

    @Test
    void shouldAdhereNotNullAnnotation() {
        //given
        RandomObjectGenerator rog = new RandomObjectGenerator(1, 5, false);

        //when
        var list = Stream.generate(() -> rog.nextObject(MyClass.class).getStringValue())
            .limit(1000).toList();

        //then
        assertThat(list).noneMatch(Objects::isNull);
    }
}
