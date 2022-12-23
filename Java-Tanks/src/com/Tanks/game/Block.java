package com.Tanks.game;

import java.awt.Graphics2D;

import com.Tanks.game.level.Tile;
import com.Tanks.game.level.TileType;

public class Block{
	private int x;
	private int y;
	private TileType type;

	public Block(float x, float y, TileType t) {
		this.x=(int)x;
		this.y=(int)y;
		this.type=t;
	}

	public TileType type() {
		return type;
	}
	
	public void destroy() {
		this.type=TileType.EMPTY;
	}
}
