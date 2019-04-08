package org;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class Ship {

	Color color;
	double degrees;
	static double shipX;
	static double shipY;
	double xVelocity;
	double yVelocity;
	double acceleration;
	static double angle;

	public Ship(Color c) {

		color = c;
		shipX = AsteroidsGame.canvasW / 2;
		shipY = AsteroidsGame.canvasH / 2;
		degrees = 0;
		acceleration = 0;
		xVelocity = 0;
		yVelocity = 0;

	}

	public void draw(GraphicsContext gc) {

		double[] xPoints = new double[] { shipX, shipX, shipX - 25, shipX, shipX + 25, shipX };
		double[] yPoints = new double[] { shipY, shipY - 33.33, shipY + 16.66, shipY + 5, shipY + 16.66, shipY - 33.33 };

		gc.setFill(color);

		gc.save();
		gc.transform(new Affine(new Rotate(degrees, shipX, shipY)));
		gc.fillPolygon(xPoints, yPoints, 6);
		gc.restore();
	}

	public void update(KeyboardInputs keyboarding, GraphicsContext gc) {

		if (KeyboardInputs.shipSpinR) {
			degrees += 5;
		}

		if (KeyboardInputs.shipSpinL) {
			degrees -= 5;
		}

		if (degrees > 360) {
			degrees -= 360;

		} else if (degrees < 0) {
			degrees += 360;
		}
		
		angle = (degrees * (Math.PI / 180));

		if (KeyboardInputs.shipFWD) {
			
			if (acceleration < 15) {
				acceleration += .25;
			}

		}
		if (KeyboardInputs.shipBACK) {
			if (acceleration > -10)
			acceleration -= .25;
		}
		
		xVelocity = Math.sin(angle) * acceleration;
		yVelocity = Math.cos(angle) * acceleration;
		
		shipX += xVelocity;

		shipY -= yVelocity;
		
		if (shipX < 0) {
			shipX += AsteroidsGame.canvasW;
		}else if (shipX > AsteroidsGame.canvasW) {
			shipX -= AsteroidsGame.canvasH;
		}
		
		if (shipY < 0) {
			shipY += AsteroidsGame.canvasH;
		}else if (shipY > AsteroidsGame.canvasH) {
			shipY -= AsteroidsGame.canvasH;
			
		}

	}

}
