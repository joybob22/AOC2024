package Day12;

import java.util.ArrayList;

public class GardenPrice {
	public long area = 0;
	public long perimeter = 0;
	public Character flower = null;
	public ArrayList<GardenSide> sides = new ArrayList<>(); 
	
	public long getPrice() {
		return area * perimeter;
	}
	
	public long getDiscountedPrice() {
		return area * sides.size();
	}
	
	public void combine(GardenPrice p) {
		this.area += p.area;
		this.perimeter += p.perimeter;
		this.sides.addAll(p.sides);
	}
}
