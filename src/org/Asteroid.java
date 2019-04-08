package org;

import java.util.Random;

import javafx.scene.paint.Color;

public class Asteroid {

	Color color;
	double astX;
	double astY;
	double xVelocity;
	double yVelocity;
	double acceleration;
	double degrees;
	static double angle;
	Random randomGen = new Random();

	public Asteroid() {
		color = Color.WHITE;
		astX = randomGen.nextInt((int) AsteroidsGame.canvasW);
		astY =  randomGen.nextInt((int) AsteroidsGame.canvasH);
		xVelocity = 0;
		yVelocity = 0;
		acceleration = randomGen.nextInt(20) + 1;
		degrees = randomGen.nextInt(360)+1;
		angle = (degrees * (Math.PI / 180));
		
	}
}
