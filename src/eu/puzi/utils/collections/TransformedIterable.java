package eu.puzi.utils.collections;

import java.util.Iterator;

public class TransformedIterable<I,O> implements Iterable<O> {

	Transformer<? super I, ? extends O> trans;
	Iterable<? extends I> iter;
	
	public TransformedIterable(Iterable<? extends I> iter, Transformer<? super I, ? extends O> trans) {
		this.iter = iter;
		this.trans = trans;
	}

	public Iterator<O> iterator() {
		return new TransformedIterator<I, O>(iter.iterator(), trans);
	}
}
