package com.maurice.GameObjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameWorld.GameRenderer;

public class Scrollable {
	
    // Protected is similar to private, but allows inheritance by subclasses.
    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isScrolledLeft;
    protected Rectangle boundingRect;

    public Scrollable(float x, float y, int width, int height, float velX, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(-scrollSpeed,0);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;
        boundingRect = new Rectangle();
    }



	public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
        //System.out.println("position set="+position.x);
        // If the Scrollable object is no longer visible:
        if (position.x+width <= 0) {
        	isScrolledLeft = true;
        }
        boundingRect.set(position.x, position.y, width, height);
    }

    // Reset: Should Override in subclass for more specific behavior.
    public void reset(float newX) {
        this.position.x = newX;
        isScrolledLeft = false;
        //System.out.println("background reset");
        
    }

    // Getters for instance variables
    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }
    
    public Vector2 getPosition(){
    	return position;
    }

    public float getTailX() {
        return position.x+width;
    	//return ;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public boolean collides(Player player) {
    	//System.out.println("intersects");
        return (Intersector.overlaps(player.getBoundingCircle(), boundingRect));
    }
    public Rectangle getBoundingRect() {
		return boundingRect;
	}
    public void levelUp(float x){
    	velocity.x=-x;
    }

}
