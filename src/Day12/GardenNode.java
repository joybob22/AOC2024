package Day12;

public class GardenNode {
	GardenNode top = null;
	GardenNode bottom = null;
	GardenNode left = null;
	GardenNode right = null;
	boolean visited = false;
	boolean topSideVisited = false;
	boolean rightSideVisited = false;
	boolean bottomSideVisited = false;
	boolean leftSideVisited = false;
	
	Character flower;
	int row;
	int column;
	
	public GardenNode(Character flower, int row, int column) {
		this.flower = flower;
		this.row = row;
		this.column = column;
	}
}
