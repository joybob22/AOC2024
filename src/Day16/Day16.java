package Day16;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day16 {

	public static void main(String[] args) {
		try {
			long startTime = System.nanoTime();
			System.out.println("Part 1: " + part1());
			long endTime = System.nanoTime();
			double durationInSeconds = (endTime - startTime) / 1000000000.0;
			System.out.println("Part 1 runtime: " + durationInSeconds);
			startTime = System.nanoTime();
			System.out.println("Part 2: " + part2());
			endTime = System.nanoTime();
			durationInSeconds = (endTime - startTime) / 1000000000.0;
			System.out.println("Part 2 runtime: " + durationInSeconds);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static int part1() throws Exception {
		String input = Files.readString(Path.of("src/Day16/Day16Input.txt"));
		ArrayList<Integer> start = new ArrayList<>();
		ArrayList<Integer> end = new ArrayList<>();
		char[][] grid = AStarGrid.parseGrid(input, start, end);
		Map<String, Double> edgeWeights = AStarGrid.createEdgeWeights(grid);
		
		return (int) AStarGrid.aStarWithWeights(grid, start.stream().mapToInt(Integer::intValue).toArray(), end.stream().mapToInt(Integer::intValue).toArray(), edgeWeights);
	}
	
	public static int part2() throws Exception {
		String input = Files.readString(Path.of("src/Day16/Day16Input.txt"));
		ArrayList<Integer> start = new ArrayList<>();
		ArrayList<Integer> end = new ArrayList<>();
		char[][] grid = AStarGrid.parseGrid(input, start, end);
		Map<String, Double> edgeWeights = AStarGrid.createEdgeWeights(grid);
		
		List<List<Node>> bestPaths = AStarGrid.aStarAllBestPaths(grid, start.stream().mapToInt(Integer::intValue).toArray(), end.stream().mapToInt(Integer::intValue).toArray(), edgeWeights);
		ArrayList<Node> uniqueNodes = new ArrayList<>();
		
//		System.out.println(bestPaths.size());
		
		for(List<Node> path : bestPaths) {
			for(Node node : path) {
				boolean isUnique = true;
				for(Node uniqueNode : uniqueNodes) {
					if(node.x == uniqueNode.x && node.y == uniqueNode.y) {
						isUnique = false;
						break;
					}
				}
				if(isUnique) {
					uniqueNodes.add(node);
				}
			}
		}
		
		return uniqueNodes.size();
	}
}
