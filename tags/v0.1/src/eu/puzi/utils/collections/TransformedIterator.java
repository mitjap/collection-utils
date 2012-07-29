package eu.puzi.utils.collections;

import java.util.Iterator;

public class TransformedIterator<I,O> implements Iterator<O> {

	Transformer<I,O> trans;
	Iterator<I> iter;
	
	public TransformedIterator(Iterator<I> iter, Transformer<I,O> trans) {
		this.iter = iter;
		this.trans = trans;
	}
	
	public boolean hasNext() {
		return iter.hasNext();
	}

	public O next() {
		return trans.transform(iter.next());
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

}
