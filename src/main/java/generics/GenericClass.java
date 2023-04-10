package generics;

public class GenericClass<T> extends Identifiable{
	private T data;

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	@Override
	public int getID() {
		return super.id;
	}

}

