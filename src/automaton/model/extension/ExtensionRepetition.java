package automaton.model.extension;

import automaton.model.Cell;
import automaton.model.Grid;

public class ExtensionRepetition extends Extension {

	@Override
	public Cell searchElement(int x, int y, Grid grid) {
		
		Cell[][] cells = grid.getCells();
		int a = x;
		int b = y;

		if (a < 0) a = 0;
		if (a >= grid.getHeight()) a = grid.getHeight() - 1;
		
		if (b < 0) b = 0;
		if (b >= grid.getWidth()) b = grid.getWidth() - 1;

		return cells[a][b];
	}

}