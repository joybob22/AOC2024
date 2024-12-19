package Day15;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Day15 {

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
	
	public static Day15Grid setup() throws Exception {
		String input = Files.readString(Path.of("src/Day15/Day15Input.txt"));
		String[] sections = input.split("\n\n");
		String[] rows = sections[0].split("\n");
		Character[][] grid = new Character[rows.length][rows[0].length()];
		
		for(int i = 0; i < rows.length; i++) {
			grid[i] = Arrays.stream(rows[i].split("")).map(s -> s.charAt(0)).toArray(Character[]::new);
		}
		
		Day15Grid day15Grid = new Day15Grid();
		day15Grid.initializeGrid(grid);
		day15Grid.moves = Arrays.stream(sections[1].split("\n")).reduce("", (a,b) -> a+b).split("");
		return day15Grid;
	}
	
	public static Day15Grid setupPart2() throws Exception {
		String input = Files.readString(Path.of("src/Day15/Day15Input.txt"));
		String[] sections = input.split("\n\n");
		String[] rows = sections[0].split("\n");
		ArrayList<ArrayList<Character>> grid = new ArrayList<>();
		
		for(int i = 0; i < rows.length; i++) {
			grid.add(Arrays.stream(rows[i].split("")).map(s -> s.charAt(0)).collect(Collectors.toCollection(ArrayList::new)));
		}
		
		for(int i = 0; i < grid.size(); i++) {
			ArrayList<Character> row = grid.get(i);
			for(int j = 0; j < grid.get(i).size(); j+=2) {
				Character space = row.get(j);
				if(space.equals('.'))
					row.add(j, '.');
				else if(space.equals('#'))
					row.add(j, '#');
				else if (space.equals('@'))
					row.add(j+1, '.');
				else {
					row.set(j, '[');
					row.add(j+1, ']');
				}
			}
		}
		
		Character[][] charGrid = grid.stream().map(r -> r.toArray(new Character[0])).toArray(Character[][]::new);
		
		
		for(int i = 0; i < charGrid.length; i++) {
			for(int j = 0; j < charGrid[i].length; j++) {
				System.out.print(charGrid[i][j]);
			}
			System.out.println();
		}
		
		Day15Grid day15Grid = new Day15Grid();
		day15Grid.initializeGridPart2(charGrid);
		day15Grid.moves = Arrays.stream(sections[1].split("\n")).reduce("", (a,b) -> a+b).split("");
		return day15Grid;
	}
	
	public static int part1() throws Exception {
		Day15Grid grid = setup();
		grid.makeMoves();
		return grid.calcGPS();
	}
	
	public static int part2() throws Exception {
		Day15Grid grid = setupPart2();
		grid.makeMovesPart2();
		System.out.println();
		System.out.println();
		for(int i = 0; i < grid.nodesArr.length; i++) {
			for(int j = 0; j < grid.nodesArr[i].length; j++) {
				System.out.print(grid.nodesArr[i][j].space);
			}
			System.out.println();
		}
		return grid.calcGPSPart2();
	}

}
