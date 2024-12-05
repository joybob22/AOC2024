package Day5;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


public class Day5 {
	public static void main(String[] args) {
		try {
			System.out.println("Part 1: " + part1());
			System.out.println("Part 2: " + part2());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int part1() throws Exception {
		String input = Files.readString(Path.of("src/Day5/Day5Input.txt"));
		String[] sections = input.split("\n\n");
		String[] rulesInput = sections[0].split("\n");
		Map<String, Boolean> rules = Arrays.stream(rulesInput).collect(Collectors.toMap(key -> key, value -> true));
		String[] pageUpdateRows = sections[1].split("\n");
		
		String middlePages = "";
		for(String pageRow : pageUpdateRows) {
			String[] pageUpdates = pageRow.split(",");
			boolean didPass = true;
			for(int i = 0; i < pageUpdates.length - 1; i++) {
				for(int j = i + 1; j < pageUpdates.length; j++) {
					if(rules.containsKey(pageUpdates[j] + "|" + pageUpdates[i])) {
						didPass = false;
						break;
					}
				}
				if(!didPass)
					break;
			}
			if(didPass)
				middlePages += (pageUpdates[pageUpdates.length/2] + ",");
		}
		
		return Arrays.stream(middlePages.split(",")).map(Integer::parseInt).reduce(0, (a,b) -> a+b);
	}
	
	public static int part2() throws Exception {
		String input = Files.readString(Path.of("src/Day5/Day5Input.txt"));
		String[] sections = input.split("\n\n");
		String[] rulesInput = sections[0].split("\n");
		Map<String, Boolean> rules = Arrays.stream(rulesInput).collect(Collectors.toMap(key -> key, value -> true));
		String[] pageUpdateRows = sections[1].split("\n");
		
		String middlePages = "";
		for(String pageRow : pageUpdateRows) {
			String[] pageUpdates = pageRow.split(",");
			boolean didPass = true;
			for(int i = 0; i < pageUpdates.length - 1; i++) {
				for(int j = i + 1; j < pageUpdates.length; j++) {
					if(rules.containsKey(pageUpdates[j] + "|" + pageUpdates[i])) {
						didPass = false;
						String temp = pageUpdates[i];
						pageUpdates[i] = pageUpdates[j];
						pageUpdates[j] = temp;
						i = i - 1;
						break;
					}
				}
			}
			if(!didPass)
				middlePages += (pageUpdates[pageUpdates.length/2] + ",");
		}
		
		return Arrays.stream(middlePages.split(",")).map(Integer::parseInt).reduce(0, (a,b) -> a+b);
	}
}
