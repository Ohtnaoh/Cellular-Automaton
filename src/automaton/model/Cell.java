package automaton.model;



/**
 * 	@author Zhum - Anthony Dagon
 *	@version 1.0
 */
public class Cell {

	private State state;
	
	/**
	 * Constructeur Cell
	 * @param state
	 */
	public Cell(String state) {
		setState(state);
	}
	
	/**
	 * @return String : state
	 */
	public String getState() {
		switch (this.state) {
		case DEAD:
			return "DEAD";
		case LIFE:
			return "LIFE";
		case OLD:
			return "OLD";
		default:
			return "ERROR";
		}
	}
	
	/**
	 * @param state
	 */
	public void setState(String state) {
		switch (state) {
		case "LIFE":
			this.state = State.LIFE;
			break;
		case "OLD":
			this.state = State.OLD;
			break;
		case "DEAD":
			this.state = State.DEAD;
			break;
		default:
			this.state = State.DEAD;
			break;
		}
	}
	
	
}
