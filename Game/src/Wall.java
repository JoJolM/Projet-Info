import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Wall extends GameObject {

	private BufferedImage texture;
	
	public Wall(int x, int y, Type type, Textures txt) {
		super(x, y, type, txt);
		
		texture = txt.extract(5,2,32,32);
	}


	public void tick() {
			x += spdX;
			y += spdY;
		
	}


	public void render(Graphics g) {
		g.drawImage(texture,x,y,null);
		
	}


	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}


	protected Rectangle getRange() {
		return null;
	}

}
