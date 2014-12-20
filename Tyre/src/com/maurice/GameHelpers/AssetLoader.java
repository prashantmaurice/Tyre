package com.maurice.GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.maurice.GameObjects.Background;
import com.maurice.GameObjects.Player;
public class AssetLoader {

    public static Texture texture;
    public static TextureRegion bg;

    public static TextureRegion tyre, ground;
    public static TextureRegion mauriceLogo,flipbutton,jumpbutton;
    
    public static BitmapFont font, font2;

    public static void load() {
    	
    	//LOAD FONT
    	font = new BitmapFont(Gdx.files.internal("ui/devgothic.fnt"),true);//true means solves upside-down text problem
        font.setScale(2);
        font.setColor(Color.GRAY);
        
        //SETTINGS FONT
        font2 = new BitmapFont(Gdx.files.internal("ui/agencyFB.fnt"),true);
        font2.setScale(2);
        font2.setColor(Color.GRAY);
        
        //LOAD MAURICE LOGO
        GLTexture.setEnforcePotImages(false);
        texture = new Texture(Gdx.files.internal("data/MauriceLogo.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        mauriceLogo = new TextureRegion(texture, 0, 0, 512, 128);
        mauriceLogo.flip(false, false);
        
        //LOAD BACKGROUND
        texture = new Texture(Gdx.files.internal("data/flypbg.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        bg = new TextureRegion(texture, 0, 0, Background.WIDTH, Background.HEIGHT);
        bg.flip(false, true);
        
        //LOAD tyre
        texture = new Texture(Gdx.files.internal("data/tyre.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        tyre = new TextureRegion(texture, 0, 0, Player.WIDTH, Player.HEIGHT);
        tyre.flip(false, true);
        
        //LOAD GROUND
        texture = new Texture(Gdx.files.internal("data/ground.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        ground = new TextureRegion(texture, 0, 0, Player.WIDTH, Player.HEIGHT);
        ground.flip(false, true);
        
        //LOAD FLIPBUTTON
        texture = new Texture(Gdx.files.internal("data/flipbutton.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        flipbutton = new TextureRegion(texture, 0, 0, 80, 80);
        flipbutton.flip(false, true);
        
        //LOAD JUMPBUTTON
        texture = new Texture(Gdx.files.internal("data/jumpbutton.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        jumpbutton = new TextureRegion(texture, 0, 0, 80, 80);
        jumpbutton.flip(false, true);
      
    }
    
    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
    	font.dispose();
    	font2.dispose();
    	
    }

}
