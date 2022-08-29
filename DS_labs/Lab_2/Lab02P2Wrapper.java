public class Lab02P2Wrapper {


	/**
	 * Exercise #2
	 * Returns true if and only if a patterns (a String) appears inside a text at least once
	 * 
	 * YOUR METHOD MUST BE RECURSIVE.  Non-recursive methods will receive half the credit obtained.
	 * HINT: The startsWith method from the String class may come in handy
	 * 
	 * @param pattern
	 * @param text
	 * @return
	 */
	public static boolean patternExists(String pattern, String text) {
		/*ADD YOUR CODE HERE*/
        if(text.length() < pattern.length()){ return false; }

        else if(pattern.isEmpty() == true || text.isEmpty() == true){ return false; }

        else if(text.startsWith(pattern)){ return true; }

        else{

            return patternExists( pattern, text.substring(1) );
        }
		
	}

    public static void main(String[] args){

        String word = "PersonaRen";
        String word1 = "TakemiBestGirl";
        String word2 = "MyString";
        String word3 = "depression";
        String word4 = "DatadataStructures";
        String word5 = "";
        String word6 = "Batman";

        System.out.println(patternExists("Persona", word));
        System.out.println(patternExists("Takemi", word1));
        System.out.println(patternExists("word", word2));
        System.out.println(patternExists("anxiety", word3));
        System.out.println(patternExists("data", word4));
        System.out.println(patternExists("word", word5));
        System.out.println(patternExists("at", word6));
        
    }

}