import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected int x,y;
	protected float spdX =0, spdY = 0;
	protected Type type;
	protected Textures txt;
	
	public GameObject(int x, int y, Type type, Textures txt) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.txt = txt;
	}


	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	protected abstract Rectangle getRange();

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getSpdX() {
		return spdX;
	}

	public void setSpdX(float spdX) {
		this.spdX = spdX;
	}

	public float getSpdY() {
		return spdY;
	}

	public void setSpdY(float spdY) {
		this.spdY = spdY;
	}
	
	

}
