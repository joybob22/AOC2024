package Day4;


public class NodeDay4 {
	NodeDay4 left = null;
	NodeDay4 right = null;
	NodeDay4 top = null;
	NodeDay4 bottom = null;
	NodeDay4 topLeft = null;
	NodeDay4 topRight = null;
	NodeDay4 bottomLeft = null;
	NodeDay4 bottomRight = null;
	Character letter = null;
	
	public int countXmasOccurences() {
		if (letter != 'X')
			return 0;
		int total = 0;
		total += look(Direction.LEFT, 'X', this) ? 1 : 0;
		total += look(Direction.RIGHT, 'X', this) ? 1 : 0;
		total += look(Direction.TOP, 'X', this) ? 1 : 0;
		total += look(Direction.BOTTOM, 'X', this) ? 1 : 0;
		total += look(Direction.TOP_LEFT, 'X', this) ? 1 : 0;
		total += look(Direction.TOP_RIGHT, 'X', this) ? 1 : 0;
		total += look(Direction.BOTTOM_LEFT, 'X', this) ? 1 : 0;
		total += look(Direction.BOTTOM_RIGHT, 'X', this) ? 1 : 0;
		return total;
	}
	
	public int countX_masOccurences() {
		if(letter != 'A' || topRight == null || topLeft == null || bottomRight == null || bottomLeft == null)
			return 0;
		if((topLeft.letter == 'M' && bottomRight.letter == 'S' && topRight.letter == 'M' && bottomLeft.letter == 'S') ||
				(topLeft.letter == 'M' && bottomRight.letter == 'S' && topRight.letter == 'S' && bottomLeft.letter == 'M') ||
				(topLeft.letter == 'S' && bottomRight.letter == 'M' && topRight.letter == 'M' && bottomLeft.letter == 'S') ||
				(topLeft.letter == 'S' && bottomRight.letter == 'M' && topRight.letter == 'S' && bottomLeft.letter == 'M'))
			return 1;
		return 0;
	}
	
	public boolean look(Direction direction, Character currentLetter, NodeDay4 currentNode) {
		NodeDay4 newNode = null;
		
		switch(direction) {
		case BOTTOM:
			newNode = currentNode.bottom;
			break;
		case BOTTOM_LEFT:
			newNode = currentNode.bottomLeft;
			break;
		case BOTTOM_RIGHT:
			newNode = currentNode.bottomRight;
			break;
		case LEFT:
			newNode = currentNode.left;
			break;
		case RIGHT:
			newNode = currentNode.right;
			break;
		case TOP:
			newNode = currentNode.top;
			break;
		case TOP_LEFT:
			newNode = currentNode.topLeft;
			break;
		case TOP_RIGHT:
			newNode = currentNode.topRight;
			break;
		}
		
		if(newNode == null)
			return false;
		
		if(currentLetter == 'A' && newNode.letter == 'S')
			return true;
		
		if(currentLetter == 'X' && newNode.letter == 'M')
			return look(direction, newNode.letter, newNode);
		
		if(currentLetter == 'M' && newNode.letter == 'A') 
			return look(direction, newNode.letter, newNode);
		
		return false;
	}
	
	private enum Direction {
		LEFT, RIGHT, TOP, BOTTOM, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT;
	}
}
