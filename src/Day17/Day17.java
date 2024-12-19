package Day17;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Day17 {

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

	public static String part1() throws Exception {
		String input = Files.readString(Path.of("src/Day17/Day17Input.txt"));
		String[] sections = input.split("\n\n");
		String[] strRegisters = sections[0].split("\n");
		ArrayList<Long> registers = new ArrayList<>();
		for(String strRegister : strRegisters) {
			String[] strings = strRegister.split(" ");
			registers.add(Long.parseLong(strings[strings.length-1]));
		}
		Day17Computer computer = new Day17Computer(registers.get(0), registers.get(1), registers.get(2), sections[1].split(" ")[1]);
		return computer.runProgram();
	}
	
	public static long part2() throws Exception {
		String input = Files.readString(Path.of("src/Day17/Day17Input.txt"));
		String[] sections = input.split("\n\n");
		String[] strRegisters = sections[0].split("\n");
		ArrayList<Long> registers = new ArrayList<>();
		for(String strRegister : strRegisters) {
			String[] strings = strRegister.split(" ");
			registers.add(Long.parseLong(strings[strings.length-1]));
		}
		
		String[] desiredOutput = sections[1].split(" ")[1].split(",");
		ArrayList<Long> possibleAnswers = new ArrayList<>();
		possibleAnswers.add(0l);
		
		for(int k = 0; k < desiredOutput.length; k++) {
			IntStream.range(0, possibleAnswers.size()).forEach(i -> possibleAnswers.set(i, possibleAnswers.get(i) * 8));
			
			ArrayList<Long> newPossibleAnswers = new ArrayList<>();
			for(Long num : possibleAnswers) {
				for(int i = 0; i < 8; i++) {
					Day17Computer computer = new Day17Computer(num+i, registers.get(1), registers.get(2), sections[1].split(" ")[1]);
					String[] output = computer.runProgram().split(",");
					boolean isMatch = true;
					for(int j = 0; j <= k; j++) {
						if(!((output.length - 1 - j) > -1) || !((desiredOutput.length-1-j) > -1) || !output[output.length - 1 - j].equals(desiredOutput[desiredOutput.length-1-j])) {
							isMatch = false;
							break;
						}
					}
					if(isMatch) {
						newPossibleAnswers.add(num+i);
					}
				}
			}
			possibleAnswers.removeAll(possibleAnswers);
			possibleAnswers.addAll(newPossibleAnswers);
		}
		
		possibleAnswers.sort(null);
		
		return possibleAnswers.get(0);
	}
}
