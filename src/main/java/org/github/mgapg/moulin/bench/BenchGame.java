package org.github.mgapg.moulin.bench;

import java.io.IOException;

import org.github.mgagp.moulin.core.Game;
import org.github.mgagp.moulin.core.event.GameEventHandler;

public class BenchGame {

	public static void main(String[] args) throws IOException {
		String[] moves = { "WPO", "BP1", "WP2" };
		BenchGame benchGame = new BenchGame();
		benchGame.play(moves, true);
	}

	public void play(String[] moves, boolean display) {
		GameEventHandler handler = new BenchGameEventHandler(moves).withDisplay();
		Game game = new Game(handler);
		handler.forGame(game);
		game.play();
	}
}
