package ru.tandemservice.test.task1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>Задание №1</h1>
 * Реализуйте интерфейс {@link IStringRowsListSorter}.
 *
 * <p>Мы будем обращать внимание в первую очередь на структуру кода и владение стандартными средствами java.</p>
 */

public class Task1Impl implements IStringRowsListSorter {

    // ваша реализация должна работать, как singleton. даже при использовании из нескольких потоков.
    public static final IStringRowsListSorter INSTANCE = new Task1Impl();

    @Override
    public void sort(final List<String[]> rows, final int columnIndex) {
        // напишите здесь свою реализацию. Мы ждем от вас хорошо структурированного, документированного и понятного кода.

        // Компаратор для сравнения массивов строк
        final Comparator<String[]> comparator = new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                final String str1 = o1[columnIndex]; // Получаем значения из
                final String str2 = o2[columnIndex]; // необходимой колонки
                if (str1 == null && str2 == null) return 0;
                if (str1 == null) return -1;
                if (str2 == null) return 1;
                if (str1.equals(str2)) return 0;
                if (str1.equals("")) return -1;
                if (str2.equals("")) return 1;
                List<String> list1 = spiltter(str1); // Разбивые строки на строковые и
                List<String> list2 = spiltter(str2); // числовые состовляющие
                for (int i = 0; i < Math.min(list1.size(), list2.size()); i++) {
                    if (list1.get(i).matches("\\d+") && list2.get(i).matches("\\d+")) { // Если состовлящие числовые
                        if (Integer.parseInt(list1.get(i)) != Integer.parseInt(list2.get(i))) {
                            return Integer.compare(Integer.parseInt(list1.get(i)),
                                    Integer.parseInt(list2.get(i)));
                        }
                        if (Integer.parseInt(list1.get(i)) < Integer.parseInt(list2.get(i)))
                            return -1;
                        if (Integer.parseInt(list1.get(i)) > Integer.parseInt(list2.get(i)))
                            return 1;
                    } else {
                        if (list1.get(i).compareTo(list2.get(i)) != 0)
                            return list1.get(i).compareTo(list2.get(i));
                    }
                }
                if (list1.size() == list2.size()) {
                    return 0;
                } else {
                    return Integer.compare(list1.size(), list2.size());
                }
            }

            // Метод для разбиения строки на числовые и строковые состовляющие
            public List<String> spiltter(final String str) {
                List<String> result = new ArrayList<>();
                Pattern pattern = Pattern.compile("\\d+|\\D+");
                Matcher matcher = pattern.matcher(str);
                while (matcher.find()) {
                    result.add(matcher.group());
                }
                return result;
            }

        };

        rows.sort(comparator);
    }

}
