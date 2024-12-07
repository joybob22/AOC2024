package Day7;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day7 {

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
	
	public static BigInteger part1() throws Exception {
		String input = Files.readString(Path.of("src/Day7/Day7Input.txt"));
		String[] rows = input.split("\n");
		
		BigInteger totalPassed = new BigInteger("0");
		String[] possibleOperators = {"+", "*"};
		
		for(String row : rows) {
			String[] sections = row.split(": ");
			BigInteger neededAnswer = new BigInteger(sections[0]);
			int[] nums = Arrays.stream(sections[1].split(" ")).mapToInt(Integer::parseInt).toArray();
			int possibleCombinations = (int) Math.pow(2, nums.length - 1);
			for(int i = 0; i < possibleCombinations + 1; i++) {
				BigInteger answer = new BigInteger(nums[0] + "");
				String[] combination = new String[nums.length-1];
				for(int j = 0; j < combination.length; j++) {
					combination[j] = possibleOperators[(i >> j) & 1];
				}
				for(int j = 1; j < nums.length; j++) {
					if(combination[j-1].equals("+")) {
						answer = answer.add(new BigInteger(nums[j] + ""));
					} else {
						answer = answer.multiply(new BigInteger(nums[j] + ""));
					}
				}
				if(answer.equals(neededAnswer)) {
					totalPassed = totalPassed.add(answer);
					break;
				}
			}
		}
		
		return totalPassed;
	}
	
	public static int multiplyTo(int[] arr, int endIndex, int currIndex) {
		if(endIndex == currIndex)
			return arr[endIndex];
		return arr[currIndex] * multiplyTo(arr, endIndex, ++currIndex);
	}
	
	public static BigInteger part2() throws Exception {
		String input = Files.readString(Path.of("src/Day7/Day7Input.txt"));
		String[] rows = input.split("\n");
		
		BigInteger totalPassed = new BigInteger("0");
		String[] possibleOperators = {"+", "*", "||"};
		
		for(String row : rows) {
			String[] sections = row.split(": ");
			BigInteger neededAnswer = new BigInteger(sections[0]);
			int[] nums = Arrays.stream(sections[1].split(" ")).mapToInt(Integer::parseInt).toArray();
			int possibleCombinations = (int) Math.pow(3, nums.length - 1);
			for(int i = 0; i < possibleCombinations + 1; i++) {
				BigInteger answer = new BigInteger(nums[0] + "");
				String[] combination = new String[nums.length-1];
				for(int j = 0; j < combination.length; j++) {
					combination[j] = possibleOperators[(int) ((i / Math.pow(3, j)) % 3)];
				}
				for(int j = 1; j < nums.length; j++) {
					if(combination[j-1].equals("+")) {
						answer = answer.add(new BigInteger(nums[j] + ""));
					} else if(combination[j-1].equals("*")) {
						answer = answer.multiply(new BigInteger(nums[j] + ""));
					} else {
						answer = new BigInteger(answer.toString() + nums[j]);
					}
				}
				if(answer.equals(neededAnswer)) {
					totalPassed = totalPassed.add(answer);
					break;
				}
			}
		}
		
		return totalPassed;
	}

}
