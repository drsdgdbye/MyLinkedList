import java.util.Iterator;
import java.util.NoSuchElementException;

class MyLinkedList<T> implements Iterable<T> {
    private Node first;
    private Node last;
    private int size;

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        return first.t;
    }

    void addFirst(T x) {
        Node oldFirst = first;
        first = new Node(null, x, oldFirst);
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.previous = first;
        }
        size++;
    }

    T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }

        Node second = first.next;
        T x = first.t;
        first.t = null;
        first.next = null;
        first = second;
        size--;
        if (isEmpty()) {
            last = null;
        } else {
            second.previous = null;
        }
        return x;
    }

    T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        return last.t;
    }

    void addLast(T x) {
        Node oldLast = last;
        last = new Node(oldLast, x, null);
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        T x = last.t;
        Node previous = last.previous;
        last.previous = null;
        last = previous;
        size--;
        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }
        return x;
    }

    T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        Node current;
        if (index < size / 2) {
            int currentIndex = 0;
            current = first;
            while (currentIndex < index) {
                current = current.next;
                currentIndex++;
            }
        } else {
            int currentIndex = size - 1;
            current = last;
            while (currentIndex > index) {
                current = current.next;
                currentIndex--;
            }
        }
        return current.t;
    }

    void set(int index, T x) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        Node current;
        if (index < size / 2) {
            int currentIndex = 0;
            current = first;
            while (currentIndex < index) {
                current = current.next;
                currentIndex++;
            }
        } else {
            int currentIndex = size - 1;
            current = last;
            while (currentIndex > index) {
                current = current.next;
                currentIndex--;
            }
        }
        current.t = x;
    }

    int indexOf(T x) {
        Node current = first;
        int currentIndex = 0;
        while (current != null && !current.t.equals(x)) {
            current = current.next;
            currentIndex++;
        }
        return current != null ? currentIndex : -1;
    }

    boolean contains(T x) {
        return indexOf(x) > -1;
    }

    T remove(T x) {
        Node current = first;

        while (current != null && !current.t.equals(x)) {
            current = current.next;
        }
        if (current == null) {
            return null;
        }
        if (current == first) {
            return removeFirst();
        }
        if (current == last) {
            return removeLast();
        }
        Node next = current.next;
        Node previous = current.previous;
        previous.next = next;
        next.previous = previous;
        size--;
        current.previous = null;
        current.next = null;
        return current.t;
    }

    void add(int index, T x) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(x);
            return;
        } else if (index == size) {
            addLast(x);
            return;
        }

        int currentIndex = 0;
        Node current = first;
        while (currentIndex < index) {
            current = current.next;
            currentIndex++;
        }
        Node newNode = new Node(current.previous, x, current);
        Node previous = current.previous;
        previous.next = newNode;
        current.previous = newNode;
        size++;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node current = first;
        while (current != null) {
            s.append(current.t.toString());
            s.append(", ");
            current = current.next;
        }
        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    private class Node {
        T t;
        Node next;
        Node previous;

        public Node(Node previous, T x, Node next) {
            this.previous = previous;
            this.t = x;
            this.next = next;
        }
    }

    private class MyLinkedListIterator implements Iterator<T> {

        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T x = current.t;
            current = current.next;
            return x;
        }

        @Override
        public void remove() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MyLinkedList.this.remove(current.t);
            current = current.next;
        }

    }
}
