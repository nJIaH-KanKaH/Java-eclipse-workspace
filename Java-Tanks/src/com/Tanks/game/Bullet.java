package com.Tanks.game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import com.Tanks.IO.Input;
import com.Tanks.graphics.Sprite;
import com.Tanks.graphics.SpriteSheet;
import com.Tanks.graphics.TextureAtlas;

public class Bullet{
	public static final int SPRITE_SCALE=16;
	public static final int SPRITES_PER_HEADING=1;
	
	private enum Heading{NORTH,	EAST,SOUTH,	WEST;}
	
	int x;int y;
	public boolean exist;
	private Heading heading;
	private Sprite sprite;
	private float scale;
	private float speed=3;

	public Bullet(float x, float y,float scale, String heading,Sprite sprite) {
		this.x=(int)x;this.y=(int)y;this.speed=3;
		this.scale=scale;
		this.heading=Heading.valueOf(heading);
		this.sprite=sprite;
		this.exist=true;
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
	public boolean update() {
			float newX=x;
			float newY=y;
			
			if(heading==Heading.NORTH) {
				newY-=speed;
			}else if(heading==Heading.SOUTH) {
				newY+=speed;
			}else if(heading==Heading.WEST) {
				newX-=speed;
			}else if(heading==Heading.EAST) {
				newX+=speed;
			}
			
			if(newX<0) {
				newX=0;
				return false;
			}else if(newX>=Game.WIDTH-SPRITE_SCALE*scale) {
				newX=Game.WIDTH-SPRITE_SCALE*scale;return false;
			}
			if(newY<0) {
				newY=0;return false;
			}else if(newY>=Game.HEIGHT-SPRITE_SCALE*scale) {
				newY=Game.HEIGHT-SPRITE_SCALE*scale;return false;
			}
			
			x=(int) newX;
			y=(int) newY;
			return true;
		}

	public void render(Graphics2D g) {
		if(exist)
			sprite.render(g, x, y);
	}
}
