package com.maurice.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.maurice.GameWorld.GameRenderer;
import com.maurice.GameWorld.GameWorld;
import com.maurice.GameWorld.GameWorld.GameState;
import com.maurice.Screens.GameScreen;
import com.sun.org.apache.bcel.internal.generic.LUSHR;

public class Player {
	private Vector3 position;
	private Vector3 velocity;
	private Vector3 acceleration;
	
	public static int HEIGHT=30;
	public static int WIDTH=30;
	public static int RADIUS=15;
	public static int COLLISION_HEIGHT=48;
	public static int COLLISION_ANTI_HEIGHT=4;//at back side
	public static int COLLISION_WIDTH=34;
	public static int COLLISION_ANTI_WIDTH=6;//WIDTH FROM OTHER SIDE
	private boolean isAlive=true;
	private boolean inAir=true;
	private GameWorld world;
	private Circle boundingCircle;
	
	public static float GRAVITY=2400;
	private float JUMP_TOLERANCE=20;
	private float DRAG=1.1f;
	
	private int GROUND_HEIGHT;
	private int surfaceHeight;
	private float jumpHeight;
	public int jumpNum=0;
	private boolean goLeft=false;
	private boolean goRight=false;
	public float angle=0;
	private float gyroVel;
	public Player(float x, float y, int velX, int velY, GameWorld gameWorld) {
		world=gameWorld;
		GROUND_HEIGHT=world.getGROUND_HEIGHT();
		position = new Vector3(x, y,0);
		velocity = new Vector3(0, velY,0);
		acceleration = new Vector3(0, GRAVITY,0);
        boundingCircle = new Circle();
		setAlive(true);
	}
	public void update(float delta){
		if((position.y+HEIGHT+velocity.y*delta*0.5)<GROUND_HEIGHT){
			velocity.add(acceleration.cpy().scl(delta));
			position.add(velocity.cpy().scl(delta));
			//inAir=true;
		}
		else if(velocity.y>30){
			System.out.println("bounce"+velocity.y+"="+position.y);
			velocity.y=-velocity.y*0.2f;
			position.add(velocity.cpy().scl(delta));
		}
		else if((velocity.y<30)&&(velocity.y>-50)){
			position.y=GROUND_HEIGHT-HEIGHT;
			//inAir=false;
			velocity.y=0;
			//System.out.println("stoppped="+velocity.y);
		}
		if(position.y+HEIGHT<GROUND_HEIGHT-JUMP_TOLERANCE)
			inAir=true;
		else
			inAir=false;
		//if((position.y+NEXTJUMP_MARGIN)<surfaceHeight){
			//inAir=true;;
		//}
		//else{
			//inAir=false;
	//	}
		angle+=delta*600;
		angle%=360;
		//System.out.println("delta="+delta);
		
		//UPDATE BOUNDING CIRCLE
		boundingCircle.set(position.x + WIDTH/2, position.y + HEIGHT/2, RADIUS);
	}
	public void jump() {
		if(!inAir){
			velocity.y=-640;
			System.out.println("jump initiated");
		}
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public Vector3 getPosition(){
		return position;
	}
	public Vector3 getVelocity(){
		return velocity;
	}
	public boolean isInAir() {
		//System.out.println("player height="+position.z);
		return position.z>0.4;
	}
	public Circle getBoundingCircle() {
        return boundingCircle;
    }
	public void restart(){
		position.y=100;
	}
	
}
