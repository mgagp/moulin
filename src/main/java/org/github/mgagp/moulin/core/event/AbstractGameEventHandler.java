package org.github.mgagp.moulin.core.event;

import org.github.mgagp.moulin.core.Dot;
import org.github.mgagp.moulin.core.Game;
import org.github.mgagp.moulin.core.Node;
import org.github.mgagp.moulin.core.Player;

public abstract class AbstractGameEventHandler implements GameEventHandler {

	@Override
	abstract public void forGame(Game game);

	@Override
	abstract public Player whoGoesFirst(Player whitePlayer, Player blackPlayer);

	@Override
	abstract public void reportWins(Player player);

	@Override
	abstract public Dot moulinWhichDotToRemove(Player otherPlayer);

	@Override
	abstract public void reportOtherPlayerHasNoDotThere();

	@Override
	abstract public void reportOtherPlayerHasMoulinThere(Player opponent, Node node);

	@Override
	abstract public void dotRemoved(Dot n);

	@Override
	abstract public void refreshDisplayBoard();

	@Override
	abstract public void promptPlayerTurn(Player player);

	@Override
	abstract public Node inputPlaceForDot(Player player);

	@Override
	abstract public void reportInvalidPlaceAlreadyOccupied(Node n);

	@Override
	abstract public void dotRemovedFromHand(Dot dot);

	@Override
	abstract public void reportMoulin(Node n);

	@Override
	abstract public Node inputMoveFrom(Player player);

	@Override
	abstract public void reportInvalidPlaceNoDot(Node n);

	@Override
	abstract public void reportInvalidPlaceOccupiedByOtherPlayer(Node n);

	@Override
	abstract public Node inputMoveTo(Player player);

	@Override
	abstract public void reportCanMoveAnywhere();

	@Override
	abstract public void reportInvalidPlaceNoConnection();

	@Override
	abstract public void reportInvalidPlaceOccupied(Node nn);

	@Override
	abstract public void dotMovedFromTo(Node n, Node nn);

	@Override
	abstract public void reportSwitchedToPlayer(Player player);

	@Override
	abstract public void reportStatePlace();

	@Override
	abstract public void reportStateMove();

	@Override
	abstract public void startingGame(Player player);

	@Override
	abstract public void reportAbandon(Player player);

	@Override
	abstract public void dotPlaced(Dot dot);

	@Override
	abstract public boolean end();
}
