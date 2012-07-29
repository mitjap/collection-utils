package eu.puzi.utils.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Test;

import eu.puzi.utils.collections.TransformedIterator;
import eu.puzi.utils.collections.Transformer;

public class TransformedIteratorTest {

	/**
	 * Main part of this test is to check if we get any syntax errors
	 */
	@Test
	public void testSimple() {
		
		// consumer and producer
		Collection<Rectangle> rectangles = new ArrayList<Rectangle>();
		rectangles.add(new Rectangle());
		rectangles.add(new Square());
		rectangles.add(new Romb());
		rectangles.add(new Parallelogram());
		
		
		TransformedIterator<Rectangle, Quadrilateral> transformedIterator = 
				new TransformedIterator<Rectangle, Quadrilateral>(rectangles.iterator(), new Transformer<Rectangle, Quadrilateral>() {
					@Override
					public Quadrilateral transform(Rectangle in) {
						return new Quadrilateral();
					}
				});
		
		Assert.assertEquals("Quadrilateral", transformedIterator.next().toString());
		Assert.assertEquals("Quadrilateral", transformedIterator.next().toString());
		Assert.assertEquals("Quadrilateral", transformedIterator.next().toString());
		Assert.assertEquals("Quadrilateral", transformedIterator.next().toString());
	}
	
	@Test
	public void testComplex() {
		
		// consumer
		// Collection<? super Rectangle> rectangles = new ArrayList<Rectangle>(); // why won't this work? 
		Collection<Rectangle> rectangles = new ArrayList<Rectangle>();
		rectangles.add(new Rectangle());
		rectangles.add(new Square());
		rectangles.add(new Romb());
		rectangles.add(new Parallelogram());
		
		// producer
		Collection<? extends Rectangle> rec = rectangles;
		
		TransformedIterator<? extends Rectangle, ? super Quadrilateral> transformedIterator = 
				new TransformedIterator<Rectangle, Quadrilateral>(rec.iterator(), new Transformer<Rectangle, Quadrilateral>() {
					@Override
					public Quadrilateral transform(Rectangle in) {
						return new Quadrilateral();
					}
				});
		
		Assert.assertEquals("Quadrilateral", transformedIterator.next().toString());
		Assert.assertEquals("Quadrilateral", transformedIterator.next().toString());
		Assert.assertEquals("Quadrilateral", transformedIterator.next().toString());
		Assert.assertEquals("Quadrilateral", transformedIterator.next().toString());
	}
	
	/**
	 * An example of what can be done
	 */
	@SuppressWarnings("unused")
	@Test
	public void test() {
		Collection<Rectangle> r = new ArrayList<Rectangle>();
		Collection<? super Rectangle> rs = new ArrayList<Quadrilateral>();
		Collection<? extends Rectangle> re = new ArrayList<Square>();
		
		//r.add(new Quadrilateral());
		r.add(new Rectangle());
		r.add(new Square());
		r.add(new Romb());
		r.add(new Parallelogram());
		
		//rs.add(new Quadrilateral());
		rs.add(new Rectangle());
		rs.add(new Square());
		rs.add(new Romb());
		rs.add(new Parallelogram());
		
		//re.add(new Quadrilateral());
		//re.add(new Rectangle());
		//re.add(new Square());
		//re.add(new Romb());
		//re.add(new Parallelogram());
	}
	
	
	
	
	static class Quadrilateral {
		public String toString() {
			return this.getClass().getSimpleName();
		}
	}
	
	static class Rectangle extends Quadrilateral {
		
	}
	
	static class Romb extends Rectangle {
		
	}
	
	static class Square extends Romb {
		
	}
	
	static class Parallelogram  extends Rectangle {
		
	}

}
