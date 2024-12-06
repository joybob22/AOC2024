package Day6;

public class Day6Grid {
	public Day6Node[][] nodesArr = null;
	public String start = "";
	
	public void initializeGrid(Character[][] arr) {
		Day6Node[][] nodeArr = new Day6Node[arr.length][arr[0].length];
		String startingChords = "";
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				Day6Node node = new Day6Node();
				node.space = arr[i][j];
				nodeArr[i][j] = node;
				if(node.space == '^')
					startingChords = i + "," + j;
			}
		}
		
		for(int i = 0; i < nodeArr.length; i++) {
			for(int j = 0; j < nodeArr[i].length; j++) {
				Day6Node node = nodeArr[i][j];
				if (i > 0)
					node.top = nodeArr[i-1][j];
				if (i < nodeArr.length-1)
					node.bottom = nodeArr[i+1][j];
				if (j > 0)
					node.left = nodeArr[i][j-1];
				if (j < nodeArr[i].length-1)
					node.right = nodeArr[i][j+1];
			}
		}
		this.nodesArr = nodeArr;
		this.start = startingChords;
	}
}
