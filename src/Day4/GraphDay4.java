package Day4;

import java.util.ArrayList;
import java.util.HashMap;

// This ended up not really being a graph lol
public class GraphDay4 {
	public HashMap<String, NodeDay4> nodes;
	public NodeDay4[][] nodesArr = null;
	
	public void initializeGraph(Character[][] arr) {
		NodeDay4[][] nodeArr = new NodeDay4[arr.length][arr[0].length];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				NodeDay4 node = new NodeDay4();
				node.letter = arr[i][j];
				nodeArr[i][j] = node;
			}
		}
		
		for(int i = 0; i < nodeArr.length; i++) {
			for(int j = 0; j < nodeArr[i].length; j++) {
				NodeDay4 node = nodeArr[i][j];
				if (i > 0)
					node.left = nodeArr[i-1][j];
				if (i < nodeArr.length-1)
					node.right = nodeArr[i+1][j];
				if (j > 0)
					node.top = nodeArr[i][j-1];
				if (j < nodeArr[i].length-1)
					node.bottom = nodeArr[i][j+1];
				if (i > 0 && j > 0)
					node.topLeft = nodeArr[i-1][j-1];
				if(i > 0 && j < nodeArr[i].length-1)
					node.topRight = nodeArr[i-1][j+1];
				if(i < nodeArr.length-1 && j > 0)
					node.bottomLeft = nodeArr[i+1][j-1];
				if(i < nodeArr.length-1 && j < nodeArr[i].length-1)
					node.bottomRight = nodeArr[i+1][j+1];
			}
		}
		this.nodesArr = nodeArr;
	}
}
