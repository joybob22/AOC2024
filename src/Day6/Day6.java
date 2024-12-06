package Day6;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;

import Day6.Day6Node.Direction;

public class Day6 {

	public static void main(String[] args) {
		try {
			System.out.println("Part 1: " + part1());
			System.out.println("Part 2: " + part2());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Day6Grid setup() throws Exception {
		String input = Files.readString(Path.of("src/Day6/Day6Input.txt"));
		String[] rows = input.split("\n");
		Character[][] grid = new Character[rows.length][rows[0].length()];
		
		for(int i = 0; i < rows.length; i++) {
			grid[i] = Arrays.stream(rows[i].split("")).map(s -> s.charAt(0)).toArray(Character[]::new);
		}
		
		Day6Grid day6Grid = new Day6Grid();
		day6Grid.initializeGrid(grid);
		return day6Grid;
	}
	
	public static int part1() throws Exception {
		Day6Grid grid = setup();
		Integer[] start = Arrays.stream(grid.start.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
		Day6Node startNode = grid.nodesArr[start[0]][start[1]];
		startNode.guardPatrol(startNode, Direction.TOP);
		return Arrays.stream(grid.nodesArr).map(a -> Arrays.stream(a).map(e -> e.visited ? 1 : 0).reduce(0, (b,c) -> b+c)).reduce(0, (a,b) -> a+b);
	}
	
	public static int part2() throws Exception {
		Day6Grid grid = setup();
		Integer[] start = Arrays.stream(grid.start.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
		Day6Node startNode = grid.nodesArr[start[0]][start[1]];
		int numLoops = 0;
		for(int i = 0; i < grid.nodesArr.length; i++) {
			for(int j = 0; j < grid.nodesArr[i].length; j++) {
				Day6Node node = grid.nodesArr[i][j];
				if(node.space == '#' || (i == start[0] && j == start[1]))
					continue;
				char originalSpace = node.space.charValue();
				grid.nodesArr[i][j].space = '#';
				numLoops += node.guardPatrol(startNode, Direction.TOP) ? 1 : 0;
				grid.nodesArr[i][j].space = originalSpace;
				Day6Node.visitedDirections = new HashMap<>();
			}
		}
		return numLoops;
	}

}
