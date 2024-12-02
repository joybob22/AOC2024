package Day2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2 {

	public static void main(String[] args) {
		try {
			System.out.println("Safe Reports Part 1: " + part1());
			System.out.println("Safe Reports Part 2: " + part2());
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public static int part1() throws Exception {
        String input = Files.readString(Path.of("src/Day2/Day2Input.txt"));
        String[] reports = input.split("\n");
        
        return (int) Stream.of(reports).map((String report) -> {
        	ArrayList<Integer> levels = Arrays.asList(report.split(" ")).stream().map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
        	return isSafeRunThrough(levels) ? 1 : 0;
        }).reduce(0, (a,b) -> a + b);
	}
	
	public static int part2() throws Exception {
        String input = Files.readString(Path.of("src/Day2/Day2Input.txt"));
        String[] reports = input.split("\n");
        
        return (int) Stream.of(reports).map((String report) -> {
        	ArrayList<Integer> levels = Arrays.asList(report.split(" ")).stream().map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
        	for(int i = 0; i < levels.size(); i++) {
        		int temp = levels.get(i);
        		levels.remove(i);
        		if(isSafeRunThrough(levels))
        			return 1;
        		levels.add(i, temp);
        	}
        	return 0;
        }).reduce(0, (a, b) -> a + b); 
	}
	
	public static boolean isSafeRunThrough(ArrayList<Integer> levels) {
		Direction direction;
    	if (levels.get(0) < levels.get(1)) {
    		direction = Direction.POSITIVE;
    	} else if (levels.get(0) > levels.get(1)) {
    		direction = Direction.NEGATIVE;
    	} else {
    		return false;
    	}
		for(int i = 0; i < levels.size() - 1; i++) {
    		int distance = direction == Direction.POSITIVE ? levels.get(i+1) - levels.get(i) : levels.get(i) - levels.get(i+1);
    		if (distance < 1 || distance > 3) {
    			return false;
    		}
		}
		return true;
	}

}

enum Direction {
	POSITIVE, NEGATIVE
}
