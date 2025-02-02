import java.util.ArrayList;
import java.util.Iterator;
import java.math.BigInteger;

/*
 *  Desc: This class generates primes, twin primes, and hexagon crosses using BigInteger data types.
 */
public class PrimeOperations extends AKS {
	
	public Pair<BigInteger> createPair(BigInteger val1, BigInteger val2) {
		 return new Pair<BigInteger>(val1, val2);
	}
	
	// Member variables for containing out lists of integers, twin primes, hexagon crosses, and the pairs of twin primes that make up the hex crosses.
	private ArrayList<BigInteger> primeList;
	private ArrayList<Pair<BigInteger>> primePairList;
	private ArrayList<Pair<BigInteger>> hexagonCrossList;
	
	public ArrayList<BigInteger> getPrimeList() {return primeList;}
	public ArrayList<Pair<BigInteger>> getPrimePairList() {return primePairList;}
	public ArrayList<Pair<BigInteger>> getHexagonCrossList() {return hexagonCrossList;}

	
	//Constructor
	public PrimeOperations() {
		super();
		this.primeList = new ArrayList<BigInteger>();
		this.primePairList = new ArrayList<Pair<BigInteger>>();
		this.hexagonCrossList = new ArrayList<Pair<BigInteger>>();
	}
	
	
	//Check if a pair of BigIntegers are twin primes
	private boolean isTwinPrime(Pair<BigInteger> pair) {
		BigInteger val1 = pair.getPairVal1();
		BigInteger val2 = pair.getPairVal2();
		BigInteger difference = (val1.subtract(val2)).abs();
		boolean differenceIs2 = difference.compareTo(BigInteger.valueOf(2)) == 0;
		
		//Check if both values are prime and have a difference of 2
		return (isPrime(val1) && isPrime(val2) && differenceIs2);
	}
	
	//Check if a pair of BigIntegers is a hexagon cross
	private boolean isHexagonCross(Pair<BigInteger> pair) {
		BigInteger val1 = pair.getPairVal1();
		BigInteger val2 = pair.getPairVal2();
		
		//Check that val2 is twice the value of val1
		if (!(val1.multiply(BigInteger.valueOf(2)).compareTo(val2) == 0)) return false;
		
		Pair<BigInteger> twinPair1 = createPair(val1.subtract(BigInteger.ONE), val1.add(BigInteger.ONE));	//Lower twin pair
		Pair<BigInteger> twinPair2 = createPair(val2.subtract(BigInteger.ONE), val2.add(BigInteger.ONE));	//Upper twin pair
		
		//Check that twinPair1 is actually a pair of twin primes
		if (!isTwinPrime(twinPair1)) return false;
		if (!isTwinPrime(twinPair2)) return false;
		
		//Passed all tests, this pair is a hexagon cross
		return true;
	}
	
	// Add a prime to the prime list if and only if it is not already in the list. (ignore duplicates)
	public void addPrime(BigInteger x)
	{
		if (!primeList.contains(x)) {			//Check if x is already in the list
			primeList.add(x);	//Make sure x is a prime number before adding it
		}
	}
	
	//Add a pair of BigIntegers to the twin primes list if it is not already in the list. (ignore duplicates)
	public void addTwinPrime(Pair<BigInteger> pair) {
		if (!primePairList.contains(pair)) {
			primePairList.add(pair);
		}
	}
	
	//Add a pair of HexagonCrosses to hexagon cross list if it is not already in the list. (ignore duplicates)
	public void addHexagonCross(Pair<BigInteger> pair) {
		if (!hexagonCrossList.contains(pair)) {
			hexagonCrossList.add(pair);
		}
	}
	
	
	// Output the prime list. Each prime should be on a separate line and the total number of primes should be on the following line.
	public void printPrimes()
	{
		System.out.println("List of primes:");
		for (int i = 0; i < primeList.size(); i++) {
			System.out.println(primeList.get(i));
		}
		System.out.println("Total number of primes: " + primeList.size());
	}
		
	// Output the twin prime list. Each twin prime should be on a separate line with a comma separating them, and the total number of twin primes should be on the following line.
	public void printTwins()
	{
		System.out.println("List of twin primes:");
		for (int i = 0; i < primePairList.size(); i++) {
			BigInteger val1 = primePairList.get(i).getPairVal1();
			BigInteger val2 = primePairList.get(i).getPairVal2();
			System.out.println(val1 + ", " + val2);
		}
		System.out.println("Total number of twin primes: " + primePairList.size());
	}
		
