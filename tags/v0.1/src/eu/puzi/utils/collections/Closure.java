package eu.puzi.utils.collections;

public interface Closure<T> {

	public void execute(T object);

	public class DoNothing<T> implements Closure<T> {
		public void execute(T object) {
			// do nothing
		}
	}
}


