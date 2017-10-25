import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

public class Window {

	private JFrame mainFrame;
	private Map map;
	private int rows;
	private int cols;

	
	public Window() {
		rows = 40;
		cols = 40;
		
		setUp();
	}
	
	public int getWidth() {
		return rows;
	}
	
	public int getHeight() {
		return cols;
	}
	
	private void setUp() {
		buildGUI();
	}
	/**
	 * Constructs the elements of the GUI, and makes it visible once finished.
	 */
	private void buildGUI() {
		mainFrame = new JFrame("River Crossing Task");

				
		map = new Map(rows, cols);
		mainFrame.add(map);
		mainFrame.setSize(map.getPreferredSize());
		
		
		//for (int i = 0; i < rows; i++) {
		//	for (int j = 0; j < cols; j++) {
		//		//p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//		//mainFrame.add(p);
		//	}
		//}
		
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
}
