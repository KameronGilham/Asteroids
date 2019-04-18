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
	double astSpeed;
	double astDegrees;
	double angle;
	double astFrames;
	Random randomGen = new Random();

	public Asteroid(double x, double y, double w, double h, double s) {
		
		astX = x;
		astY =  y;
		astW =  w;
		astH =  h;
		astSpeed = s;
		astDegrees = new Random().nextInt(360) + 1;
		color = Color.GRAY;
		astFrames = 0;
		xVelocity = 0;
		yVelocity = 0;
		angle = (astDegrees * (Math.PI / 180));
		
	}
	
	public Asteroid() {
		
		this(new Random().nextInt(100) - 100,
				new Random().nextInt(100) - 100,
				new Random().nextInt(60) + 20,
				new Random().nextInt(60) + 20,
				new Random().nextInt(5) + 1);

	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(color);
		gc.save();
		gc.fillRect(astX -astW/2, astY- astH/2, astW, astH);
		gc.restore();
	}
	
	public void update(GraphicsContext gc) {
		
		xVelocity = Math.sin(angle) * astSpeed;
		yVelocity = Math.cos(angle) * astSpeed;
		
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
