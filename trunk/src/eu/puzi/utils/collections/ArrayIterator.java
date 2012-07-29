package eu.puzi.utils.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T>{
	private T[] array;
	private int index;
	
	public ArrayIterator(T[] array) {
		this.array = array;
		index = 0;
	}

	public boolean hasNext() {
		return index < array.length;
	}

	public T next() {
		if (hasNext()) {
			return array[index++];
		}
		
		throw new NoSuchElementException();
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}
