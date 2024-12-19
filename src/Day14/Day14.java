package Day14;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {

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
		String input = Files.readString(Path.of("src/Day14/Day14Input.txt"));
		String regex = "^p=(\\d+),(\\d+) v=(.*,.*)$";
		Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(input);
		ArrayList<Day14Node> nodes = new ArrayList<>();
		
		while(matcher.find()) {
			int pX = Integer.parseInt(matcher.group(1));
			int pY = Integer.parseInt(matcher.group(2));
			int[] v = Arrays.stream(matcher.group(3).split(",")).mapToInt(Integer::parseInt).toArray();
			Day14Node node = new Day14Node(pX, pY, v[0], v[1]);
			nodes.add(node);
		}
		
		int rightBound = 101;
		int bottomBound = 103;
		
		for(int i = 0; i < 100; i++) {
			for(Day14Node node : nodes) {
				int x = node.positionX + node.velocityX;
				int y = node.positionY + node.velocityY;
				if(x < 0) {
					x += rightBound;
				}
				else if(x >= rightBound) {
					x = x % rightBound;
				}
				
				if(y < 0) {
					y += bottomBound;
				}
				else if(y >= bottomBound) {
					y = y % bottomBound;
				}
				node.positionX = x;
				node.positionY = y;
			}
		}
		
		int ignoreX = rightBound / 2;
		int ignoreY = bottomBound / 2;
		
		long q1 = 0;
		long q2 = 0;
		long q3 = 0;
		long q4 = 0;
		
		for(Day14Node node : nodes) {
			if(node.positionX == ignoreX || node.positionY == ignoreY)
				continue;
			
			if(node.positionX < ignoreX && node.positionY < ignoreY)
				q1++;
			if(node.positionX > ignoreX && node.positionY < ignoreY)
				q2++;
			if(node.positionX < ignoreX && node.positionY > ignoreY)
				q3++;
			if(node.positionX > ignoreX && node.positionY > ignoreY)
				q4++;
		}
		
		
		return q1 * q2 * q3 * q4;
	}
	
	public static long part2() throws Exception {
		String input = Files.readString(Path.of("src/Day14/Day14Input.txt"));
		String regex = "^p=(\\d+),(\\d+) v=(.*,.*)$";
		Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(input);
		ArrayList<Day14Node> nodes = new ArrayList<>();
		
		while(matcher.find()) {
			int pX = Integer.parseInt(matcher.group(1));
			int pY = Integer.parseInt(matcher.group(2));
			int[] v = Arrays.stream(matcher.group(3).split(",")).mapToInt(Integer::parseInt).toArray();
			Day14Node node = new Day14Node(pX, pY, v[0], v[1]);
			nodes.add(node);
		}
		
		int rightBound = 101;
		int bottomBound = 103;
		
		String[][] board = new String[103][101];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = " ";
			}
		}
		
		for(int i = 0; i < 1000000; i++) {
			
			for(Day14Node node : nodes) {
				board[node.positionY][node.positionX] = "*";
			}
			
			boolean foundBunch = false;
			for(int k = 1; k < board.length-1; k++) {
				for(int j = 1; j < board[k].length-1; j++) {
					if(board[k][j] == "*" && 
						board[k-1][j] == "*" && 
						board[k-1][j-1] == "*" && 
						board[k][j-1] == "*" &&
						board[k+1][j] == "*" &&
						board[k][j+1] == "*" &&
						board[k+1][j+1] == "*") {
						foundBunch = true;
						break;
					}
				}
				if(foundBunch)
					break;
			}
			
			
			if(foundBunch) {
				for(int k = 0; k < board.length; k++) {
					for(int j = 0; j < board[k].length; j++) {
						System.out.print(board[k][j]);
					}
					System.out.println();
				}
				
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("-----------------------------");
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
			}
			
			
			for(Day14Node node : nodes) {
				int x = node.positionX + node.velocityX;
				int y = node.positionY + node.velocityY;
				if(x < 0) {
					x += rightBound;
				}
				else if(x >= rightBound) {
					x = x % rightBound;
				}
				
				if(y < 0) {
					y += bottomBound;
				}
				else if(y >= bottomBound) {
					y = y % bottomBound;
				}
				node.positionX = x;
				node.positionY = y;
			}
			
			for(int k = 0; k < board.length; k++) {
				for(int j = 0; j < board[k].length; j++) {
					board[k][j] = " ";
				}
			}
		}
		
		int ignoreX = rightBound / 2;
		int ignoreY = bottomBound / 2;
		
		long q1 = 0;
		long q2 = 0;
		long q3 = 0;
		long q4 = 0;
		
		for(Day14Node node : nodes) {
			if(node.positionX == ignoreX || node.positionY == ignoreY)
				continue;
			
			if(node.positionX < ignoreX && node.positionY < ignoreY)
				q1++;
			if(node.positionX > ignoreX && node.positionY < ignoreY)
				q2++;
			if(node.positionX < ignoreX && node.positionY > ignoreY)
				q3++;
			if(node.positionX > ignoreX && node.positionY > ignoreY)
				q4++;
		}
		
		
		return q1 * q2 * q3 * q4;
	}

}
