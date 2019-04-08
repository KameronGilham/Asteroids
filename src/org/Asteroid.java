package org;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Asteroid {

	Color color;
	double astX;
	double astY;
	double astW;
	double astH;
	double xVelocity;
	double yVelocity;
	double acceleration;
	double degrees;
	double angle;
	double astFrames;
	Random randomGen = new Random();

	public Asteroid(int d) {
		color = Color.WHITE;
		astX = randomGen.nextInt((int) AsteroidsGame.canvasW);
		astY =  randomGen.nextInt((int) AsteroidsGame.canvasH);
		astW =  randomGen.nextInt(20) + 20;
		astH =  randomGen.nextInt(20) + 20;
		astFrames = 0;
		xVelocity = 0;
		yVelocity = 0;
		acceleration = randomGen.nextInt(5) + 1;
		degrees = d;
		angle = (degrees * (Math.PI / 180));
		
	}
	public void draw(GraphicsContext gc) {
		gc.setFill(color);
		gc.save();
		gc.fillRect(astX -astW/2, astY- astH/2, astW, astH);
		gc.restore();
	}
	
	public void update(GraphicsContext gc) {
		
		//if(astFrames == 0) {
		//	angle = ((randomGen.nextInt(360)+1) * (Math.PI / 180));
		//}
		
		xVelocity = Math.sin(angle) * acceleration;
		yVelocity = Math.cos(angle) * acceleration;
		
		astX += xVelocity;

		astY -= yVelocity;
		
		if (astX < 0) {
			astX += AsteroidsGame.canvasW;
		}else if (astX > AsteroidsGame.canvasW) {
			astX -= AsteroidsGame.canvasH;
		}
		
		if (astY < 0) {
			astY += AsteroidsGame.canvasH;
		}else if (astY > AsteroidsGame.canvasH) {
			astY -= AsteroidsGame.canvasH;
			
		}
		
		astFrames++;
	}
}
