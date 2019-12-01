import java.util.ArrayList;
import java.util.Iterator;
import java.math.BigInteger;

/*
 *  Desc: This class generates primes, twin primes, and hexagon crosses using BigInteger data types.
 */
public class PrimeOperations extends NaiveTest {
	
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
	private void addPrime(BigInteger x)
	{
		if (!primeList.contains(x)) {			//Check if x is already in the list
			primeList.add(x);	//Make sure x is a prime number before adding it
		}
	}
	
	//Add a pair of BigIntegers to the twin primes list if it is not already in the list. (ignore duplicates)
	private void addTwinPrime(Pair<BigInteger> pair) {
		if (!primePairList.contains(pair)) {
			primePairList.add(pair);
		}
	}
	
	//Add a pair of HexagonCrosses to hexagon cross list if it is not already in the list. (ignore duplicates)
	private void addHexagonCross(Pair<BigInteger> pair) {
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
	
	// Generate and store a list of twin primes, count refers to the lower number in the pair.
	public void generateTwinPrimes(int start, int count)
	{
		primePairList.clear();
		
		int generatedTwinPrimes = 0;
		int i = start;
		int j = i + 2;
		while (generatedTwinPrimes < count) {
			BigInteger val1 = BigInteger.valueOf(i);
			BigInteger val2 = BigInteger.valueOf(j);
			Pair<BigInteger> pair = createPair(val1, val2);
			if (isTwinPrime(pair)) {
				addTwinPrime(pair);
				generatedTwinPrimes++;
			}
			i++;
			j++;
		}
	}
	
	// Generate and store the hexagon crosses, along with the two twin primes that generate the hexagon cross.
	public void generateHexPrimes(int start, int count)
	{
		hexagonCrossList.clear();
		
		int i = start;
		int j = i + 1;
		int generatedHexCrosses = 0;
		
		while (generatedHexCrosses < count) {
			
			//Lower Twin Prime values
			BigInteger val1 = BigInteger.valueOf(i);
			BigInteger val2 = BigInteger.valueOf(j);
			Pair<BigInteger> hexCrossPair = createPair(val1, val2);
			if (isHexagonCross(hexCrossPair)) {
				addHexagonCross(hexCrossPair);
				generatedHexCrosses++;
			}
			
			i++;
			j = i * 2;
		}
	}
}
	
/*
	public class IterablePrimes implements Iterable<BigInteger>
	{		
	
	}
	
	public IterablePrimes iteratePrimes() { return new IterablePrimes();}

	public class IterableCrosses implements Iterable<BigInteger>
	{		
	
	}
	
	public IterableCrosses iterateCrosses() { return new IterablePrimes();}
*/


