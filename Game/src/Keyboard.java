import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter{
	
	Handler handler;
	
	public Keyboard(Handler handler) {
		this.handler = handler;
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			
			if(temp.getType() == Type.Player) {
				if(key == KeyEvent.VK_Z) handler.setUp(true);
				if(key == KeyEvent.VK_S) handler.setDown(true);
				if(key == KeyEvent.VK_Q) handler.setLeft(true);
				if(key == KeyEvent.VK_D) handler.setRight(true);
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			
			if(temp.getType() == Type.Player) {
				if(key == KeyEvent.VK_Z) handler.setUp(false);
				if(key == KeyEvent.VK_S) handler.setDown(false);
				if(key == KeyEvent.VK_Q) handler.setLeft(false);
				if(key == KeyEvent.VK_D) handler.setRight(false);
			}
		}
	}

}
