import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird{
	private  static int birdX,birdY,velocity,acceleration;
	private Image Hamlet= null;
	
	public int getBirdX() {
		return birdX;
	}
	public int getBirdY() {
		return birdY;
	}
	Bird(){
		birdX=200;
		birdY=100;
		acceleration=5;
		velocity=0;
		try {
		    Hamlet = ImageIO.read(new File("Hamlet.jpg"));
		} catch (IOException e) {
		}
	}
	public void init() {
		birdX=200;
		birdY=100;
		acceleration=5;
		velocity=0;
	}
	
	public Image getHamlet() {
		return Hamlet;
	}
	public void tuck() {
		if(birdY>0) {
			velocity=-20;
			birdY+=velocity;
		}
	}
	public void fall() {
		velocity+=acceleration;
		birdY+=velocity;
	}

}
