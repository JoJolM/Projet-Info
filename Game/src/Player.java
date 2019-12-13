import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

	private Game game;
	private BufferedImage[] textures;
	
	Handler handler;
	int actWalk = 0;
	
	public Player(int x, int y, Type type, Handler handler, Game game, Textures txt) {
		super(x, y, type, txt);
		this.handler = handler;
		this.game = game;
		textures = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			textures[i] = txt.extract(i+1, 1, 32, 48);
		}
		
	}

	
	public void tick() {
		x += spdX;
		y += spdY;
		
		collision();
		
		if(handler.isUp()) spdY = -5;
		else if (!handler.isDown()) spdY = 0;
		
		if(handler.isDown()) spdY = 5;
		else if (!handler.isUp()) spdY = 0;

		if(handler.isRight()) spdX = 5;
		else if (!handler.isLeft()) spdX = 0;
		
		if(handler.isLeft()) spdX = -5;
		else if (!handler.isRight()) spdX = 0;
	

	}
	
	private void collision() {
		if(game.hp <= 0){
			handler.removeObject(this);
		}
		for(int i = 0; i < handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			
			if(temp.getType() == Type.Wall) {
				if(getBounds().intersects(temp.getBounds())){
					x += spdX *-1;
					y += spdY *-1;
				}				
			}
			if(temp.getType() == Type.Enemy) {
				if(getBounds().intersects(temp.getBounds())){
					game.hp -= 20;
					x += -20*((temp.x-x)/Math.abs(x-temp.x));
					y += -20*((temp.y-y)/Math.abs(y-temp.y));		
				}
			}
			if(temp.getType() == Type.Well) {
				if(getBounds().intersects(temp.getBounds())){
					game.hp = 200;
					game.mp = 200;
					handler.removeObject(temp);
				}
			}
		}
		
	}


	public void render(Graphics g) {
		if(spdX == 0 && spdY == 0) {
			g.drawImage(textures[0],x,y,null);
		}
		else if(actWalk < 30) {
			g.drawImage(textures[1],x,y,null);
			actWalk++;
		}
		else if(actWalk < 60){
			g.drawImage(textures[2],x,y,null);
			actWalk++;
		}
		else {
			g.drawImage(textures[2],x,y,null);
			actWalk = 0;
		}
			
	}


	public Rectangle getBounds() {
		return new Rectangle(x,y,32,48);
	}
	
	public Rectangle getRange() {
		return new Rectangle(x-16,y-16,80,100);
	}

}
