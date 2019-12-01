

// Pair class implementation.
public class Pair<T> {
	private T pairVal1;
	private T pairVal2;
	
	//Constructor
	public Pair(T pairV1, T pairV2) {
		pairVal1 = pairV1;
		pairVal2 = pairV2;
	}
	public Pair() {};
	
	//Setters
	public void setPairVal1(T val) {
		pairVal1 = val;
	}
	public void setPairVal2(T val) {
		pairVal2 = val;	
	}
	
	//Getters
	public T getPairVal1() {
		return pairVal1;
	}
	public T getPairVal2() {
		return pairVal2;
	}
	
	//Helpers
	public void printPair() {
		System.out.println("Pair1: " + pairVal1 + "\tPair2: " + pairVal2);
	}
	
}
