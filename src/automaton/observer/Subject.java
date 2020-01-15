package automaton.observer;

import java.util.ArrayList;

/**
 * 	@author Zhum - Anthony Dagon
 *	@version 1.0
 *  Sujet
 */
public class Subject {
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();

	/**
	 * Notifie l'observateur
	 */
	public void notifyObservers() {
		for (Observer o: observers) {
			o.update();
		}
	}

	/**
	 * Ajoute un observateur
	 * @param o
	 */
	public void addObserver(Observer o) {
		observers.add(o);
	}
	
	/**
	 * Supprime un observateur
	 * @param o
	 */
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
}
