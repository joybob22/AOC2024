package Day2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Day2 {

	public static void main(String[] args) {
		System.out.println("Safe Reports Part 1: " + part1());
		System.out.println("Safe Reports Part 2: " + part2());

	}
	
	public static int part1() {
		int safeReports = 0;
		try {
            String input = Files.readString(Path.of("src/Day2/Day2Input.txt"));
            String[] reports = input.split("\n");
            for(String report : reports) {
            	int[] levels = Arrays.stream(report.split(" ")).mapToInt(Integer::parseInt).toArray();
            	boolean isSafe = true;
            	Direction direction;
            	if (levels[0] < levels[1]) {
            		direction = Direction.POSITIVE;
            	} else if (levels[0] > levels[1]) {
            		direction = Direction.NEGATIVE;
            	} else {
            		continue;
            	}
            	
            	for(int i = 0; i < levels.length - 1; i++) {
            		int distance = direction == Direction.POSITIVE ? levels[i+1] - levels[i] : levels[i] - levels[i+1];
            		if (distance < 1 || distance > 3) {
            			isSafe = false;
            			break;
            		}
            	}
            	
            	if(isSafe)
            		safeReports++;
            }
            
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions (e.g., file not found)
        }
		
		return safeReports;
	}
	
	public static int part2() {
		int safeReports = 0;
		try {
            String input = Files.readString(Path.of("src/Day2/Day2Input.txt"));
            String[] reports = input.split("\n");
            for(String report : reports) {
            	ArrayList<Integer> levels = Arrays.asList(report.split(" ")).stream().map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
            	boolean isSafe = true;
            	boolean levelRemoved = false;
            	
            	Direction direction;
            	if (levels.get(0) < levels.get(1)) {
            		direction = Direction.POSITIVE;
            	} else if (levels.get(0) > levels.get(1)) {
            		direction = Direction.NEGATIVE;
            	} else if (canRemoveWithSafeRunThrough(levels, 0)) {
    				safeReports++;
    				continue;
    			} else {
    				continue;
    			}
            	
            	for(int i = 0; i < levels.size() - 1; i++) {
            		int distance = direction == Direction.POSITIVE ? levels.get(i+1) - levels.get(i) : levels.get(i) - levels.get(i+1);
            		if (distance < 1 || distance > 3) {
            			if(canRemoveWithSafeRunThrough(levels, i)) {
            				break;
            			} else {
            				isSafe = false;
            				break;
            			}
            		}
            	}
            	
            	if(isSafe) {
            		safeReports++;
            	}
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return safeReports;
	}
	
	public static boolean canRemoveWithSafeRunThrough(ArrayList<Integer> levels, int i) {
		Integer temp;
		if(i > 0) {
			temp = levels.get(i-1);
			levels.remove(i-1);
			if(isSafeRunThrough(levels)) {
				return true;
			}
			levels.add(i-1, temp);
		}
		
		temp = levels.get(i);
		levels.remove(i);
		if (isSafeRunThrough(levels)) {
			return true;
		}
		levels.add(i, temp);

		temp = levels.get(i+1);
		levels.remove(i+1);
		if(isSafeRunThrough(levels)) {
			return true;
		}
		
		return false;
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
