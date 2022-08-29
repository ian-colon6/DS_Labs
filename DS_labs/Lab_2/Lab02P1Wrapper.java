public class Lab02P1Wrapper {

	/**
	 * Exercise #1
	 * Returns the quotient obtained by dividing two integers
	 * by RECURSIVELY subtracting one integer from the other
	 *  
	 * @param dividend
	 * @param divisor
	 * @return
	 */
    
	public static long div(int dividend, int divisor) {
		/*ADD YOUR CODE HERE*/
        if(dividend < divisor){ return 0; }

        else if(divisor == 0){ return 0; }

        else if(dividend - divisor == 0){ return 1; }

        else{ return  1 + div(dividend - divisor, divisor); }
		
	}



    public static void main(String[] args){


        long num = div(10, 5);
        long num1 = div(25, 5);
        long num2 = div(30, 6);
        long num3 = div(32, 3);

        System.out.println(num);
        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num3);

    }

}