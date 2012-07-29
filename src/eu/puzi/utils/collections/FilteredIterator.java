package eu.puzi.utils.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilteredIterator<E> implements Iterator<E> {
	private Iterator<? extends E> iterator;
	private Predicate<? super E> predicate;
	private E next;
	
	public FilteredIterator(Iterator<? extends E> iterator, Predicate<? super E> predicate) {
		this.iterator = iterator;
		this.predicate = predicate;
	}

	public boolean hasNext() {
		while (this.next == null && iterator.hasNext()) {
			E next = iterator.next();
			if (predicate.evaluate(next)) {
				this.next = next; 
			}
		}
		
		return this.next != null; 
	}

	public E next() {
		if (hasNext()) {
			return next;
		}
		throw new NoSuchElementException();
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}
