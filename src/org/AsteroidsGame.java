package org;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
	List<Ship> ships = new ArrayList<Ship>();
	Boolean gameOver = false;
	Boolean youWin = false;

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
				fire();
				collision();

				Iterator<Ship> shipIter = ships.iterator();
				while (shipIter.hasNext()) {
					Ship shipQ = shipIter.next();
					shipQ.draw(gc);
					shipQ.update(keyboarding, gc);
				}

				Iterator<Bullet> bullIter = bullets.iterator();
				while (bullIter.hasNext()) {
					Bullet bulletQ = bullIter.next();
					bulletQ.update(keyboarding, gc);
					if (bulletQ.bulletFrames > 30) {
						bullIter.remove();
					} else {
						bulletQ.draw(gc);
					}

				}

				Iterator<Asteroid> astIter = asteroids.iterator();
				while (astIter.hasNext()) {
					Asteroid astQ = astIter.next();
					astQ.draw(gc);
					astQ.update(gc);
				}
				if (gameOver) {

					bullets.remove(bullet);
					gc.setFont(Font.font(canvasH / 9));
					gc.setStroke(Color.RED);
					gc.strokeText("Game Over", canvasW / 5, canvasH / 2);
				}

				if (youWin) {
					gc.setFont(Font.font(canvasH / 9));
					gc.setStroke(Color.GREEN);
					gc.strokeText("You Win!", canvasW / 4, canvasH / 2);
				}

			}

		}.start();
	}

	@Override
	public void init() {

		ship = new Ship(Color.BLUE);
		ships.add(ship);
		int astNum = randomGen.nextInt(5) + 3;
		

		for (int i = 0; i < astNum; i++) {
			
			asteroid = new Asteroid();
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

		Iterator<Bullet> bullIter = bullets.iterator();

		while (bullIter.hasNext()) {

			Bullet bulletQ = bullIter.next();

			Double bX1 = bulletQ.bulletX - bulletQ.bulletW / 2;
			Double bX2 = bulletQ.bulletX + bulletQ.bulletW / 2;
			Double bY1 = bulletQ.bulletY - bulletQ.bulletH / 2;
			Double bY2 = bulletQ.bulletY + bulletQ.bulletH / 2;

			ListIterator<Asteroid> astIter = asteroids.listIterator();

			while (astIter.hasNext()) {
				Asteroid astQ = astIter.next();

				Double aX1 = astQ.astX - astQ.astW / 2;
				Double aX2 = astQ.astX + astQ.astW / 2;
				Double aY1 = astQ.astY - astQ.astH / 2;
				Double aY2 = astQ.astY + astQ.astH / 2;

				if (bX1 <= aX2 && bX2 >= aX1 && bY1 <= aY2 && bY2 >= aY1) {

					bullIter.remove();
					astIter.remove();
					astIter.add(new Asteroid(astQ.astX, astQ.astY, astQ.astW / 2, astQ.astH / 2, astQ.astSpeed));
					astIter.add(new Asteroid(astQ.astX, astQ.astY, astQ.astW / 2, astQ.astH / 2, astQ.astSpeed));
					break;

				}

				if (asteroids.size() == 0) {
					youWin = true;
				}

			}

		}

		Iterator<Asteroid> astIter = asteroids.iterator();
		while (astIter.hasNext()) {
			Asteroid astQ = astIter.next();

			Double aX1 = astQ.astX - astQ.astW / 2;
			Double aX2 = astQ.astX + astQ.astW / 2;
			Double aY1 = astQ.astY - astQ.astH / 2;
			Double aY2 = astQ.astY + astQ.astH / 2;

			Double sX1 = Ship.shipX - 25;
			Double sX2 = Ship.shipX + 25;
			Double sY1 = Ship.shipY - 25;
			Double sY2 = Ship.shipY + 25;

			if (sX1 <= aX2 && sX2 >= aX1 && sY1 <= aY2 && sY2 >= aY1) {

				ships.remove(ship);
				gameOver = true;

			}

		}
	}

}
