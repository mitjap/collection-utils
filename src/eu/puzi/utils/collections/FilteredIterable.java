package eu.puzi.utils.collections;

import java.util.Iterator;

public class FilteredIterable<E> implements Iterable<E> {
	
	private Iterable<? extends E> iterable;
	private Predicate<? super E> predicate;
	
	public FilteredIterable(Iterable<? extends E> iterable, Predicate<? super E> predicate) {
		this.iterable = iterable;
		this.predicate = predicate;
	}

	@Override
	public Iterator<E> iterator() {
		return new FilteredIterator<E>(iterable.iterator(), predicate);
	}
}
