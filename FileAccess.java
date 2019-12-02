// This file gives access to the underlying datafile and stores the data in the Workout class.
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

//File code examples from https://www.geeksforgeeks.org/file-handling-java-using-filewriter-filereader/

public class FileAccess {
  
  //Load primes into primes from filePath
  public static boolean loadPrimes(PrimeOperations primes, String filePath) {
	  
	//Open a scanner in the specified file
	File file = new File(filePath);
	Scanner scan = null;
	try {
		 scan = new Scanner(file);
	} catch (FileNotFoundException fe) {
		System.out.println(fe);
	}
	
	int primeValue;
	
	//Scan in new values
	while (scan.hasNextLine()) {
		primeValue = scan.nextInt();
		primes.addPrime(BigInteger.valueOf(primeValue));
	}
	
	//Cleanup
	scan.close();
	return true;
  }
  
  //Load crosses in from filePath
  public static boolean loadCrosses(PrimeOperations primes, String filePath) {
	  
	//Open a scanner in the specified file
    File file = new File(filePath);
    Scanner scan = null;
    try {
    	scan = new Scanner(file);
    } catch (FileNotFoundException fe) {
    	System.out.println(fe);
    }
    
    int pairVal1;
    int pairVal2;
    scan.useDelimiter("(\\p{javaWhitespace}|,)+");	//Delimit white space and commas
    
    //Scan in new values and had as a hexagon cross
    while (scan.hasNextLine()) {
    	pairVal1 = scan.nextInt();
    	pairVal2 = scan.nextInt();
    	Pair<BigInteger> crossPair = new Pair<BigInteger>(BigInteger.valueOf(pairVal1), BigInteger.valueOf(pairVal2));
    	primes.addHexagonCross(crossPair);
    }
    
    scan.close();
	return true;
  }
  
  
  //Saves all of the primes currently in primes parameter
  public static boolean savePrimes(PrimeOperations primes, String filePath)
  {
	
	//Try to opena new file
	FileWriter file = null;
	try {
		file = new FileWriter(filePath);
	} catch (IOException ie) {
		System.out.println(ie);
	}
	
	//Write to the file
	PrintWriter writer = new PrintWriter(file);
	PrimeOperations.IterablePrimes primeIterator = primes.iteratePrimes();
	for (BigInteger value : primeIterator) {
		writer.print(value.intValue());
		writer.print("\n");
	}
	
	//Cleanup
	writer.close();
	
  	return true;
  }
  
  //Save all crosses to crossOutput.txt in your datapath
  public static boolean saveCrosses(PrimeOperations primes, String filePath)
  {
	//Try to open a new file
	FileWriter file = null;
	try {
		file = new FileWriter(filePath);
	} catch (IOException ie) {
		System.out.println(ie);
	}
	
	//Write to the file
	PrintWriter writer = new PrintWriter(file);
	PrimeOperations.IterableCrosses crossIterator = primes.iterateCrosses();
	for (Pair<BigInteger> pair : crossIterator) {
		int pairVal1 = pair.getPairVal1().intValue();
		int pairVal2 = pair.getPairVal2().intValue();
		writer.print(pairVal1 + ", " + pairVal2);
		writer.print("\n");
	}
	
	//Cleanup
	writer.close();
  	return true;
  }
  
}