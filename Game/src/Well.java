import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Well extends GameObject {

	private BufferedImage[] textures;
	
	boolean used = false;
	int anim = 0;
	
	public Well(int x, int y, Type type, Handler handler, Textures txt) {
		super(x, y, type, txt);
		
		textures = new BufferedImage[2];
		for (int i = 0; i < 2; i++) {
			textures[i] = txt.extract(i+6, 2, 32, 32);
		}
		
	}


	public void tick() {
		

	}


	public void render(Graphics g) {
		if(anim < 20) {
			g.drawImage(textures[0],x,y,null);
			anim++;
		}
		else if(anim < 40){
			g.drawImage(textures[1],x,y,null);
			anim++;
		}
		else {
			g.drawImage(textures[1],x,y,null);
			anim = 0;
		}
			
	}


	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}


	protected Rectangle getRange() {
		return null;
	}


}
