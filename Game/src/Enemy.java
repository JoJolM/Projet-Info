import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Enemy extends GameObject {
	
	private Handler handler;
	private BufferedImage[] textures;
	
	int actSpd = 0;
	int hp = 100;
	int anim = 0;

	public Enemy(int x, int y, Type type, Handler handler, Textures txt) {
		super(x, y, type, txt);
		this.handler = handler;
		
		textures = new BufferedImage[2];
		for (int i = 0; i < 2; i++) {
			textures[i] = txt.extract(i+4, 1, 32, 32);
		}
	}

	
	public void tick() {
		x += spdX;
		y += spdY;
		actSpd += 1;
		
		
		for(int i = 0; i < handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(hp <= 0){
				handler.removeObject(this);
			}
			
			if(temp.getType() == Type.Wall) {
				if(getCollision().intersects(temp.getBounds())){
					x += -3*spdX;
					y += -3*spdY;
					spdX = -1;
					spdY = -1;
				}else if(actSpd%30 ==0) {
					spdX = (float) ((Math.random()*8)-3.5);
					spdY = (float) ((Math.random()*8)-3.5);			
				}
				
			}
			if(temp.getType() == Type.Spell) {
				if(getBounds().intersects(temp.getBounds())){
					hp -= 25;
					spdX = 0;
					spdY = 0;
					handler.removeObject(temp);
				}
			}
			if(temp.getType() == Type.Hammer) {
				if(getBounds().intersects(temp.getBounds())){
					hp -= 50;
					spdX = 0;
					spdY = 0;
					handler.removeObject(temp);
				}
			}
		}
	}

	
	public void render(Graphics g) {
		if(anim < 60) {
			g.drawImage(textures[0],x,y,null);
			anim++;
		}
		else if(anim < 120){
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
	public Rectangle getCollision() {
		return new Rectangle(x-20,y-20,64,64);
	}

	protected Rectangle getRange() {

		return null;
	}

}
