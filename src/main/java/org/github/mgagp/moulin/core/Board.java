package org.github.mgagp.moulin.core;

/**
 * The board is like a square piece of wood.
 * <p>
 * The little spots on which player's dots may be placed are the nodes.
 * <p>
 * All nodes are represented as an array.
 * <p>
 * Each node is initialized by receiving to which other nodes it connected.
 * 
 * @author Marc
 *
 */
public class Board {

	public static final int A = 10;
	public static final int B = 11;
	public static final int C = 12;
	public static final int D = 13;
	public static final int E = 14;
	public static final int F = 15;
	public static final int G = 16;
	public static final int H = 17;
	public static final int I = 18;
	public static final int J = 19;
	public static final int K = 20;
	public static final int L = 21;
	public static final int M = 22;
	public static final int N = 23;
	
	public Node[] nodes = new Node[24];

	public Board() {
		
		for (int i = 0; i < 24; i++) {
			nodes[i] = new Node(i);
		}
		
		// The connect method receives which node is connected to the right, down, left and up
		
		// "0--------1--------2"
		// "|   3----4----5   |"
		// "|   |  6-7-8  |   |"
		// "9---A--B   C--D---E"
		// "|   |  F-G-H  |   |"
		// "|   I----J----K   |"
		// "L--------M--------N"		
		
		nodes[0].connect(nodes, 1, 9, null, null);
		nodes[1].connect(nodes, 2, 4, 0, null);
		nodes[2].connect(nodes, null, E, 1, null);
		
		nodes[3].connect(nodes, 4, A, null, null);
		nodes[4].connect(nodes, 5, 7, 3, 1);
		nodes[5].connect(nodes, null, D, 4, null);
		
		nodes[6].connect(nodes, 7, B, null, null);
		nodes[7].connect(nodes, 8, null, 6, 4);
		nodes[8].connect(nodes, null, C, 7, null);
		
		nodes[9].connect(nodes, A, L, null, 0);
		nodes[A].connect(nodes, B, I, 9, 3);
		nodes[B].connect(nodes, null, F, A, 6);
		
		nodes[C].connect(nodes, D, H, null, 8);
		nodes[D].connect(nodes, E, K, C, 5);
		nodes[E].connect(nodes, null, N, D, 2);
		
		nodes[F].connect(nodes, G, null, null, B);
		nodes[G].connect(nodes, H, J, F, null);
		nodes[H].connect(nodes, null, null, G, C);
		
		nodes[I].connect(nodes, J, null, null, A);
		nodes[J].connect(nodes, K, M, I, G);
		nodes[K].connect(nodes, null, null, J, D);
		
		nodes[L].connect(nodes, M, null, null, 9);
		nodes[M].connect(nodes, N, null, L, J); 
		nodes[N].connect(nodes, null, null, M, E);
	}

}
