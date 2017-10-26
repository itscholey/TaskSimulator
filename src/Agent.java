
public class Agent {

	private Tile position;
	// indicates either Von Neumann or Moore neighbours
	//private static final boolean isVN = true;
	//private static final int viewDist = 3;
	private boolean moveRight = true;
	
	public Agent(Tile position) {
		this.position = position;
		position.setType(Tile.AGENT);
	}
	
	public Tile getPosition() {
		return position;
	}
	
	public void setPosition(Tile t) {
		position.setType(Tile.DEFAULT);
		position = t;
		position.setType(Tile.AGENT);
	}
	
	
	public void move(Map view) {
		

		// look at where the agent is currently
		int[] pos = new int[2];
		pos = position.getLocation();
		// decide where is best to go next
		System.out.println("[" + pos[0] + ", " + pos[1] + "]");	
		
		if (moveRight && pos[1] < view.getCols() -1) {
			setPosition(view.getTile(pos[0], pos[1]+1));
			
			if (position.getLocation()[1] == view.getCols() - 1 ) {
				moveRight = false;
			}
		}
		else {
			setPosition(view.getTile(pos[0], pos[1]-1));
			
			if (position.getLocation()[1] == 0) {
				moveRight = true;
			}
		}
		
		// move and update position
		
		
	}
	
	
}
