package com.maurice.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.maurice.GameWorld.GameRenderer;
import com.maurice.GameWorld.GameWorld;
import com.maurice.GameHelpers.GameGestureListener;
import com.maurice.GameHelpers.ScreenTransition;
import com.maurice.GameHelpers.ScreenTransitionSlide;
import com.maurice.tyre.TyreGame;

public class GameScreen extends AbstractGameScreen{

	TyreGame game;
    private GameWorld world;
    private GameRenderer renderer;
    private int score=123;//useful for final display of score after gameover
    public static int GAME_WIDTH;
    
    private GestureDetector input;
    
    public GameScreen(TyreGame speedBoatGame) {
    	super(speedBoatGame);
        //System.out.println("GameScreen Attached");
        this.game = speedBoatGame;
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		//float gameWidth = screenWidth;
		//float gameHeight = screenHeight;
		float gameWidth = 800;
		GAME_WIDTH=getWidth();
        float gameHeight = screenHeight / (screenWidth / gameWidth);

		int midPointX = (int) (gameWidth / 2);
		int midPointY = (int) (gameHeight / 2);
        
		//System.out.println("Screenmidpoint==="+midPointX+"=="+midPointY);
        world = new GameWorld(midPointX,midPointY,(int)gameHeight, (int)gameWidth,this.game, this);
        renderer = new GameRenderer(world,midPointX, midPointY, (int)gameHeight, (int)gameWidth);
        
        input=new GestureDetector(new GameGestureListener(world, this));
        Gdx.input.setInputProcessor(input);
    }
    

    @Override
    public void render(float delta) {
    	long tt1 = TimeUtils.nanoTime();
        
    	world.update(delta);
        long delta3 = TimeUtils.nanoTime()-tt1;
        System.out.println("screen update time="+ delta3/100000);
        long tt2 = TimeUtils.nanoTime();
        
        renderer.render(world.getRuntime(),delta);
        //game.yourScore=(int) world.getRuntime();
        //game.setYourScore(124);
        long delta2 = TimeUtils.nanoTime()-tt2;
        System.out.println("screen render time="+ delta2/100000);
        System.out.println("screen totals time="+ delta*1000);
        System.out.println("framerate="+1/delta);
        
        
    }
    public TyreGame getGame(){
    	return this.game;
    }
    public int getHeight(){
    	return Gdx.graphics.getHeight();
    }
    public int getWidth(){
    	return Gdx.graphics.getWidth();
    }
    public int getScore() {
    	//return 10;
    	return score;
    }
    public void setScore(int score) {
    	//world.setScore(score);
    }
    @Override
    public void resize(int width, int height) {
        System.out.println("GameScreen - resizing");
        renderer.resize(width, height);
    }

    @Override
    public void show() {
        System.out.println("GameScreen - show called");
    }

    @Override
    public void hide() {
        System.out.println("GameScreen - hide called");     
    }

    @Override
    public void pause() {
        System.out.println("GameScreen - pause called");        
    }

    @Override
    public void resume() {
        System.out.println("GameScreen - resume called");       
    }

    @Override
    public void dispose() {
        this.dispose();
    }
	@Override
	 public InputProcessor getInputProcessor () {
		System.out.println("input processor 1 requested");
		return input;
	 } 
	public void getSettingsMenu(){
		//game.setScreen(new testtwo(game));
		//ScreenTransition transition = ScreenTransitionFade.init(0.75f);
		//game.setScreen(new testtwo(game), transition);
		
		ScreenTransition transition = ScreenTransitionSlide.init(0.75f,
		ScreenTransitionSlide.UP, false, Interpolation.sineOut);
		game.setScreen(new SettingsScreen(game), transition);
		System.out.println("changescreen called");
	}

}
