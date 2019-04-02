package org;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ship {
	
	Color color;
	int degrees;
	int shipX;
	int shipY;
	
public Ship() {
	
	color = Color.WHITE;
	shipX = AsteroidsGame.canvasW/2;
	shipY = AsteroidsGame.canvasH/2;
	
	
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
	
	double[] xPoints = new double[]{shipX , shipX - 50, shipX + 50};
	double[] yPoints = new double[]{shipY, shipY + 50, shipY + 50};
	
		gc.setFill(color);
		
		gc.save();
		gc.translate(getShipX(), getShipY());
		gc.rotate(degrees);
		gc.fillPolygon(xPoints, yPoints, 3);
		gc.translate(-getShipX(), -getShipY());
		gc.restore();
	}


	public void update(KeyboardInputs keyboarding, GraphicsContext gc) {
		
		if(KeyboardInputs.shipSpinR) {
			degrees++;
		}
		if(KeyboardInputs.shipSpinL) {
			degrees--;
		}
		
	
	}

}
