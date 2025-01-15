package myArrayList;

import java.util.Comparator;

public class MyArrayList<E> {

    private static final int DEFAULT_CAPACITY = 20;

    private transient Object[] elements;

    private static final Object[] EMPTY_ELEMENTS = {};

    private int size;

    /*
    Конструктор по умолчанию.
    Создает массив дефолтного размера.
    */
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /*
    Параметризированный конструктор.
    Создает массив заданного размера или дефолтного размера, в зависимости от переменной capacity.
    Выбрасывает исключение, если capacity < 0
    */
    public MyArrayList(int capacity) {
        if (capacity > 0) {
            elements = new Object[capacity];
        } else if (capacity == 0) {
            elements = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    /*
    Приватный метод для получения элемента по индексу.
    Используется для получения элемента без валидации индекса.
    */
    private E elementData(int index) {
        return (E) elements[index];
    }

    /*
    Приватный метод для увеличения внутреннего массива.
    При заполнении внутреннего массива происходит его увеличение в 2 раза.
    */
    private void increase() {
        Object[] newElements = new Object[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    /*
    Приватный метод для проверки размера массива.
    Вызывает метод increase(), если массив заполнен.
    */
    private void checkCapacity() {
        if (elements.length <= size) {
            increase();
        }
    }

    /*
    Метод добавления элемента в конец массива.
    Вызывает метод checkCapacity(),увеличивает массив, если тот заполнен.
    */
    public void add(E e) {
        checkCapacity();
        elements[size++] = e;
    }

    /*
    Приватный метод для проверки индекса.
    Выбрасывает исключение, если индекс некорректен.
    */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Wrong index: " + index + ", Size: " + size);
        }
    }

    /*
    Метод добавления элемента по индексу.
    Проверяет индекс и заполненность массива.
    */
    public void add(int index, E e) {
        checkIndex(index);
        checkCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = e;
        size++;
    }

    /*
    Метод получения элемента по индексу с проветркой.
    */
    public E get(int index) {
        checkIndex(index);
        return elementData(index);
    }

    /*
    Метод для установки элемента по индексу.
    */
    public void set(int i, E e) {
        checkIndex(i);
        elements[i] = e;
    }

    /*
    Метод получения размера массива.
    */
    public int size() {
        return size;
    }

    /*
    Метод очистки массива.
    */
    public void clear() {
        size = 0;
        elements = EMPTY_ELEMENTS;
    }

    /*
    Метод удаления элемента по индексу с проверкой.
    */
    public E remove(int index) {
        checkIndex(index);
        E e = elementData(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return e;
    }

    /*
    Метод сортировки листа.
    */
    public void sort(Comparator<? super E> c) {
        quickSort(this, 0, size - 1, c);
    }

    /*
    Приватный вспомогательный метод сортировки листа.
    */
    private void quickSort(MyArrayList<E> myArrayList, int low, int high, Comparator<? super E> c) {
        if (myArrayList.size == 0) return;
        if (low >= high) return;
        int middle = low + (high - low) / 2;
        E e = myArrayList.get(middle);
        int i = low, j = high;
        while (i <= j) {
            while (c.compare(myArrayList.get(i), e) < 0) {
                i++;
            }
            while (c.compare(myArrayList.get(j), e) > 0) {
                j--;
            }
            if (i <= j) {
                E temp = myArrayList.get(i);
                myArrayList.set(i, myArrayList.get(j));
                myArrayList.set(j, temp);
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(myArrayList, low, j, c);

        if (high > i)
            quickSort(myArrayList, i, high, c);
    }
}
