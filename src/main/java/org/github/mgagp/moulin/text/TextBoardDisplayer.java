package org.github.mgagp.moulin.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.github.mgagp.moulin.core.Node;

public class TextBoardDisplayer {

	public boolean displayRule = true;

	String line1Game = "%c--------%c--------%c";
	String line1Rule = "0--------1--------2";

	String line2Game = "|        |        |";

	String line3Game = "|   %c----%c----%c   |";
	String line3Rule = "|   3----4----5   |";

	String line4Game = "|   |    |    |   |";

	String line5Game = "|   |  %c-%c-%c  |   |";
	String line5Rule = "|   |  6-7-8  |   |";

	String line6Game = "|   |  |   |  |   |";

	String line7Game = "%c---%c--%c   %c--%c---%c";
	String line7Rule = "9---A--B   C--D---E";

	String line8Game = "|   |  |   |  |   |";

	String line9Game = "|   |  %c-%c-%c  |   |";
	String line9Rule = "|   |  F-G-H  |   |";

	String line10Game = "|   |    |    |   |";

	String line11Game = "|   %c----%c----%c   |";
	String line11Rule = "|   I----J----K   |";

	String line12Game = "|        |        |";

	String line13Game = "%c--------%c--------%c";
	String line13Rule = "L--------M--------N";

	List<String> lines = new ArrayList<>(13);

	public TextBoardDisplayer() {
		init();
	}

	public void init() {
		lines.addAll(Arrays.asList(line1Game, line1Rule, line2Game, line2Game, line3Game, line3Rule, line4Game,
				line4Game, line5Game, line5Rule, line6Game, line6Game, line7Game, line7Rule, line8Game, line8Game,
				line9Game, line9Rule, line10Game, line10Game, line11Game, line11Rule, line12Game, line12Game,
				line13Game, line13Rule));
	}

	public void display(Node[] nodes) {
		int n = 0;
		for (int i = 0; i < 13; i++) {
			if (i % 2 == 0) {
				if (i == 6) {
					System.out.printf(lines.get(i * 2), displayNodeChar(nodes[n + 0]), displayNodeChar(nodes[n + 1]),
							displayNodeChar(nodes[n + 2]), displayNodeChar(nodes[n + 3]), displayNodeChar(nodes[n + 4]),
							displayNodeChar(nodes[n + 5]));
					n += 3;
				} else {
					System.out.printf(lines.get(i * 2), displayNodeChar(nodes[n + 0]), displayNodeChar(nodes[n + 1]),
							displayNodeChar(nodes[n + 2]));
				}
				n += 3;
			} else {
				System.out.printf(lines.get(i * 2));

			}
			if (displayRule) {
				System.out.printf("   %s", lines.get(i * 2 + 1));
			}
			System.out.printf("\n");
		}
	}

	public void display1(Node[] nodes) {
		System.out.printf("%c--------%c--------%c   0--------1--------2\n", //
				displayNodeChar(nodes[0]), displayNodeChar(nodes[1]), displayNodeChar(nodes[2]));

		System.out.printf("|        |        |\n");

		System.out.printf("|   %c----%c----%c   |   |   3----4----5   |\n", //
				displayNodeChar(nodes[3]), displayNodeChar(nodes[4]), displayNodeChar(nodes[5]));

		System.out.printf("|   |    |    |   |\n");

		System.out.printf("|   |  %c-%c-%c  |   |   |   |  6-7-8  |   |\n", //
				displayNodeChar(nodes[6]), displayNodeChar(nodes[7]), displayNodeChar(nodes[8]));

		System.out.printf("|   |  |   |  |   |\n");

		System.out.printf("%c---%c- %c   %c--%c---%c   9---A- B   C--D---E\n", //
				displayNodeChar(nodes[9]), displayNodeChar(nodes[10]), displayNodeChar(nodes[11]),
				displayNodeChar(nodes[12]), displayNodeChar(nodes[13]), displayNodeChar(nodes[14]));

		System.out.printf("|   |  |   |  |   |\n");

		System.out.printf("|   |  %c-%c-%c  |   |   |   |  F-G-H  |   |\n", //
				displayNodeChar(nodes[15]), displayNodeChar(nodes[16]), displayNodeChar(nodes[17]));

		System.out.printf("|   |    |    |   |\n");

		System.out.printf("|   %c----%c----%c   |   |   I----J----K   |\n", //
				displayNodeChar(nodes[18]), displayNodeChar(nodes[19]), displayNodeChar(nodes[20]));

		System.out.printf("|        |        |\n");

		System.out.printf("%c--------%c--------%c   L--------M--------N\n", //
				displayNodeChar(nodes[21]), displayNodeChar(nodes[22]), displayNodeChar(nodes[23]));
	}

	private Character displayNodeChar(Node node) {
		if (node.dot == null)
			return '*';
		return node.dot.player.name.charAt(0);
	}

}
