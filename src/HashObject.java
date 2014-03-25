//Hash object used to keep count of word occurences
class HashObject<T> 
{
    T data;
    int count;

    HashObject(T data, int count) 
    {
        this.data = data;
        this.count = count;
    }
}
