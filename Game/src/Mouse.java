import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
	
	private Handler handler;
	private Camera camera;
	private Game game;
	private Textures txt;
	
	
	public Mouse(Handler handler, Camera camera, Game game, Textures txt) {
		this.handler = handler;
		this.camera = camera;
		this.game = game;
		this.txt = txt;
	}
	
	public void mousePressed (MouseEvent e) {
		int mx = (int)(e.getX() + camera.getX());
		int my = (int)(e.getY() + camera.getY());
		
		for(int i =0; i< handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			
			if(temp.getType() == Type.Player && game.mp > 0 && e.getButton() == 1) {
				handler.addObject(new Spell(temp.getX()+16, temp.getY()+24, Type.Spell, handler, mx, my, txt));
				game.mp -= 5;
			}
			
			if(temp.getType() == Type.Player && e.getButton() == 3) {
				handler.addObject(new Hammer(temp.getX()+16, temp.getY()+24, Type.Hammer, handler, mx, my, txt));
			}
		}
	}

}
