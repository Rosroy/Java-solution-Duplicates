import java.util.*;
import java.util.stream.*;
import java.text.*;
public class Solution {

	    private final static int size = 10000000;
	    private static Random rand = new Random();

	    public static void main(String [] args) {
	    	DecimalFormat formatter = new DecimalFormat("###,###,###,###");
	       System.out.println("Input array size: " + formatter.format(size));

	        for (int i = 0; i < 5; i++) {

	            // For testing with a small input and print the result use this as input:
	           // int [] arr = new int [] {1, 99, 2, 82, 99, -20, 9, 2, 9, 45, -319, 1};
	           //System.out.println(Arrays.toString(arr));

	            System.out.println("[Test " + Integer.toString(i+1) + "]");
	            int [] arr = getArray();
	            duplicatesusingMap(arr);
	            duplicatesusingSet(arr);
	        }
	    }

	   private static int [] getArray() {  
	        return IntStream.generate(() -> rand.nextInt())
	                       .limit(size)
	                       .toArray();
	    }


	    private static void duplicatesusingMap(int [] arr) {
	        
	        int [] newarr = new int [arr.length];
	        int n = 0; //Number of distinct array elements
	        HashMap<Integer, Integer> map = new HashMap<>();
	        for (int i = 0, j = 0; i < arr.length; i++) {
	            if (map.put(arr [i], 1) == null) {
	                newarr [j++] = arr [i];
	                ++n;
	            }
	        }
	        int [] result = Arrays.copyOf(newarr, n);   
	        System.out.println("using map- out array size: " + result.length);

	    }
	    private static void duplicatesusingSet(int [] arr) {
	        
	        Set<Integer> set = new HashSet<>();
	        int [] newarr = new int [arr.length];
	        int n = 0;
	        for (int i = 0, j = 0; i < arr.length; i++) {
	            if (set.add(arr [i])) {
	                newarr [j++] = arr [i];
	                ++n;
	            }
	        }
	        int [] result = Arrays.copyOf(newarr, n);
	       
	        System.out.println("using set- out array size: " + result.length);
	        
	    }
	    
	    
}
	       
	        
/*Tradeoffs:

1)Data validation can be performed on the file type, according to the current requirements. For eg, .exe or .ini files can be removed via file validation to avoid slowness in the code
2) A way of doing this could be to write the data to a file or database and compute a hash value for the fields to be de-duplicated
Then we could store the hash values in memory with a suitable reference to the file (e.g. the byte index of where the original value is in the written file). 
Whenever there is a hit i.e there is a hash match,we can look up the original value and check whether it is identical (as hashes for different values may fall together).If identical then drop the values .

3)To handle unbounded streams of items, filter can be used where we can randomly choose  bits and reset them to 0, as we insert new items ,
 such that junk data that have become very old and stale can be deleted from the filter e.g a Bloom filter
A Bloom filter basically attempts to insert an item that is already represented within the filter means setting a number of bits to 1 
that are already recording 1 values. We can keep the Bloom filter and look at the final density of 1s and 0s 
to estimate the number of distinct items represented (taking into account collisions under hash functions).

*/
    
