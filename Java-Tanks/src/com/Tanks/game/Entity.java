package com.Tanks.game;

import java.awt.Graphics2D;
import java.util.List;

import com.Tanks.IO.Input;

public abstract class Entity {
	
	public final EntityType type;
	
	private float x;
	private float y;
	
	protected Entity(EntityType type,float x,float y) {
		this.type=type;
		this.x(x);
		this.y(y);
	}
	
	public abstract void update(Input input);
	
	public abstract void render(Graphics2D g);

	public float x() {
		return x;
	}

	public void x(float x) {
		this.x = x;
	}

	public float y() {
		return y;
	}

	public void y(float y) {
		this.y = y;
	}
}