	// Output the hexagon cross list. Each should be on a separate line listing the two twin primes and the corresponding hexagon cross, with the total number on the following line.
	public void printHexes()
	{
		System.out.println("List of hexagon crosses:");
		for (int i = 0; i < hexagonCrossList.size(); i++) {
			Pair<BigInteger> hexCrossPair = hexagonCrossList.get(i);
			BigInteger hexCrossVal1 = hexCrossPair.getPairVal1();
			BigInteger hexCrossVal2 = hexCrossPair.getPairVal2();
			
			Pair<BigInteger> lowerTwinPrime = createPair(hexCrossVal1.subtract(BigInteger.ONE), hexCrossVal1.add(BigInteger.ONE));
			Pair<BigInteger> upperTwinPrime = createPair(hexCrossVal2.subtract(BigInteger.ONE), hexCrossVal2.add(BigInteger.ONE));
			BigInteger lowerTwinPrimeVal1 = lowerTwinPrime.getPairVal1();
			BigInteger lowerTwinPrimeVal2 = lowerTwinPrime.getPairVal2();
			BigInteger upperTwinPrimeVal1 = upperTwinPrime.getPairVal1();
			BigInteger upperTwinPrimeVal2 = upperTwinPrime.getPairVal2();
			System.out.println("Prime Pairs: " + lowerTwinPrimeVal1 + ", " + lowerTwinPrimeVal2 + " and "
					+ upperTwinPrimeVal1 + ", " + upperTwinPrimeVal2 + " separated by " + hexCrossVal1 + ", " + hexCrossVal2);
		}
		System.out.println("Total hexagon crosses: " + hexagonCrossList.size());
	}
		
	// Generate and store a list of primes.
	public void generatePrimes(int start, int count)
	{
		primeList.clear();
		
		int generatedPrimes = 0;
		int i = start;
		while (generatedPrimes < count) {
			BigInteger val = BigInteger.valueOf(i);
			if (isPrime(val)) {
				addPrime(val);
				generatedPrimes++;
			}
			i++;
		}
	}
	
	
	// Generate and store a list of twin primes.
	public void generateTwinPrimes()
	{
		primePairList.clear();
		for (int i = 0; i < primeList.size()-1; i++)
		{
			if (primeList.get(i+1).equals((primeList.get(i).add(BigInteger.valueOf(2)))))
			{
				primePairList.add(new Pair<BigInteger>(primeList.get(i), (primeList.get(i+1))));
			}
		}
	}
	
	// Generate and store the hexagon crosses, along with the two twin primes that generate the hexagon cross.
	public void generateHexPrimes()
	{
		hexagonCrossList.clear();
		generateTwinPrimes();
		for (int i=0; i < primePairList.size()-1; i++)
		{
			BigInteger n = primePairList.get(i).getPairVal1().add(BigInteger.ONE);
			
			for (int j=i+1; j < primePairList.size(); j++)
			{
				BigInteger twoN = primePairList.get(j).getPairVal1().add(BigInteger.ONE);
				if (n.multiply(BigInteger.valueOf(2)).equals(twoN) )
				{
					hexagonCrossList.add(new Pair<BigInteger>(n, twoN));				
				}		
			}
		}
	}
		

	
	//----------------------Prime Iterator--------------------------
	public class PrimeIterator implements Iterator<BigInteger> {
		private int position = 0;
		
		public boolean hasNext() {
			if (position < primeList.size()) {
				return true;
			} else {
				return false;
			}
		}
		
		public BigInteger next() {
			if (this.hasNext())
				return primeList.get(position++);
			else
				return null;
		}
	}
	
	public class IterablePrimes implements Iterable<BigInteger>
	{		

		@Override
		public Iterator<BigInteger> iterator() {
			return new PrimeIterator();
		}
		
	}
	
	public IterablePrimes iteratePrimes() { return new IterablePrimes();}
	//-------------------------End Primes Iterator---------------------------
	
	//-------------------------Hexagon Cross Iterator-------------------------
	public class CrossIterator implements Iterator<Pair<BigInteger>> {
		private int position = 0;
		
		public boolean hasNext() {
			if (position < hexagonCrossList.size()) {
				return true;
			} else {
				return false;
			}
		}
		
		public Pair<BigInteger> next() {
			if (this.hasNext())
				return hexagonCrossList.get(position++);
			else
				return null;
		}
	}
	
	public class IterableCrosses implements Iterable<Pair<BigInteger>>
	{		
		@Override
		public Iterator<Pair<BigInteger>> iterator() {
			return new CrossIterator(); 
		}
	}

	public IterableCrosses iterateCrosses() { return new IterableCrosses();}
	//------------------------------End Hexagon Cross Iterator-----------------------
	
	
}
	

	



