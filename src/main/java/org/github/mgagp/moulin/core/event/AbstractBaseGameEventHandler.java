package org.github.mgagp.moulin.core.event;

import org.github.mgagp.moulin.core.BlackPlayer;
import org.github.mgagp.moulin.core.Dot;
import org.github.mgagp.moulin.core.Game;
import org.github.mgagp.moulin.core.Node;
import org.github.mgagp.moulin.core.Player;

public abstract class AbstractBaseGameEventHandler extends AbstractGameEventHandler {

	@Override
	public void forGame(Game game) {
		
	}

	@Override
	public void reportAbandon(Player player) {

	}

	@Override
	public void dotPlaced(Dot dot) {

	}

	@Override
	public Player whoGoesFirst(Player whitePlayer, BlackPlayer blackPlayer) {
		return whitePlayer;
	}

	@Override
	public void reportWins(Player player) {

	}

	@Override
	public void reportOtherPlayerHasNoDotThere() {

	}

	@Override
	public void reportOtherPlayerHasMoulinThere(Player opponent, Node node) {

	}

	@Override
	public void dotRemoved(Dot n) {

	}

	@Override
	public void refreshDisplayBoard() {

	}

	@Override
	public void promptPlayerTurn(Player player) {

	}

	@Override
	public void reportInvalidPlaceAlreadyOccupied(Node n) {

	}

	@Override
	public void dotRemovedFromHand(Dot dot) {

	}

	@Override
	public void reportMoulin(Node n) {

	}

	@Override
	public void reportInvalidPlaceNoDot(Node n) {

	}

	@Override
	public void reportInvalidPlaceOccupiedByOtherPlayer(Node n) {

	}

	@Override
	public void reportCanMoveAnywhere() {

	}

	@Override
	public void reportInvalidPlaceNoConnection() {

	}

	@Override
	public void reportInvalidPlaceOccupied(Node nn) {

	}

	@Override
	public void dotMovedFromTo(Node n, Node nn) {

	}

	@Override
	public void reportSwitchedToPlayer(Player player) {

	}

	@Override
	public void reportStatePlace() {

	}

	@Override
	public void reportStateMove() {

	}

	@Override
	public void startingGame(Player player) {

	}
	
	@Override
	public boolean end() {
		return false;
	}
}
