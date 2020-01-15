package automaton.model.automaton;

import automaton.model.Grid;
import automaton.model.Rule;
import automaton.model.extension.Extension;
import automaton.model.neighborhood.Neighborhood;

/**
 *  @author Zhum - Anthony Dagon
 *	@version 1.0
 */
public abstract class Automaton implements Rule {

	private Grid grid;
	private Neighborhood neighborhood;
	private Extension extension;
	private int generation;

	/**
	 * Constructeur Automaton
	 * @param grid
	 * @param neighborhood
	 * @param extension
	 */
	public Automaton(Grid grid, Neighborhood neighborhood, Extension extension) {
		this.grid = grid;
		this.neighborhood = neighborhood;
		this.extension = extension;
	}

	/**
	 * @return Object : grid
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * @param grid
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	/**
	 * @return Object : neighborhood
	 */
	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	/**
	 * @param neighborhood
	 */
	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}

	/**
	 * @return Object : extension
	 */
	public Extension getExtension() {
		return extension;
	}

	/**
	 * @param extension
	 */
	public void setExtension(Extension extension) {
		this.extension = extension;
	}

	/**
	 * @return int : generation
	 */
	public int getGeneration() {
		return generation;
	}

	/**
	 * @param generation
	 */
	public void setGeneration(int generation) {
		this.generation = generation;
	}

}
