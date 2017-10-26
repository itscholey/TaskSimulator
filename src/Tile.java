import java.awt.Color;

public class Tile {
	
	public static final Color GRASS = new Color(255,255,255);
	public static final Color AGENT = new Color(255,0,0);
	public static final Color RIVER = new Color(0,0,255);
	public static final Color STONE = new Color(25,25,25);
	public static final Color FOOD  = new Color(0,255,0);
	public static final Color DEFAULT = GRASS;
	
	public static final Color[] TILE_TYPES = {
		GRASS,
		AGENT,
		RIVER,
		STONE,
		FOOD,
		DEFAULT
	};
	
	private Color color = GRASS;
	private int[] location;
	

	public Tile(Color c, int xLoc, int yLoc) {
		color = c;
		location = new int[2];
		location[0] = xLoc;
		location[1] = yLoc;
		
	}
	
	public void setType(Color c) {
		color = c;
	}
	
	public Color getType() {
		return color;
	}
	
	public int[] getLocation() {
		return location;
	}
}
