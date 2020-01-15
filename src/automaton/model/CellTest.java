package automaton.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CellTest {
	Cell construc = new Cell(null);
	
	
	@Test
	void testCell() {
		Cell actual = new Cell(null);

		//test
		assertEquals(actual, construc);

	}

	@Test
	void testGetState() {
		Cell c = new Cell("LIFE");
		assertEquals(c.getState(), "ffdf");
	}

	@Test
	void testSetState() {
		Cell cell = new Cell(null);
		cell.setState("DEAD");
		assertEquals(cell.getState(), "DEAD");
		
		
	}

}
