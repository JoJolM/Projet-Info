import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Well extends GameObject {

	private BufferedImage[] textures;
	private Game game;
	private Handler handler;
	
	boolean used = false;
	int anim = 0;
	
	public Well(int x, int y, Type type, Handler handler, Textures txt, Game game) {
		super(x, y, type, txt);
		
		this.handler = handler;		
		this.game = game;
		
		textures = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			textures[i] = txt.extract(i+6, 2, 32, 32);
		}
		
	}


	public void tick() {
		for(int i = 0; i < handler.object.size();i++){
			GameObject temp = handler.object.get(i);
			
			if(temp.getType() == Type.Player && used == false) {
				if(getBounds().intersects(temp.getBounds()) && (game.hp < 200 || game.mp < 200)){
					game.hp = 200;
					game.mp = 200;
					used = true;	
				}
			}
			
		}
		
	}


	public void render(Graphics g) {
		if (used == false) {
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
		else g.drawImage(textures[2],x,y,null);

			
	}


	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}


	protected Rectangle getRange() {
		return null;
	}


}
