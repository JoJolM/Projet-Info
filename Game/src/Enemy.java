import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Enemy extends GameObject {
	
	protected Handler handler;
	protected Game game;
	protected BufferedImage[] textures;

	public Enemy(int x, int y, Type type, Handler handler, Textures txt, Game game) {
		super(x, y, type, txt);
		this.handler = handler;
		this.game = game;
		
	}

	public abstract void tick();	
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	public abstract Rectangle getCollision();
	protected abstract Rectangle getRange();

}
