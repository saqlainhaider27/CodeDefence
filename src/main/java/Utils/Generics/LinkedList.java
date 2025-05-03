package Utils.Generics;

public class LinkedList<TData> {

    private static class Node<TData> {
        TData data;
        Node<TData> next;

        Node(TData data) {
            this.data = data;
        }
    }

    private Node<TData> head;
    private int size = 0;

    public void add(TData data) {
        Node<TData> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<TData> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public boolean remove(TData data) {
        if (head == null) return false;

        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }

        Node<TData> current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public TData get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Node<TData> current = head;
        for (int i = 0; i < index; i++) current = current.next;

        return current.data;
    }

    public int size() {
        return size;
    }

    public boolean contains(TData data) {
        Node<TData> current = head;
        while (current != null) {
            if (current.data.equals(data)) return true;
            current = current.next;
        }
        return false;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public void printAll() {
        Node<TData> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
