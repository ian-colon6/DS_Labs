public class Lab01P1Wrapper {
	
	private static class Arithmetic{
		/*TODO ADD THE FOLLOWING:
		 * PRIVATE FIELDS,
		 * CONSTRUCTOR, 
		 * GETTERS, 
		 * SETTERS, 
		 * MEMBER METHODS
		 */
		private int num1;
		private int num2;
		
		Arithmetic(int a, int b){
			this.num1 = a;
			this.num2 = b;
		}
		
		public int getA() { return this.num1; }
		public int getB() { return this.num2; }
		
		public void setA(int numA) { this.num1 = numA; }
		public void setB(int numB) { this.num2 = numB; }
		
		public int add(){
			return this.getA() + this.getB();
		}
		
		public int subtract(){
			return this.getA() - this.getB();
		}
		
		public int multiply(){
			return this.getA() * this.getB();
		}
		
		public double divide(){
			return this.getA() / this.getB();
		}
	}
	
	public static class Calculator extends Arithmetic/*TODO What is missing here?*/{

		/*TODO ADD CONSTRUCTOR HERE*/
		public Calculator(int num_1, int num_2) {
			super(num_1, num_2);
		}
		
	}
	
}