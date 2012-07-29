package eu.puzi.utils.collections;

public interface Predicate<T> {
	public boolean evaluate(T object);
	
	public class Not<T> implements Predicate<T> {
		
		private Predicate<T> predicate;
		
		public Not(Predicate<T> predicate) {
			this.predicate = predicate;
		}

		public boolean evaluate(T object) {
			return !predicate.evaluate(object);
		}
	}

	public class True<T> implements Predicate<T> {
		
		public True() {
			
		}
		
		public boolean evaluate(T object) {
			return true;
		}
	}

	public class Or<T> implements Predicate<T> {
		
		private ArrayIterator<Predicate<T>> predicates;
		
		public Or(Predicate<T>... predicates) {
			this.predicates = new ArrayIterator<Predicate<T>>(predicates);
		}

		public boolean evaluate(final T object) {
			return CollectionUtils.findFirst(predicates, new Predicate<Predicate<T>>() {
				public boolean evaluate(Predicate<T> predicate) {
					return predicate.evaluate(object);
				}
			}) != null;
		}
	}

	public class And<T> implements Predicate<T> {
		
		private ArrayIterator<Predicate<T>> predicates;
		
		public And(Predicate<T>... predicates) {
			this.predicates = new ArrayIterator<Predicate<T>>(predicates);
		}

		public boolean evaluate(final T object) {
			return CollectionUtils.findFirst(predicates, new Predicate<Predicate<T>>() {
				public boolean evaluate(Predicate<T> predicate) {
					return !predicate.evaluate(object);
				}
			}) == null;
		}
	}
}

