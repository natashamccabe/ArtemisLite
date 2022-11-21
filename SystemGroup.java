/**
 * 
 */
package artemisLite;

import java.util.ArrayList;
import java.util.List;


public class SystemGroup {
	
	private SystemName name;

	private List<Square> squares = new ArrayList<Square>();
	
	
	/**
	 * constructor with name
	 * @param name
	 */
	public SystemGroup(SystemName name) {
		super();
		this.name = name;
	}

	public List<Square> getSquares() {
		return squares;
	}

	public void setSquares(List<Square> squares) {
		this.squares = squares;
	}

	public SystemName getName() {
		return name;
	}

	public void setName(SystemName name) {
		this.name = name;
	}
	
	public void addSquare(Square newSquare) {
		squares.add(newSquare);
	}
	
	
	
}
