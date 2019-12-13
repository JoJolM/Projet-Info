import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private boolean up =false, down = false, left = false, right = false;
	

	public void tick() {
		for(int i = 0; i<object.size(); i++) {
			//conserve tous les objets en un objet temporaire
			GameObject temp = object.get(i);
			
			temp.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i<object.size(); i++) {
			//rendu de tous les objets en un objet temporaire
			GameObject temp = object.get(i);
			
			temp.render(g);
		}
	}
	
	public void addObject(GameObject temp) {
		object.add(temp);
	}
	
	public void removeObject(GameObject temp) {
		object.remove(temp);
	}
	
	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

}
