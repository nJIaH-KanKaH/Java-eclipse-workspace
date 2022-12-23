package com.Tanks.game.level;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Tanks.game.Block;
import com.Tanks.game.Bullet;
import com.Tanks.game.Game;
import com.Tanks.game.GameObject;
import com.Tanks.game.Player;
import com.Tanks.graphics.TextureAtlas;
import com.Tanks.utils.Utils;

public class Level {
	public static final int TILE_SCALE=8;
	public static final int TILE_IN_GAME_SCALE=2;
	public static final int SCALED_TILE_SIZE=TILE_SCALE*TILE_IN_GAME_SCALE;
	public static final int TILES_IN_WIDTH=Game.WIDTH/SCALED_TILE_SIZE;
	public static final int TILES_IN_HEIGHT=Game.HEIGHT/SCALED_TILE_SIZE;
	
	private Map<TileType, Tile>tiles;
	private int[][]tileMap;
	private List<Point> grassCoords;
	private List<Bullet>bullets;
	
	
	public Level(TextureAtlas atlas) {
		tileMap=new int[TILES_IN_WIDTH][TILES_IN_HEIGHT];
		bullets=new ArrayList<>();
		tiles=new HashMap<TileType,Tile>();
		tiles.put(TileType.BRICK, new Tile(atlas.cut(32*TILE_SCALE,0*TILE_SCALE,TILE_SCALE,TILE_SCALE),
				TILE_IN_GAME_SCALE, TileType.BRICK));
		tiles.put(TileType.METAL, new Tile(atlas.cut(32*TILE_SCALE,2*TILE_SCALE,TILE_SCALE,TILE_SCALE),
				TILE_IN_GAME_SCALE, TileType.METAL));
		tiles.put(TileType.WATER, new Tile(atlas.cut(32*TILE_SCALE,4*TILE_SCALE,TILE_SCALE,TILE_SCALE),
				TILE_IN_GAME_SCALE, TileType.WATER));
		tiles.put(TileType.GRASS, new Tile(atlas.cut(34*TILE_SCALE,4*TILE_SCALE,TILE_SCALE,TILE_SCALE),
				TILE_IN_GAME_SCALE, TileType.GRASS));
		tiles.put(TileType.ICE, new Tile(atlas.cut(36*TILE_SCALE,4*TILE_SCALE,TILE_SCALE,TILE_SCALE),
				TILE_IN_GAME_SCALE, TileType.ICE));
		tiles.put(TileType.EMPTY, new Tile(atlas.cut(36*TILE_SCALE,6*TILE_SCALE,TILE_SCALE,TILE_SCALE),
				TILE_IN_GAME_SCALE, TileType.EMPTY));
		try {
			tileMap=Utils.levelParser("res/level.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		grassCoords=new ArrayList<Point>();
		for(int i=0;i<tileMap.length;i++)
			for(int j=0;j<tileMap[i].length;j++) {
				Tile tile=tiles.get(TileType.fromNumeric(tileMap[i][j]));
				if(tile.type()==TileType.GRASS)
					grassCoords.add(new Point(j*SCALED_TILE_SIZE, i*SCALED_TILE_SIZE));
			}

	}
	public void addBullet(Bullet b) {
		bullets.add(b);
	}
	
	public void update(Player p) {
		int x1=(int)p.x();int y1=(int)p.y();int x2=x1+(int)(Player.SPRITE_SCALE*p.scale());int y2=y1+ (int)(Player.SPRITE_SCALE*p.scale());
		if(p.getShot())
			bullets.add(p.shot());
		for(int i=0;i<tileMap.length;i++) {
			for(int j=0;j<tileMap[i].length;j++) {
				if(tileMap[i][j]!=0 && tileMap[i][j]!=4) {
					if(cheq(x1, y1, i, j)||cheq(x1, y2, i, j)||cheq(x2, y1, i, j)||cheq(x2, y2, i, j) ||cheq(x1+(x2-x1)/2, y1+(y2-y1)/2, i, j)) {
						tileMap[i][j]=0;
					}
				}
			}
		}
	}
	
	private boolean cheq(int x,int y,int i,int j) {
		if(x>=j*SCALED_TILE_SIZE && x<=(j+1)*SCALED_TILE_SIZE && y>=i*SCALED_TILE_SIZE && y<=(i+1)*SCALED_TILE_SIZE)
			return true;
		else return false;
	}
	
	public void render(Graphics2D g) {
		int s=bullets.size();
		for(int b=0;b<s;b++) {
			boolean flaq=bullets.get(b).update();
			if(!flaq) {bullets.get(b).exist=false;bullets.remove(b);b--;s--;}
			else {
				for(int i=0;i<tileMap.length;i++) {
					for(int j=0;j<tileMap[i].length;j++) {
						if(flaq && tileMap[i][j]!=0 && tileMap[i][j]!=4)
							if(cheq(bullets.get(b).x()+(int)(0.5*TILE_SCALE),bullets.get(b).y()+(int)(0.5*TILE_SCALE), i, j)) {
								if(tileMap[i][j]!=2) {bullets.get(b).exist=false;bullets.remove(b);tileMap[i][j]=0;b--;s--;flaq=false;}
								else {bullets.get(b).exist=false;bullets.remove(b);b--;s--;flaq=false;}
							}
					}
				}
			}
		}
		for(int i=0;i<tileMap.length;i++) {
			for(int j=0;j<tileMap[i].length;j++) {
				Tile tile=tiles.get(TileType.fromNumeric(tileMap[i][j]));
				if(tile.type()!=TileType.GRASS)
				tile.render(g, j*SCALED_TILE_SIZE, i*SCALED_TILE_SIZE);
			}
		}
		for(Bullet b:bullets) {
			b.render(g);
		}
	}
	
	public void renderGrass(Graphics2D g) {
		for(Point p:grassCoords) {
			tiles.get(TileType.GRASS).render(g, p.x, p.y);
		}
	}
}
