package Day11;

import java.util.ArrayList;
import java.util.HashMap;

public class StoneSequence {
	public static HashMap<Long, StoneSequence> sequences = new HashMap<>();
	
	HashMap<Integer, Long> numStones = new HashMap<>();
	ArrayList<StoneSequence> referenceSequences = new ArrayList<>();
	Long num;
	
	public StoneSequence(Long num) {
		this.num = num;
		this.numStones.put(0, (long) 1);
		sequences.put(num, this);
	}
	
	public Long getStonesAt(int numBlinks) {
		if(numStones.containsKey(numBlinks))
			return numStones.get(numBlinks);
		
		Long stones = (long) 0;
		
		if (referenceSequences.size() == 0) {
			if(num == 0) {
				if(!sequences.containsKey((long) 1)) {
					StoneSequence sequence = new StoneSequence((long) 1);
					sequences.put((long) 1, sequence);
					referenceSequences.add(sequence);
				} else {
					referenceSequences.add(sequences.get(((long) 1)));
				}
			} else {
				String strStone = num + "";
				int numDigits = strStone.length();
				
				if(numDigits % 2 == 0) {
					Long newStone1 = (long) Integer.parseInt(strStone.substring(0, numDigits / 2)); 
					Long newStone2 = (long) Integer.parseInt(strStone.substring(numDigits / 2, numDigits));
					if(sequences.containsKey(newStone1)) {
						referenceSequences.add(sequences.get(newStone1));
					} else {
						StoneSequence sequence1 = new StoneSequence((long) newStone1);
						sequences.put((long) newStone1, sequence1);
						referenceSequences.add(sequence1);						
					}
					
					if(sequences.containsKey(newStone2)) {
						referenceSequences.add(sequences.get(newStone2));
					} else {
						StoneSequence sequence2 = new StoneSequence((long) newStone2);
						sequences.put((long) newStone2, sequence2);
						referenceSequences.add(sequence2);						
					}
				} else {
					StoneSequence sequence1 = new StoneSequence((long) num * 2024);
					sequences.put((long) num * 2024, sequence1);
					referenceSequences.add(sequence1);
				}
			}
		}
		
		for(StoneSequence refSeq : referenceSequences) {
			stones += refSeq.getStonesAt(numBlinks-1);
		}
		numStones.put(numBlinks, stones);
		return stones;
	}
}
