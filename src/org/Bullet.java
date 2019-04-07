package org;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet {
	
	double bulletX;
	double bulletY;
	double bulletW;
	double bulletH;
	boolean enroute = false;
	double xVelocity;
	double yVelocity;
	double acceleration;
	static double angle;
	Color color;
	double bulletFrames;
	
	
	public Bullet() {
		color = Color.WHITE;
		bulletX = Ship.shipX;
		bulletY = Ship.shipY;
		bulletW = 10;
		bulletH = 10;
		bulletFrames = 0;
		acceleration = 20;
		angle = Ship.angle;
		xVelocity = 0;
		yVelocity = 0;
		
	}
	
	public void draw(GraphicsContext gc) {
		
		gc.setFill(color);
		gc.fillRect(bulletX, bulletY, bulletW, bulletH);
		
	}

	public void update(KeyboardInputs keyboarding, GraphicsContext gc) {
		
		if (KeyboardInputs.shoot) {
			enroute = true;
			}
	
		if (enroute) {
			draw(gc);
			bulletFrames++;
		}
		
		
		xVelocity = Math.sin(angle) * acceleration;
		yVelocity = Math.cos(angle) * acceleration;
		
		bulletX += xVelocity;

		bulletY -= yVelocity;
		
		if (bulletFrames > 45){
			enroute = false;
			bulletX = Ship.shipX;
			bulletY = Ship.shipY;
			bulletFrames= 0;
			
		}
		
		if (bulletX < 0) {
			bulletX += AsteroidsGame.canvasW;
		}else if (bulletX > AsteroidsGame.canvasW) {
			bulletX -= AsteroidsGame.canvasH;
		}
		
		if (bulletY < 0) {
			bulletY += AsteroidsGame.canvasH;
		}else if (bulletY > AsteroidsGame.canvasH) {
			bulletY -= AsteroidsGame.canvasH;
			
		}
		
		
	}
}
