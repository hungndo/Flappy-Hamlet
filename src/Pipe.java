import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Pipe {
	private int pipeIndex, pipe_hole,currentIndex;
	private final int TOTAL_PIPE= 20;
	private ArrayList<Integer> pipeX =new ArrayList<Integer>(TOTAL_PIPE);
	private ArrayList<Integer> pipeY1 =new ArrayList<Integer>(TOTAL_PIPE);
	private ArrayList<Integer> pipeY2 =new ArrayList<Integer>(TOTAL_PIPE);
	private Image canonUP=null,canonDOWN=null;
	Random rand=new Random();
	Pipe(){
		pipeIndex=0;
		currentIndex=0;
		for(int i=0;i<TOTAL_PIPE;i++) {
			pipeX.add(-1);
			pipeY1.add(-1);
			pipeY2.add(-1);
		}
		try {
			canonUP=ImageIO.read(new File("cannonUp.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			canonDOWN=ImageIO.read(new File("cannonDown.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Image getCanonUP() {
		return canonUP;
	}


	public Image getCanonDOWN() {
		return canonDOWN;
	}


	public int getTOTAL_PIPE() {
		return TOTAL_PIPE;
	}


	public int getPipeX(int x) {
		return pipeX.get(x);
	}

	public int getPipeY1(int x) {
		return pipeY1.get(x);
	}

	public int getPipeY2(int x) {
		return pipeY2.get(x);
	}

	public void spawn() {
		if(readyToSpawn()) {
			pipe_hole=rand.nextInt(Screen.getSCREEN_HEIGHT()-300)+150;
			pipeY1.set(pipeIndex, pipe_hole-Screen.getCELL_LENGTH()*2);
			pipeY2.set(pipeIndex, pipe_hole+Screen.getCELL_LENGTH()*1);
			pipeX.set(pipeIndex, Screen.getSCREEN_WIDTH());
			currentIndex=pipeIndex;
			pipeIndex= (pipeIndex+1)%TOTAL_PIPE;
		}
	}
	public void init() {
		pipeIndex=0;
		currentIndex=0;
		for(int i=0;i<TOTAL_PIPE;i++) {
			pipeX.set(i,-1);
			pipeY1.set(i,-1);
			pipeY2.set(i,-1);
		}
	}
	public void move() {
		for(int i=0;i<TOTAL_PIPE;i++){
			int tmp=pipeX.get(i);
			if(tmp!=-1) {
				pipeX.set(i,tmp-10);
			}
			if(tmp<=0) {
				destroyPipe(i);
			}
		}
	}
	public boolean readyToSpawn() {
		if(pipeX.get(currentIndex)<=950) {
			return true;
		}
		else 
			return false;
	}
	public void destroyPipe(int index) {
		pipeX.set(index, -1);
	}
}
