import java.util.ArrayList;
import java.util.Arrays;



public class Lab01P4Wrapper {

	public static interface AdvancedArithmetic{
		
		/**
		 * Method that takes an integer as input and return the sum of all its divisors 
		 * For example divisors of 6 are 1, 2, 3 and 6, so divisorSum() should return 12
		 *
		 * @param n - number to take the divisors of
		 * @return An integer denoting the sum of each divisor of n
		 */
		int divisorSum(int n);
		
		/**
		 * Method that takes an integer as input and return an array of all its prime factors 
		 * For example prime factors of 12 are 2, 2 and 3, so primeFactors() should return [2, 2, 3]
		 *
		 * HINT: Use an ArrayList to store the numbers, then pass them on to an int array, 
		 * similar to a vector in C++.
		 *
		 * HINT: Remember the difference between Object Data Types and Primitive Data Types
		 * so you can succesfully store values and pass them
		 *
		 * @param n - number to take the divisors of
		 * @return An array of type int denoting each prime factor
		 */
		int[] primeFactors(int n);
		
		/**
		 * Method that determines whether a given integer n is a prime number
		 * 
		 * @param n
		 * @return if the number is a prime number or not
		 */
		boolean isPrime(int n);
		
		
		/**
		 * Method that return the nth number in the fibonacci sequence
		 * 
		 * For example if n = 5, then fibonacci(n) returns 5 because the fifth term in the sequence is 5
		 * 
		 * Remember that the sequence starts with 1 and 1, then the next term in the series is the sum
		 * of the previous 2 terms:
		 * 
		 * 1,1,2,3,5,8,13,21,...
		 * 
		 * By definition, the fibonacci sequence is defined as follows:
		 * 
		 * F_0 = 0
		 * F_1 = 1
		 * F_n = F_(n-2) + F_(n-1)
		 * 
		 * Bonus Challenge: Doing this recursively is trivial, but can you do this ITERATIVELY?
		 * 
		 * @param n - index of the fibonacci sequence we want to find
		 * @return the value of nth term of the fibonacci sequence
		 */
		int fibonacci(int n);
		
	}
	
	public static class AdvancedCalculator implements AdvancedArithmetic/*TODO What needs to be added here?*/{
		
		public int[] testValues; //DO NOT DELETE THIS, TEST WILL FAIL
		
		public AdvancedCalculator(int[] testValues) {
			this.testValues = testValues;
		}

        @Override

		public int divisorSum(int n){

            int sum = 0;

            for(int i = 1; i <= n; i++){

                if( n % i == 0){
                    sum += i;
                }
            }
            return sum;
        }
		
        @Override

		public int[] primeFactors(int n){

            ArrayList<Integer> primes = new ArrayList<>();
            

            for(int factor = 2; factor <= n; factor++){

                while(n % factor == 0){
                    primes.add(factor);
                    n = n / factor;
                }
            }
            //For the method to return a primitive int[], we must first manipulate the Integer ArrayList
            //Firstly, used .stream() to enable method manipulation for primes
            //used mapToInt lambda expression represented as num, thus each element in primes shall be applied with
            //the .intValue() method.
            //Lastly, used .toArray() method to store all manipulated elements in an int[].
            return primes.stream().mapToInt(num -> num.intValue()).toArray();

        }
		
        @Override

		public boolean isPrime(int n){
            //Prime numbers have only two divisible numbers, themselves and 1
            //Hence the total amount of factors a number must have to be prime is 2
            int prime_count = 0;

            for(int i = 1; i <= n; i++){
                if(n % i == 0){
                    prime_count++;
                }

            }
            
            return (prime_count == 2);
            
        }

        @Override
		
		public int fibonacci(int n){

            int fib = 0;
            int fib_1 = 1;
            int fib_2 = 1;

            for(int i = 0; i < n; i++){
                fib = fib_1;
                fib_1 = fib_2;
                fib_2 = fib + fib_1;
            }

            return fib;
        }
		
	}
}

