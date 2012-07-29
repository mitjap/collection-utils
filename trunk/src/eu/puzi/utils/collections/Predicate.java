package eu.puzi.utils.collections;

public interface Predicate<T> {
	public boolean evaluate(T object);
	
	public class Not<T> implements Predicate<T> {
		
		private Predicate<? super T> predicate;
		
		public Not(Predicate<? super T> predicate) {
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
		
		private ArrayIterator<? extends Predicate<? super T>> predicates;
		
		public Or(Predicate<? super T>... predicates) {
			this.predicates = new ArrayIterator<Predicate<? super T>>(predicates);
		}

		public boolean evaluate(final T object) {
			return CollectionUtils.findFirst(predicates, new Predicate<Predicate<? super T>>() {
				public boolean evaluate(Predicate<? super T> predicate) {
					return predicate.evaluate(object);
				}
			}) != null;
		}
	}

	public class And<T> implements Predicate<T> {
		
		private ArrayIterator<? extends Predicate<? super T>> predicates;
		
		public And(Predicate<? super  T>... predicates) {
			this.predicates = new ArrayIterator<Predicate<? super T>>(predicates);
		}

		public boolean evaluate(final T object) {
			return CollectionUtils.findFirst(predicates, new Predicate<Predicate<? super T>>() {
				public boolean evaluate(Predicate<? super T> predicate) {
					return !predicate.evaluate(object);
				}
			}) == null;
		}
	}
}

