package org.github.mgagp.moulin.core.event;

import org.github.mgagp.moulin.core.Dot;
import org.github.mgagp.moulin.core.Game;
import org.github.mgagp.moulin.core.Node;
import org.github.mgagp.moulin.core.Player;

public interface GameEventHandler {
	
	void forGame(Game game);
	
	Player whoGoesFirst(Player whitePlayer, Player blackPlayer);

	Dot moulinWhichDotToRemove(Player otherPlayer);

	void reportOtherPlayerHasNoDotThere();

	void reportOtherPlayerHasMoulinThere(Player opponent, Node node);

	void dotRemoved(Dot n);

	void refreshDisplayBoard();

	void promptPlayerTurn(Player player);

	Node inputPlaceForDot(Player player);

	void reportInvalidPlaceAlreadyOccupied(Node n);

	void dotRemovedFromHand(Dot dot);

	void reportMoulin(Node n);

	Node inputMoveFrom(Player player);

	void reportInvalidPlaceNoDot(Node n);

	void reportInvalidPlaceOccupiedByOtherPlayer(Node n);

	Node inputMoveTo(Player player);

	void reportCanMoveAnywhere();

	void reportInvalidPlaceNoConnection();

	void reportInvalidPlaceOccupied(Node n);

	void dotMovedFromTo(Node n, Node nn);

	void reportSwitchedToPlayer(Player player);

	void reportStatePlace();

	void reportStateMove();

	void startingGame(Player player);

	void dotPlaced(Dot dot);

	void reportWins(Player player);

	void reportAbandon(Player player);

	boolean end();

}
