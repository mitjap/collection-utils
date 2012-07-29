package eu.puzi.utils.collections;

import java.util.Iterator;

public class ArrayIterable<T> implements Iterable<T> {

	private T[] array;
	
	public ArrayIterable(T[] array) {
		this.array = array;
	}
	
	public Iterator<T> iterator() {
		return new ArrayIterator<T>(array);
	}
}
