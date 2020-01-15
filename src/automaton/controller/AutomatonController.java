package automaton.controller;

import automaton.model.Cell;
import automaton.model.Factory;
import automaton.model.Grid;
import automaton.model.TypeAutomaton;
import automaton.model.TypeExtension;
import automaton.model.automaton.Automaton;
import automaton.model.extension.Extension;
import automaton.model.neighborhood.Neighborhood;
import automaton.observer.Subject;

/**
 * 	@author Zhum - Anthony Dagon
 *	@version 1.0
 */
public class AutomatonController extends Subject {

	private Grid grid;
	private Factory factory;
	private Automaton automaton;
	private Neighborhood neighborhood;
	private Extension extension;
	private int generation;
	
	/**
	 * Constructeur du Controlleur
	 */
	public AutomatonController() {
		this.factory = Factory.getInstance();
		this.grid = new Grid();
	}
	
	/**
	 * Génère le bon automate 
	 * @param typeA
	 * @param typeE
	 * @param width
	 * @param height
	 */
	public void setAutomate(TypeAutomaton typeA, TypeExtension typeE, int width, int height) {
		setGrid(width, height);
		automaton = this.factory.createAutomaton(typeA, typeE, this.grid);
		generation=automaton.getGeneration();
	}
	
	/**
	 * Génère la grille
	 * @param width
	 * @param height
	 */
	private void setGrid(int width, int height) {
		grid = new Grid(width, height);
	}
	
	/**
	 * Génère le prochain état de la grille et nous la renvois
	 * @return Object : Grid
	 */
	public Grid nextStep() {
		generation++;
		automaton.nextStep();
		automaton.setGeneration(generation);
		grid = automaton.getGrid();
		this.generation = automaton.getGeneration();
		notifyObservers();
		return grid;
	}

	/**
	 * @return generation
	 */
	public int getGeneration() {
		return generation;
	}

	/**
	 * Génère le prochain état d'une céllule et renvois la grille
	 * @param x
	 * @param y
	 * @return
	 */
	public Grid nextEtat(int x, int y) {
		Cell[][] cells = grid.getCells();
		String res = cells[x][y].getState();
		System.out.println("Cell position at : [" + x + "," + y + "]  OldState : " + res);
		
		if (res=="LIFE") {
			res = "DEAD";
		}else {
			res = "LIFE";
		}
		
		cells[x][y].setState(res);
		System.out.println("NewState : " + res);
		grid.setCells(cells);
		
		return grid;
	}

	/**
	 * @return Object : Grid
	 */
	public Grid getGrid() {
		return automaton.getGrid();
	}
	
	
}
