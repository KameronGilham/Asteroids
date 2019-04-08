package org;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet {
	
	Color color;
	boolean enroute = false;
	double bulletX;
	double bulletY;
	double bulletW;
	double bulletH;
	double xVelocity;
	double yVelocity;
	double acceleration;
	double angle;
	double bulletFrames;
	
	
	public Bullet(Color c) {
		color = c;
		bulletW = 10;
		bulletH = 10;
		bulletX = Ship.shipX - bulletW/2;
		bulletY = Ship.shipY - bulletH/2;
		bulletFrames = 0;
		acceleration = 20;
		angle = Ship.angle;
		xVelocity = 0;
		yVelocity = 0;
		
	}
	
	public void draw(GraphicsContext gc) {
		
		gc.setFill(color);
		gc.save();
		gc.fillRect(bulletX, bulletY, bulletW, bulletH);
		gc.restore(); 
		
	}

	public void update(KeyboardInputs keyboarding, GraphicsContext gc) {
		
		
		
		if (KeyboardInputs.shoot) {
			enroute = true;
			}
	
		if (enroute) {
			if (bulletFrames == 0) {
				bulletX = Ship.shipX - bulletW/2;
				bulletY = Ship.shipY - bulletH/2;
				angle = Ship.angle;
			}
			draw(gc);
			bulletFrames++;
		}
		
		
		xVelocity = Math.sin(angle) * acceleration;
		yVelocity = Math.cos(angle) * acceleration;
		
		bulletX += xVelocity;

		bulletY -= yVelocity;
		
		if (bulletFrames > 45){
			enroute = false;
			bulletX = Ship.shipX - bulletW/2;
			bulletY = Ship.shipY - bulletH/2;
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
