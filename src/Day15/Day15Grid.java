package Day15;

import Day6.Day6Node.Direction;

public class Day15Grid {
	public Day15Node[][] nodesArr = null;
	public Day15Node robotPosition = new Day15Node(0,0,false,false,'@');
	public String[] moves = null;
	
	/*********************************/
	/******** Part 1 Methods *********/
	/*********************************/
	
	public void initializeGrid(Character[][] arr) {
		Day15Node[][] nodeArr = new Day15Node[arr.length][arr[0].length];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				Day15Node node = new Day15Node(i, j, arr[i][j] == '#', arr[i][j] == 'O', arr[i][j]);
				nodeArr[i][j] = node;
				if(arr[i][j] == '@') {
					robotPosition.row = i;
					robotPosition.column = j;
				}
			}
		}
		this.nodesArr = nodeArr;
	}
	
	public void makeMoves() {
		for(int i = 0; i < moves.length; i++) {
			if(moves[i].equals("^") && makeMove(Direction.TOP, robotPosition.row-1, robotPosition.column))
				robotPosition.row--;
			else if(moves[i].equals("v") && makeMove(Direction.BOTTOM, robotPosition.row+1, robotPosition.column))
				robotPosition.row++;
			else if(moves[i].equals(">") && makeMove(Direction.RIGHT, robotPosition.row, robotPosition.column+1))
				robotPosition.column++;
			else if(moves[i].equals("<") && makeMove(Direction.LEFT, robotPosition.row, robotPosition.column-1))
				robotPosition.column--;
		}
	}
	
	public boolean makeMove(Direction direction, int row, int column) {
		if(nodesArr[row][column].isWall)
			return false;
		if(!nodesArr[row][column].hasBox)
			return true;
		
		boolean isAbleToMove = false;
		switch(direction) {
		case BOTTOM:
			isAbleToMove = makeMove(direction, row+1, column);
			break;
		case LEFT:
			isAbleToMove = makeMove(direction, row, column-1);
			break;
		case RIGHT:
			isAbleToMove = makeMove(direction, row, column+1);
			break;
		case TOP:
			isAbleToMove = makeMove(direction, row-1, column);
			break;
		}
		
		if(isAbleToMove) {
			switch(direction) {
			case BOTTOM:
				nodesArr[row+1][column].hasBox = true;
				nodesArr[row][column].hasBox = false;
				break;
			case LEFT:
				nodesArr[row][column-1].hasBox = true;
				nodesArr[row][column].hasBox = false;
				break;
			case RIGHT:
				nodesArr[row][column+1].hasBox = true;
				nodesArr[row][column].hasBox = false;
				break;
			case TOP:
				nodesArr[row-1][column].hasBox = true;
				nodesArr[row][column].hasBox = false;
				break;
			}
		}
		
		return isAbleToMove;
	}
	
	public int calcGPS() {
		int gps = 0;
		
		for(int i = 0; i < nodesArr.length; i++) {
			for(int j = 0; j < nodesArr[i].length; j++) {
				if(nodesArr[i][j].hasBox)
					gps += (100 * i) + j;
			}
		}
		
		return gps;
	}
	
	
	/*********************************/
	/******** Part 2 Methods *********/
	/*********************************/
	
	public void initializeGridPart2(Character[][] arr) {
		Day15Node[][] nodeArr = new Day15Node[arr.length][arr[0].length];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				Day15Node node = new Day15Node(i, j, arr[i][j] == '#', (arr[i][j] == '[' || arr[i][j] == ']'), arr[i][j] == '@' ? '.' : arr[i][j]);
				nodeArr[i][j] = node;
				if(arr[i][j] == '@') {
					robotPosition.row = i;
					robotPosition.column = j;
				}
			}
		}
		this.nodesArr = nodeArr;
	}
	
	public void makeMovesPart2() {
		for(int i = 0; i < moves.length; i++) {
			if(moves[i].equals("^") && makeMove2(Direction.TOP, robotPosition.row-1, robotPosition.column, false)) {
				makeMove2(Direction.TOP, robotPosition.row-1, robotPosition.column, true);
				robotPosition.row--;
			}
			else if(moves[i].equals("v") && makeMove2(Direction.BOTTOM, robotPosition.row+1, robotPosition.column, false)) {
				makeMove2(Direction.BOTTOM, robotPosition.row+1, robotPosition.column, true);
				robotPosition.row++;
			}
			else if(moves[i].equals(">") && makeMove2(Direction.RIGHT, robotPosition.row, robotPosition.column+1, true))
				robotPosition.column++;
			else if(moves[i].equals("<") && makeMove2(Direction.LEFT, robotPosition.row, robotPosition.column-1, true))
				robotPosition.column--;
//			printGrid();
		}
	}
	
	public boolean makeMove2(Direction direction, int row, int column, boolean makeMove) {
		if(nodesArr[row][column].isWall)
			return false;
		if(!nodesArr[row][column].hasBox)
			return true;
		
		boolean isAbleToMoveLeftSide = false;
		boolean isAbleToMoveRightSide = false;
		switch(direction) {
		case BOTTOM:
			if(nodesArr[row][column].space == '[') {
				isAbleToMoveLeftSide = makeMove2(direction, row+1, column, makeMove);
				isAbleToMoveRightSide = makeMove2(direction, row+1, column+1, makeMove);
			} else {
				isAbleToMoveLeftSide = makeMove2(direction, row+1, column-1, makeMove);
				isAbleToMoveRightSide = makeMove2(direction, row+1, column, makeMove);
			}
			break;
		case LEFT:
			isAbleToMoveLeftSide = makeMove2(direction, row, column-1, makeMove);
			isAbleToMoveRightSide = true;
			break;
		case RIGHT:
			isAbleToMoveLeftSide = makeMove2(direction, row, column+1, makeMove);
			isAbleToMoveRightSide = true;
			break;
		case TOP:
			if(nodesArr[row][column].space == '[') {
				isAbleToMoveLeftSide = makeMove2(direction, row-1, column, makeMove);
				isAbleToMoveRightSide = makeMove2(direction, row-1, column+1, makeMove);
			} else {
				isAbleToMoveLeftSide = makeMove2(direction, row-1, column-1, makeMove);
				isAbleToMoveRightSide = makeMove2(direction, row-1, column, makeMove);
			}
			break;
		}
		
		if(isAbleToMoveLeftSide && isAbleToMoveRightSide && makeMove) {
			switch(direction) {
			case BOTTOM:
				if(nodesArr[row][column].space == '[') {
					nodesArr[row+1][column].hasBox = true;
					nodesArr[row+1][column].space = '[';
					nodesArr[row+1][column+1].hasBox = true;
					nodesArr[row+1][column+1].space = ']';
					nodesArr[row][column].hasBox = false;
					nodesArr[row][column].space = '.';
					nodesArr[row][column+1].hasBox = false;
					nodesArr[row][column+1].space = '.';
				} else {
					nodesArr[row+1][column].hasBox = true;
					nodesArr[row+1][column].space = ']';
					nodesArr[row+1][column-1].hasBox = true;
					nodesArr[row+1][column-1].space = '[';
					nodesArr[row][column].hasBox = false;
					nodesArr[row][column].space = '.';
					nodesArr[row][column-1].hasBox = false;
					nodesArr[row][column-1].space = '.';
				}
				break;
			case LEFT:
				nodesArr[row][column-1].hasBox = true;
				nodesArr[row][column-1].space = nodesArr[row][column].space;
				nodesArr[row][column].hasBox = false;
				nodesArr[row][column].space = '.';
				break;
			case RIGHT:
				nodesArr[row][column+1].hasBox = true;
				nodesArr[row][column+1].space = nodesArr[row][column].space;
				nodesArr[row][column].hasBox = false;
				nodesArr[row][column].space = '.';
				break;
			case TOP:
				if(nodesArr[row][column].space == '[') {
					nodesArr[row-1][column].hasBox = true;
					nodesArr[row-1][column].space = '[';
					nodesArr[row-1][column+1].hasBox = true;
					nodesArr[row-1][column+1].space = ']';
					nodesArr[row][column].hasBox = false;
					nodesArr[row][column].space = '.';
					nodesArr[row][column+1].hasBox = false;
					nodesArr[row][column+1].space = '.';
				} else {
					nodesArr[row-1][column].hasBox = true;
					nodesArr[row-1][column].space = ']';
					nodesArr[row-1][column-1].hasBox = true;
					nodesArr[row-1][column-1].space = '[';
					nodesArr[row][column].hasBox = false;
					nodesArr[row][column].space = '.';
					nodesArr[row][column-1].hasBox = false;
					nodesArr[row][column-1].space = '.';
				}
				break;
			}
		}
		
		return isAbleToMoveLeftSide && isAbleToMoveRightSide;
	}
	
	public int calcGPSPart2() {
		int gps = 0;
		
		for(int i = 0; i < nodesArr.length; i++) {
			for(int j = 0; j < nodesArr[i].length; j++) {
				if(nodesArr[i][j].space == '[')
					gps += (100 * i) + j;
			}
		}
		
		return gps;
	}
	
	public void printGrid() {
		System.out.println();
		System.out.println();
		for(int i = 0; i < nodesArr.length; i++) {
			for(int j = 0; j < nodesArr[i].length; j++) {
				if(i == robotPosition.row && j == robotPosition.column) {
					System.out.print('@');
				} else {					
					System.out.print(nodesArr[i][j].space);
				}
			}
			System.out.println();
		}
	}
}
