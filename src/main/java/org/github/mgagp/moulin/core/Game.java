package org.github.mgagp.moulin.core;

import org.github.mgagp.moulin.core.event.GameEventHandler;

public class Game {

	public Board board;

	public Player whitePlayer;

	public BlackPlayer blackPlayer;

	public Player currentPlayer;

	private final GameEventHandler gameEventHandler;

	public Game(GameEventHandler handler) {
		gameEventHandler = handler;
		board = new Board();
		whitePlayer = new WhitePlayer(board);
		blackPlayer = new BlackPlayer(board);
		whitePlayer.opponent = blackPlayer;
		blackPlayer.opponent = whitePlayer;
	}

	public void play() {
		currentPlayer = gameEventHandler.whoGoesFirst(whitePlayer, blackPlayer);

		gameEventHandler.startingGame(currentPlayer);

		while (!end()) {
			playerTurn();
		}
	}

	public void playerTurn() {
		gameEventHandler.refreshDisplayBoard();
		gameEventHandler.promptPlayerTurn(currentPlayer);

		if (currentPlayer.whatsNext() == Phase.PLACE) {
			place();
		} else {
			move();
		}
	}

	private void place() {
		gameEventHandler.reportStatePlace();
		Node n = gameEventHandler.inputPlaceForDot(currentPlayer);

		if (n == null) {
			currentPlayer.abandon = true;
			return;
		}

		Dot dot = currentPlayer.hand.iterator().next();

		if (n.place(dot) == null) {
			gameEventHandler.reportInvalidPlaceAlreadyOccupied(n);
		} else {
			dot.node = n;
			currentPlayer.hand.remove(dot);
			currentPlayer.onboard.add(dot);

			gameEventHandler.dotRemovedFromHand(dot);
			gameEventHandler.dotPlaced(dot);

			if (n.moulin()) {
				gameEventHandler.reportMoulin(n);
				gameEventHandler.refreshDisplayBoard();
				moulinRemove();
			}

			gameEventHandler.reportSwitchedToPlayer(currentPlayer.opponent);
			currentPlayer = currentPlayer.opponent;
		}
	}

	private void move() {
		gameEventHandler.reportStateMove();
		Node n = gameEventHandler.inputMoveFrom(currentPlayer);

		if (n == null) {
			currentPlayer.abandon = true;
			return;
		}

		boolean valid = true;

		if (valid && n.dot == null) {
			gameEventHandler.reportInvalidPlaceNoDot(n);
			valid = false;
		}

		if (valid && n.dot.player != currentPlayer) {
			gameEventHandler.reportInvalidPlaceOccupiedByOtherPlayer(n);
			valid = false;
		}

		Node nn = null;

		if (valid) {
			nn = gameEventHandler.inputMoveTo(currentPlayer);
		}

		if (valid) {
			if (nn == null) {
				currentPlayer.abandon = true;
				return;
			}
		}

		if (valid) {
			if (currentPlayer.onboard.size() <= 3) {
				gameEventHandler.reportCanMoveAnywhere();
			} else {

				if (!n.around(nn)) {
					gameEventHandler.reportInvalidPlaceNoConnection();
					valid = false;
				}
			}
		}

		if (valid && nn.dot != null) {
			gameEventHandler.reportInvalidPlaceOccupied(nn);
			valid = false;
		}

		if (valid) {
			nn.dot = n.dot;
			nn.dot.node = nn;

			n.dot = null;

			gameEventHandler.dotMovedFromTo(n, nn);

			if (nn.moulin()) {
				gameEventHandler.reportMoulin(nn);
				gameEventHandler.refreshDisplayBoard();
				moulinRemove();
			}

			gameEventHandler.reportSwitchedToPlayer(currentPlayer.opponent);
			currentPlayer = currentPlayer.opponent;
		}
	}

	private void moulinRemove() {
		boolean removed = false;

		while (!removed) {
			Dot dot = gameEventHandler.moulinWhichDotToRemove(currentPlayer.opponent);
			if (dot == null || dot.player == null || dot.player == currentPlayer) {
				gameEventHandler.reportOtherPlayerHasNoDotThere();
			} else {
				if (dot.node.moulin()) {
					gameEventHandler.reportOtherPlayerHasMoulinThere(currentPlayer.opponent, dot.node);
				} else {
					dot.player.onboard.remove(dot);
					gameEventHandler.dotRemoved(dot);
					dot.node.dot = null;
					dot.node = null;
					removed = true;
				}
			}
		}
	}

	public boolean end() {
		if (currentPlayer.abandon) {
			gameEventHandler.reportAbandon(currentPlayer);
			gameEventHandler.refreshDisplayBoard();
			return true;
		}
		if (currentPlayer.hand.isEmpty() && currentPlayer.hand.isEmpty()) {
			if (currentPlayer.onboard.size() < 3) {
				gameEventHandler.reportWins(currentPlayer.opponent);
				gameEventHandler.refreshDisplayBoard();
				return true;
			}
			if (!currentPlayer.canMove()) {
				gameEventHandler.reportWins(currentPlayer.opponent);
				gameEventHandler.refreshDisplayBoard();
				return true;
			}
		}
		if (gameEventHandler.end()) {
			return true;
		}
		return false;
	}

	public String stateTextCompact() {
		StringBuilder s = new StringBuilder();
		s.append(currentPlayer.name.substring(0, 1));
		for (int i = 0; i < 24; i++) {
			if (board.nodes[i].dot == null) {
				s.append(".");
			} else {
				s.append(board.nodes[i].dot.player.name.substring(0, 1));
				// s.append(Integer.toString(i, 25));
			}
		}
		return s.toString();
	}

	public static Game fromStateTextCompact(String state, GameEventHandler gameEventHandler) {
		Game game = new Game(gameEventHandler);
		if (state.charAt(0) == 'W') {
			game.currentPlayer = game.whitePlayer;
		} else {
			game.currentPlayer = game.blackPlayer;
		}
		for (int i = 0; i < 24; i++) {
			if (state.charAt(i + 1) != '.') {
				Player player;
				if (state.charAt(i + 1) == 'W') {
					player = game.whitePlayer;
				} else {
					player = game.blackPlayer;
				}
				Dot dot = player.hand.iterator().next();
				player.hand.remove(dot);
				player.onboard.add(dot);
				game.board.nodes[i].dot = dot;
				dot.node = game.board.nodes[i];
			}
		}
		int n = Integer.parseInt(state.substring(25, 26));
		while (game.whitePlayer.hand.size() != n) {
			Dot dot = game.whitePlayer.hand.iterator().next();
			game.whitePlayer.hand.remove(dot);
		}
		n = Integer.parseInt(state.substring(26, 27));
		while (game.blackPlayer.hand.size() != n) {
			Dot dot = game.blackPlayer.hand.iterator().next();
			game.blackPlayer.hand.remove(dot);
		}
		return game;
	}
}
