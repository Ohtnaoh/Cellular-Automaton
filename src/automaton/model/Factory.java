package automaton.model;

import automaton.model.automaton.Automaton;
import automaton.model.automaton.AutomatonBriansbrian;
import automaton.model.automaton.AutomatonFredkin;
import automaton.model.automaton.AutomatonGameoflife;
import automaton.model.neighborhood.Neighborhood;
import automaton.model.neighborhood.NeighborhoodEight;
import automaton.model.neighborhood.NeighborhoodFour;
import automaton.model.extension.Extension;
import automaton.model.extension.ExtensionPeriodicity;
import automaton.model.extension.ExtensionRepetition;


/**
 * 	@author Zhum - Anthony Dagon
 *	@version 1.0
 */
public class Factory {
	private TypeAutomaton typeAutomaton;
	private TypeExtension typeExtension;
	private Automaton automaton;
	private Extension extension;
	private Neighborhood neighborhood;
	
	private static Factory factory;
	
	/**
	 * Méthode de création d'un automate
	 * @param typeA
	 * @param typeE
	 * @param grid
	 * @return Object : Automaton
	 */
	public Automaton createAutomaton(TypeAutomaton typeA, TypeExtension typeE, Grid grid) {
		extension = this.createExtension(typeE);
		switch(typeA) {
			case GAMEOFLIFE:
				neighborhood = this.useNeighborhood("HEIGHT");
				automaton = new AutomatonGameoflife(grid, neighborhood, extension);
				break;
				
			case FREDKIN:
				neighborhood = this.useNeighborhood("FOUR");
				automaton = new AutomatonFredkin(grid, neighborhood, extension);
				break;
				
			case BRIANSBRIAN:
				neighborhood = this.useNeighborhood("HEIGHT");
				automaton = new AutomatonBriansbrian(grid, neighborhood, extension);
				break;
				
			default:
				automaton = null;				
		}
		return automaton;
	}
	
	/**
	 * Méthode de création d'une Extension
	 * @param typeE
	 * @return Object : Extension
	 */
	private Extension createExtension(TypeExtension typeE) {
		switch(typeE) {				
			case PERIODICITY:
				extension = new ExtensionPeriodicity();
				break;
				
			case REPETITION:
				extension = new ExtensionRepetition();
				break;
				
			default:
				extension = null;				
		}
		return extension;
	}
	
	/**
	 * Méthode de creation d'un Voisinage
	 * @param s
	 * @return Object : Neighborhood
	 */
	private Neighborhood useNeighborhood(String s) {
		switch(s) {
			case "FOUR": // Moore
				neighborhood = new NeighborhoodFour();
				break;
				
			case "HEIGHT": // VonNeumann
				neighborhood = new NeighborhoodEight();
				break;
				
			default:
				neighborhood = null;				
		}
		return neighborhood;
	}
	
	/**
	 * @return Object : Factory
	 */
	public static Factory getInstance() {
		if(factory == null) factory = new Factory();
		
		return factory;
	}
}
