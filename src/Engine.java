
public class Engine {

	private Window gui;
	private Agent agent;
	
	public Engine() {
		gui = new Window();
		agent = new Agent();
	}
	
	public static void main(String[] args) {
		Engine engine = new Engine();
	}
}
