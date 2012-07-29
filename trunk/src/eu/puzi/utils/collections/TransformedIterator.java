package eu.puzi.utils.collections;

import java.util.Iterator;

public class TransformedIterator<I,O> implements Iterator<O> {

	Transformer<? super I, ? extends O> trans;
	Iterator<? extends I> iter;
	
	public TransformedIterator(Iterator<? extends I> iter, Transformer<? super I, ? extends O> trans) {
		this.iter = iter;
		this.trans = trans;
	}
	
	public boolean hasNext() {
		return iter.hasNext();
	}

	public O next() {
		I in = iter.next();
		return trans.transform(in);
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

}
