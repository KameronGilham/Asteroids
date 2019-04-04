package org;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class Ship {
	
	Color color;
	int degrees;
	double shipX;
	double shipY;
	int xVelocity;
	int yVelocity;
	double acceleration;
	double angle = (degrees * (Math.PI/180));
	
public Ship() {
	
	color = Color.WHITE;
	shipX = AsteroidsGame.canvasW/2;
	shipY = AsteroidsGame.canvasH/2;
	degrees = 0;
	acceleration = 1; 
	xVelocity = 0;
	yVelocity = 0;
	
}
	
	
	
public double getShipX() {
	return shipX;
}



public void setShipX(int shipX) {
	this.shipX = shipX;
}



public double getShipY() {
	return shipY;
}



public void setShipY(int shipY) {
	this.shipY = shipY;
}



public void draw(GraphicsContext gc) {
	
	double[] xPoints = new double[]{shipX, shipX, shipX - 25, shipX + 25, shipX};
	double[] yPoints = new double[]{shipY, shipY - 33.33, shipY + 16.66, shipY + 16.66, shipY - 33.33};
	
		gc.setFill(color);
		
		gc.save();
		gc.transform(new Affine(new Rotate(degrees,shipX,shipY)));
		gc.fillPolygon(xPoints, yPoints, 5);
		gc.restore();
	}


	public void update(KeyboardInputs keyboarding, GraphicsContext gc) {
		
		if(KeyboardInputs.shipSpinR) {
			degrees++;
		}
		if(KeyboardInputs.shipSpinL) {
			degrees--;
		
			
			
		if (degrees > 360) {
			degrees -= 360;
		}else if (degrees < 0) {
			degrees += 360;
		}
		
		
		}
		if(KeyboardInputs.shipFWD) {
			
		  xVelocity += Math.cos(angle) * acceleration;
		  yVelocity += Math.sin(angle) * acceleration;
		  
		  shipX += xVelocity;
		  
		  shipY += yVelocity;
		  
		  
		}
	
	}

}
