
public class Agent {

	private Tile position;
	// indicates either Von Neumann or Moore neighbours
	private static final boolean isVN = true;
	private static final int viewDist = 3;
	
	
	public Agent(Tile position) {
		this.position = position;
		position.setType(Tile.AGENT);
	}
	
	public void move() {
		
	}
	
	public Tile getPosition() {
		return position;
	}
	
	public void setPostion(Tile t) {
		position.setType(Tile.GRASS);
		position = t;
		position.setType(Tile.AGENT);
	}
	
}
