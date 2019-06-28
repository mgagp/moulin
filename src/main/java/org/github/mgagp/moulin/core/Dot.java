package org.github.mgagp.moulin.core;

public class Dot {

	/** The owner of the dot */
	public final Player player;

	/** The node on which the dot is located on the board or null */
	public Node node;

	public Dot(Player player) {
		this.player = player;
	}

	public boolean canMove() {
		if (node == null) {
			return false;
		}
		if (node.right != null && node.right.dot == null) {
			return true;
		}
		if (node.down != null && node.down.dot == null) {
			return true;
		}
		if (node.left != null && node.left.dot == null) {
			return true;
		}
		if (node.up != null && node.up.dot == null) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Dot [player=" + player + ", node=" + node + "]";
	}

}
