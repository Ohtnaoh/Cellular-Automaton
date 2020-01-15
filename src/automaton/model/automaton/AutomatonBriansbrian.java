package automaton.model.automaton;

import automaton.model.Cell;
import automaton.model.Grid;
import automaton.model.extension.Extension;
import automaton.model.neighborhood.Neighborhood;


/**
 * 	@author Zhum - Anthony Dagon
 *	@version 1.0
 */
public class AutomatonBriansbrian extends Automaton{

	/**
	 * Constructeur AutomatonBriansbrian
	 * @param grid
	 * @param neighborhood
	 * @param extension
	 */
	public AutomatonBriansbrian(Grid grid, Neighborhood neighborhood, Extension extension) {
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
		
		for (int x = 0; x < height; x++)
			for (int y = 0; y < width; y++)
				newGrid[x][y] = new Cell(oldGrid[x][y].getState());

		for (int x = 0; x < getGrid().getHeight(); x++)
			for (int y = 0; y < getGrid().getWidth(); y++) {
				
				Cell[] neighbors = getNeighborhood().getNeighbors(getGrid(), x, y, getExtension());
				int neighborsInLife = 0;
				
				for (int i = 0; i < neighbors.length; i++) {
					if (neighbors[i].getState() == "LIFE") neighborsInLife++;
				}
					

				if (oldGrid[x][y].getState() == "LIFE") newGrid[x][y].setState("OLD");
				
				else if (oldGrid[x][y].getState() == "OLD") newGrid[x][y].setState("DEAD");
				
				else if (oldGrid[x][y].getState() == "DEAD") {
					if (neighborsInLife == 2) newGrid[x][y].setState("LIFE");
				}
					
			}
		getGrid().setCells(newGrid);
	}

}
