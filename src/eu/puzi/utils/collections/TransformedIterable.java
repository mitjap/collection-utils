package eu.puzi.utils.collections;

import java.util.Iterator;

public class TransformedIterable<I,O> implements Iterable<O> {

	Transformer<I,O> trans;
	Iterable<I> iter;
	
	public TransformedIterable(Iterable<I> iter, Transformer<I,O> trans) {
		this.iter = iter;
		this.trans = trans;
	}

	public Iterator<O> iterator() {
		return new TransformedIterator<I, O>(iter.iterator(), trans);
	}
}
