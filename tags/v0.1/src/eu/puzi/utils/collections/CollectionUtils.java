package eu.puzi.utils.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionUtils {
	
	public static <E, T extends Comparable<E>> boolean equals(T o1, T o2) {
		return o1 == null && o2 == null || o1 != null && o2 != null && o1.equals(o2); 
	}
	
	public static <T> boolean hasElements(Collection<T> c) {
		return !c.isEmpty();
	}
	
	public static <T> T findFirst(Iterator<T> iter, Predicate<T> predicate) {
		while(iter.hasNext()) {
			T object = iter.next();
			if (predicate.evaluate(object)) {
				return object;
			}
		}
		
		return null;
	}
	
	public static <T> Iterable<T> filter(Iterable<T> iter, Predicate<T> predicate) {
		return new FilteredIterable<T>(iter, predicate);
	} 
	
	public static <T> Iterator<T> filter(Iterator<T> iter, Predicate<T> predicate) {
		return new FilteredIterator<T>(iter, predicate);
	}
	
	public static <T> Collection<T> collect(Iterable<T> iter) {
		return collect(iter.iterator());
	}
	
	public static <T, C extends Collection<T>> C collect(Iterable<T> iter, C c) {
		return collect(iter.iterator(), c);
	}
	
	public static <T> Collection<T> collect(Iterator<T> iter) {
		return collect(iter, new ArrayList<T>());
	}
	
	public static <T, C extends Collection<T>> C collect(Iterator<T> iter, final C collection) {
		forAllDo(iter, new Closure<T>() {
			public void execute(T object) {
				collection.add(object);
			}
		});
		return collection;
	}
	
	public static <T> void forAllDo(Iterable<T> iter, Closure<T> closure) {
		forAllDo(iter.iterator(), closure);
	}

	public static <T> void forAllDo(Iterator<T> iter, Closure<T> closure) {
		while (iter.hasNext()) {
			closure.execute(iter.next());
		}
	}
	
	public static <T> Integer count(Iterable<T> iter) {
		return count(iter.iterator());
	}

	public static <T> Integer count(Iterator<T> iter) {
		return countUpTo(iter, Integer.MAX_VALUE);
	}
	
	public static <T> Integer countUpTo(Iterable<T> iter, final Integer max) {
		return countUpTo(iter.iterator(), max);
	}

	public static <T> Integer countUpTo(Iterator<T> iter, final Integer max) {
		int c = 0;
		
		while (iter.hasNext() && c < max) {
			c++;
			iter.next();
		}
		
		return c;
	}
	
	public static <I,O> Iterable<O> transform(Iterable<I> iter, Transformer<I,O> trans) {
		return new TransformedIterable<I, O>(iter, trans);
	}
	
	public static <I,O> Iterator<O> transform(Iterator<I> iter, Transformer<I,O> trans) {
		return new TransformedIterator<I, O>(iter, trans);
	}
}
