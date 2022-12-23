package com.Tanks.game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.Tanks.IO.Input;
import com.Tanks.display.Display;
import com.Tanks.game.level.Level;
import com.Tanks.graphics.Sprite;
import com.Tanks.graphics.SpriteSheet;
import com.Tanks.graphics.TextureAtlas;
import com.Tanks.utils.Time;

public class Game implements Runnable{
	
	public static final int WIDTH=800;
	public static final int HEIGHT=600;
	public static final String TITLE ="Tanks";
	public static final int CLEAR_COLOR=0xff000000;
	public static final int NUM_BUFFERS=3;
	
	public static final float UPDATE_RATE=60.0f;
	public static final float UPDATE_INTERVAL=Time.SECOND/UPDATE_RATE;
	public static final long IDLE_TIME=1;
	
	public static final String ATLAS_FILE_NAME_STRING="texture_atlas.png";
	
	private boolean running;
	private Thread gameThread; 
	
	private Graphics2D graphics;
	private TextureAtlas atlas;
	private Input input;
	private Player player;
	private Level lvl;


	public Game() {
		running=false;
		Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
		graphics=Display.getgraphics();
		input=new Input();
		Display.addInputListener(input);
		atlas=new TextureAtlas(ATLAS_FILE_NAME_STRING);
		player=new Player(300, 300, 2, 1, atlas);
		lvl=new Level(atlas);
	}
	
	public synchronized void start() {
		if(running)
			return;
		running=true;
		gameThread=new Thread(this);
		gameThread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running=false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		cleanUp();
	}
	
	private void update() {
		player.update(input);
		lvl.update(player);
	}
	
	private void render() {
		Display.clear();
		lvl.render(graphics);
		player.render(graphics);
		lvl.renderGrass(graphics);
		Display.swapBuffers();
	}
	
	public void run() {
		int fps=0;
		int upd=0;
		int updL=0;
		
		long count=0;
		
		float delta=0;
		
		long lastTime=Time.get();
		while(running) {
			long now=Time.get();
			long elapsedTime=now-lastTime;
			lastTime=now;
			
			count+=elapsedTime;
			
			boolean render=false;
			delta+=(elapsedTime/UPDATE_INTERVAL);
			while(delta>1) {
				update();
				upd++;
				delta--;
				if(render)
					updL++;
				else
					render=true;
			}
			
			if(render) {
				render();
				fps++;
				}
			else {
				try {
					Thread.sleep(IDLE_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if(count>=Time.SECOND) {
				Display.setTitle(TITLE+" || fps: "+fps+" | upd: "+upd+" | updL: "+updL);
				upd=0;
				fps=0;
				updL=0;
				count=0;
			}
		}
	}
	
	private void cleanUp() {
		Display.destroy();
	}
}
