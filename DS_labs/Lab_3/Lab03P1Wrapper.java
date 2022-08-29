
//import java.util.Arrays;
import java.util.HashMap;



public class Lab03P1Wrapper {
    
    public static int[] twoSum(int[] array, int targetSum) {
		/*ADD YOUR CODE HERE*/

        HashMap<Integer, Integer> nums = new HashMap<Integer, Integer>();
        int[] result = new int[2];

        for(int i = 0; i < array.length; i++){

            nums.put(targetSum - array[i], array[i]);
            if( nums.containsValue(targetSum - array[i]) ){
                result[0] = targetSum - array[i];
                result[1] = array[i];
                return result;
            }
        }
        
		return result; 
        
	}
}