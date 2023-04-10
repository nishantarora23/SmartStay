package generics;

/**
 * A generic class that extends the abstract class Identifiable and implements its methods
 * @param <T>
 */
public class GenericClass<T> extends Identifiable{
	private T data;

	/**
	 * This methods takes a generic data as input and sets the generic element
	 * @param data
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * This method returns the generic element
	 * @return
	 */
	public T getData() {
		return data;
	}

	/**
	 * This method returns the id of the generic element
	 */
	@Override
	public int getID() {
		return super.id;
	}

	/**
	 * This method checks whether the two generic elements are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		
		if(this.getData().equals(((GenericClass<T>)obj).getData())) {
			return true;
		}
		return false;
	}
}

