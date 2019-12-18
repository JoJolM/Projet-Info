import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class RedDogoo extends Enemy {

	int actSpd = 0;
	int hp = 200;
	int anim = 0;
	
	public RedDogoo(int x, int y, Type type, Handler handler, Textures txt, Game game) {
		super(x, y, type, handler, txt, game);

		textures = new BufferedImage[2];
		for (int i = 0; i < 2; i++) {
			textures[i] = txt.extract(i+6, 3, 32, 32);
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
			
			if(temp.getType() == Type.Player) { // 'IA' de l'ennemi, si le joueur est à proximité il va commencer à le poursuivre
				if(getRange().intersects(temp.getBounds())){
					if(actSpd == 30) {
						spdX = (float) (3*((temp.x-x)/(Math.abs(x-temp.x)+0.01))); //+0.01 pour éviter /0
						spdY = (float) (3*((temp.y-y)/(Math.abs(y-temp.y)+0.01))); //+0.01 pour éviter /0
					}
				}
			}
			
			else if(temp.getType() == Type.Wall || temp.getType() == Type.WallB) {
				if(getCollision().intersects(temp.getBounds())){
					x += -3*spdX;
					y += -3*spdY;
					spdX = -1;
					spdY = -1;
				}else if(actSpd == 45) {
					spdX = (float) ((Math.random()*6)-2.5);
					spdY = (float) ((Math.random()*6)-2.5);		
					actSpd = 0;
				}
			}
			
			if(temp.getType() == Type.Lava) {
				if(getBounds().intersects(temp.getBounds())){
					spdX *= 0.7;
					spdY *= 0.7;
				}
			}
			
			if(temp.getType() == Type.Spell) {
				if(getBounds().intersects(temp.getBounds())){
					hp -= 25;
					spdX = 0;
					spdY = 0;
					handler.removeObject(temp);
					game.score += 5;
				}
			}
			if(temp.getType() == Type.Hammer) {
				if(getBounds().intersects(temp.getBounds())){
					hp -= 50;
					spdX = 0;
					spdY = 0;
					handler.removeObject(temp);
					game.score += 10;
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
		return new Rectangle(x-300,y-300,600,600);
	}

}

