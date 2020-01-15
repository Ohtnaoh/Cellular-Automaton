package automaton.model.extension;

import automaton.model.Cell;
import automaton.model.Grid;

/**
 * 	@author Zhum - Anthony Dagon
 *	@version 1.0
 */
public class ExtensionPeriodicity extends Extension {

	@Override
	public Cell searchElement(int x, int y, Grid grid) {
		
		Cell[][] cells = grid.getCells();
		
		int a = x < 0 ? x += grid.getHeight() : x % grid.getHeight();
		int b = y < 0 ? y += grid.getWidth() : y % grid.getWidth();

		return cells[a][b];
	}

}
