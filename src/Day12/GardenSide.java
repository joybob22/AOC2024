package Day12;

public class GardenSide {
	int rowStart;
	int rowEnd;
	int columnStart;
	int columnEnd;
	
	public GardenSide(int rowStart, int rowEnd, int columnStart, int columnEnd) {
		this.rowStart = rowStart;
		this.rowEnd = rowEnd;
		this.columnStart = columnStart;
		this.columnEnd = columnEnd;
	}
	
	public int getSideDistance() {
		return rowStart - rowEnd != 0 ? Math.abs(rowStart - rowEnd) : Math.abs(columnStart - columnEnd);
	}
	
	public boolean isPartOfRange(int row, int column) {
		if(columnStart - columnEnd == 0 && column == this.columnStart) {
			return (row > rowStart && row < rowEnd) || (row < rowStart && row > rowEnd);
		}
		
		if(rowStart - rowEnd == 0 && row == this.rowStart) {
			return (column > columnStart && column < columnEnd) || (column < columnStart && column > columnEnd);
		}
		
		return false;
	}
}
