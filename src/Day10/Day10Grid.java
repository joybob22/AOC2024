package Day10;

import java.util.ArrayList;
import java.util.HashMap;

import Day6.Day6Node.Direction;

public class Day10Grid {
	public Day10Node[][] nodesArr = null;
	public ArrayList<Day10Node> trailheads = new ArrayList<>();
	public ArrayList<Day10Node> alreadyVisitedEnds = new ArrayList<>();
	public HashMap<Day10Node, ArrayList<Direction>> alreadyExploredRoutes = new HashMap<>();
	
	public void initializeGrid(int[][] arr) {
		Day10Node[][] nodeArr = new Day10Node[arr.length][arr[0].length];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				Day10Node node = new Day10Node(arr[i][j], i, j);
				nodeArr[i][j] = node;
				if (node.height == 0)
					trailheads.add(node);
			}
		}
		
		for(int i = 0; i < nodeArr.length; i++) {
			for(int j = 0; j < nodeArr[i].length; j++) {
				Day10Node node = nodeArr[i][j];
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
	}
	
	public int trailHeadScores() {
//		Object[] trailheadScores = trailheads.stream().map(a -> {
//			int validPaths = searchValidPaths(a);
//			alreadyVisitedEnds = new ArrayList<>();
//			alreadyExploredRoutes = new HashMap<>();
//			return validPaths;
//		}).toArray();
//		for(int i = 0; i < trailheadScores.length; i++) {
//			System.out.println(trailheadScores[i]);
//		}
		return trailheads.stream().map(a -> {
				int validPaths = searchValidPaths(a);
				alreadyVisitedEnds = new ArrayList<>();
				alreadyExploredRoutes = new HashMap<>();
				return validPaths;
			}).reduce(0, (a,b) -> a+b);
	}
	
	private int searchValidPaths(Day10Node node) {
		if (node.height == 9 && !alreadyVisitedEnds.contains(node)) {
			alreadyVisitedEnds.add(node);
			return 1;
		}
		
		if(node.height == 9)
			return 0;
		
		int validPaths = 0;
		// Look Down
		if(node.bottom != null && node.bottom.height - node.height == 1 &&
			(!alreadyExploredRoutes.containsKey(node) || 
			(alreadyExploredRoutes.containsKey(node) && !alreadyExploredRoutes.get(node).contains(Direction.BOTTOM)))) {
			
			if(alreadyExploredRoutes.containsKey(node))
				alreadyExploredRoutes.get(node).add(Direction.BOTTOM);
			else {
				ArrayList<Direction> dir = new ArrayList<>();
				dir.add(Direction.BOTTOM);
				alreadyExploredRoutes.put(node, dir);
			}
			
			validPaths += searchValidPaths(node.bottom);	
			
			
		}
		// Look Up
		if(node.top != null && node.top.height - node.height == 1 &&
			(!alreadyExploredRoutes.containsKey(node) || 
			(alreadyExploredRoutes.containsKey(node) && !alreadyExploredRoutes.get(node).contains(Direction.TOP)))) {
				
			if(alreadyExploredRoutes.containsKey(node))
				alreadyExploredRoutes.get(node).add(Direction.TOP);
			else {
				ArrayList<Direction> dir = new ArrayList<>();
				dir.add(Direction.TOP);
				alreadyExploredRoutes.put(node, dir);
			}
			
			validPaths += searchValidPaths(node.top);	
			
			
		}
		// Look Left
		if(node.left != null && node.left.height - node.height == 1 &&
			(!alreadyExploredRoutes.containsKey(node) || 
			(alreadyExploredRoutes.containsKey(node) && !alreadyExploredRoutes.get(node).contains(Direction.LEFT)))) {
				
			if(alreadyExploredRoutes.containsKey(node))
				alreadyExploredRoutes.get(node).add(Direction.LEFT);
			else {
				ArrayList<Direction> dir = new ArrayList<>();
				dir.add(Direction.LEFT);
				alreadyExploredRoutes.put(node, dir);
			}
			
			validPaths += searchValidPaths(node.left);	
			
			
		}
		// Look Right
		if(node.right != null && node.right.height - node.height == 1 &&
			(!alreadyExploredRoutes.containsKey(node) || 
			(alreadyExploredRoutes.containsKey(node) && !alreadyExploredRoutes.get(node).contains(Direction.RIGHT)))) {
				
			if(alreadyExploredRoutes.containsKey(node))
				alreadyExploredRoutes.get(node).add(Direction.RIGHT);
			else {
				ArrayList<Direction> dir = new ArrayList<>();
				dir.add(Direction.RIGHT);
				alreadyExploredRoutes.put(node, dir);
			}
			
			validPaths += searchValidPaths(node.right);	
			
			
		}
		
		return validPaths;
	}
	
	public int trailHeadScores2() {
		return trailheads.stream().map(a -> searchValidPaths2(a)).reduce(0, (a,b) -> a+b);
	}
	
	private int searchValidPaths2(Day10Node node) {
		if (node.height == 9)
			return 1;
		
		int validPaths = 0;
		// Look Down
		if(node.bottom != null && node.bottom.height - node.height == 1)
			validPaths += searchValidPaths2(node.bottom);
		// Look Up
		if(node.top != null && node.top.height - node.height == 1)
			validPaths += searchValidPaths2(node.top);
		// Look Left
		if(node.left != null && node.left.height - node.height == 1)
			validPaths += searchValidPaths2(node.left);
		// Look Right
		if(node.right != null && node.right.height - node.height == 1)
			validPaths += searchValidPaths2(node.right);
		
		return validPaths;
	}

}
