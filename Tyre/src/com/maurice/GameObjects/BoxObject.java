package com.maurice.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameWorld.GameWorld;

public class BoxObject extends Flippable{
	public static int WIDTH=30;
	public static int HEIGHT=30;
	private boolean isAlive;
	public BoxObject(float x, float y, float scrollSpeed, boolean onTop) {
		super(x, y, WIDTH, HEIGHT, 0, scrollSpeed, onTop);
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
}
