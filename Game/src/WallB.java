import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class WallB extends GameObject {
	
	private Handler handler;
	private BufferedImage textures[];
	private Game game;
	
	int hp = 50;
	
	
	public WallB(int x, int y, Type type, Handler handler, Textures txt, Game game) {
		super(x, y, type, txt);
		this.handler = handler;
		this.game = game;
		
		textures = new BufferedImage[2];
		for (int i = 0; i < 2; i++) {
			textures[i] = txt.extract(i+4, 4, 32, 32);
		}
	}


	public void tick() {
		for(int i = 0; i < handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			if(hp <= 0){
				handler.removeObject(this);
			}
			
			if(temp.getType() == Type.Spell) {
				if(getBounds().intersects(temp.getBounds())){
					hp -= 25;
					spdX = 0;
					spdY = 0;
					handler.removeObject(temp);
					game.score += 1;
				}
			}
			if(temp.getType() == Type.Hammer) {
				if(getBounds().intersects(temp.getBounds())){
					hp -= 50;
					spdX = 0;
					spdY = 0;
					handler.removeObject(temp);
					game.score += 2;
				}
			}
		}
	}


	public void render(Graphics g) {
		if (hp > 25)
			g.drawImage(textures[0],x,y,null);
		else
			g.drawImage(textures[1],x,y,null);
		
	}


	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}


	protected Rectangle getRange() {
		return null;
	}

}
