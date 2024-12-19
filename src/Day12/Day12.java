package Day12;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day12 {

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
	
	public static GardenGrid setup() throws Exception {
		String input = Files.readString(Path.of("src/Day12/Day12Input.txt"));
		String[] rows = input.split("\n");
		Character[][] grid = new Character[rows.length][rows[0].length()];
		
		for(int i = 0; i < rows.length; i++) {
			grid[i] = Arrays.stream(rows[i].split("")).map(s -> s.charAt(0)).toArray(Character[]::new);
		}
		
		GardenGrid gardenGrid = new GardenGrid();
		gardenGrid.initializeGrid(grid);
		return gardenGrid;
	}
	
	public static long part1() throws Exception {
		GardenGrid grid = setup();
		long totalPrice = grid.calculateTotalGardenPrice();
//		for(GardenPrice price : grid.prices) {
//			System.out.println("Flower: " + price.flower + "; area: " + price.area + "; perimeter: " + price.perimeter + "; price: " + price.getPrice());
//		}
		return totalPrice;
	}
	
	public static long part2() throws Exception {
		GardenGrid grid = setup();
		long totalPrice = grid.calculateTotalGardenPriceDiscounted();
//		for(GardenPrice price : grid.prices) {
//			System.out.println("Flower: " + price.flower + "; area: " + price.area + "; sides: " + price.sides.size() + "; price: " + price.getDiscountedPrice());
//		}
		return totalPrice;
	}

}
