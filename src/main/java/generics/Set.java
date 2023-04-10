package generics;

import java.util.*;

public class Set<E extends Identifiable> {

	private HashMap<Integer, E> items;

	public Set() {
		items = new HashMap<>();
	}

	public void push(E item) {
		if (!items.containsKey(item.getID())) {
			for(E value : items.values()) {
				if((((GenericClass)value).getData()).equals(((GenericClass)item).getData())){
					System.out.println(((GenericClass)value).getData()+ " Element already exists");
					return;	
				}
			}
			items.put(item.getID(), item);
			System.out.println("Element added to set.");
		}
		else {
			System.out.println(item.getID() +" ID already exists");
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
	
	public HashMap<Integer, E> getItems() {
		return items;
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
		
		HashMap<Integer, E> otherItems = (HashMap<Integer, E>)other.getItems();
		
		for (E item : items.values()) {
			if (other.peek(item.getID())) {
				if(!(items.get(item.getID()).equals(otherItems.get(item.getID()))))
				return false;
			}
			else {
				return false;
			}
		}
		return true;
	}

    public void display() {
        for (E item : items.values()) {
        	
            System.out.print("( ID: "+item.getID()+" ,Value: "+((GenericClass<?>)item).getData()+" )"+"\n");
        }
    }
}
