package com.maurice.GameObjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameWorld.GameRenderer;

public class Flippable extends Scrollable{
    private boolean onTop;

    public Flippable(float x, float y, int width, int height, float velX, float scrollSpeed,boolean onTop) {
    	super(x, y,  width,  height, velX, scrollSpeed);
    	this.onTop=onTop;
    }
    public boolean collides(Player player) {
    	//System.out.println("intersects");
        return super.collides(player);
    }
    public boolean isOnTop() {
		return onTop;
	}
	public void setOnTop(boolean onTop) {
		this.onTop = onTop;
	}
}
