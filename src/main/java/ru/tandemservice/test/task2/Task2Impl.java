package ru.tandemservice.test.task2;

import ru.tandemservice.test.task1.IStringRowsListSorter;
import ru.tandemservice.test.task1.Task1Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <h1>Задание №2</h1>
 * Реализуйте интерфейс {@link IElementNumberAssigner}.
 *
 * <p>Помимо качества кода, мы будем обращать внимание на оптимальность предложенного алгоритма по времени работы
 * с учетом скорости выполнения операции присвоения номера:
 * большим плюсом (хотя это и не обязательно) будет оценка числа операций, доказательство оптимальности
 * или указание области, в которой алгоритм будет оптимальным.</p>
 */

/**
 * Оценка числа операций:
 * Минимальное количество операций, если в листе elements номера элементов идут по порядку: O(1)
 * Максимальное количество опереций, если в листе elements каждый номер элемента
 * не совпадает с номером элемента в той же позиции в отсортированном листе и они
 * идут парами, например [1, 0, 3, 2, 7, 6]: O(1,5 * n)
 * Среднее число операций: O(n)
 */

public class Task2Impl implements IElementNumberAssigner{

    // ваша реализация должна работать, как singleton. даже при использовании из нескольких потоков.
    public static volatile IElementNumberAssigner INSTANCE = new Task2Impl();

    @Override
    public void assignNumbers(final List<IElement> elements) {
        // напишите здесь свою реализацию. Мы ждем от вас хорошо структурированного, документированного и понятного кода, работающего за разумное время.
        if (elements == null) return;
        if (elements.size() < 2) return;
        final int MAX_NEST_LEVEL = 500;
        List<Integer> numbers = new ArrayList<>();
        for (IElement element : elements) {
            numbers.add(element.getNumber()); // Копируем все номера элементов elements в отдельный лист numbers
        }
        Collections.sort(numbers); // Сортируем numbers
        for (int i = 0; i < elements.size(); i++) {
            numbersChanger(elements, numbers, i, new ArrayList<>(), MAX_NEST_LEVEL);
        }
    }

    /**
     * @param position - позиция, в которой в данный момент ищем несовпадение
     * @param sortedNumbers - лист с отсортированными номерами элементов
     * @param checkedPositions - позиций, в которых уже искали несовпадение
     */
    private void numbersChanger(final List<IElement> elements, final List<Integer> sortedNumbers, final int position,
                                final List<Integer> checkedPositions, final int MAX_NEST_LEVEL) {

        if (sortedNumbers.get(position) != elements.get(position).getNumber()) { // Если номер элемента (в индексе position) не совпадает с номером в sortedNumbers
            final int foundPosition = indexOf(elements, sortedNumbers.get(position)); // foundPosition - позиция элемента, у которого номер совпадает с номером в sortedNumbers (в индексе position)
            if (foundPosition != -1) {  // Если найдена позиция, в которой номер элемента совпадает с номером в sortedNumbers
                if (checkedPositions.contains(foundPosition) || // Если foundPosition есть в checkedPositions (проверка для избежания зацикливания)
                        checkedPositions.size() > MAX_NEST_LEVEL) { // или размер checkedPositions больше MAX_NEST_LEVEL (для избежания StackOverflowException)
                    int uniqueNumber; // Псевдослучайное число
                    do {
                        uniqueNumber = (int) (Math.random() * Integer.MAX_VALUE + Math.random() * Integer.MIN_VALUE); // Генерируем uniqueNumber
                    }
                    while (indexOf(elements, uniqueNumber) != -1); // Повторяем генерацию, пока число не будет уникальным
                    elements.get(position).setupNumber(uniqueNumber); // Присваем элементу с позицией position uniqueNumber
                    return;
                } else { // Если foundPosition нет в checkedPositions
                    checkedPositions.add(position); // Добавляем position в checkedPositions
                    numbersChanger(elements, sortedNumbers, foundPosition,
                            checkedPositions, MAX_NEST_LEVEL); //Вызываем этот же метод, только с foundPosition в качестве position
                }
            }
            elements.get(position).setupNumber(sortedNumbers.get(position)); // Присваеваем элементу с позицией position соответствующий номер
        }
    }

    // Поиск позиции номера number в листе elements
    private int indexOf(final List<IElement> elements, final int number) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getNumber() == number) return i;
        }
        return -1;
    }

    /**
     * Возвращает instance объекта реализующего интерфейс IElementNumberAssigner
     * @return объект реализующий интерфейс IElementNumberAssigner
     */
    public static IElementNumberAssigner getInstance(){
        return INSTANCE;
    }

}
