package org.github.mgagp.moulin.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Marc
 *
 */
public class Node {

	int number;

	Node left;

	Node right;

	Node up;

	Node down;

	List<Node> around = new ArrayList<>(4);

	public Dot dot;

	public Node(int number) {
		this.number = number;
	}

	void connect(Node[] nodes, Integer right, Integer down, Integer left, Integer up) {
		this.right = right == null ? null : nodes[right];
		this.down = down == null ? null : nodes[down];
		this.left = left == null ? null : nodes[left];
		this.up = up == null ? null : nodes[up];
		this.around.add(this.right);
		this.around.add(this.down);
		this.around.add(this.left);
		this.around.add(this.up);
	}

	public Dot place(Dot dot) {
		if (this.dot == null) {
			this.dot = dot;
			return dot;
		}
		return null;
	}

	public boolean around(Node node) {
		return node == left || node == right || node == down || node == up;
	}

	public boolean moulin() {
		boolean isMoulin = moulinRight(dot) || moulinDown(dot) || moulinLeft(dot) || moulinUp(dot) || moulinHorizontal(dot) || moulinVertical(dot);
		return isMoulin;
	}

	public boolean moulin(Dot dot) {
		boolean isMoulin = moulinRight(dot) || moulinDown(dot) || moulinLeft(dot) || moulinUp(dot) || moulinHorizontal(dot) || moulinVertical(dot);
		return isMoulin;
	}

	public boolean moulinRight(Dot dot) {
		if (dot == null)
			return false;
		if (right == null || right.dot == null || right.dot.player != dot.player)
			return false;
		if (right.right == null || right.right.dot == null || right.right.dot.player != dot.player)
			return false;
		if (this.dot == null && dot.node == right)
			return false;
		return true;
	}

	public boolean moulinDown(Dot dot) {
		if (dot == null)
			return false;
		if (down == null || down.dot == null || down.dot.player != dot.player)
			return false;
		if (down.down == null || down.down.dot == null || down.down.dot.player != dot.player)
			return false;
		if (this.dot == null && dot.node == down)
			return false;
		return true;
	}

	public boolean moulinLeft(Dot dot) {
		if (dot == null)
			return false;
		if (left == null || left.dot == null || left.dot.player != dot.player)
			return false;
		if (left.left == null || left.left.dot == null || left.left.dot.player != dot.player)
			return false;
		if (this.dot == null && dot.node == left)
			return false;
		return true;
	}

	public boolean moulinUp(Dot dot) {
		if (dot == null)
			return false;
		if (up == null || up.dot == null || up.dot.player != dot.player)
			return false;
		if (up.up == null || up.up.dot == null || up.up.dot.player != dot.player)
			return false;
		if (this.dot == null && dot.node == up)
			return false;
		return true;
	}

	public boolean moulinHorizontal(Dot dot) {
		if (dot == null)
			return false;
		if (right == null || right.dot == null || right.dot.player != dot.player)
			return false;
		if (left == null || left.dot == null || left.dot.player != dot.player)
			return false;
		if (this.dot == null) {
			if (dot.node == right || dot.node == left) {
				return false;
			}
		}
		return true;
	}

	public boolean moulinVertical(Dot dot) {
		if (dot == null)
			return false;
		if (up == null || up.dot == null || up.dot.player != dot.player)
			return false;
		if (down == null || down.dot == null || down.dot.player != dot.player)
			return false;
		if (this.dot == null) {
			if (dot.node == up || dot.node == down) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Node [number=" + Integer.toString(number, 24).toUpperCase() + "]";
	}

}
