package com.maurice.GameWorld;

import java.util.ArrayList;
import java.util.Random;

import com.maurice.GameObjects.Player;
import com.maurice.GameObjects.ScrollHandler;
import com.maurice.GameObjects.Scrollable;
import com.maurice.Screens.GameScreen;
import com.maurice.tyre.TyreGame;


public class GameWorld{
	
	private int midPointX;
    private int midPointY;
    private int gameHeight;
    private int gameWidth;
	public static int PLAYER_POSX=100;
    public static float RAMP_INCLINATION=0.8F;
    public int score=0;
    
    public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	private GameState currentState;
    public enum GameState {
        READY, RUNNING, GAMEOVER
    }
    private TyreGame game;
    private GameScreen gameScreen;
    private float runtime=0;
    private int currlevel=1;
    private static int GROUND_HEIGHT;//FROM TOP
    public static float COLLISION_OBJECTS_HEIGHT=4F;
    private int gameSpeed=5;//set speed
    private ArrayList<Scrollable> blocks;
    public boolean isSheild=false;

	Random rand = new Random();
	private Player player;
	private ScrollHandler scroller;
	String toast="";
	int lastToast=0;
	
	//SETTINGS RELATED
	public boolean settingsOn=false;
	public GameWorld(int midPointX,int midPointY, int gameHeight, int gameWidth, TyreGame game2, GameScreen gameScreen) {
		currentState=GameState.READY;
		this.midPointX=midPointX;
		this.midPointY=midPointY;
		this.gameHeight=gameHeight;
		this.gameWidth=gameWidth;
		this.gameScreen=gameScreen;
		GROUND_HEIGHT=((int) (this.gameHeight*0.55f));
		
		System.out.println("GameWidth="+gameWidth);
		System.out.println("GameHeight="+gameHeight);
		//this.tileWidth=gcd(gameHeight,gameWidth);
		this.game=game2;
		player=new Player(PLAYER_POSX,0,0,0,this);
		scroller = new ScrollHandler(gameHeight, this);
		blocks=scroller.getBlocks();
		//addBlockNow();
		//currentState=GameState.RUNNING;//enable this to auto start
		
	}
	public void update(float delta) {
		switch (currentState) {
        case READY:
        	updateReady(delta);
            break;
        case RUNNING:
        	updateRunning(delta);
        	break;
        case GAMEOVER:
        	updateGameover(delta);
        	break;
        default:
            break;
        }
	}
	public void updateReady(float delta){
		
	}
	public void updateRunning(float delta){
		runtime+=delta;
    	player.update(delta);
    	checkCollision();
    	scroller.update(delta);
    	
    	//LEVEL UPDATE
    	if((runtime>10)&(runtime<12))
    		setLevel(2);
	}

	public void updateGameover(float delta){
	}
	public  TyreGame getGame(){
		return this.game;
	}
	public int getGameWidth() {
		return gameWidth;
	}
	public int getGameHeight() {
		return gameHeight;
	}
	public float getRuntime() {
		return runtime;
	}
	public void setRuntime(float runtime) {
		this.runtime = runtime;
	}
	public void flip() {
		scroller.flip();
		System.out.println("flipped");
	}
	public Player getPlayer() {
		return player;
	}
	public int getGameSpeed() {
		return gameSpeed;
	}
	public ArrayList<Scrollable> getBlocks() {
		return blocks;
	}
	public void checkCollision(){
		if(scroller.collides(player)){
			currentState=GameState.GAMEOVER;
		}
	}
	public boolean isReady(){
		return currentState==GameState.READY;
	}
	public boolean isRunning(){
		return currentState==GameState.RUNNING;
	}
	public boolean isGameover(){
		return currentState==GameState.GAMEOVER;
	}
	public void start(){
		currentState=GameState.RUNNING;
	}
	public void restart(){
		score=0;
		currentState=GameState.RUNNING;
		scroller.restart();
		player.restart();
	}
	public ScrollHandler getScroller() {
        return scroller;
    }
	public void setToast(String msg){
		msg=toast;
		lastToast=30;
	}
	public String getToast(){
		return toast;
	}
	public static int getGROUND_HEIGHT() {
		return GROUND_HEIGHT;
	}
	public void levelUp(){
		scroller.levelUp();
	}
	private void setLevel(int i) {
		if(currlevel<i){
			currlevel=i;
			setToast("LEVEL=2");
			System.out.println("level increased");
			levelUp();
		}
	}
	

}
