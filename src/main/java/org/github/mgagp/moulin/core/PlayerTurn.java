package org.github.mgagp.moulin.core;

public class PlayerTurn {
	Node on;
	Node to;
	Node rm;
	Dot dotRm;

	@Override
	public String toString() {
		return "PlayerTurn [on=" + on + ", to=" + to + ", rm=" + rm + ", dotRm=" + dotRm + "]";
	}
}