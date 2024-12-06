package Day6;

import java.util.ArrayList;
import java.util.HashMap;

public class Day6Node {
	Day6Node top = null;
	Day6Node bottom = null;
	Day6Node left = null;
	Day6Node right = null;
	
	Character space = null;
	boolean visited = false;
	
	static HashMap<Day6Node, ArrayList<Direction>> visitedDirections = new HashMap<>();
	
	public boolean guardPatrol(Day6Node node, Direction direction) {
		ArrayList<Direction> visitedDirections = Day6Node.visitedDirections.get(node);
		
		if(visitedDirections != null && visitedDirections.contains(direction))
			return true;
		
		node.visited = true;
		if(visitedDirections == null) {
			Day6Node.visitedDirections.put(node, new ArrayList<Direction>());
		} else {
			visitedDirections.add(direction);			
		}
		
		Day6Node newNode = null;
		
		switch(direction) {
		case BOTTOM:
			newNode = node.bottom;
			break;
		case LEFT:
			newNode = node.left;
			break;
		case RIGHT:
			newNode = node.right;
			break;
		case TOP:
			newNode = node.top;
			break;
		}
		
		if(newNode == null) {
			return false;
		}
		
		if(newNode.space == '#')
			return guardPatrol(node, direction.rotate());
		
		return guardPatrol(newNode, direction);
	}
	
	public enum Direction {
		LEFT, RIGHT, TOP, BOTTOM;
		
		public Direction rotate() {
			if(this == Direction.LEFT)
				return Direction.TOP;
			if(this == Direction.TOP)
				return Direction.RIGHT;
			if(this == Direction.RIGHT)
				return Direction.BOTTOM;
			return Direction.LEFT;
		}
	}
}
