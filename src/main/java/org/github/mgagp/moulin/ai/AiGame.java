package org.github.mgagp.moulin.ai;

import java.io.IOException;

public class AiGame {

	public static void main(String[] args) throws IOException {
		String[] moves = { "WPO", "BP1", "WP2" };
		AiGame benchGame = new AiGame();
		benchGame.play(moves, true);
	}

	public void play(String[] moves, boolean display) {
	}
}
