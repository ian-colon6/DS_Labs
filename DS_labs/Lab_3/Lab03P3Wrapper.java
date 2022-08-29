public class Lab03P3Wrapper {
    

    public static int[] searchMatrix(int[][] matrix, int target){
	    /*ADD YOUR CODE HERE*/

        int row = 0;
        int col = 0;
        int[] coord = {-1, -1};

        while (row < matrix.length && col < matrix[row].length) {
            
            if(matrix[row][col] == target){
                coord[0] = row;
                coord[1] = col;
                return coord;
            }else{
                col++;
            }

            if(col == matrix[row].length - 1){
                col = 0;
                row++;
            }

        }
        
    
        return coord;
        

    }


    public static void main(String args[]){


        int[][] testMatrix = {
            {1, 4, 7, 12, 15, 1000}, 

            {2, 5, 19, 31, 32, 1001}, 

            {3, 8, 24, 33, 35, 1002},

            {40, 41, 42, 44, 45, 1003}, 

            {99, 100, 103, 106, 128, 1004}
        };

        System.out.println(searchMatrix(testMatrix, 1));
    }
}
