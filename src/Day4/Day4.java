package Day4;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;

public class Day4 {

	public static void main(String[] args) {
		try {
			System.out.println("Part 1: " + part1());
			System.out.println("Part 2: " + part2());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static GraphDay4 setup() throws Exception {
		String input = Files.readString(Path.of("src/Day4/Day4Input.txt"));
		String[] rows = input.split("\n");
		Character[][] grid = new Character[rows.length][rows[0].length()];
		
		for(int i = 0; i < rows.length; i++) {
			grid[i] = Arrays.stream(rows[i].split("")).map(s -> s.charAt(0)).toArray(Character[]::new);
		}
		
		GraphDay4 graph = new GraphDay4();
		graph.initializeGraph(grid);
		return graph;
	}
	
	public static int part1() throws Exception {
		GraphDay4 graph = setup();
		return Arrays.stream(graph.nodesArr).map(row -> Arrays.stream(row).map(NodeDay4::countXmasOccurences).reduce(0, (a,b) -> a+b)).reduce(0, (a,b) -> a+b);
	}
	
	public static int part2() throws Exception {
		GraphDay4 graph = setup();
		return Arrays.stream(graph.nodesArr).map(row -> Arrays.stream(row).map(NodeDay4::countX_masOccurences).reduce(0, (a,b) -> a+b)).reduce(0, (a,b) -> a+b);
	}
	
	

}
