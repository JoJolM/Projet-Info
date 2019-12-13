import java.awt.image.BufferedImage;

public class Textures {

	private BufferedImage image;
	
	public Textures(BufferedImage image) {
		this.image = image;	
	}
	
	public BufferedImage extract(int c, int l, int w, int h) {
		return image.getSubimage((c*32)-32, (l*32)-32, w, h);
	}

}
