package com.Tanks.game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Tanks.IO.Input;
import com.Tanks.game.level.TileType;
import com.Tanks.graphics.Sprite;
import com.Tanks.graphics.SpriteSheet;
import com.Tanks.graphics.TextureAtlas;

public class Player extends Entity {
	
	public static final int SPRITE_SCALE=16;
	public static final int SPRITES_PER_HEADING=1;
	
	private enum Heading{
		NORTH(0*SPRITE_SCALE,0*SPRITE_SCALE,1*SPRITE_SCALE,1*SPRITE_SCALE),
		EAST(6*SPRITE_SCALE,0*SPRITE_SCALE,1*SPRITE_SCALE,1*SPRITE_SCALE),
		SOUTH(4*SPRITE_SCALE,0*SPRITE_SCALE,1*SPRITE_SCALE,1*SPRITE_SCALE),
		WEST(2*SPRITE_SCALE,0*SPRITE_SCALE,1*SPRITE_SCALE,1*SPRITE_SCALE);
		private int x,y,h,w;
		private Heading(int x,int y,int h,int w) {
			this.x=x;
			this.y=y;
			this.h=h;
			this.w=w;
		}
		
		protected BufferedImage texture(TextureAtlas atlas) {
			return atlas.cut(x, y, w, h);
		}
	}
	private enum bHeading{
		NORTH((int)(20*SPRITE_SCALE),(int)(6.25*SPRITE_SCALE),(int)(0.5*SPRITE_SCALE),(int)(0.5*SPRITE_SCALE)),
		EAST((int)(21.5*SPRITE_SCALE),(int)(6.25*SPRITE_SCALE),(int)(0.5*SPRITE_SCALE),(int)(0.5*SPRITE_SCALE)),
		SOUTH((int)(21*SPRITE_SCALE),(int)(6.25*SPRITE_SCALE),(int)(0.5*SPRITE_SCALE),(int)(0.5*SPRITE_SCALE)),
		WEST((int)(20.5*SPRITE_SCALE),(int)(6.25*SPRITE_SCALE),(int)(0.5*SPRITE_SCALE),(int)(0.5*SPRITE_SCALE));
		
		private int x,y,h,w;
		private bHeading(int x,int y,int h,int w) {
			this.x=x;
			this.y=y;
			this.h=h;
			this.w=w;
		}
		
		protected BufferedImage texture(TextureAtlas atlas) {
			return atlas.cut(x, y, w, h);
		}
	}
	
	private Heading heading;
	private bHeading bulletHeading;
	private Map<Heading, Sprite>spriteMap;
	private Map<bHeading, Sprite>bMap;
	private float scale;
	private float speed;
	private boolean shot;
	
	public boolean getShot() {
		return shot;
	}
	
	public float scale() {
		return scale;
	}

	public Player(float x, float y,float scale,float speed,TextureAtlas atlas) {
		super(EntityType.Player, x, y);
		heading=Heading.NORTH;
		bulletHeading=bHeading.NORTH;
		spriteMap=new HashMap<Heading,Sprite>();
		bMap=new HashMap<>();
		this.scale=scale;
		this.speed=speed;
		for(Heading h:Heading.values()) {
			SpriteSheet sheet=new SpriteSheet(h.texture(atlas), SPRITES_PER_HEADING, SPRITE_SCALE);
			Sprite sprite=new Sprite(sheet, scale);
			spriteMap.put(h, sprite);
		}
		for(bHeading h:bHeading.values()) {
			SpriteSheet sheet=new SpriteSheet(h.texture(atlas), 1, (int)(0.5*SPRITE_SCALE));
			Sprite sprite=new Sprite(sheet, scale);
			bMap.put(h, sprite);
		}
	}
	
	public Bullet shot() {
		Sprite sprite=bMap.get(bulletHeading);
		int bX=(int)x();int bY=(int)y();
		switch(bulletHeading) {
		case NORTH:bX=bX+(int)(0.5*SPRITE_SCALE); break;
		case WEST:bY=bY+(int)(0.5*SPRITE_SCALE);break;
		case SOUTH:bX=bX+(int)(0.5*SPRITE_SCALE);bY=bY+(int)(1.5*SPRITE_SCALE);break;
		case EAST:bY=bY+(int)(0.5*SPRITE_SCALE);bX=bX+(int)(1.5*SPRITE_SCALE);break;
		}
		return new Bullet(bX, bY, speed, bulletHeading.name(), sprite);
	}

	@Override
	public void update(Input input) {
			float newX=x();
			float newY=y();
			shot=false;
			
			if(input.getKey(KeyEvent.VK_UP)) {
				newY-=speed;
				heading=Heading.NORTH;bulletHeading=bHeading.NORTH;
			}else if(input.getKey(KeyEvent.VK_DOWN)) {
				newY+=speed;
				heading=Heading.SOUTH;bulletHeading=bHeading.SOUTH;
			}else if(input.getKey(KeyEvent.VK_LEFT)) {
				newX-=speed;
				heading=Heading.WEST;bulletHeading=bHeading.WEST;
			}else if(input.getKey(KeyEvent.VK_RIGHT)) {
				newX+=speed;
				heading=Heading.EAST;bulletHeading=bHeading.EAST;
			}
			if(input.getKey(KeyEvent.VK_SPACE)) {
				shot();shot=true;
			}
			
			if(newX<0) {
				newX=0;
			}else if(newX>=Game.WIDTH-SPRITE_SCALE*scale) {
				newX=Game.WIDTH-SPRITE_SCALE*scale;
			}
			if(newY<0) {
				newY=0;
			}else if(newY>=Game.HEIGHT-SPRITE_SCALE*scale) {
				newY=Game.HEIGHT-SPRITE_SCALE*scale;
			}
			
			x(newX);
			y(newY);
		}

	@Override
	public void render(Graphics2D g) {
		spriteMap.get(heading).render(g, x(), y());
	}

}
