package org;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	Ship ship;
	Bullet bullet;
	Bullet bullet2;
	Bullet bullet3;
	List<Bullet> bullets = new ArrayList<Bullet>();
	

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

		init();

		new AnimationTimer() {

			@Override
			public void handle(long now) {

				gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
				ship.update(keyboarding, gc);
				ship.draw(gc);
				//bullet.update(keyboarding, gc);
				
				Iterator<Bullet> iter = bullets.iterator();
				while(iter.hasNext()) {
					Bullet queue = iter.next();
					queue.update(keyboarding, gc);
				}
				


			}

		}.start();
	}
	
	public void init() {
		ship = new Ship(Color.BLUE);
		
	    bullet = new Bullet(Color.RED);
		bullet2 = new Bullet(Color.ORANGE);
		bullet3 = new Bullet(Color.WHITE);
		
		bullets.add(bullet);
		bullets.add(bullet2);
		bullets.add(bullet3);
	}
}
