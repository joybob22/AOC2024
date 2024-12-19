package Day13;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 {

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
	
	public static long part1() throws Exception {
		String input = Files.readString(Path.of("src/Day13/Day13Input.txt"));
		String[] games = input.split("\n\n");
		
		long numTokens = 0L;
		
		for(String game : games) {
			String regex = "^.*X.(\\d+), Y.(\\d+)$";
			Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
			Matcher matcher = pattern.matcher(game);
			
			int a1 = 0;
			int a2 = 0;
			int b1 = 0;
			int b2 = 0;
			int x1 = 0;
			int y2 = 0;
			
			int row = 0;
			while(matcher.find()) {
				if(row == 0) {
					a1 = Integer.parseInt(matcher.group(1));
					a2 = Integer.parseInt(matcher.group(2));
				}
				
				if(row == 1) {
					b1 = Integer.parseInt(matcher.group(1));
					b2 = Integer.parseInt(matcher.group(2));
				}
				
				if(row == 2) {
					x1 = Integer.parseInt(matcher.group(1));
					y2 = Integer.parseInt(matcher.group(2));
				}
				
				row++;
			}
			
			// Multiply -a1 to all of 2 and multiply a2 to all of 1
			int mA1 = a1 * a2;
			int mB1 = b1 * a2;
			int mx1 = x1 * a2;
			
			int mA2 = a2 * (-1 * a1);
			int mB2 = b2 * (-1 * a1);
			int my2 = y2 * (-1 * a1);
			
			double fB = mB1 + mB2;
			double fF = mx1 + my2;
			
			double b = fF / fB;
			
			if(b % 1 != 0)
				continue;
			
			double nB1 = b1 * b;
			double nx = x1 - nB1;
			double a = nx / a1;
			
			if(a % 1 != 0)
				continue;
			
			if(a > 100 || b > 100)
				continue;
			
			numTokens += (a*3 + b);
		}

		return numTokens;
	}
	
	public static long part2() throws Exception {
		String input = Files.readString(Path.of("src/Day13/Day13Input.txt"));
		String[] games = input.split("\n\n");
		
		long numTokens = 0L;
		
		for(String game : games) {
			String regex = "^.*X.(\\d+), Y.(\\d+)$";
			Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
			Matcher matcher = pattern.matcher(game);
			
			int a1 = 0;
			int a2 = 0;
			int b1 = 0;
			int b2 = 0;
			long x1 = 0;
			long y2 = 0;
			
			int row = 0;
			while(matcher.find()) {
				if(row == 0) {
					a1 = Integer.parseInt(matcher.group(1));
					a2 = Integer.parseInt(matcher.group(2));
				}
				
				if(row == 1) {
					b1 = Integer.parseInt(matcher.group(1));
					b2 = Integer.parseInt(matcher.group(2));
				}
				
				if(row == 2) {
					x1 = Integer.parseInt(matcher.group(1)) + 10000000000000L;
					y2 = Integer.parseInt(matcher.group(2)) + 10000000000000L;
				}
				
				row++;
			}
			
			// Multiply -a1 to all of 2 and multiply a2 to all of 1
			int mA1 = a1 * a2;
			int mB1 = b1 * a2;
			long mx1 = x1 * a2;
			
			int mA2 = a2 * (-1 * a1);
			int mB2 = b2 * (-1 * a1);
			long my2 = y2 * (-1 * a1);
			
			double fB = mB1 + mB2;
			double fF = mx1 + my2;
			
			double b = fF / fB;
			
			if(b % 1 != 0)
				continue;
			
			double nB1 = b1 * b;
			double nx = x1 - nB1;
			double a = nx / a1;
			
			if(a % 1 != 0)
				continue;
			
			numTokens += (a*3 + b);
		}

		return numTokens;
	}
}
