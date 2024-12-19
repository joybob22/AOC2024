package Day16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarGrid {
	
	public static char[][] parseGrid(String grid, ArrayList<Integer> start, ArrayList<Integer> end) {
	    String[] rows = grid.split("\n");
	    char[][] gridArray = new char[rows.length][rows[0].length()];
	    for (int i = 0; i < rows.length; i++) {
	        gridArray[i] = rows[i].toCharArray();
	        for(int j = 0; j < gridArray[i].length; j++) {
	        	if(gridArray[i][j] == 'S') {
	        		start.add(i);
	        		start.add(j);
	        	}
	        	if(gridArray[i][j] == 'E') {
	        		end.add(i);
	        		end.add(j);
	        	}
	        }
	    }
	    return gridArray;
	}
	
	public static double aStarWithWeights(char[][] grid, int[] start, int[] end, Map<String, Double> edgeWeights) {
	    int rows = grid.length, cols = grid[0].length;
	    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // N, E, S, W

	    PriorityQueue<Node> openSet = new PriorityQueue<>();
	    Set<String> closedSet = new HashSet<>();
	    Map<String, Double> gCosts = new HashMap<>();

	    Node startNode = new Node(start[0], start[1], 0, heuristic(start, end), 0, 1); // Start facing east
	    openSet.add(startNode);
	    gCosts.put(start[0] + "," + start[1], 0.0);

	    while (!openSet.isEmpty()) {
	        Node current = openSet.poll();
	        String currentKey = current.x + "," + current.y;

	        if (closedSet.contains(currentKey)) continue;
	        closedSet.add(currentKey);

	        if (current.x == end[0] && current.y == end[1]) {
	            System.out.println("Total Cost: " + current.totalCost);
	            return current.totalCost;
	        }

	        for (int i = 0; i < directions.length; i++) {
	            int newX = current.x + directions[i][0];
	            int newY = current.y + directions[i][1];
	            String edgeKey = current.x + "," + current.y + "->" + newX + "," + newY;

	            if (newX < 0 || newX >= rows || newY < 0 || newY >= cols || grid[newX][newY] == '#' || !edgeWeights.containsKey(edgeKey)) {
	                continue; // Skip walls and invalid edges
	            }

	            double edgeWeight = edgeWeights.get(edgeKey);
	            double directionChangePenalty = (i != current.direction) ? 1000 : 0;
	            double tentativeGCost = current.gCost + edgeWeight + directionChangePenalty;

	            if (tentativeGCost < gCosts.getOrDefault(newX + "," + newY, Double.POSITIVE_INFINITY)) {
	                gCosts.put(newX + "," + newY, tentativeGCost);

	                Node neighbor = new Node(newX, newY, tentativeGCost, heuristic(new int[]{newX, newY}, end), tentativeGCost, i);
	                neighbor.parent = current;

	                openSet.add(neighbor);
	            }
	        }
	    }

	    System.out.println("No path found!");
	    return Double.POSITIVE_INFINITY;
	}
	
	public static List<List<Node>> aStarAllBestPaths(
		    char[][] grid, int[] start, int[] end, Map<String, Double> edgeWeights
		) {
		    int rows = grid.length, cols = grid[0].length;
		    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // N, E, S, W

		    PriorityQueue<Node> openSet = new PriorityQueue<>();
		    Map<String, Map<Integer, Double>> visitedCosts = new HashMap<>(); // Tracks best cost per direction
		    List<List<Node>> bestPaths = new ArrayList<>();
		    double bestCost = Double.POSITIVE_INFINITY;

		    Node startNode = new Node(start[0], start[1], 0, heuristic(start, end), 0, 1); // Start facing east
		    openSet.add(startNode);

		    while (!openSet.isEmpty()) {
		        Node current = openSet.poll();
		        String currentKey = current.x + "," + current.y;

		        // Ensure visitedCosts exists for this node
		        visitedCosts.putIfAbsent(currentKey, new HashMap<>());
		        Map<Integer, Double> directionCosts = visitedCosts.get(currentKey);

		        // Skip if this direction has already been visited with a lower cost
		        if (directionCosts.containsKey(current.direction) && directionCosts.get(current.direction) < current.gCost) {
		            continue;
		        }

		        // Update visitedCosts for this direction
		        directionCosts.put(current.direction, current.gCost);

		        if (current.x == end[0] && current.y == end[1]) {
		            // Check if the current path matches the best cost
		            if (current.totalCost <= bestCost) {
		                if (current.totalCost < bestCost) {
		                    bestCost = current.totalCost;
		                    bestPaths.clear(); // Clear previous paths if a lower cost is found
		                }
		                bestPaths.add(reconstructPath(current)); // Add the current path
		            }
		            continue; // Continue to explore other paths
		        }

		        for (int i = 0; i < directions.length; i++) {
		            int newX = current.x + directions[i][0];
		            int newY = current.y + directions[i][1];
		            String edgeKey = current.x + "," + current.y + "->" + newX + "," + newY;

		            if (newX < 0 || newX >= rows || newY < 0 || newY >= cols || grid[newX][newY] == '#' || !edgeWeights.containsKey(edgeKey)) {
		                continue; // Skip walls and invalid edges
		            }

		            double edgeWeight = edgeWeights.get(edgeKey);
		            double directionChangePenalty = (i != current.direction) ? 1000 : 0;
		            double tentativeGCost = current.gCost + edgeWeight + directionChangePenalty;

		            if (tentativeGCost <= bestCost) { // Allow paths with cost equal to bestCost
		                Node neighbor = new Node(newX, newY, tentativeGCost, heuristic(new int[]{newX, newY}, end), tentativeGCost, i);
		                neighbor.parent = current;

		                openSet.add(neighbor);
		            }
		        }
		    }

		    return bestPaths;
		}

	
	private static List<Node> reconstructPath(Node current) {
	    List<Node> path = new ArrayList<>();
	    while (current != null) {
	        path.add(current);
	        current = current.parent;
	    }
	    Collections.reverse(path);
	    return path;
	}

	
	public static Map<String, Double> createEdgeWeights(char[][] grid) {
	    Map<String, Double> edgeWeights = new HashMap<>();
	    int rows = grid.length, cols = grid[0].length;

	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            if (grid[i][j] == '#') continue;

	            if (i + 1 < rows && grid[i + 1][j] != '#') {
	                edgeWeights.put(i + "," + j + "->" + (i + 1) + "," + j, 1.0);
	                edgeWeights.put((i + 1) + "," + j + "->" + i + "," + j, 1.0);
	            }
	            if (j + 1 < cols && grid[i][j + 1] != '#') {
	                edgeWeights.put(i + "," + j + "->" + i + "," + (j + 1), 1.0);
	                edgeWeights.put(i + "," + (j + 1) + "->" + i + "," + j, 1.0);
	            }
	        }
	    }

	    return edgeWeights;
	}
	
	private static double heuristic(int[] current, int[] end) {
	    return Math.abs(current[0] - end[0]) + Math.abs(current[1] - end[1]);
	}
}

class Node implements Comparable<Node> {
    int x, y;             // Node position
    double gCost;         // Cost from start to this node
    double hCost;         // Heuristic cost to the goal
    double totalCost;     // Total path cost for this node
    Node parent;          // To reconstruct the path
    int direction;        // 0 = north, 1 = east, 2 = south, 3 = west

    public Node(int x, int y, double gCost, double hCost, double totalCost, int direction) {
        this.x = x;
        this.y = y;
        this.gCost = gCost;
        this.hCost = hCost;
        this.totalCost = totalCost;
        this.parent = null;
        this.direction = direction;
    }

    public double getFCost() {
        return gCost + hCost;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.getFCost(), other.getFCost());
    }
}
