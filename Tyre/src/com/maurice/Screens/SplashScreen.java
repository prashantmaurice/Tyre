package com.maurice.Screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import com.maurice.TweenAccessors.SpriteAccessor;
import com.maurice.tyre.TyreGame;

public class SplashScreen extends AbstractGameScreen {
	 
	Skin skin;
	Stage stage;
	SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private static final String PREFS_NAME = "user";
	private int gameSpeed;
	private float screenHeight, screenWidth;
	private TyreGame game;
	
	private TweenManager manager;
	private SpriteBatch batcher;
	private Sprite sprite;
	public SplashScreen(TyreGame game){
		super(game);
		this.game=game;
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		shapeRenderer = new ShapeRenderer();
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();


	}
	
	@Override
	public void render(float delta) {
		/*Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//DRAW BACKGROUND
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(colorFromHex(0xFFFBFBFBL));
		shapeRenderer.rect(0,0, screenWidth, screenHeight);
		//shapeRenderer.setColor(colorFromHex(0xFF007DFDL));
		//shapeRenderer.rect(0,screenHeight-4, screenWidth, 4);
		shapeRenderer.end();
		*/
		manager.update(delta);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batcher.begin();
		//batcher.draw(AssetLoader.bg,0, 0, 200, 200);
		sprite.draw(batcher);
		batcher.end();
		
		
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
		System.out.println("splash shown");
		sprite = new Sprite(AssetLoader.mauriceLogo);
		sprite.setColor(1, 1, 1, 0);

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		float desiredWidth = width * .7f;
		float scale = desiredWidth / sprite.getWidth();

		sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
		sprite.setPosition((width / 2) - (sprite.getWidth() / 2), (height / 2)
				- (sprite.getHeight() / 2));
		setupTween();
		batcher = new SpriteBatch();
		
	}
	
	private void setupTween() {
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		manager = new TweenManager();
		TweenCallback cb = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				game.setScreen(new MainMenuScreen(game));
				System.out.println("game screen called");
			}
		};

		Tween.to(sprite, SpriteAccessor.ALPHA, .8f).target(1)
				.ease(TweenEquations.easeInOutQuad).repeatYoyo(1, .4f)
				.setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
				.start(manager);
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
	private Color colorFromHex(long hex){
        float a = (hex & 0xFF000000L) >> 24;
        float r = (hex & 0xFF0000L) >> 16;
        float g = (hex & 0xFF00L) >> 8;
        float b = (hex & 0xFFL);   
        return new Color(r/255f, g/255f, b/255f, a/255f);
}
}