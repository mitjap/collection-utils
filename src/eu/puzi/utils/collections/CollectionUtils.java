package eu.puzi.utils.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionUtils {
	
	public static <E, T> boolean equals(T o1, T o2) {
		return o1 == null && o2 == null || o1 != null && o2 != null && o1.equals(o2); 
	}
	
	public static boolean hasElements(Collection<?> c) {
		return c != null && !c.isEmpty();
	}
	
	public static <T> T findFirst(Iterable<? extends T> iter, Predicate<? super T> predicate) {
		return findFirst(iter != null ? iter.iterator() : null, predicate);
	}
	
	public static <T> T findFirst(Iterator<? extends T> iter, Predicate<? super T> predicate) {
		while(iter != null && iter.hasNext()) {
			T object = iter.next();
			if (predicate.evaluate(object)) {
				return object;
			}
		}
		
		return null;
	}
	
	public static <T> Iterable<? extends T> filter(Iterable<? extends T> iter, Predicate<? super T> predicate) {
		return new FilteredIterable<T>(iter, predicate);
	} 
	
	public static <T> Iterator<T> filter(Iterator<? extends T> iter, Predicate<? super T> predicate) {
		return new FilteredIterator<T>(iter, predicate);
	}
	
	public static <T> Collection<T> collect(Iterable<? extends T> iter) {
		return collect(iter != null ? iter.iterator() : null);
	}
	
	public static <T, C extends Collection<T>> C collect(Iterable<? extends T> iter, C c) {
		return collect(iter != null ? iter.iterator() : null, c);
	}
	
	public static <T> Collection<T> collect(Iterator<? extends T> iter) {
		return collect(iter, new ArrayList<T>());
	}
	
	public static <T, C extends Collection<T>> C collect(Iterator<? extends T> iter, final C collection) {
		forAllDo(iter, new Closure<T>() {
			public void execute(T object) {
				collection.add(object);
			}
		});
		return collection;
	}
	
	public static <T> void forAllDo(Iterable<? extends T> iter, Closure<? super T> closure) {
		forAllDo(iter != null ? iter.iterator() : null, closure);
	}

	public static <T> void forAllDo(Iterator<? extends T> iter, Closure<? super T> closure) {
		while (iter != null && iter.hasNext()) {
			closure.execute(iter.next());
		}
	}
	
	public static <T> Integer count(Iterable<? extends T> iter) {
		return count(iter != null ? iter.iterator() : null);
	}

	public static <T> Integer count(Iterator<? extends T> iter) {
		return countUpTo(iter, Integer.MAX_VALUE);
	}
	
	public static <T> Integer countUpTo(Iterable<? extends T> iter, final Integer max) {
		return countUpTo(iter != null ? iter.iterator() : null, max);
	}

	public static <T> Integer countUpTo(Iterator<? extends T> iter, final Integer max) {
		int c = 0;
		
		while (iter != null && iter.hasNext() && c < max) {
			c++;
			iter.next();
		}
		
		return c;
	}
	
	public static <I,O> Iterable<O> transform(Iterable<? extends I> iter, Transformer<? super I, ? extends O> trans) {
		return new TransformedIterable<I, O>(iter, trans);
	}
	
	public static <I,O> Iterator<O> transform(Iterator<? extends I> iter, Transformer<? super I, ? extends O> trans) {
		return new TransformedIterator<I, O>(iter, trans);
	}
}
