package org.github.mgagp.moulin.core;

public class Board {

	public Node[] nodes = new Node[24];

	public Board() {
		for (int i = 0; i < 24; i++) {
			nodes[i] = new Node(i);
		}
		nodes[0].connect(nodes, 1, 9, null, null);
		nodes[1].connect(nodes, 2, 4, 0, null);
		nodes[2].connect(nodes, null, 14, 1, null);
		nodes[3].connect(nodes, 4, 10, null, null);
		nodes[4].connect(nodes, 5, 7, 3, 1);
		nodes[5].connect(nodes, null, 13, 4, null);
		nodes[6].connect(nodes, 7, 11, null, null);
		nodes[7].connect(nodes, 8, null, 6, 4);
		nodes[8].connect(nodes, null, 12, 7, null);
		nodes[9].connect(nodes, 10, 21, null, 0);
		nodes[10].connect(nodes, 11, 18, 9, 3);
		nodes[11].connect(nodes, null, 15, 10, 6);
		nodes[12].connect(nodes, 13, 17, null, 8);
		nodes[13].connect(nodes, 14, 20, 12, 5);
		nodes[14].connect(nodes, null, 23, 13, 2);
		nodes[15].connect(nodes, 16, null, null, 11);
		nodes[16].connect(nodes, 17, 19, 15, null);
		nodes[17].connect(nodes, null, null, 16, 12);
		nodes[18].connect(nodes, 19, null, null, 10);
		nodes[19].connect(nodes, 20, 22, 18, 16);
		nodes[20].connect(nodes, null, null, 19, 13);
		nodes[21].connect(nodes, 22, null, null, 9);
		nodes[22].connect(nodes, 23, null, 21, 19);
		nodes[23].connect(nodes, null, null, 22, 14);
	}

}
