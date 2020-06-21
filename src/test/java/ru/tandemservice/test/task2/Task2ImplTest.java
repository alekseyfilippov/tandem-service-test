package ru.tandemservice.test.task2;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class Task2ImplTest {

    @Test
    public void assignNumbersTest1() throws Exception {

        ElementExampleImpl.Context context = new ElementExampleImpl.Context();

        final Task2Impl task2 = new Task2Impl();

        final List<IElement> elements = new ArrayList<>();

        final Set<Integer> numbers = new LinkedHashSet<>();

        final List<Integer> expectedNumbers = new ArrayList<>();

        final List<Integer> actualNumbers = new ArrayList<>();

        final int NUMBER_OF_ELEMENTS = 1000;

        while (numbers.size() < NUMBER_OF_ELEMENTS) {
            numbers.add((int) (Math.random() * NUMBER_OF_ELEMENTS - Math.random() * NUMBER_OF_ELEMENTS));
        }

        for (Integer number : numbers) {
            expectedNumbers.add(number);
        }

        Collections.sort(expectedNumbers);

        for (Integer number : numbers) {
            elements.add(new ElementExampleImpl(context, number));
        }

        task2.assignNumbers(elements);

        for (IElement element : elements) {
            actualNumbers.add(element.getNumber());
        }

        System.out.println("количество выполнения setNumber: " + context.getOperationCount());

        assertArrayEquals(expectedNumbers.toArray(), actualNumbers.toArray());

    }

}