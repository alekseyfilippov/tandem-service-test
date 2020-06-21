package ru.tandemservice.test.task1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Task1ImplTest {

    @Test
    public void sortTestOnlyNumbers() {
        Task1Impl task1 = new Task1Impl();

        List<String[]> actual = new ArrayList<>();
        List<String[]> expected = new ArrayList<>();

        actual.add(new String[]{"1", "2", "3", "4"});
        actual.add(new String[]{"1", "2", "4", "3"});
        actual.add(new String[]{"2", "1", "3", "4"});
        actual.add(new String[]{"2", "1", "4", "3"});
        actual.add(new String[]{"4", "3", "2", "1"});

        expected.add(new String[]{"4", "3", "2", "1"});
        expected.add(new String[]{"1", "2", "3", "4"});
        expected.add(new String[]{"2", "1", "3", "4"});
        expected.add(new String[]{"1", "2", "4", "3"});
        expected.add(new String[]{"2", "1", "4", "3"});

        task1.sort(actual, 2);

        for (int i = 0; i < actual.size(); i++) {
            assertArrayEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void sortTestNoChangesNullAndEmpty() {
        Task1Impl task1 = new Task1Impl();

        List<String[]> actual = new ArrayList<>();
        List<String[]> expected = new ArrayList<>();

        actual.add(new String[]{"1", "2", null, "4"});
        actual.add(new String[]{"1", "2", null, "3"});
        actual.add(new String[]{"2", "1", "", "4"});
        actual.add(new String[]{"2", "1", "", "3"});
        actual.add(new String[]{"4", "3", "abc", "1"});

        expected.add(new String[]{"1", "2", null, "4"});
        expected.add(new String[]{"1", "2", null, "3"});
        expected.add(new String[]{"2", "1", "", "4"});
        expected.add(new String[]{"2", "1", "", "3"});
        expected.add(new String[]{"4", "3", "abc", "1"});

        task1.sort(actual, 2);

        for (int i = 0; i < actual.size(); i++) {
            assertArrayEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void sortTestNullAndEmpty() {
        Task1Impl task1 = new Task1Impl();

        List<String[]> actual = new ArrayList<>();
        List<String[]> expected = new ArrayList<>();

        actual.add(new String[]{"1", "2", "3", "4"});
        actual.add(new String[]{"1", "2", "", "3"});
        actual.add(new String[]{"2", "1", null, "4"});
        actual.add(new String[]{"2", "1", "", "3"});
        actual.add(new String[]{"4", "3", null, "1"});

        expected.add(new String[]{"2", "1", null, "4"});
        expected.add(new String[]{"4", "3", null, "1"});
        expected.add(new String[]{"1", "2", "", "3"});
        expected.add(new String[]{"2", "1", "", "3"});
        expected.add(new String[]{"1", "2", "3", "4"});

        task1.sort(actual, 2);

        for (int i = 0; i < actual.size(); i++) {
            assertArrayEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void sortTest1() {
        Task1Impl task1 = new Task1Impl();

        List<String[]> actual = new ArrayList<>();
        List<String[]> expected = new ArrayList<>();

        actual.add(new String[]{"1", "2", "123abc312", "4"});
        actual.add(new String[]{"1", "2", "abc123321", "3"});
        actual.add(new String[]{"2", "1", "321abc123", "4"});
        actual.add(new String[]{"2", "1", "123321abc", "3"});
        actual.add(new String[]{"4", "3", "abc123abc", "1"});

        expected.add(new String[]{"1", "2", "123abc312", "4"});
        expected.add(new String[]{"2", "1", "321abc123", "4"});
        expected.add(new String[]{"2", "1", "123321abc", "3"});
        expected.add(new String[]{"4", "3", "abc123abc", "1"});
        expected.add(new String[]{"1", "2", "abc123321", "3"});

        task1.sort(actual, 2);

        for (int i = 0; i < actual.size(); i++) {
            assertArrayEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void sortTestNoChanges() {
        Task1Impl task1 = new Task1Impl();

        List<String[]> actual = new ArrayList<>();
        List<String[]> expected = new ArrayList<>();

        actual.add(new String[]{"1", "2", "abc123abc", "4"});
        actual.add(new String[]{"1", "2", "abc123abc", "3"});
        actual.add(new String[]{"2", "1", "cde123", "4"});
        actual.add(new String[]{"2", "1", "cde123", "3"});
        actual.add(new String[]{"4", "3", "kbd", "1"});

        expected.add(new String[]{"1", "2", "abc123abc", "4"});
        expected.add(new String[]{"1", "2", "abc123abc", "3"});
        expected.add(new String[]{"2", "1", "cde123", "4"});
        expected.add(new String[]{"2", "1", "cde123", "3"});
        expected.add(new String[]{"4", "3", "kbd", "1"});

        task1.sort(actual, 2);

        for (int i = 0; i < actual.size(); i++) {
            assertArrayEquals(expected.get(i), actual.get(i));
        }
    }

}