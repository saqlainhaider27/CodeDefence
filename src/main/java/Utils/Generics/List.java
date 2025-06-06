package Utils.Generics;

import java.util.Arrays;
import java.lang.reflect.Array;

import java.util.stream.DoubleStream;

public class List<T> implements Iterable<T> {

    private T[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public List() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void add(T item) {
        ensureCapacity();
        elements[size++] = item;
    }
    public void add(T ... item){
        for (int i = 0; i < item.length; i++) {
            add(item[i]);
        }
    }
    public void add(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    public T get(int index) {
        checkIndex(index);
        return elements[index];
    }

    public T remove(int index) {
        checkIndex(index);
        T removed = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return removed;
    }

    public boolean remove(T item) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(item)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public void set(int index, T item) {
        checkIndex(index);
        elements[index] = item;
    }

    public int size() {
        return size;
    }

    public boolean contains(T item) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(item)) return true;
        }
        return false;
    }

    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, size * 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return elements[index++];
            }
        };
    }
    public T[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @SuppressWarnings("unchecked")
    public <A> A toArray(A a) {
        Class<?> arrayClass = a.getClass();
        if (!arrayClass.isArray()) {
            throw new IllegalArgumentException("Provided argument is not an array");
        }
        Class<?> componentType = arrayClass.getComponentType();
        if (componentType.isPrimitive()) {
            Object primitiveArray = Array.newInstance(componentType, size);
            for (int i = 0; i < size; i++) {
                Array.set(primitiveArray, i, elements[i]);
            }
            return (A) primitiveArray;
        }
        if (Array.getLength(a) < size) {
            return (A) Arrays.copyOf(elements, size);
        } else {
            System.arraycopy(elements, 0, a, 0, size);
            if (Array.getLength(a) > size)
                Array.set(a, size, null);
            return a;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
