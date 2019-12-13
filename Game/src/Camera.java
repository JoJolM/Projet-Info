
public class Camera {
	private float x, y;

	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject object) {
		
		x += ((object.getX() - x) - 1600/2) * 0.05f;
		y += ((object.getY() - y) - 900/2) * 0.05f;
		
		if(x<= 0) x = 0;
		if(x>= 1600+6) x = 1600+6;
		if(y<= 0) y = 0;
		if(y>= 900-75) y = 900-75;		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
