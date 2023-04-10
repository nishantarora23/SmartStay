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

