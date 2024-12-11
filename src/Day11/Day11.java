package Day11;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day11 {

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
		String input = Files.readString(Path.of("src/Day11/Day11Input.txt"));
		ArrayList<Long> stones = new ArrayList<>();
		Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).forEach(a -> stones.add((long) a));
		
		for(int i = 0; i < 25; i++) {
			for(int j = 0; j < stones.size(); j++) {
				Long stone = stones.get(j);
				if(stone == 0) {
					stones.set(j, (long) 1);
					continue;
				}
				String strStone = stone.toString();
				int numDigits = strStone.length();
				
				if(numDigits % 2 == 0) {
					Long newStone1 = (long) Integer.parseInt(strStone.substring(0, numDigits / 2)); 
					Long newStone2 = (long) Integer.parseInt(strStone.substring(numDigits / 2, numDigits));
					stones.set(j, newStone1);
					stones.add(j+1, newStone2);
					j++;
				} else {
					stones.set(j, stone * 2024);
				}
			}
		}
		
		return stones.size();
	}
	
	public static Long part2() throws Exception {
		String input = Files.readString(Path.of("src/Day11/Day11Input.txt"));
		ArrayList<Long> stones = new ArrayList<>();
		Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).forEach(a -> stones.add((long) a));
		
		Long numStones = (long) 0;
		
		for(Long stone : stones) {
			StoneSequence sequence = new StoneSequence(stone);
			numStones += sequence.getStonesAt(75);
		}
		
		return numStones;
	}

}
