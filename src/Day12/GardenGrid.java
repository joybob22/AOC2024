package Day12;

import java.util.ArrayList;

import Day6.Day6Node.Direction;

public class GardenGrid {
	public GardenNode[][] nodesArr = null;
	public ArrayList<GardenPrice> prices = new ArrayList<>();
	
	public void initializeGrid(Character[][] arr) {
		GardenNode[][] nodeArr = new GardenNode[arr.length][arr[0].length];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				GardenNode node = new GardenNode(arr[i][j], i, j);
				nodeArr[i][j] = node;
			}
		}
		
		for(int i = 0; i < nodeArr.length; i++) {
			for(int j = 0; j < nodeArr[i].length; j++) {
				GardenNode node = nodeArr[i][j];
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
	
	public long calculateTotalGardenPrice() {
		for(int i = 0; i < nodesArr.length; i++) {
			for(int j = 0; j < nodesArr[i].length; j++) {
				GardenNode node = nodesArr[i][j];
				if (!node.visited) {
					GardenPrice price = calcPriceFor(node);
					price.flower = node.flower;
					prices.add(price);
				}
			}
		}
		
		return prices.stream().map(p -> p.getPrice()).reduce(0L, (a,b) -> a+b);
	}
	
	
	public GardenPrice calcPriceFor(GardenNode node) {
		node.visited = true;
		
		GardenPrice price = new GardenPrice();
		price.area = 1;
		price.perimeter = 0;
		
		if(node.top != null && node.top.visited == false && node.top.flower == node.flower) {
			price.combine(calcPriceFor(node.top));
		} else if(node.top == null || node.top.flower != node.flower) {
			price.perimeter++;
		}
		
		if(node.right != null && node.right.visited == false && node.right.flower == node.flower) {
			price.combine(calcPriceFor(node.right));
		} else if(node.right == null || node.right.flower != node.flower) {
			price.perimeter++;
		}
		
		if(node.bottom != null && node.bottom.visited == false && node.bottom.flower == node.flower) {
			price.combine(calcPriceFor(node.bottom));
		} else if(node.bottom == null || node.bottom.flower != node.flower) {
			price.perimeter++;
		}
		
		if(node.left != null && node.left.visited == false && node.left.flower == node.flower) {
			price.combine(calcPriceFor(node.left));
		} else if(node.left == null || node.left.flower != node.flower) {
			price.perimeter++;
		}
		
		return price;
	}
	
	public long calculateTotalGardenPriceDiscounted() {
		for(int i = 0; i < nodesArr.length; i++) {
			for(int j = 0; j < nodesArr[i].length; j++) {
				GardenNode node = nodesArr[i][j];
				if (!node.visited) {
					GardenPrice price = calcDiscountedPriceFor(node);
					price.flower = node.flower;
					prices.add(price);
				}
			}
		}
		
		return prices.stream().map(p -> p.getDiscountedPrice()).reduce(0L, (a,b) -> a+b);
	}
	
	public GardenPrice calcDiscountedPriceFor(GardenNode node) {
		node.visited = true;
		
		GardenPrice price = new GardenPrice();
		price.area = 1;
		
		if(node.top != null && node.top.visited == false && node.top.flower == node.flower) {
			price.combine(calcDiscountedPriceFor(node.top));
		} else if((node.top == null || node.top.flower != node.flower) && node.topSideVisited == false) {
			// Calc Top Side
			node.topSideVisited = true;
			GardenSide side = new GardenSide(node.row, node.row, node.column, node.column);
			findFullSide(node, side, Direction.TOP);
			price.sides.add(side);
		}
		
		if(node.right != null && node.right.visited == false && node.right.flower == node.flower) {
			price.combine(calcDiscountedPriceFor(node.right));
		} else if((node.right == null || node.right.flower != node.flower) && node.rightSideVisited == false) {
			// Calc Right Side
			node.rightSideVisited = true;
			GardenSide side = new GardenSide(node.row, node.row, node.column+1, node.column+1);
			findFullSide(node, side, Direction.RIGHT);
			price.sides.add(side);
		}
		
		if(node.bottom != null && node.bottom.visited == false && node.bottom.flower == node.flower) {
			price.combine(calcDiscountedPriceFor(node.bottom));
		} else if((node.bottom == null || node.bottom.flower != node.flower) && node.bottomSideVisited == false) {
			// Calc Bottom Side
			node.bottomSideVisited = true;
			GardenSide side = new GardenSide(node.row+1, node.row+1, node.column, node.column);
			findFullSide(node, side, Direction.BOTTOM);
			price.sides.add(side);
		}
		
		if(node.left != null && node.left.visited == false && node.left.flower == node.flower) {
			price.combine(calcDiscountedPriceFor(node.left));
		} else if((node.left == null || node.left.flower != node.flower) && node.leftSideVisited == false) {
			// Calc Left Side
			node.leftSideVisited = true;
			GardenSide side = new GardenSide(node.row, node.row, node.column, node.column);
			findFullSide(node, side, Direction.LEFT);
			price.sides.add(side);
		}
		
		return price;
	}
	
	public void findFullSide(GardenNode node, GardenSide side, Direction direction) {
		switch (direction) {
		case BOTTOM:
			if((node.left != null && node.left.flower == node.flower) && node.left.bottomSideVisited == false && (node.left.bottom == null || node.left.bottom.flower != node.flower)) {
				side.columnStart--;
				node.left.bottomSideVisited = true;
				findFullSide(node.left, side, direction);
			}
			if((node.right != null && node.right.flower == node.flower) && node.right.bottomSideVisited == false && (node.right.bottom == null || node.right.bottom.flower != node.flower)) {
				side.columnEnd++;
				node.right.bottomSideVisited = true;
				findFullSide(node.right, side, direction);
			}
			break;
		case LEFT:
			if((node.top != null && node.top.flower == node.flower) && node.top.leftSideVisited == false && (node.top.left == null || node.top.left.flower != node.flower)) {
				side.rowStart--;
				node.top.leftSideVisited = true;
				findFullSide(node.top, side, direction);
			}
			if((node.bottom != null && node.bottom.flower == node.flower) && node.bottom.leftSideVisited == false && (node.bottom.left == null || node.bottom.left.flower != node.flower)) {
				side.rowEnd++;
				node.bottom.leftSideVisited = true;
				findFullSide(node.bottom, side, direction);
			}
			break;
		case RIGHT:
			if((node.top != null && node.top.flower == node.flower) && node.top.rightSideVisited == false && (node.top.right == null || node.top.right.flower != node.flower)) {
				side.rowStart--;
				node.top.rightSideVisited = true;
				findFullSide(node.top, side, direction);
			}
			if((node.bottom != null && node.bottom.flower == node.flower) && node.bottom.rightSideVisited == false && (node.bottom.right == null || node.bottom.right.flower != node.flower)) {
				side.rowEnd++;
				node.bottom.rightSideVisited = true;
				findFullSide(node.bottom, side, direction);
			}
			break;
		case TOP:
			if((node.left != null && node.left.flower == node.flower) && node.left.topSideVisited == false && (node.left.top == null || node.left.top.flower != node.flower)) {
				side.columnStart--;
				node.left.topSideVisited = true;
				findFullSide(node.left, side, direction);
			}
			if((node.right != null && node.right.flower == node.flower) && node.right.topSideVisited == false && (node.right.top == null || node.right.top.flower != node.flower)) {
				side.columnEnd++;
				node.right.topSideVisited = true;
				findFullSide(node.right, side, direction);
			}
			break;
		}
	}
}
