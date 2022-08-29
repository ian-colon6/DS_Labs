public class Lab02P4Wrapper {
	
	/**
	 * Returns the value of the function x^n RECURSIVELY using fast exponentiation, 
	 * where x is the base and n is the exponent.
	 *
	 * Non-recursive implementations will be given half the given credit, 
	 * WARNING: You CANNOT use the pow(double a, double b) method from the Math class.
	 * @param x - base
	 * @param n - exponent
	 * @return value of x^n
	 */
	public static double pow(double x, int n) {
		/*ADD YOUR CODE HERE*/
        if(n > 0){ return (x * pow(x, n - 1)); }

        else if(n < 0){ return (1/pow(x, -n));}

        else{ return 1; }
		
	}

    public static void main(String[] args){

        double num = pow(2, 10);
        double num_1 = pow(3, -1);
        double num_2 = pow(3, 2);
        double num_3 = pow(5, 6);
        double num_4 = pow(100, 0);
        double num_5 = pow(2, -3);

        System.out.println(num);
        System.out.println(num_1);
        System.out.println(num_2);
        System.out.println(num_3);
        System.out.println(num_4);
        System.out.println(num_5);
    }

}
