

public class Engine {

	private Window gui;
	private Agent agent;
	private static final int TIME_STEPS = 100000;
	
	public Engine() {
		gui = new Window();
		agent = new Agent(gui.getRandomPosition());
		run();
	}
	
	public static void main(String[] args) {
		Engine engine = new Engine();
	}
	
	private void update() {
		agent.move(gui.getMap());
		gui.getFrame().repaint();
	}
	
	private void run() {
		for (int i = 0; i < TIME_STEPS; i++){

			update();
		}
	}
}
