package automaton.model.neighborhood;

import automaton.model.Cell;
import automaton.model.Grid;
import automaton.model.extension.Extension;

/**
 * 	@author Zhum - Anthony Dagon
 *	@version 1.0
 */
public abstract class Neighborhood {
	
	/**
	 * Méthode Abstraite Return Cell[]
	 * @param grid
	 * @param x
	 * @param y
	 * @param ext
	 * @return
	 */
	public abstract Cell[] getNeighbors(Grid grid, int x, int y, Extension ext);
}
