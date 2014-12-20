package com.maurice.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.maurice.tyre.TyreGame;
public abstract class AbstractGameScreen implements Screen {
 protected TyreGame game;
 public AbstractGameScreen (TyreGame game) {
 this.game = game;
 }
 public abstract void render (float deltaTime);
 public abstract void resize (int width, int height);
 public abstract void show ();
 public abstract void hide ();
 public abstract void pause ();
 public void resume () {
 //Assets.instance.init(new AssetManager());
 }
 public void dispose () {
 //Assets.instance.dispose();
 }
public void changeScreen() {
	// TODO Auto-generated method stub
	
}
public int getWidth() {
	// TODO Auto-generated method stub
	return 0;
}
public InputProcessor getInputProcessor() {
	// TODO Auto-generated method stub
	return null;
}
}