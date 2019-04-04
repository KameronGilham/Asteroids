package org;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class Ship {
	
	Color color;
	int degrees;
	int shipX;
	int shipY;
	int xVelocity;
	int yVelocity;
	int acceleration;
	int angle = (int) (degrees * (Math.PI/180));
	
public Ship() {
	
	color = Color.WHITE;
	shipX = AsteroidsGame.canvasW/2;
	shipY = AsteroidsGame.canvasH/2;
	degrees = 0;
	acceleration = 1;
	xVelocity = 0;
	yVelocity = 0;
	
}
	
	
	
public int getShipX() {
	return shipX;
}



public void setShipX(int shipX) {
	this.shipX = shipX;
}



public int getShipY() {
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
		
			
			
		if (angle > Math.PI * 2) {
			angle -= Math.PI * 2;
		}else if (angle < 0) {
			angle += Math.PI * 2;
		}
		
		
		}
		if(KeyboardInputs.shipFWD) {
			
		  xVelocity += Math.sin(angle) * acceleration;
		  yVelocity -= Math.cos(angle) * acceleration;
		  
		  shipX += xVelocity;
		  
		  shipY =+ yVelocity;
		  
		  
		  
		}
	
	}

}
