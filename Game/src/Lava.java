import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Lava extends GameObject {

	private BufferedImage[] textures;
	int anim = 0;
	
	public Lava(int x, int y, Type type, Textures txt) {
		super(x, y, type, txt);
		
		textures = new BufferedImage[2];
		textures[0] = txt.extract(4, 3, 32, 32);
		textures[1] = txt.extract(5, 3, 32, 32);
	}


	public void tick() {	
	}


	public void render(Graphics g) {		
		if(anim < 30) {
			g.drawImage(textures[0],x,y,null);
			anim++;
		}
		else if(anim < 60){
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