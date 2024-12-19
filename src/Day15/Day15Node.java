package Day15;

public class Day15Node {
	int row;
	int column;
	char space;
	boolean isWall;
	boolean hasBox;
	
	public Day15Node(int row, int column, boolean isWall, boolean hasBox, char space) {
		this.row = row;
		this.column = column;
		this.isWall = isWall;
		this.hasBox = hasBox;
		this.space = space;
	}
}
