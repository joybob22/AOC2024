package Day10;

public class Day10Node {
	Day10Node top = null;
	Day10Node bottom = null;
	Day10Node left = null;
	Day10Node right = null;
	
	int height;
	int row;
	int column;
	
	public Day10Node(int height, int row, int column) {
		this.height = height;
		this.row = row;
		this.column = column;
	}
}
