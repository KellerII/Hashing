public interface HashObjectInterface<T> 
{
    public void increaseCount(T object);

    public int getSize();
   
    public int getCount(T object);
    
    public OverrideIterator<HashObject<T>> getIterator();
}
