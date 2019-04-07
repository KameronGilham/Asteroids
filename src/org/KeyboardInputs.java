package org;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardInputs {
	
	static boolean shipFWD = false;
	static boolean shipBACK = false;
	static boolean shipSpinL = false;
	static boolean shipSpinR = false;
	static boolean shoot = false;
	

	public void keyboardInput(KeyEvent key, GraphicsContext gc) {
		if (key.getEventType() == KeyEvent.KEY_PRESSED) {
			if (key.getCode() == KeyCode.UP) {
				shipFWD = true;
			}
			if (key.getCode() == KeyCode.DOWN) {
				shipBACK = true;
			}
			if (key.getCode() == KeyCode.LEFT) {
				shipSpinL = true;
			}
			if (key.getCode() == KeyCode.RIGHT) {
				shipSpinR = true;
			}
			if (key.getCode() == KeyCode.SPACE) {
				Bullet.angle = Ship.angle;
				shoot = true;
				
			}
			
		}
		if (key.getEventType() == KeyEvent.KEY_RELEASED) {
			if (key.getCode() == KeyCode.UP) {
				shipFWD = false;
			}
			if (key.getCode() == KeyCode.DOWN) {
				shipBACK = false;
			}
			if (key.getCode() == KeyCode.LEFT) {
				shipSpinL = false;
			}
			if (key.getCode() == KeyCode.RIGHT) {
				shipSpinR = false;
			}if (key.getCode() == KeyCode.SPACE) {
				shoot = false;
			}
		}
		

	}

}