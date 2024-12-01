package Day1;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.*;

public class Day1 {

	public static void main(String[] args) {
		System.out.println("Total Distance: " + part1());
		System.out.println("Total Similarity Score: " + part2());
	}
	
	public static int part1() {
		ArrayList<Integer> firstList = new ArrayList<>();
		ArrayList<Integer> secondList = new ArrayList<>();
		
        try {
            String input = Files.readString(Path.of("src/Day1/Day1Input.txt"));

            String regex = "^(\\d*) *(\\d*)";
            Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                firstList.add(Integer.parseInt(matcher.group(1)));
                secondList.add(Integer.parseInt(matcher.group(2)));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions (e.g., file not found)
        }
        
        Collections.sort(firstList);
        Collections.sort(secondList);
        
        int totalDistance = 0;
        
        for(int i = 0; i < firstList.size(); i++) {
        	int distance = firstList.get(i) - secondList.get(i);
        	distance = distance < 0 ? distance * -1 : distance;
        	totalDistance += distance;
        }
        
        return totalDistance;
	}
	
	public static int part2() {
		ArrayList<Integer> firstList = new ArrayList<>();
		HashMap<String, Integer> occurences = new HashMap<>(); 
		
        try {
            String input = Files.readString(Path.of("src/Day1/Day1Input.txt"));

            String regex = "^(\\d*) *(\\d*)";
            Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                firstList.add(Integer.parseInt(matcher.group(1)));
                String secondNum = matcher.group(2);
                occurences.put(secondNum, occurences.containsKey(secondNum) ? occurences.get(secondNum) + 1 : 1);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions (e.g., file not found)
        }
        
        int totalSimilarityScore = 0;
        
        for(int i = 0; i < firstList.size(); i++) {
        	Integer numInQuestion = firstList.get(i);
        	int similarityScore = occurences.containsKey(numInQuestion.toString()) ? numInQuestion * occurences.get(numInQuestion.toString()) : 0;
        	totalSimilarityScore += similarityScore;
        }
        
        return totalSimilarityScore;
	}
}
