package automaton.model;

import automaton.observer.Subject;


/**
 * 	@author Zhum - Anthony Dagon
 *	@version 1.0
 */
public class Grid extends Subject{
	
	private int height, width;
	private Cell[][] cells;
	
	
	public Grid() {
		//
	}
	
	/**
	 * Constructeur de la Grille
	 * @param height
	 * @param width
	 */
	public Grid (int height, int width) {
		this.height = height;
		this.width = width;
		this.cells = new Cell[height][width];
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				this.cells[y][x] = new Cell("DEAD");
			}
		}
	}

	

	/**
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return Cell[][]
	 */
	public Cell[][] getCells() {
		return cells;
	}

	/**
	 * @param cells
	 */
	public void setCells(Cell[][] cells) {
		this.cells = cells;
		notifyObservers();
	}
	
	
	
}
