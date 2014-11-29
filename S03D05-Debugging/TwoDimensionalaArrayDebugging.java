
public class TwoDimensionalaArrayDebugging {

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 5, 6 }, {1, 9} };
		printMatrix(matrix);
	}

	private static void printMatrix(int[][] matrix) {
		for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
			printRow(matrix[rowIndex]);
		}
	}

	private static void printRow(int[] row) {
		for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
			System.out.printf("%d ", row[columnIndex]);
		}
		
		System.out.println();
	}

}
