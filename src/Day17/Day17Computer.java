package Day17;

import java.util.Arrays;

public class Day17Computer {
	long registerA;
	long registerB;
	long registerC;
	String program;
	
	public Day17Computer(long registerA, long registerB, long registerC, String program) {
		this.registerA = registerA;
		this.registerB = registerB;
		this.registerC = registerC;
		this.program = program;
	}
	
	public String runProgram() throws Exception {
		String output = "";
		int pointer = 0;
		int[] bits = Arrays.stream(program.split(",")).mapToInt(Integer::parseInt).toArray();
		
		while(pointer < bits.length-1) {
			int opcode = bits[pointer];
			int operand = bits[pointer+1];
			
			if(opcode == 0)
				adv(getComboOperand(operand));
			else if(opcode == 1)
				bxl(operand);
			else if(opcode == 2)
				bst(getComboOperand(operand));
			else if(opcode == 3 && registerA != 0) {
				pointer = operand;
				continue;
			}
			else if(opcode == 4)
				bxc();
			else if(opcode == 5) {
				output += (getComboOperand(operand) % 8) + ",";
			}
			else if(opcode == 6)
				bdv(getComboOperand(operand));
			else if(opcode == 7)
				cdv(getComboOperand(operand));
			
			pointer += 2;
		}
		
		
		return output.substring(0, output.length()-1);
	}
	
	private long getComboOperand(int n) throws Exception {
		if (n >= 0 && n <= 3) {
			return n;
		}
		
		if(n == 4)
			return registerA;
		if(n == 5)
			return registerB;
		if(n == 6)
			return registerC;
		
		throw new Exception("Received invalid combo operand " + n);
	}
	
	private void adv(long n) {
		registerA = (long) (registerA / (Math.pow(2, n)));
	}
	
	private void bxl(long n) {
		registerB = registerB ^ n;
	}
	
	private void bst(long n) {
		registerB = n % 8;
	}
	
	private void bxc() {
		registerB = registerB ^ registerC;
	}
	
	private void bdv(long n) {
		registerB = (long) (registerA / (Math.pow(2, n)));
	}
	
	private void cdv(long n) {
		registerC = (long) (registerA / (Math.pow(2, n)));
	}
}
