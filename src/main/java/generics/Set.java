package generics;

import java.util.*;

/**
 * A generic Set class
 * @param <E>
 */
public class Set<E extends Identifiable> {

	private HashMap<Integer, E> items;

	/**
	 * A constructor that initializes the HashMap used for Generic Set implementation
	 */
	public Set() {
		items = new HashMap<>();
	}

	/**
	 * This method adds a unique element having a unique ID in the set
	 * @param item
	 */
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

	/**
	 * This method takes id as input and removes and returns the corresponding element from the set 
	 * @param id
	 * @return
	 */
	public E pop(int id) {
		return items.remove(id);
	}

	/**
	 * This method takes id as input, checks whether the corresponding element is present or not in the set and returns true or false
	 * @param id
	 * @return
	 */
	public boolean peek(int id) {
		return items.containsKey(id);
	}

	/**
	 * This method returns the size of the set
	 * @return
	 */
	public int getSize() {
		return items.size();
	}
	
	/**
	 * This method returns the items of the set
	 * @return
	 */
	public HashMap<Integer, E> getItems() {
		return items;
	}

	/**
	 * This method overrides the equals method and checks whether two sets are equal
	 */
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

	/**
	 * This method displays the id and corresponding element of the set
	 */
    public void display() {
        for (E item : items.values()) {
            System.out.print("( ID: "+item.getID()+" ,Value: "+((GenericClass<?>)item).getData()+" )"+"\n");
        }
    }
}
