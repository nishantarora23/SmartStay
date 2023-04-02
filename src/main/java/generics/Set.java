package generics;

import java.util.*;

public class Set<E extends Identifiable> {

    private HashMap<Integer, E> items;

    public Set() {
        items = new HashMap<>();
    }

    public void push(E item) {
        if (!items.containsKey(item.getID())) {
            items.put(item.getID(), item);
        }
    }

    public E pop(int id) {
        return items.remove(id);
    }

    public boolean peek(int id) {
        return items.containsKey(id);
    }

    public int getSize() {
        return items.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set<?> other = (Set<?>) obj;
        if (getSize() != other.getSize()) {
            return false;
        }
        for (E item : items.values()) {
            if (!other.peek(item.getID())) {
                return false;
            }
        }
        return true;
    }

    public void display() {
        System.out.print("(");
        for (E item : items.values()) {
            System.out.print(item + ", ");
        }
        System.out.println(")");
    }
}
