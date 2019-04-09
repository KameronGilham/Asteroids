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
	Bullet bullet2;
	Bullet bullet3;
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

				Iterator<Bullet> iter = bullets.iterator();
				while (iter.hasNext()) {
					Bullet queue = iter.next();
					queue.update(keyboarding, gc);
					if (queue.bulletFrames > 30) {
						iter.remove();
					} else {
						queue.draw(gc);
					}

				}

				Iterator<Asteroid> iter2 = asteroids.iterator();
				while (iter2.hasNext()) {
					Asteroid queue2 = iter2.next();
					queue2.draw(gc);
					queue2.update(gc);
				}

			}

		}.start();
	}

	@Override
	public void init() {

		ship = new Ship(Color.BLUE);

		for (int i = 0; i < randomGen.nextInt(5) + 1; i++) {

			int astDegrees = randomGen.nextInt(360) + 1;
			asteroid = new Asteroid(astDegrees);
			asteroids.add(asteroid);
		}

	}

	public void fire() {

		if (KeyboardInputs.shoot) {
			
			bullet = new Bullet(Color.RED);
			bullets.add(bullet);

		}

	}
}
