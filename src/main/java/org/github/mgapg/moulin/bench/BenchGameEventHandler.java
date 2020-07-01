package org.github.mgapg.moulin.bench;

import org.github.mgagp.moulin.core.Dot;
import org.github.mgagp.moulin.core.Game;
import org.github.mgagp.moulin.core.Node;
import org.github.mgagp.moulin.core.Player;
import org.github.mgagp.moulin.core.event.AbstractBaseGameEventHandler;
import org.github.mgagp.moulin.core.event.GameEventHandler;
import org.github.mgagp.moulin.text.TextBoardDisplayer;

public class BenchGameEventHandler extends AbstractBaseGameEventHandler {

	public Game game;

	private final String[] moves;

	private int i = 0;

	private TextBoardDisplayer displayer;

	public BenchGameEventHandler(String[] moves) {
		this.moves = moves;
	}

	@Override
	public void forGame(Game game) {
		this.game = game;
	}

	public GameEventHandler withDisplay() {
		displayer = new TextBoardDisplayer();
		return this;
	}

	@Override
	public Player whoGoesFirst(Player whitePlayer, Player blackPlayer) {
		return moves[0].charAt(0) == 'W' ? whitePlayer : blackPlayer;
	}

	@Override
	public Dot moulinWhichDotToRemove(Player otherPlayer) {
		// WPKR1
		int ir = moves[i].indexOf('R');
		if (ir == -1) {
			throw new BenchGameException();
		}
		Integer n = Integer.valueOf(moves[i].substring(i, i + 1), 25);
		return otherPlayer.board.nodes[n].dot;
	}

	@Override
	public Node inputPlaceForDot(Player player) {
		if (moves[i].charAt(0) != player.name.charAt(0)) {
			throw new BenchGameException();
		}
		if (moves[i].charAt(1) != 'P') {
			throw new BenchGameException();
		}
		Integer n = Integer.valueOf(moves[i].substring(2, 3), 25);
		if (n.equals(24)) {
			return null;
		}
		return player.board.nodes[n];
	}

	@Override
	public Node inputMoveFrom(Player player) {
		// WM01
		if (moves[i].charAt(0) != player.name.charAt(0)) {
			throw new BenchGameException();
		}
		if (moves[i].charAt(1) != 'M') {
			throw new BenchGameException();
		}
		Integer n = Integer.valueOf(moves[i].substring(2, 3), 25);
		return player.board.nodes[n];
	}

	@Override
	public Node inputMoveTo(Player player) {
		// BM23
		if (moves[i].charAt(0) != player.name.charAt(0)) {
			throw new BenchGameException();
		}
		if (moves[i].charAt(1) != 'M') {
			throw new BenchGameException();
		}
		Integer ni = Integer.valueOf(moves[i].substring(3, 4), 25);
		return player.board.nodes[ni];
	}

	@Override
	public void reportSwitchedToPlayer(Player player) {
		i++;
		if (i == moves.length) {
			return;
		}
		if (moves[i].charAt(0) != player.name.charAt(0)) {
			throw new BenchGameException();
		}
	}

	@Override
	public void refreshDisplayBoard() {
		if (displayer != null) {
			displayer.display(game.board.nodes);
		}
	}

	@Override
	public boolean end() {
		return i == moves.length;
	}
}
