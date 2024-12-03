package Day3;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day3 {

	public static void main(String[] args) {
		try {
			System.out.println("Part 1: " + part1());
			System.out.println("Part 2: " + part2());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int part1() throws Exception {
		String input = Files.readString(Path.of("src/Day3/Day3Input.txt"));
		String regex = "mul\\((\\d{1,3},\\d{1,3})\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        
        int total = 0;
        while(matcher.find()) {
        	total += Stream.of(matcher.group(1).split(",")).map(Integer::parseInt).reduce(1, (a,b) -> a*b);
        }
        
        
		return total;
	}
	
	public static int part2() throws Exception {
		String input = Files.readString(Path.of("src/Day3/Day3Input.txt"));
		String regex = "mul\\((\\d{1,3},\\d{1,3})\\)|don't\\(\\)|do\\(\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        
        int total = 0;
        boolean on = true;
        
        while(matcher.find()) {
        	String match = matcher.group();
        	
        	if(match.equals("don't()")) {
        		on = false;
        		continue;
        	}
        	
        	if(match.equals("do()")) {
        		on = true;
        		continue;
        	}
        	
        	if(!on) 
        		continue;
        	
        	total += Stream.of(matcher.group(1).split(",")).map(Integer::parseInt).reduce(1, (a,b) -> a*b);
        }
        
        
		return total;
	}

}
