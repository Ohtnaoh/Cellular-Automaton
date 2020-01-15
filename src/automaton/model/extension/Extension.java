package automaton.model.extension;

import automaton.model.Cell;
import automaton.model.Grid;

/**
 * 	@author Zhum - Anthony Dagon
 *	@version 1.0
 */
public abstract class Extension {
	
	/**
	 * Méthode Abstraite Return : Cell
	 * @param x
	 * @param y
	 * @param grid
	 * @return
	 */
	public abstract Cell searchElement(int x, int y, Grid grid);
}
