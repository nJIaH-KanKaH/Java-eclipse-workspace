import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {
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
		
		public static BufferedImage loadImageWithFullWay(String name) {
			BufferedImage image=null;
			try {
				image=ImageIO.read(new File(name));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return image;
		}
	}
	
	public static class TextureAtlas {
		BufferedImage image;
		public static final int STANDARD_WIDTH=640;
		public static final int STANDART_HEIGHT=360;
		public TextureAtlas(String imageName) {
			image=Utils.resize(ResourceLoader.loadImage(imageName), STANDARD_WIDTH, STANDART_HEIGHT);
		}
		public TextureAtlas(BufferedImage image) {
			image=Utils.resize(image, STANDARD_WIDTH, STANDART_HEIGHT);
		}
		
		public BufferedImage cut(int x,int y,int w,int h) {
			return image.getSubimage(x, y, w, h);
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
