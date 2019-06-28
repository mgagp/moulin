package org.github.mgagp.moulin.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player {

	public Board board;

	public String name;

	public List<Dot> dots;

	public Set<Dot> hand;

	public Set<Dot> onboard;

	public Player opponent;

	public boolean abandon;

	public Player(Board board) {
		this.board = board;
		dots = new ArrayList<>();
		hand = new HashSet<>();
		onboard = new HashSet<Dot>();
		for (int i = 0; i < 9; i++) {
			Dot dot = new Dot(this);
			dots.add(dot);
		}
		hand.addAll(dots);
		name = this.getClass().getSimpleName();
	}

	public Phase whatsNext() {
		if (hand.isEmpty()) {
			return Phase.MOVE;
		} else {
			return Phase.PLACE;
		}
	}

	public boolean canMove() {
		return onboard.stream().filter(dot -> dot.canMove()).findFirst().isPresent();
	}

	@Override
	public String toString() {
		return "Player [name=" + name + "]";
	}

}
