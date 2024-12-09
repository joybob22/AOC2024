package Day9;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Day9 {

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
		String input = Files.readString(Path.of("src/Day9/Day9Input.txt"));
		ArrayList<FileBlock> fileBlocks = new ArrayList<>();
		
		boolean isFreeSpace = false;
		int id = 0;
		for(char block : input.toCharArray()) {
			int num = Integer.parseInt(block + "");
			FileBlock fileBlock = new FileBlock(num, isFreeSpace ? null : id);
			fileBlocks.add(fileBlock);
			id += (isFreeSpace ? 0 : 1);
			isFreeSpace = !isFreeSpace;
		}
		
		for(int i = 0; i < fileBlocks.size(); i++) {
			FileBlock fileBlock = fileBlocks.get(i);
			if (fileBlock.id != null)
				continue;
			
			for(int j = fileBlocks.size()-1; j > i; j--) {
				FileBlock endBlock = fileBlocks.get(j);
				if(endBlock.id == null)
					continue;
				int remainingFreeSpace = fileBlock.blockSpace - endBlock.blockSpace;
				
				if(remainingFreeSpace == 0) {
					fileBlock.id = endBlock.id;
					fileBlocks.remove(j);
				}
				else if(remainingFreeSpace > 0) {
					fileBlock.blockSpace = remainingFreeSpace;
					fileBlocks.add(i, endBlock);
					fileBlocks.remove(j+1);
				} else {
					fileBlock.id = endBlock.id;
					endBlock.blockSpace = Math.abs(remainingFreeSpace);
				}
				
				break;
			}
		}
		
		BigInteger checkSum = BigInteger.ZERO;
		BigInteger position = BigInteger.ZERO;
		
		for(int i = 0; i < fileBlocks.size(); i++) {
			FileBlock fileBlock = fileBlocks.get(i);
			if(fileBlock.id == null)
				break;
			for(int j = 0; j < fileBlock.blockSpace; j++) {
				checkSum = checkSum.add(position.multiply(new BigInteger(fileBlock.id + "")));
				position = position.add(BigInteger.ONE);
			}
		}
		
		return checkSum;
	}
	
	public static BigInteger part2() throws Exception {
		String input = Files.readString(Path.of("src/Day9/Day9Input.txt"));
		ArrayList<FileBlock> fileBlocks = new ArrayList<>();
		
		boolean isFreeSpace = false;
		int id = 0;
		for(char block : input.toCharArray()) {
			int num = Integer.parseInt(block + "");
			FileBlock fileBlock = new FileBlock(num, isFreeSpace ? null : id);
			fileBlocks.add(fileBlock);
			id += (isFreeSpace ? 0 : 1);
			isFreeSpace = !isFreeSpace;
		}
		
		
		for(int i = fileBlocks.size()-1; i > 0; i--) {
			FileBlock fileBlock = fileBlocks.get(i);
			if (fileBlock.id == null)
				continue;
			
			for(int j = 0; j < i; j++) {
				FileBlock startBlock = fileBlocks.get(j);
				if(startBlock.id != null)
					continue;
				int remainingFreeSpace = startBlock.blockSpace - fileBlock.blockSpace;
				
				if(remainingFreeSpace == 0) {
					startBlock.id = fileBlock.id;
					fileBlock.id = null;
				}
				else if(remainingFreeSpace > 0) {
					startBlock.blockSpace = remainingFreeSpace;
					fileBlocks.add(j, new FileBlock(fileBlock.blockSpace, fileBlock.id));
					fileBlock.id = null;
					
				} else {
					continue;
				}
				
				break;
			}
		}
		
		BigInteger checkSum = BigInteger.ZERO;
		BigInteger position = BigInteger.ZERO;
		
		for(int i = 0; i < fileBlocks.size(); i++) {
			FileBlock fileBlock = fileBlocks.get(i);
			for(int j = 0; j < fileBlock.blockSpace; j++) {
				if(fileBlock.id != null)
					checkSum = checkSum.add(position.multiply(new BigInteger(fileBlock.id + "")));
				position = position.add(BigInteger.ONE);
			}
		}
		
		return checkSum;
	}
}
