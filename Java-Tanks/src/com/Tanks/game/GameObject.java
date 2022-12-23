package com.Tanks.game;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Observable;

public abstract class GameObject extends Observable{
	protected float x;
	protected float y;
	protected float speed;
	protected Dimension borders;
	
	protected GameObject(float x,float y,float speed) {
		this.x=x;
		this.y=y;
		this.speed=speed;
	}
	
	public abstract void destroy();
	
	public abstract void update();
	
	public abstract void render(Graphics2D g);
}
