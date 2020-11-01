package ru.job4j.tree;
import java.util.*;
import java.util.function.Predicate;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> el = findBy(parent);
        if (el.isPresent() && !findBy(child).isPresent()) {
            Node<E> parentNode = el.get();
            Node<E> childNode = new Node<>(child);
            parentNode.getChildren().add(childNode);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> predicate = x -> x.getValue().equals(value);
        Optional<Node<E>> rsl = findByPredicate(predicate);
        return rsl;
    }

    public boolean isBinary() {
        Predicate<Node<E>> predicate = x -> x.getChildren().size() > 2;
        Optional<Node<E>> rsl = findByPredicate(predicate);
        return rsl.isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> predicate) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (predicate.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.getChildren());
        }
        return rsl;
    }
}