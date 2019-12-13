import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;

	private boolean on = false;
	private Thread thread;
	private Handler handler;
	private Camera camera;
	private Textures txt;
	
	private BufferedImage map = null;
	private BufferedImage textures = null;
	private BufferedImage floor = null;
	
	public int hp = 200;
	public int mp = 200;
	
	public Game() {
		new GamePanel(1600,900,"geimu",this);
		start();
		
		handler = new Handler();
		camera = new Camera(0,0);
		
		Background loader = new Background();
		map = loader.loadImage("/map.png");
		textures = loader.loadImage("/textures.png");
		txt = new Textures(textures);
		
		floor = txt.extract(4, 2, 32, 32);
		
		this.addKeyListener(new Keyboard(handler));
		this.addMouseListener(new Mouse(handler, camera, this, txt));		
		
		loadLevel(map);
		
	}
	
	private void start() {
		on = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	private void stop() {
		on = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double framerate = 60.0; //images par seconde
		double ns = 1000000000 / framerate;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(on) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >=1) {
				tick();
				//updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
				//updates = 0;
			}
		}
		stop();
	}
	
	//met à jour les élements du jeu
	public void tick() {
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getType() == Type.Player) {
				camera.tick(handler.object.get(i));
			}
		}
		
		handler.tick();
	}
	
	//rendu du jeu
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(2); //Charge des trames en avances (3 pré chargées ici)
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.translate(-camera.getX(), -camera.getY());
		
		for(int xx = 0; xx < 30*110; xx+= 32) {
			for(int yy = 0; yy < 30*72; yy+=32) {
				g.drawImage(floor,xx,yy,null);
			}
		}
		
		//rendu des objets
		handler.render(g);
		
		g2d.translate(camera.getX(), camera.getY());
		
		if (hp<0) {
			g.setColor(Color.black);
			g.fillRect(10, 10, 200, 20);
			g.setColor(Color.white);
			g.drawRect(10, 10, 200, 20);
		}
		else {
			g.setColor(Color.black);
			g.fillRect(10, 10, 200, 20);
			g.setColor(Color.green);
			g.fillRect(10, 10, hp, 20);
			g.setColor(Color.white);
			g.fillRect(10, 25, hp, 4);
			g.setColor(Color.gray);
			g.drawRect(10, 10, 200, 20);
		}
		
		
		g.setColor(Color.black);
		g.fillRect(10, 30, 200, 20);
		g.setColor(Color.cyan);
		g.fillRect(10, 30, mp, 20);
		g.setColor(Color.white);
		g.fillRect(10, 45, mp, 4);
		g.setColor(Color.gray);
		g.drawRect(10, 30, 200, 20);
		
		g.dispose();
		bs.show();
		
	}
	
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		for (int xx = 0; xx < w; xx++) {
			for(int yy= 0; yy<h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 0 && blue == 0)
					handler.addObject(new Wall(xx*32,yy*32, Type.Wall, txt));
				if(red == 0 && green == 0 && blue == 255)
					handler.addObject(new Player(xx*32,yy*32, Type.Player, handler, this, txt));
				if(red == 255 && green == 255 && blue == 0)
					handler.addObject(new Enemy(xx*32,yy*32, Type.Enemy, handler, txt));
				if(red == 255 && green == 255 && blue == 255)
					handler.addObject(new Well(xx*32,yy*32, Type.Well, handler, txt));
			}
			
		}
	}
	
	public static void main (String[] args) {
		new Game();
	}

}
