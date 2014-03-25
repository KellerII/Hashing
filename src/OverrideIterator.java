//Interface for overriding Java's iterator
public interface OverrideIterator<T> 
{
	T next();

	boolean hasNext();
}
