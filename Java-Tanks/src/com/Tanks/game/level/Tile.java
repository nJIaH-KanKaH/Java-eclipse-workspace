package com.Tanks.game.level;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.Tanks.graphics.SpriteSheet;
import com.Tanks.utils.Utils;

public class Tile {
	private BufferedImage image;
	private TileType type;
	
	protected Tile(BufferedImage image,int scale,TileType type) {
		this.type=type;
		this.image=Utils.resize(image, (int)(image.getWidth()*scale), (int)(image.getHeight()*scale));
	}
	
	protected void render(Graphics2D g,int x,int y) {
		g.drawImage(image, x, y, null);
	}
	
	protected TileType type() {
		return type;
	}
}
