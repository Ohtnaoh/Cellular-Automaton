package automaton.model.automaton;

import automaton.model.Cell;
import automaton.model.Grid;
import automaton.model.extension.Extension;
import automaton.model.neighborhood.Neighborhood;

/**
 * 	@author Zhum - Anthony Dagon
 *	@version 1.0
 */
public class AutomatonFredkin extends Automaton{

	/**
	 * Contructeur AutomatonFredkin
	 * @param grid
	 * @param neighborhood
	 * @param extension
	 */
	public AutomatonFredkin(Grid grid, Neighborhood neighborhood, Extension extension) {
		super(grid, neighborhood, extension);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Prochaine étape : Return la nouvelle grille
	 */
	@Override
	public void nextStep() {
		int height = getGrid().getHeight();
		int width = getGrid().getWidth();
		
		Cell[][] oldGrid = getGrid().getCells();
		Cell[][] newGrid = new Cell[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				newGrid[i][j] = new Cell(oldGrid[i][j].getState());
			}
		}
			
		for (int x = 0; x < getGrid().getHeight(); x++)
			for (int y = 0; y < getGrid().getWidth(); y++) {
				Cell[] neighbors = getNeighborhood().getNeighbors(getGrid(), x, y, getExtension());
				int neighborsInLife = 0;
				for (int k = 0; k < neighbors.length; k++) {
					if (neighbors[k].getState() == "LIFE") neighborsInLife++;
				}
					
				if (neighborsInLife == 1 || neighborsInLife == 3) {
					newGrid[x][y].setState("LIFE");
				}
				else {
					newGrid[x][y].setState("DEAD");
				}
					
			}
		getGrid().setCells(newGrid);
	}

}
