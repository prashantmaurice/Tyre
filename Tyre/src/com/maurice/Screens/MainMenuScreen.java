package com.maurice.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.maurice.GameHelpers.AssetLoader;
import com.maurice.GameHelpers.ScreenTransition;
import com.maurice.GameHelpers.ScreenTransitionSlide;
import com.maurice.tyre.TyreGame;

public class MainMenuScreen extends AbstractGameScreen {
	 
	Skin skin;
	Stage stage;
	SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private static final String PREFS_NAME = "user";
	private float screenHeight, screenWidth;
	private TyreGame game;
	

	public MainMenuScreen(TyreGame game){
		super(game);
		this.game=game;
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		shapeRenderer = new ShapeRenderer();
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		System.out.println("screen sidth="+screenWidth);


		//Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		AssetLoader.font2.setColor(Color.BLACK);
		skin.add("default", AssetLoader.font2);
		
		// Create a table that fills the screen. Everything else will go inside this table.
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		
		//GAME TITLE
		Label nameLabel = new Label("FLYP RUNNER:", skin);
		//nameLabel.setFontScale(2f);
		nameLabel.setColor(colorFromHex(0xFF515151L));
		table.add(nameLabel).padBottom(screenHeight/15);
		
		
	    
	    //PLAY BUTTON
	    table.row().padTop(screenHeight/15);
	    final TextButton button = new TextButton("PLAY", skin);
	    button.pad(20);
	    button.setColor(colorFromHex(0xFF515151L));
	    table.add(button);
	    button.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				
				getMainGame();
			}
		});
	    
	    //SETTINGS BUTTON
	    table.row().padTop(screenHeight/15);
	    final TextButton button2 = new TextButton("SETTINGS", skin);
	    button2.pad(20);
	    button2.setColor(colorFromHex(0xFF515151L));
	    table.add(button2);
	    button2.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				getSettingsScreen();
			}
		});
	    
	    //CREATORS BUTTON
	    table.row().padTop(screenHeight/15);
	    final TextButton button3 = new TextButton("CREATORS", skin);
	    button3.pad(20);
	    button3.setColor(colorFromHex(0xFF515151L));
	    table.add(button3);
	    button3.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				getCreatorsScreen();
			}
		});
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//DRAW BACKGROUND
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(colorFromHex(0xFFFBFBFBL));
		shapeRenderer.rect(0,0, screenWidth, screenHeight);
		//shapeRenderer.setColor(colorFromHex(0xFF007DFDL));
		//shapeRenderer.rect(0,screenHeight-4, screenWidth, 4);
		shapeRenderer.end();
		
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		Table.drawDebug(stage);
		
	}
	
	public void loadSetings(){
		//Preferences prefs = Gdx.app.getPreferences( PREFS_NAME );
	   	 //prefs.putInteger( "high1", 1000 );
	   	 //getPrefs().flush();

	   	 //gameSpeed=prefs.getInteger("speed");
	   	 //System.out.println("GameSpeed="+gameSpeed);
	   	 //getPrefs().putInteger( "speed", 23 );
	     //getPrefs().flush();
	}
    public void updateSettings(){
	   	 /*Preferences prefs = Gdx.app.getPreferences( game.getPrefsName());
	   	 
	   	 //UPDATE GYRO SETTINGS
	   	 prefs.putInteger( game.GYRO_KEY, (int) gyroSensitivity.getValue() );
	   	 game.GYROSENSITIVITY=(int) gyroSensitivity.getValue();
	   	 
	   	 prefs.flush();
	   	 //game.GYROSENSITIVITY=prefs.getInteger(game.GYRO_KEY);
	   	 System.out.println("new Gyrosensitivity="+game.GYROSENSITIVITY);
	   	 //getPrefs().putInteger( "speed", 23 );
	   	 System.out.println( Gdx.app.getPreferences( game.getPrefsName()));
	        /*System.out.println("high2="+getPrefs().getInteger( "high2", 0 ));
	   	 if(highScore>yourScore){
	   		 highScore=yourScore;
	   		 prefs.putInteger( "high1", yourScore );
	   	 }*/
    }
    protected Preferences getPrefs(){
        return Gdx.app.getPreferences( PREFS_NAME );
    }


	@Override
	public void resize (int width, int height) {
		stage.setViewport(width, height, true);
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
	}

	@Override
	public void dispose () {
		stage.dispose();
		skin.dispose();
	}


	@Override
	public void show() {
		//loadSetings();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	 public InputProcessor getInputProcessor () {
		//System.out.println("input processor 1 requested");
		return stage;
	 } 
	public void getMainGame(){
		//game.setScreen(new testtwo(game));
		//ScreenTransition transition = ScreenTransitionFade.init(0.75f);
		//game.setScreen(new testtwo(game), transition);
		
		ScreenTransition transition = ScreenTransitionSlide.init(0.75f,
		ScreenTransitionSlide.LEFT, false, Interpolation.sineOut);
		game.setScreen(new GameScreen(game), transition);
		//System.out.println("gamescreen called");
	}
	public void getSettingsScreen(){
		ScreenTransition transition = ScreenTransitionSlide.init(0.75f,
		ScreenTransitionSlide.UP, false, Interpolation.sineOut);
		game.setScreen(new SettingsScreen(game), transition);
	}
	public void getCreatorsScreen(){
		ScreenTransition transition = ScreenTransitionSlide.init(0.75f,
		ScreenTransitionSlide.UP, false, Interpolation.sineOut);
		game.setScreen(new CreatorsScreen(game), transition);
	}
	private Color colorFromHex(long hex){
        float a = (hex & 0xFF000000L) >> 24;
        float r = (hex & 0xFF0000L) >> 16;
        float g = (hex & 0xFF00L) >> 8;
        float b = (hex & 0xFFL);   
        return new Color(r/255f, g/255f, b/255f, a/255f);
}
}