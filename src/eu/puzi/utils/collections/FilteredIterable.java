package eu.puzi.utils.collections;

import java.util.Iterator;

public class FilteredIterable<E> implements Iterable<E> {
	
	private Iterable<E> iterable;
	private Predicate<E> predicate;
	
	public FilteredIterable(Iterable<E> iterable, Predicate<E> predicate) {
		this.iterable = iterable;
		this.predicate = predicate;
	}

	@Override
	public Iterator<E> iterator() {
		return new FilteredIterator<E>(iterable.iterator(), predicate);
	}
}
