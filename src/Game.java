import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame{
	/**
	 * 
	 */
	Game(){
		setSize(Screen.getSCREEN_WIDTH(),Screen.getSCREEN_HEIGHT());
		setTitle("FlappyHamlet");
		Screen panel= new Screen();
		add(panel);
	}
	public static void main(String[] args) {
		JFrame frame= new Game();
		frame.setVisible(true);
}
}
