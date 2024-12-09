package Day8;

public class Day8Node {
	Day8Node top = null;
	Day8Node bottom = null;
	Day8Node left = null;
	Day8Node right = null;
	
	Character space = null;
	boolean isAntinode = false;
	
	int row;
	int column;
	
	public Day8Node(int row, int column) {
		this.row = row;
		this.column = column;
	}
}
