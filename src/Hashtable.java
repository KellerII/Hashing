public class Hashtable<T> implements HashObjectInterface<T> 
{

	private Hashtable<T>.HashNode[] numNodes;
	private int size;
	private Primes p;
	protected OverrideComparator<? super T> comparator;
	protected HashInterface<? super T> hashFunctionObject;
	
	//Hash table nodes
	private class HashNode 
        {
		public T object;
		public int count;
		
		//Creates a new HashNode 
		public HashNode(T o, int c) 
                {
			object = o;
			count = c;
			size++;
		}
	}
	
	//Keep track of selected primes for the sizes of the hashFunctionObject table
	private class Primes 
        {
		
		public int[] primesList;
		public int currentPrime;
		public int lastPrime;
		
		//Create a new Primes hashFunctionObject
		public Primes()
                {
			currentPrime = 0;
			lastPrime = 0;
			primesList = new int[]{31, 79, 157, 337, 613, 1259, 2557, 5171, 10739,
				               21317, 42787, 85711, 171317, 343547, 679837};
		}
		
		//Return the next prime or a prime closest to twice the current
                //primes size
		public int next()
                {
			if (currentPrime < primesList.length) 
                        {
				currentPrime++;
				lastPrime = primesList[currentPrime - 1];
				return primesList[currentPrime - 1];
			} else 
                        {
				lastPrime *= 2;
				calcNextPrime();
				return lastPrime;
			}
		}
		
                //Calculate the next prime using the last prime
		private void calcNextPrime() 
                {
			boolean found = false;
			boolean prime = true;
			while (!found) 
                        {
				lastPrime++;
				for (int i = 2; i < lastPrime; i++) 
                                {
					if (lastPrime % i == 0) 
                                        {
						prime = false;
						break;
					}
				}
				if (!prime) 
                                {
					prime = true;
				} else 
                                {
					found = true;
				}
			}
		}
	}
	
	//Create a hashFunctionObject table
	public Hashtable(OverrideComparator<? super T> c, HashInterface<? super T> h) 
        {
		p = new Primes();
		numNodes = (Hashtable<T>.HashNode[]) new Hashtable.HashNode[p.next()];
		size = 0;
		comparator = c;
		hashFunctionObject = h;
	}
	
        //Increments the hashFunctionObject or adds it to the table if it isn't already
        //in the table also increase hashFunctionObject table size if needed
	public void increaseCount(T object) 
        {
		Double loadFactor = (size + 1.0) / numNodes.length;
		//If adding the hashFunctionObject hashFunctionObject increases the load count beyond .5
                //a new hashFunctionObject table is created
		if (loadFactor.compareTo(0.5) >= 0) 
                {
			rehash();
		}
		increaseCount(object, 1, numNodes);
	}

        //Increments the hashFunctionObject or adds it to the array if it isn't already
        //in the array
	private void increaseCount(T object, int c, Hashtable<T>.HashNode[] array) 
        {
		int place = indexPosition(object, array);
		if (array[place] == null) 
                {
			array[place] = new HashNode(object, c);
		} else 
                {
			array[place].count += c;
		}
	}
	
	//Rehashed to a larger table and sets that table as the hashtable
	private void rehash() 
        {
		Hashtable<T>.HashNode[] temp = (Hashtable<T>.HashNode[]) new Hashtable.HashNode[p.next()];
		size = 0;
		for (int i = 0; i < numNodes.length; i++) 
                {
			if (numNodes[i] != null) 
                        {
				increaseCount(numNodes[i].object, numNodes[i].count, temp);
			}
		}
		numNodes = temp;
	}
	
	public int getSize() 
        {
		return size;
	}
	
        //Returns the count of the hashFunctionObject or 0 if it isn't found
	public int getCount(T object) 
        {
		int place = indexPosition(object);
		if (numNodes[place] == null) 
                {
			return 0;
		} else {
			return numNodes[place].count;
		}
	}
	
	//Returns the index of the hashFunctionObject
	private int indexPosition(T object) 
        {
		return indexPosition(object, numNodes);
	}

	//Returns the index of the position of an hashFunctionObject
	private int indexPosition(T object, Hashtable<T>.HashNode[] node) 
        {
		int i = 0;
		int hash = this.hashFunctionObject.hash(object);
		int probe = (hash + i * i) % node.length;
		while (node[probe] != null && comparator.compare(node[probe].object, object) != 0) 
                {
			i++;
			probe = (hash + i * i) % node.length;
		}
		return probe;
	}
	
	//Returns an iterator
	public OverrideIterator<HashObject<T>> getIterator() 
        {
		return new OverrideIterator<HashObject<T>>() 
                {
			int current = findNextFrom(-1);

			int next = findNextFrom(current);
			

			private int findNextFrom(int c) 
                        {
				int temp = c + 1;
				while (temp < numNodes.length && numNodes[temp] == null) 
                                {
					temp++;
				}
				return temp;
			}
			
			//Returns hashFunctionObject and iterates
			public HashObject<T> next() 
                        {
				if (!hasNext()) 
                                {
					throw new java.util.NoSuchElementException();
				}
				HashObject<T> temp = new HashObject<T>(numNodes[current].object, numNodes[current].count);
				current = next;
				next = findNextFrom(current);
				return temp;
			}
			
			//bool if objects are left
			public boolean hasNext() 
                        {
				return current < numNodes.length;
			}
		};
	}
}
