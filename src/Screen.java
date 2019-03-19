import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Screen extends JPanel implements ActionListener {
	private static final int SCREEN_WIDTH=1200, SCREEN_HEIGHT=700,CELL_LENGTH=100;
	private boolean gameIsStillGoingOn =false;
	private Bird bird= new Bird();
	private Pipe pipe= new Pipe();
	private Image backGround=null;

	public static int getSCREEN_WIDTH() {
		return SCREEN_WIDTH;
	}

	public static int getSCREEN_HEIGHT() {
		return SCREEN_HEIGHT;
	}

	public static int getCELL_LENGTH() {
		return CELL_LENGTH;
	}

	Screen(){
		try {
			backGround= ImageIO.read(new File("backGround.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addKeyListener(new Keys());
		setFocusable(true);
		setBackground(Color.CYAN);
		initialize();

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {
		//draw backGround
		g.drawImage(backGround.getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT, Image.SCALE_SMOOTH), 0, 0,null);
		//draw bird
		g.drawImage(bird.getHamlet().getScaledInstance(CELL_LENGTH/2,CELL_LENGTH/2, Image.SCALE_SMOOTH), bird.getBirdX(), bird.getBirdY(), null);
		//draw pipe
		for(int i=0;i<pipe.getTOTAL_PIPE();i++) {
			int tmpX=pipe.getPipeX(i);
			if(tmpX!=-1) {
				g.drawImage(pipe.getCanonDOWN().getScaledInstance(CELL_LENGTH, pipe.getPipeY1(i)+CELL_LENGTH, Image.SCALE_FAST), pipe.getPipeX(i),0,null);
				g.drawImage(pipe.getCanonUP().getScaledInstance(CELL_LENGTH, SCREEN_HEIGHT-pipe.getPipeY2(i), Image.SCALE_FAST), pipe.getPipeX(i),pipe.getPipeY2(i),null);

			}
		}
		/*
		 int tmpY=pipe.getPipeY1(i);
				for(int y=0;y<tmpY;y+=CELL_LENGTH) {
					g.fillRect(tmpX, y, CELL_LENGTH, CELL_LENGTH);
				}
				tmpY=pipe.getPipeY2(i);
				for(int y=tmpY;y<SCREEN_HEIGHT;y+=CELL_LENGTH) {
					g.fillRect(tmpX, y, CELL_LENGTH, CELL_LENGTH);
				}
		 */
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(gameIsStillGoingOn) {
			repaint();
			bird.fall();
			pipe.move();
			pipe.spawn();
			if(birdHitsThePipe()) {
				gameIsStillGoingOn=false;
			}
		}
		
	}
	private void initialize() {
		Timer t=new Timer(80,this);
		t.start();
	}
	public class Keys extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent evt) {
			if(evt.getKeyCode()==KeyEvent.VK_SPACE) {
				bird.tuck();
			}
			if(evt.getKeyCode()==KeyEvent.VK_ENTER) {
				bird.init();
				pipe.init();
				gameIsStillGoingOn=true;
			}
			
		}
	}
	public boolean birdHitsThePipe() {
		int birdX=bird.getBirdX(),birdY=bird.getBirdY(),birdX2=birdX+CELL_LENGTH/2;
		for(int i=0;i<pipe.getTOTAL_PIPE();i++) {
			int tmpX=pipe.getPipeX(i);
			if(tmpX!=-1 && ((birdX2<tmpX+CELL_LENGTH&&birdX2>=tmpX)||(birdX<tmpX+CELL_LENGTH&&birdX>=tmpX))&&(birdY-CELL_LENGTH<pipe.getPipeY1(i)||birdY+CELL_LENGTH/2>pipe.getPipeY2(i))) {
				return true;
			}
		}
		return false;
	}

}
