import javax.swing.JPanel;

import java.awt.*;
import java.util.Random;

public class Map extends JPanel {

	public static final int PREFERRED_TILE_SIZE = 20;
	private int prefWidth;
	private int prefHeight;
	
	private Tile[][] mapGrid;
	
	private static final int DEFAULT_ROWS = 25;
	private static final int DEFAULT_COLS = 25;
	private int rows;
	private int cols;
	private Random random;
	private static final int seed = 150;
	private boolean useSeed = false;
	private final double riverBendProb = 0.0;
	private final int maxRiverWidth = 1;
	private final boolean generateRiver = false;
			
	
	public Map() {
		this(DEFAULT_ROWS, DEFAULT_COLS);
	}
	
	public Map(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		
		if (useSeed) {
			random = new Random(seed);
		}
		else {
			random = new Random();
		}
		mapGrid = new Tile[rows][cols];
		
		prefWidth  = (cols * PREFERRED_TILE_SIZE) + cols + 1;
		prefHeight = (rows * PREFERRED_TILE_SIZE) + rows + 1;
		setPreferredSize(new Dimension(prefWidth, prefHeight));
		System.out.println("Preferred: " + prefWidth + ", " + prefHeight);
		
		setUp();
	}
	
	
	public Tile getRandomPosition() {
		return mapGrid[random.nextInt(rows)][random.nextInt(cols)];
	}
	
	
	
	private void setUp() {

		// set up map cell arrays
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {				
				mapGrid[i][j] = new Tile(Tile.GRASS);
			}
		}
		
		//mapGrid[rows-1][cols-1].setType(Tile.AGENT);
		mapGrid[3][3].setType(Tile.FOOD);
		
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public Tile[][] getMap() {
		return mapGrid;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, getWidth(), getHeight());
		int width  = (getWidth()-cols)  / cols;
		int height = (getHeight()-rows) / rows;
		
		for (int i = 0; i < rows; i++) {
			// draw row border
			g.setColor(Color.BLACK);
			g.drawLine(0, ((height*i)+i), ((width*cols)+cols), ((height*i)+i));
			
			for (int j = 0; j < cols; j++) {
				g.setColor(mapGrid[i][j].getType());
				
				int x = (j * width)  + j + 1;
				int y = (i * height) + i + 1;


				g.fillRect(x, y, width, height);
				
				// draw column border
				g.setColor(Color.BLACK);
				g.drawLine(x-1, y-1, x-1, ((height*rows)+rows));
				
			}
			
		}
		g.setColor(Color.BLACK);
		// last row
		g.drawLine(0, ((height*cols)+cols), ((width*cols)+cols), ((height*cols)+cols));
		// last column
		g.drawLine(((width*rows)+rows), 0, ((width*rows)+rows), ((height*cols)+rows));
		
		System.out.println(getWidth() + " " + getHeight());
		
	}
}
