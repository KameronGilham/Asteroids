package org;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AsteroidsGame extends Application {

	static double canvasW = 800;
	static double canvasH = 800;
	Random randomGen = new Random();
	Ship ship;
	Asteroid asteroid;
	Bullet bullet;
	List<Bullet> bullets = new ArrayList<Bullet>();
	List<Asteroid> asteroids = new ArrayList<Asteroid>();

	@Override
	public void start(Stage stage) throws Exception {
		Canvas canvas = new Canvas(canvasW, canvasH);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		KeyboardInputs keyboarding = new KeyboardInputs();

		BorderPane game = new BorderPane();
		Scene scene = new Scene(game, canvasW, canvasH, Color.BLACK);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> keyboarding.keyboardInput(key, gc));
		scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> keyboarding.keyboardInput(key, gc));
		game.setCenter(canvas);
		stage.setScene(scene);
		stage.setTitle("Asteroids");
		stage.show();

		new AnimationTimer() {

			@Override
			public void handle(long now) {

				gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
				ship.update(keyboarding, gc);
				ship.draw(gc);
				fire();
				collision();

				Iterator<Bullet> iter = bullets.iterator();
				while (iter.hasNext()) {
					Bullet bulletQ = iter.next();
					bulletQ.update(keyboarding, gc);
					if (bulletQ.bulletFrames > 30) {
						iter.remove();
					} else {
						bulletQ.draw(gc);
					}

				}

				Iterator<Asteroid> iter2 = asteroids.iterator();
				while (iter2.hasNext()) {
					Asteroid astQ = iter2.next();
					astQ.draw(gc);
					astQ.update(gc);
				}

			}

		}.start();
	}

	@Override
	public void init() {

		ship = new Ship(Color.BLUE);
		int astNum = randomGen.nextInt(5) + 3;

		for (int i = 0; i < astNum; i++) {

			int astDegrees = randomGen.nextInt(360) + 1;
			asteroid = new Asteroid(astDegrees);
			asteroids.add(asteroid);
		}

	}

	public void fire() {

		if (KeyboardInputs.shoot && bullets.size() <= 3) {

			bullet = new Bullet(Color.RED);
			bullets.add(bullet);

		}

	}

	public void collision() {
		Double sX1 = Ship.shipX + 25;
		Double sX2 = Ship.shipX - 25;
		Double sY1 = Ship.shipY - 25;
		Double sY2 = Ship.shipY + 25;

		Iterator<Bullet> iter = bullets.iterator();
		
		while (iter.hasNext()) {

			Bullet bulletQ = iter.next();

			Double bX1 = bulletQ.bulletX - bulletQ.bulletW / 2;
			Double bX2 = bulletQ.bulletX + bulletQ.bulletW / 2;
			Double bY1 = bulletQ.bulletY - bulletQ.bulletH / 2;
			Double bY2 = bulletQ.bulletY + bulletQ.bulletH / 2;
			
			Iterator<Asteroid> iter2 = asteroids.iterator();
			
			System.out.println("b");
			
			while (iter2.hasNext()) {
				Asteroid astQ = iter2.next();

				Double aX1 = astQ.astX - astQ.astW/2;
				Double aX2 = astQ.astX + astQ.astW/2;
				Double aY1 = astQ.astY - astQ.astH/2;
				Double aY2 = astQ.astY + astQ.astH/2;
				
				System.out.println("a");
				
				if(bX1 <= aX2 && bX2 >= aX1 && bY1 <=aY2 && bY2 >= aY1) {
					
					iter.remove();
					iter2.remove();
					
					System.out.println("r");
				}

			}
		}
		

		
		
		

	}

}
