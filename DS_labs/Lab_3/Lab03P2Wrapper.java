import java.util.HashMap;

public class Lab03P2Wrapper {


    public static int firstDuplicate(int[] array) {
		/*ADD YOUR CODE HERE*/
        boolean flag = false;
        int dupe = 0;
        HashMap<Integer, Integer> numbers = new HashMap<Integer, Integer>();

        for(int i = 0; i < array.length; i++){
            
            if(numbers.containsKey(array[i]) ){
                flag = true;
                dupe = array[i];
                break;
            }else{
                numbers.put(array[i], i);
            }

        }

        if(flag){
            return dupe;
        }else{
            return -1;
        }
		
	}
}
