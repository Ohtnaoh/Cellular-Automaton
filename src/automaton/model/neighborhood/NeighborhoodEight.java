package automaton.model.neighborhood;

import automaton.model.Cell;
import automaton.model.Grid;
import automaton.model.extension.Extension;

/**
 * 	@author Zhum - Anthony Dagon
 *	@version 1.0
 */
public class NeighborhoodEight extends Neighborhood{

	@Override
	public Cell[] getNeighbors(Grid grid, int x, int y, Extension ext) {
		
		Cell[] neighbors = new Cell[8];
		
				
		neighbors[0] = ext.searchElement(x - 1, y - 1, grid);
		neighbors[1] = ext.searchElement(x - 1, y, grid);
		neighbors[2] = ext.searchElement(x - 1, y + 1, grid);
		neighbors[3] = ext.searchElement(x, y - 1, grid);
		neighbors[4] = ext.searchElement(x, y + 1, grid);
		neighbors[5] = ext.searchElement(x + 1, y - 1, grid);
		neighbors[6] = ext.searchElement(x + 1, y, grid);
		neighbors[7] = ext.searchElement(x + 1, y + 1, grid);
		
		return neighbors;
	}

}
