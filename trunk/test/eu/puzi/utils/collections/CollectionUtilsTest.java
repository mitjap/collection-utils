package eu.puzi.utils.collections;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Test;

import eu.puzi.utils.collections.CollectionUtils;
import eu.puzi.utils.collections.Predicate;

public class CollectionUtilsTest {

	@Test
	public void testEquals1() {
		Integer o1 = null;
		Integer o2 = null;
		
		Assert.assertTrue(CollectionUtils.equals(o1, o2));
	}
	
	@Test
	public void testEquals2() {
		Integer o1 = 1;
		Integer o2 = null;
		
		Assert.assertFalse(CollectionUtils.equals(o1, o2));	
	}
	
	@Test
	public void testEquals3() {
		Integer o1 = null;
		Integer o2 = 1;
		
		Assert.assertFalse(CollectionUtils.equals(o1, o2));	
	}
	
	@Test
	public void testEquals4() {
		Integer o1 = 2;
		Integer o2 = 1;
		
		Assert.assertFalse(CollectionUtils.equals(o1, o2));	
	}
	
	@Test
	public void testEquals5() {
		Integer o1 = 1;
		Integer o2 = 1;
		
		Assert.assertTrue(CollectionUtils.equals(o1, o2));	
	}
	
	@Test
	public void testHasElements1() {
		Collection<? super Integer> c = new ArrayList<Number>();
		c.add(1);
		c.add(2);
		c.add(3);
		
		Assert.assertTrue(CollectionUtils.hasElements(c));
	}
	
	@Test
	public void testHasElements2() {
		Collection<? super Integer> c = new ArrayList<Number>();
		c.add(1);
		
		Assert.assertTrue(CollectionUtils.hasElements(c));
	}
	
	@Test
	public void testHasElements3() {
		Collection<? super Integer> c = new ArrayList<Number>();
		
		Assert.assertFalse(CollectionUtils.hasElements(c));
	}
	
	@Test
	public void testHasElements4() {
		Assert.assertFalse(CollectionUtils.hasElements(null));
	}
	
	@Test
	public void testFindFirst1() {
		Collection<Integer> c = new ArrayList<Integer>();
		
		// not common styling but was much prettier
		Integer i1 = 1; c.add(i1);
		Integer i2 = 2; c.add(i2);
		Integer i3 = 3; c.add(i3);
		Integer i4 = 1; c.add(i4);
		Integer i5 = 2; c.add(i5);
		Integer i6 = 3; c.add(i6);
		
		// first element that we find must be i2
		Assert.assertTrue(CollectionUtils.findFirst(c, new Predicate<Integer>() {
			@Override
			public boolean evaluate(Integer i) {
				return i % 1 == 0;
			}
		}) == i1);
	}
	
	@Test
	public void testFindFirst2() {
		Collection<Integer> c = new ArrayList<Integer>();
		
		// not common styling but was much prettier
		Integer i1 = 1; c.add(i1);
		Integer i2 = 2; c.add(i2);
		Integer i3 = 3; c.add(i3);
		Integer i4 = 1; c.add(i4);
		Integer i5 = 2; c.add(i5);
		Integer i6 = 3; c.add(i6);
		
		// first element that we find must be i2
		Assert.assertTrue(CollectionUtils.findFirst(c, new Predicate<Integer>() {
			@Override
			public boolean evaluate(Integer i) {
				return i % 2 == 0;
			}
		}) == i2);
	}
	
	@Test
	public void testFindFirst3() {
		Collection<Integer> c = new ArrayList<Integer>();
		
		// not common styling but was much prettier
		Integer i1 = 1; c.add(i1);
		Integer i2 = 2; c.add(i2);
		Integer i3 = 3; c.add(i3);
		Integer i4 = 1; c.add(i4);
		Integer i5 = 2; c.add(i5);
		Integer i6 = 3; c.add(i6);
		
		// no element found
		Assert.assertTrue(CollectionUtils.findFirst(c, new Predicate<Integer>() {
			@Override
			public boolean evaluate(Integer i) {
				return i % 4 == 0;
			}
		}) == null);
	}
	
	@Test
	public void testFindFirst4() {
		Collection<Integer> c = new ArrayList<Integer>();
		
		Assert.assertTrue(CollectionUtils.findFirst(c, new Predicate<Integer>() {
			@Override
			public boolean evaluate(Integer i) {
				return i % 2 == 0;
			}
		}) == null);
	}
	
	@Test
	public void testFindFirst5() {
		Assert.assertTrue(CollectionUtils.findFirst((Collection<Integer>)null, new Predicate<Integer>() {
			@Override
			public boolean evaluate(Integer i) {
				return i % 2 == 0;
			}
		}) == null);
	}
}
