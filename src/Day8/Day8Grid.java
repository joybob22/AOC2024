package Day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Day8Grid {
	public Day8Node[][] nodesArr = null;
	public HashMap<Character, ArrayList<Day8Node>> antennas = new HashMap<>();
	
	
	public void initializeGrid(Character[][] arr) {
		Day8Node[][] nodeArr = new Day8Node[arr.length][arr[0].length];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				Day8Node node = new Day8Node(i,j);
				node.space = arr[i][j];
				nodeArr[i][j] = node;
				if(node.space == '.')
					continue;
				if(antennas.containsKey(node.space)) {
					antennas.get(node.space).add(node);
				} else {
					ArrayList<Day8Node> arrList = new ArrayList<>();
					arrList.add(node);
					antennas.put(node.space, arrList);
				}
			}
		}
		
		for(int i = 0; i < nodeArr.length; i++) {
			for(int j = 0; j < nodeArr[i].length; j++) {
				Day8Node node = nodeArr[i][j];
				if (i > 0)
					node.top = nodeArr[i-1][j];
				if (i < nodeArr.length-1)
					node.bottom = nodeArr[i+1][j];
				if (j > 0)
					node.left = nodeArr[i][j-1];
				if (j < nodeArr[i].length-1)
					node.right = nodeArr[i][j+1];
			}
		}
		this.nodesArr = nodeArr;
	}
	
	public int findAntinodes() {
		Set<Character> listOfAntennas = antennas.keySet();
		
		for(Character key : listOfAntennas) {
			ArrayList<Day8Node> arr = antennas.get(key);
			for(int i = 0; i < arr.size(); i++) {
				Day8Node firstAntenna = arr.get(i);
				for(int j = i + 1; j < arr.size(); j++) {
					Day8Node secondAntenna = arr.get(j);
					int antinodeRow = firstAntenna.row + (firstAntenna.row - secondAntenna.row);
					int antinodeColumn = firstAntenna.column + (firstAntenna.column - secondAntenna.column);
					if(antinodeRow >= 0 && antinodeRow < nodesArr.length && antinodeColumn >= 0 && antinodeColumn < nodesArr[0].length) {
						nodesArr[antinodeRow][antinodeColumn].isAntinode = true;
//						System.out.println("Antinode found at " + antinodeRow + "," + antinodeColumn);
					}
					
					antinodeRow = secondAntenna.row + (secondAntenna.row - firstAntenna.row);
					antinodeColumn = secondAntenna.column + (secondAntenna.column - firstAntenna.column);
					if(antinodeRow >= 0 && antinodeRow < nodesArr.length && antinodeColumn >= 0 && antinodeColumn < nodesArr[0].length) {
						nodesArr[antinodeRow][antinodeColumn].isAntinode = true;
//						System.out.println("Antinode found at " + antinodeRow + "," + antinodeColumn);
					}
					
				}
			}
		}
		return Arrays.stream(nodesArr).map(a -> Arrays.stream(a).map(b -> b.isAntinode ? 1 : 0).reduce(0, (c,d) -> c + d)).reduce(0, (c,d) -> c+d);
	}
	
	public int findAntinodesPart2() {
		Set<Character> listOfAntennas = antennas.keySet();
		
		for(Character key : listOfAntennas) {
			ArrayList<Day8Node> arr = antennas.get(key);
			for(int i = 0; i < arr.size(); i++) {
				Day8Node firstAntenna = arr.get(i);
				for(int j = i + 1; j < arr.size(); j++) {
					Day8Node secondAntenna = arr.get(j);
					firstAntenna.isAntinode = true;
					secondAntenna.isAntinode = true;
					
					int antinodeRow = firstAntenna.row + (firstAntenna.row - secondAntenna.row);
					int antinodeColumn = firstAntenna.column + (firstAntenna.column - secondAntenna.column);
					
					while(antinodeRow >= 0 && antinodeRow < nodesArr.length && antinodeColumn >= 0 && antinodeColumn < nodesArr[0].length) {
						nodesArr[antinodeRow][antinodeColumn].isAntinode = true;
						antinodeRow += (firstAntenna.row - secondAntenna.row);
						antinodeColumn += (firstAntenna.column - secondAntenna.column);
					}
					
					antinodeRow = secondAntenna.row + (secondAntenna.row - firstAntenna.row);
					antinodeColumn = secondAntenna.column + (secondAntenna.column - firstAntenna.column);
					
					while(antinodeRow >= 0 && antinodeRow < nodesArr.length && antinodeColumn >= 0 && antinodeColumn < nodesArr[0].length) {
						nodesArr[antinodeRow][antinodeColumn].isAntinode = true;
						antinodeRow += (secondAntenna.row - firstAntenna.row);
						antinodeColumn += (secondAntenna.column - firstAntenna.column);
					}
					
				}
			}
		}
		return Arrays.stream(nodesArr).map(a -> Arrays.stream(a).map(b -> b.isAntinode ? 1 : 0).reduce(0, (c,d) -> c + d)).reduce(0, (c,d) -> c+d);
	}
	
}
