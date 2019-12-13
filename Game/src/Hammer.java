import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Hammer extends GameObject {
	
	private Handler handler;
	private BufferedImage texture;
	
	public Hammer(int x, int y, Type type, Handler handler, int mx, int my, Textures txt) {
		super(x, y, type, txt);
		this.handler = handler;
		
		spdX = (mx - x)/60;
		spdY = (my - y)/60;
		
		texture = txt.extract(7, 1, 20, 20);

	}


	public void tick() {
		x += spdX;
		y += spdY;

		for(int i = 0; i < handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			
			if(temp.getType() == Type.Wall) {				
				if(getBounds().intersects(temp.getBounds())){
					handler.removeObject(this);
				}
			}
			if(temp.getType() == Type.Player) {				
				if(!getBounds().intersects(temp.getRange())){
					handler.removeObject(this);
				}
			}
		}

	}

	public void render(Graphics g) {
		g.drawImage(texture,x,y,null);

	}

	
	public Rectangle getBounds() {
		return new Rectangle(x,y,20,20);
	}


	protected Rectangle getRange() {
		return null;
	}

}
