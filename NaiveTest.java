import java.math.BigInteger;
import java.math.*;

public class NaiveTest
{	
	public static BigInteger sqrt(BigInteger x) {
	    BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
	    BigInteger div2 = div;
	    // Loop until we hit the same value twice in a row, or wind
	    // up alternating.
	    for(;;) {
	        BigInteger y = div.add(x.divide(div)).shiftRight(1);
	        if (y.equals(div) || y.equals(div2))
	            return y;
	        div2 = div;
	        div = y;
	    }
	}
	
	public boolean isPrime(BigInteger number) {

      if (number.compareTo(BigInteger.ONE) == 0)
            return false;

      if (number.compareTo(BigInteger.valueOf(2)) == 0)
            return true;
      
      if (number.mod(BigInteger.valueOf(2)).intValue() == 0)
            return false;

      for (int d = 3; (sqrt(number)).compareTo(BigInteger.valueOf(d)) != -1; d++)   {	
            if (number.mod(BigInteger.valueOf(d)).intValue() == 0)
                  return false;
      }

      return true;

	}
}
