import java.io.IOException;

public class Count 
{

	//Return an array of HashObject objects containing each unique word 
	private static <T> HashObject<T>[] arrayCount(HashObjectInterface<T> objectArray) 
        {
		int size = objectArray.getSize();
		HashObject<T>[] count = (HashObject<T>[]) new HashObject[size];
		OverrideIterator<HashObject<T>> iterator = objectArray.getIterator();
		int i = 0;
		while (iterator.hasNext()) 
                {
                    count[i] = iterator.next();
                    i++;
		}
		return count;
	}
	
        //Counts the number of word occurences
	public static void wordCount(String file) 
        {
		HashObjectInterface<String> hashtable = null;
		
		hashtable = new Hashtable<String>(new OverrideStringComparator(), new HashFunction());
		
		//Creates the hashtable from the file stated in the arguments
		try 
                {
			FileWordReader reader = new FileWordReader(file);
			String word = reader.nextWord();
			while (word != null) 
                        {
				hashtable.increaseCount(word);
				word = reader.nextWord();
                        }
                        
		} catch (IOException e) 
                {
			System.err.println("Error processing " + file + " " + e);
			System.exit(1);
		}
		
		//Retrieves the objects from the array
		HashObject<String>[] counts = arrayCount(hashtable);
		int elements = counts.length;
		
		System.out.println("Count" + " \t" + "Word");
                System.out.println("-------------------------");
		for (int i = 0; i < elements; i++) 
                {
                    System.out.println(counts[i].count + " \t" + counts[i].data);
		}
	}

        public static void wordCountNoPrint(String file) 
        {
		HashObjectInterface<String> counter = null;
		
		counter = new Hashtable<String>(new OverrideStringComparator(), new HashFunction());
		
		try 
                {
			FileWordReader reader = new FileWordReader(file);
			String word = reader.nextWord();
			while (word != null) 
                        {
				counter.increaseCount(word);
				word = reader.nextWord();
                        }
                        
		} catch (IOException e) 
                {
			System.err.println("Error processing " + file + " " + e);
			System.exit(1);
		}
	}
	
	//prints out the average time to process the count over 10 runs
	public static void averageWordCount(String file) 
        {
		long totalTime = 0;
                int run = 1;
                System.out.println("Individual Run Times: ");
		for (int i = 0; i < 10; i++) 
                {
			long startTime = System.currentTimeMillis();
			wordCountNoPrint(file);
			long endTime = System.currentTimeMillis();
			long time = endTime - startTime;
			System.out.println(run + ": " + time + " ms");
			totalTime += time; 
                        run++;
		}
		long averageTime = totalTime / 10;
		System.out.println("Average Run Time: " + averageTime + " ms\n");
	}
}
