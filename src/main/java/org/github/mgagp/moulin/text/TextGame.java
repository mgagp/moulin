package org.github.mgagp.moulin.text;

import java.io.IOException;

import org.github.mgagp.moulin.core.Game;

public class TextGame {

	public static void main(String[] args) throws IOException {
		TextGameEventHandler handler = new TextGameEventHandler();
		Game game = new Game(handler);
		handler.game = game;
		game.play();
	}

}
