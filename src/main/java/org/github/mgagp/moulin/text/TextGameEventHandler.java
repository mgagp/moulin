package org.github.mgagp.moulin.text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.github.mgagp.moulin.core.Dot;
import org.github.mgagp.moulin.core.Game;
import org.github.mgagp.moulin.core.Node;
import org.github.mgagp.moulin.core.Player;
import org.github.mgagp.moulin.core.event.AbstractBaseGameEventHandler;

public class TextGameEventHandler extends AbstractBaseGameEventHandler {

	Game game;

	private TextBoardDisplayer displayer;

	public TextGameEventHandler() {
		displayer = new TextBoardDisplayer();
	}

	@Override
	public void reportWins(Player player) {
		System.out.println("End of game, " + player.name + " win's opponent has less than 3 dots");
	}

	@Override
	public void reportSwitchedToPlayer(Player player) {
		System.out.println(player.name + "' turn");
	}

	@Override
	public void reportAbandon(Player player) {
		System.out.println("Abandon - " + player.name);
	}

	@Override
	public void startingGame(Player player) {
		System.out.println("Starting game with " + player.name);
	}

	@Override
	public Dot moulinWhichDotToRemove(Player otherPlayer) {
		int n = promptNo("Moulin ! Remove other player's dot node ? ");
		return otherPlayer.board.nodes[n].dot;
	}

	@Override
	public void reportOtherPlayerHasNoDotThere() {
		System.out.println("Other player has no dot there... select another place.");
	}

	@Override
	public void reportOtherPlayerHasMoulinThere(Player opponent, Node node) {
		System.out.println("Other player has a moulin there... select another place.");
	}

	@Override
	public void refreshDisplayBoard() {
		displayer.display(game.board.nodes);
	}

	@Override
	public void promptPlayerTurn(Player player) {
		System.out.println(player.name + "'s turn");
	}

	@Override
	public Node inputPlaceForDot(Player player) {
        String prompt = "Place dot on node ? (" + player.hand.size() + " left) ";
		int n = promptNo(prompt);
		if (n == 24) {
			player.abandon = true;
			return null;
		}
		return player.board.nodes[n];
	}

	@Override
	public void reportInvalidPlaceAlreadyOccupied(Node n) {
		System.out.println("There is already a dot there... select another place.");
	}

	@Override
	public Node inputMoveFrom(Player player) {
		int n = promptNo("Move dot from node ? ");
		if (n == 24) {
			player.abandon = true;
			return null;
		}
		return player.board.nodes[n];
	}

	@Override
	public Node inputMoveTo(Player player) {
		int nn = promptNo("Move dot to node ? ");
		if (nn == 24) {
			player.abandon = true;
			return null;
		}
		return player.board.nodes[nn];
	}

	@Override
	public void reportInvalidPlaceNoDot(Node n) {
		System.out.println("There no dot there... select another place.");
	}

	@Override
	public void reportInvalidPlaceOccupiedByOtherPlayer(Node n) {
		System.out.println("This dot is not yours... select another place.");
	}

	@Override
	public void reportCanMoveAnywhere() {
		System.out.println("Moving anywhere!");
	}

	@Override
	public void reportInvalidPlaceNoConnection() {
		System.out.println("The destination must connect... select another place.");
	}

	@Override
	public void reportInvalidPlaceOccupied(Node nn) {
		System.out.println("The destination has a dot... select another place.");
	}

	@Override
	public void reportStatePlace() {
		System.out.println("---------- Place");
	}

	@Override
	public void reportStateMove() {
		System.out.println("---------- Move");
	}

	private int promptNo(String prompt) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.print(prompt);
			try {
				String line = br.readLine();
				if (line != null && !line.strip().equals("")) {
					int i = Integer.parseInt(line, 0, 1, 25);
					if (i >= 0 && i <= 24)
						return i;
					else
						System.out.println("Invalid input.");
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid input.");
			} catch (IOException e) {
				System.out.println("Invalid input.");
			}
		}
	}

}
