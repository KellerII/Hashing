public class HashFunction implements HashInterface<String> 
{
	//Hash function for DJB2 algorithm
	public int hash(String s) 
        {
            int hash = 5381;

            for (int i = 0; i < s.length(); i++) 
            {
                    hash = ((hash << 5) + hash) + (int) s.charAt(i);
            }
            return Math.abs(hash);
	}
}
