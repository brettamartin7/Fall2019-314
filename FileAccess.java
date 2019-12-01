// This file gives access to the underlying datafile and stores the data in the Workout class.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

//File code examples from https://www.geeksforgeeks.org/file-handling-java-using-filewriter-filereader/

public class FileAccess {
  
  public static boolean loadPrimes(PrimeOperations primes, String filename) {
	FileReader reader = null;
	String filePath = Config.DATAPATH + filename;
	try {
		reader = new FileReader(filePath);
	} catch (FileNotFoundException fe) {
		System.out.println("File not found");
		System.out.println(fe);
	}
	
	int i = 0;
	int primeValue;
	try {
		while ((primeValue = reader.read())!=-1) {
			primes.getPrimeList().set(i, BigInteger.valueOf(primeValue));
		}
	} catch (IOException ie) {
		System.out.println("Error while reading file");
		System.out.println(ie);
	}
	
	return true;
  }
  
//  public static boolean loadCrosses(Primes primes, String filename) {
//    return true;
//  }
//  
//  public static boolean savePrimes(Primes primes, String filename)
//  {
//  	return true;
//  }
//  
//  public static boolean saveCrosses(Primes primes, String filename)
//  {
//  	return true;
//  }
  
}