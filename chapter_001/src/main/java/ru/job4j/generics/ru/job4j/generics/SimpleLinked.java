package ru.job4j.generics.ru.job4j.generics;
import java.util.*;

public class SimpleLinked<E> implements Iterable<E> {
    private static class Node<E> {
        E value;
        Node<E> next;

        Node(E value) {
            this.value = value;
        }
    }

    Node<E> head = new Node<>(null);
    Node<E> tail = head;
    int size;
    int modCount;

    public void add(E value) {
        if(head == null)
            head = new Node<>(value);
        else {
            Node<E> temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = new Node<>(value);
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        Node<E> node = head.next;
        while(index > 0) {
            node = node.next;
            index--;
        }
        return node.value;
    }

    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node<E> lastReturned = null;
        private Node<E> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        public boolean hasNext() {
            return nextIndex < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.value;
        }
    }
}

