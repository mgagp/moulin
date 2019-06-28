package org.marcg.moulin.bench;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.github.mgagp.moulin.core.Game;
import org.github.mgagp.moulin.core.PlayerTurn;
import org.github.mgagp.moulin.core.Turn;
import org.github.mgagp.moulin.core.event.GameEventHandler;
import org.github.mgapg.moulin.bench.BenchGameEventHandler;
import org.junit.jupiter.api.Test;

class BenchGameTest {

	@Test
	void testWhiteAbandonsOnFirstMove() {
		String[] moves = { "WPO" };
		BenchGameEventHandler handler = new BenchGameEventHandler(moves);
		Game game = new Game(handler);
		handler.game = game;
		game.play();
		assertTrue(game.whitePlayer.abandon);
	}

	@Test
	void testWhiteMoulinBlackAbandons() {
		String[] moves = { "WP0", "BP3", "WP1", "BP4", "WP2R3", "BPO" };
		BenchGameEventHandler handler = new BenchGameEventHandler(moves);
		handler.withDisplay();
		Game game = new Game(handler);
		handler.game = game;
		game.play();
		assertTrue(game.blackPlayer.abandon);
		String state = game.stateTextCompact();
		System.out.println(state);
	}

	@Test
	void push1() {
		String[] moves = {};
		GameEventHandler handler = new BenchGameEventHandler(moves).withDisplay();
		Game game = new Game(handler);
		handler.forGame(game);
		Turn turn = new Turn();
		turn.push(game.whitePlayer, game.board.nodes[0]);
		handler.refreshDisplayBoard();
		turn.pop();
	}

	@Test
	void push2() {
		String[] moves = { "WP0", "BP3", "WP1", "BP4", };
		GameEventHandler handler = new BenchGameEventHandler(moves).withDisplay();
		Game game = new Game(handler);
		handler.forGame(game);
		game.play();
		handler.refreshDisplayBoard();
		Turn turn = new Turn();
		// turn.push(game.whitePlayer, game.board.nodes[2], game.board.nodes[3]);
		turn.push(game, "WP2R3");
		handler.refreshDisplayBoard();
		turn.pop();
		handler.refreshDisplayBoard();
	}

	@Test
	void gameFromState1() {
		String state = "W..WWWWBBBBBBWWWBB....WBW";
		BenchGameEventHandler gameEventHandler = new BenchGameEventHandler(null);
		Game game = Game.fromStateTextCompact(state, gameEventHandler);
		gameEventHandler.forGame(game);
		gameEventHandler.withDisplay();
		gameEventHandler.refreshDisplayBoard();
		Turn turn = new Turn();
		List<PlayerTurn> validMoves = turn.validMoves(game);
		assertNotNull(validMoves);
		assertEquals(4, validMoves.size());
	}

	@Test
	void generateTurns1() {
		String state = "WW.WWWWBBBBBBWWWBB....WB.";
		BenchGameEventHandler gameEventHandler = new BenchGameEventHandler(null);
		Game game = Game.fromStateTextCompact(state, gameEventHandler);
		gameEventHandler.forGame(game);
		gameEventHandler.withDisplay();
		gameEventHandler.refreshDisplayBoard();
		Turn turn = new Turn();
		List<PlayerTurn> validMoves = turn.validMoves(game);
		assertNotNull(validMoves);
		assertEquals(7, validMoves.size());
		assertEquals("PlayerTurn [on=Node [number=0], to=Node [number=1], rm=null, dotRm=null]", validMoves.get(0).toString());
		assertEquals("PlayerTurn [on=Node [number=2], to=Node [number=1], rm=null, dotRm=null]", validMoves.get(1).toString());
		assertEquals(
				"PlayerTurn [on=Node [number=4], to=Node [number=1], rm=Node [number=G], dotRm=Dot [player=Player [name=BlackPlayer], node=Node [number=G]]]",
				validMoves.get(2).toString());
		assertEquals(
				"PlayerTurn [on=Node [number=4], to=Node [number=1], rm=Node [number=M], dotRm=Dot [player=Player [name=BlackPlayer], node=Node [number=M]]]",
				validMoves.get(3).toString());
		assertEquals("PlayerTurn [on=Node [number=C], to=Node [number=H], rm=null, dotRm=null]", validMoves.get(4).toString());
		assertEquals("PlayerTurn [on=Node [number=D], to=Node [number=K], rm=null, dotRm=null]", validMoves.get(5).toString());
		assertEquals("PlayerTurn [on=Node [number=E], to=Node [number=N], rm=null, dotRm=null]", validMoves.get(6).toString());
	}
}
