import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Spell extends GameObject {
	
	private Handler handler;
	private BufferedImage texture;
	
	public Spell(int x, int y, Type type, Handler handler, int mx, int my, Textures txt) {
		super(x, y, type, txt);
		this.handler = handler;

		spdX = (mx - x)/30;
		spdY = (my - y)/30;
		
		texture = txt.extract(6, 1, 15, 15);

	}


	public void tick() {
		x += spdX;
		y += spdY;
		
		spdX = (float) (spdX * 0.98);
		spdY = (float) (spdY * 0.98);
		
		if(Math.abs(spdX) < 0.5 && Math.abs(spdY) < 0.5){
			handler.removeObject(this);
		}
		
		for(int i = 0; i < handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			
			if(temp.getType() == Type.Wall) {
				
				if(getBounds().intersects(temp.getBounds())){
					handler.removeObject(this);
				}
			}
		}

	}

	public void render(Graphics g) {
		g.drawImage(texture,x,y,null);

	}

	
	public Rectangle getBounds() {
		return new Rectangle(x,y,15,15);
	}



	protected Rectangle getRange() {
		return null;
	}

}
