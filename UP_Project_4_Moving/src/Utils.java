import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {
	public static double calc(long sp/*,double radius*/) {
		return Math.PI / 2 - ((double)sp) * Math.PI / 30.0;	//radius
	}
	
	public static class ResourceLoader {
		public static final String PATH="res/";
			
		public static BufferedImage loadImage(String fileName) {
			BufferedImage image=null;
			try {
				image=ImageIO.read(new File(PATH+fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return image;
		}
	}
	
	public static class Sprite {
		private float scale;
		private BufferedImage image;
		
		public Sprite(BufferedImage im,float scale) {
			this.image=im;
			this.scale=scale;
			image=Utils.resize(image, (int)(image.getWidth()*this.scale), (int)(image.getHeight()*this.scale));
		}
		
		public void render(Graphics g,float x,float y) {
			g.drawImage(image, (int)(x-image.getWidth()/2), (int)(y-image.getHeight()/2),null);
		}
	}
	public static BufferedImage resize(BufferedImage image,int width,int height) {
		BufferedImage newImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		newImage.getGraphics().drawImage(image, 0, 0, width, height,null);
		return newImage;
	}
}
