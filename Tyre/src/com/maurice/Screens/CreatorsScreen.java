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
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.maurice.GameHelpers.AssetLoader;
import com.maurice.GameHelpers.ScreenTransition;
import com.maurice.GameHelpers.ScreenTransitionSlide;
import com.maurice.tyre.TyreGame;

public class CreatorsScreen extends AbstractGameScreen {
	 
	Skin skin;
	Stage stage;
	SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private float screenHeight, screenWidth;
	private TyreGame game;
	

	public CreatorsScreen(TyreGame game){
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
		
		//ABOUT ME HEAD
		table.row().padTop(screenHeight/15);
		String contentHead="About Me,";
	    Label contentLabelH=new Label(contentHead,skin);
	    contentLabelH.setFontScale(1f);
	    contentLabelH.setWrap(true);
	    table.add(contentLabelH).width(screenWidth-40);
			    
		//ABOUT ME CONTENT
		table.row().padTop(screenHeight/15);
		String content="Hi, my name is Maurice. I create concept mobile games and applications. I am a student from IIT Madras ";
	    Label contentLabel=new Label(content,skin);
	    contentLabel.setFontScale(1f);
	    contentLabel.setWrap(true);
	    table.add(contentLabel).width(screenWidth-40);
	    
	    
	    //SUBMIT BUTTON
	    table.row().padTop(screenHeight/15);
	    final TextButton button = new TextButton("BACK", skin);
	    button.pad(20);
	    button.setColor(colorFromHex(0xFF419FFFL));
	    table.add(button);
	    button.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				getMainMenu();
			}
		});
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//DRAW BACKGROUND
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(colorFromHex(0xFF222222L));
		shapeRenderer.rect(0,0, screenWidth, screenHeight);
		shapeRenderer.setColor(colorFromHex(0xFF007DFDL));
		shapeRenderer.rect(0,screenHeight-4, screenWidth, 4);
		shapeRenderer.end();
		
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		Table.drawDebug(stage);
		
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
		System.out.println("input processor 1 requested");
		return stage;
	 } 
	public void getMainMenu(){
		ScreenTransition transition = ScreenTransitionSlide.init(0.75f,
		ScreenTransitionSlide.DOWN, false, Interpolation.sineOut);
		game.setScreen(new MainMenuScreen(game), transition);
		//System.out.println("gamescreen called");
	}
	private Color colorFromHex(long hex){
        float a = (hex & 0xFF000000L) >> 24;
        float r = (hex & 0xFF0000L) >> 16;
        float g = (hex & 0xFF00L) >> 8;
        float b = (hex & 0xFFL);   
        return new Color(r/255f, g/255f, b/255f, a/255f);
}
}