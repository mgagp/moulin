package org.github.mgagp.moulin.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Turn {

	public Stack<PlayerTurn> turnStack = new Stack<>();

	public void push(Game game, String action) {
		Player player = action.charAt(0) == 'W' ? game.whitePlayer : game.blackPlayer;
		Integer onIdx = Integer.valueOf(action.substring(2, 3), 24);
		Node on = game.board.nodes[onIdx];
		Node to = null;
		if (action.substring(1, 2).equals("M")) {
			Integer toIdx = Integer.valueOf(action.substring(3, 4), 24);
			to = game.board.nodes[toIdx];
		}
		Node rm = null;
		int ir = action.indexOf('R');
		if (ir != -1) {
			Integer rmIdx = Integer.valueOf(action.substring(ir + 1, ir + 2), 25);
			rm = game.board.nodes[rmIdx];
		}
		if (to == null) {
			if (rm == null) {
				push(player, on);
			} else {
				push(player, on, rm);
			}
		} else {
			if (rm == null) {
				push(player, on.dot, to);
			} else {
				push(player, on.dot, to, rm);
			}
		}
	}

	public void push(Player player, Node on) {
		PlayerTurn turn = new PlayerTurn();
		turn.on = on;
		turnStack.push(turn);
		Dot dot = player.hand.iterator().next();
		dot.node = on;
		dot.node.dot = dot;
		player.hand.remove(dot);
		player.onboard.add(dot);
	}

	public void push(Player player, Node on, Node rm) {
		PlayerTurn turn = new PlayerTurn();
		turn.on = on;
		turn.rm = rm;
		turn.dotRm = rm.dot;
		turnStack.push(turn);
		Dot dot = player.hand.iterator().next();
		dot.node = on;
		dot.node.dot = dot;
		player.hand.remove(dot);
		player.onboard.add(dot);
		rm.dot.player.onboard.remove(rm.dot);
		rm.dot.node = null;
		rm.dot = null;
	}

	public void push(Player player, Dot dot, Node to) {
		PlayerTurn turn = new PlayerTurn();
		turn.on = dot.node;
		turn.to = to;
		turnStack.push(turn);
		dot.node = to;
		to.dot = dot;
	}

	public void push(Player player, Dot dot, Node to, Node rm) {
		PlayerTurn turn = new PlayerTurn();
		turn.on = dot.node;
		turn.to = to;
		turn.rm = rm;
		turn.dotRm = rm.dot;
		turnStack.push(turn);
		dot.node = to;
		to.dot = dot;
		rm.dot.player.onboard.remove(rm.dot);
		rm.dot.node = null;
		rm.dot = null;
	}

	public void pop() {
		PlayerTurn turn = turnStack.pop();
		if (turn.to == null) {
			Dot dot = turn.on.dot;
			dot.player.onboard.remove(dot);
			dot.player.hand.add(dot);
			turn.on.dot = null;
			dot.node = null;
		} else {
			Dot dot = turn.to.dot;
			turn.on.dot = dot;
			dot.node = turn.on;
			turn.to.dot = null;
		}
		if (turn.rm != null) {
			Dot dot = turn.dotRm;
			dot.player.onboard.add(dot);
			dot.node = turn.rm;
			turn.rm.dot = dot;
		}
	}

	public List<PlayerTurn> validMoves(Game game) {
		Player player = game.currentPlayer;
		List<PlayerTurn> turns = new ArrayList<>();
		if (!player.hand.isEmpty()) {
			Dot dot = player.dots.iterator().next();
			List<Node> nodes = Arrays.asList(game.board.nodes).stream().filter(n -> n.dot == null).collect(Collectors.toList());
			for (Node node : nodes) {
				if (node.moulin(dot)) {
					List<Node> opponentNodes = player.opponent.onboard.stream().filter(d -> !d.node.moulin()).map(d -> d.node).collect(Collectors.toList());
					for (Node opponentNode : opponentNodes) {
						PlayerTurn t = new PlayerTurn();
						t.on = node;
						t.rm = opponentNode;
						t.dotRm = opponentNode.dot;
						turns.add(t);
					}
				} else {
					PlayerTurn t = new PlayerTurn();
					t.on = node;
					turns.add(t);
				}
			}
		} else {
			if (player.onboard.size() <= 3) {
				List<Node> nodes = player.onboard.stream().map(d -> d.node).collect(Collectors.toList());
				for (Node node : nodes) {
					List<Node> toNodes = Arrays.asList(game.board.nodes).stream().filter(n -> n.dot == null).collect(Collectors.toList());
					for (Node toNode : toNodes) {
						validMoves(node, toNode, turns);
					}
				}
			} else {
				List<Node> nodes = player.onboard.stream().filter(d -> d.canMove()).map(d -> d.node).collect(Collectors.toList());
				for (Node node : nodes) {
					if (node.right != null && node.right.dot == null) {
						validMoves(node, node.right, turns);
					}
					if (node.down != null && node.down.dot == null) {
						validMoves(node, node.down, turns);
					}
					if (node.left != null && node.left.dot == null) {
						validMoves(node, node.left, turns);
					}
					if (node.up != null && node.up.dot == null) {
						validMoves(node, node.up, turns);
					}
				}
			}
		}
		return turns;
	}

	private void validMoves(Node on, Node to, List<PlayerTurn> turns) {
		Dot dot = on.dot;
		Player player = dot.player;
		if (to.moulin(dot)) {
			List<Node> opponentNodes;
			if (player.opponent.onboard.size() <= 3) {
				opponentNodes = player.opponent.onboard.stream().map(d -> d.node).collect(Collectors.toList());
			} else {
				opponentNodes = player.opponent.onboard.stream().filter(d -> !d.node.moulin()).map(d -> d.node).collect(Collectors.toList());
			}
			for (Node opponentNode : opponentNodes) {
				PlayerTurn t = new PlayerTurn();
				t.on = on;
				t.to = to;
				t.rm = opponentNode;
				t.dotRm = opponentNode.dot;
				turns.add(t);
			}
		} else {
			PlayerTurn t = new PlayerTurn();
			t.on = on;
			t.to = to;
			turns.add(t);
		}
	}
}
