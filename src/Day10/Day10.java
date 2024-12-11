package Day10;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day10 {

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
	
	public static Day10Grid setup() throws Exception {
		String input = Files.readString(Path.of("src/Day10/Day10Input.txt"));
		String[] rows = input.split("\n");
		int[][] grid = new int[rows.length][rows[0].length()];
		
		for(int i = 0; i < rows.length; i++) {
			grid[i] = Arrays.stream(rows[i].split("")).mapToInt(Integer::parseInt).toArray();
		}
		
		Day10Grid day10Grid = new Day10Grid();
		day10Grid.initializeGrid(grid);
		return day10Grid;
	}
	
	public static int part1() throws Exception {
		Day10Grid grid = setup();
		return grid.trailHeadScores();
	}
	
	public static int part2() throws Exception {
		Day10Grid grid = setup();
		return grid.trailHeadScores2();
	}

}
