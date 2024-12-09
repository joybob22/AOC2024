package Day8;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day8 {

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
	
	public static Day8Grid setup() throws Exception {
		String input = Files.readString(Path.of("src/Day8/Day8Input.txt"));
		String[] rows = input.split("\n");
		Character[][] grid = new Character[rows.length][rows[0].length()];
		
		for(int i = 0; i < rows.length; i++) {
			grid[i] = Arrays.stream(rows[i].split("")).map(s -> s.charAt(0)).toArray(Character[]::new);
		}
		
		Day8Grid day8Grid = new Day8Grid();
		day8Grid.initializeGrid(grid);
		return day8Grid;
	}
	
	public static int part1() throws Exception {
		Day8Grid grid = setup();
		return grid.findAntinodes();
	}
	
	public static int part2() throws Exception {
		Day8Grid grid = setup();
		return grid.findAntinodesPart2();
	}

}
