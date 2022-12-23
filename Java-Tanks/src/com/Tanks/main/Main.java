package com.Tanks.main;


import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Timer;

import com.Tanks.display.Display;
import com.Tanks.game.Game;

public class Main {

	public static void main(String[] args) {
		Game tanksGame=new Game();
		tanksGame.start();
	}

}
