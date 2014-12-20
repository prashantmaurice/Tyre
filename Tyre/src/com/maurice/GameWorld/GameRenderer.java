package com.maurice.GameWorld;


import java.sql.Time;
import java.util.ArrayList;

import sun.rmi.runtime.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.TimeUtils;
import com.maurice.GameHelpers.AssetLoader;
import com.maurice.GameObjects.Background;
import com.maurice.GameObjects.Ball;
import com.maurice.GameObjects.BigWave;
import com.maurice.GameObjects.BoxObject;
import com.maurice.GameObjects.BuoyLeft;
import com.maurice.GameObjects.BuoyRight;
import com.maurice.GameObjects.Dock;
import com.maurice.GameObjects.Mine;
import com.maurice.GameObjects.Ramp;
import com.maurice.GameObjects.Rock;
import com.maurice.GameObjects.Bubble;
import com.maurice.GameObjects.Cluster;
import com.maurice.GameObjects.Coin;
import com.maurice.GameObjects.ConnectLine;
import com.maurice.GameObjects.Pin;
import com.maurice.GameObjects.Player;
import com.maurice.GameObjects.Rocket;
import com.maurice.GameObjects.ScrollHandler;
import com.maurice.GameObjects.Scrollable;
import com.maurice.GameObjects.Shield;
import com.maurice.GameObjects.Tile;

public class GameRenderer {
    
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    
    private int midPointX;
    private int midPointY;
    private int gameHeight;
    private int gameWidth;
    //colors-light
    private Color color1=colorFromHex(0xE60000L);//0xrrggbb//red
	private Color color2=colorFromHex(0x0099FFL);//blue
	private Color color3=colorFromHex(0xF28080L);//light red
	private Color color4=colorFromHex(0x80CCFFL);//light blue
	private Color[] colors = new Color[]{color1,color2,color3,color4};
	//colors-faded
	private Color color1d=colorFromHex(0xA10000L);//0xaarrggbb
	private Color color2d=colorFromHex(0xB26B00L);
	private Color color3d=colorFromHex(0x005C99L);
	private Color[] colorsfaded = new Color[]{color1d,color3d,color2d};
	
	private SpriteBatch batch, batchTime;
	Texture texture;
	Texture rock;
	private Animation waterAnimation;
	int margin=2;
	Sprite sprite;
	private TextureRegion region;
	private TextureRegion bg, grass, coin;
	private Background background1,background2;

    public static final int VIRTUAL_WIDTH = 800;
    public static final int VIRTUAL_HEIGHT = 480;
    private static final float ASPECT_RATIO =(float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;
    private int GROUND_HEIGHT,GROUND_WIDTH=100;//FROM TOP
    private Rectangle viewport;
    private SpriteBatch sb;
    
    private Player player;
    private ArrayList<Scrollable> blocks;
    private ScrollHandler scroller;
    private ArrayList<Background> background;
    private ArrayList<Bubble> bubbles;
    private float curr_runtime;
    
    //TESTING FRAMERATE
    private float framerate=60;
    private float totalframerate;
    private float avgframerate=60;
    private float iterations=0;
    private float timerate;
    private float avgtimerate=0;
    private float f0,f1,f2,f3,f4,f5,f6,f7;
    
    //MENU RELATED
    Skin skin;
    public GameRenderer(GameWorld world, int midPointX,int midPointY,int gameHeight,int gameWidth) {
        myWorld = world;
        
        this.midPointX=midPointX;
        this.midPointY=midPointY;
        this.gameHeight=gameHeight;
        this.gameWidth=gameWidth;
        GROUND_HEIGHT=myWorld.getGROUND_HEIGHT();
        sb = new SpriteBatch();
        cam = new OrthographicCamera(gameWidth, gameHeight);
        cam.setToOrtho(true, gameWidth, gameHeight);
        //cam.rotate(45);
        
        batch = new SpriteBatch();  //text display
        batch.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
             
        initGameObjects();
        initAssets();
        cam.update();
        //cam.apply(Gdx.gl10);
    }
    private void initGameObjects() {
    	//GROUND_HEIGHT=myWorld.getGroundHeight();
        player=myWorld.getPlayer();
        scroller = myWorld.getScroller();
        blocks = scroller.getBlocks();
    	background1=scroller.getBg1();
        background2=scroller.getBg2();
    }
    private void initAssets() {
        bg = AssetLoader.bg;
        //grass = AssetLoader.grass;
        //waterAnimation = AssetLoader.waterAnimation;
        //coin = AssetLoader.coin;
        /*birdMid = AssetLoader.bird;
        birdDown = AssetLoader.birdDown;
        birdUp = AssetLoader.birdUp;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
        */

        
       
    }

    public void render(float runtime, float delta) {
    	
    	//System.out.println("test2");
        // set viewport to 
        //Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
          //                (int) viewport.width, (int) viewport.height);

        // clear previous frame
    	//this.curr_runtime+=delta;
    	Gdx.gl.glClearColor(0.2f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
/*
        //DRAW DEFAULT GROUND
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(colorFromHex(0xFFFFFFFFL));
        shapeRenderer.rect(0,GROUND_HEIGHT, gameWidth, 100);
        shapeRenderer.end();
 */      
        batch.begin();

        //DRAW BACKGROUND
        batch.disableBlending();
        batch.draw(bg,background1.getX(), 0, gameWidth, gameHeight);
        batch.draw(bg,background2.getX(), 0, gameWidth, gameHeight);
        
        //DRAW GROUND
        batch.draw(AssetLoader.ground,0,GROUND_HEIGHT, gameWidth, GROUND_WIDTH);
        
        //DRAW PLAYER
        batch.enableBlending();
        //batch.draw(AssetLoader.tyre,GameWorld.PLAYER_POSX,player.getPosition().y, Player.WIDTH, Player.HEIGHT);
        batch.draw(AssetLoader.tyre, GameWorld.PLAYER_POSX, player.getPosition().y,
        		Player.WIDTH/2, Player.HEIGHT/2, Player.WIDTH, Player.HEIGHT, 1, 1, player.angle);
        
        //System.out.println("angle="+player.angle);
        //AssetLoader.font2.setScale(1);
        //AssetLoader.font2.draw(batch, "TAP TO START", midPointX-30, gameHeight*0.3f);
    	
    	
    	//DRAW BLOCKS AND COINS
        for (int i = 0; i < blocks.size(); i++) {
    		Scrollable c = (Scrollable) blocks.get(i);
			if(BoxObject.class==c.getClass()){
				BoxObject d=(BoxObject) c;
				batch.draw(AssetLoader.ground,c.getX(),
						((d.isOnTop())?0:(GROUND_WIDTH+BoxObject.HEIGHT))+c.getY(),BoxObject.WIDTH ,BoxObject.HEIGHT);
			}
		}
    	
    	//FRAMERATE RELATED
    	framerate=1/delta;
    	if(framerate<200)
    		avgframerate=(avgframerate*0.95f)+(framerate*0.05f);
    	AssetLoader.font2.setColor(Color.WHITE);
        AssetLoader.font2.setScale((float) 1);
        if(framerate<30){
        	AssetLoader.font2.setColor(Color.RED);
        	//AssetLoader.font.draw(batch, "LOW FRAMERATE", 5, 80);
        	System.out.println("framerate = "+framerate);
        }
        AssetLoader.font2.draw(batch, "FRAMERATE="+(int)avgframerate, 5, 5);
        
        //AssetLoader.font.draw(batch, "TESTTIME="+(int)(avgtimerate/1000), 5, 100);
        AssetLoader.font.setScale((float) 0.5F);
        //System.out.println("framerate = "+framerate);
        //System.out.println("avgframerate = "+avgframerate);
        
        
        //DRAW BUTTONS
        batch.draw(AssetLoader.flipbutton,10,gameHeight-90, 80, 80);
        batch.draw(AssetLoader.jumpbutton,gameWidth-90,gameHeight-90, 80, 80);
        
        //GAMEOVER TITLES
        AssetLoader.font2.setColor(Color.WHITE);
        if(myWorld.isGameover())
        	AssetLoader.font2.draw(batch, "TAP TO RESTART", gameWidth*0.4f,gameHeight*0.3f);
        if(myWorld.isReady())
        	AssetLoader.font2.draw(batch, "TAP TO START", gameWidth*0.4f,gameHeight*0.3f);
        
        //TOAST
        AssetLoader.font2.draw(batch, myWorld.getToast(), gameWidth*0.4f,gameHeight*0.3f);
        
        batch.end();
        
        //DEBUGGING COLLISIONS
        /*shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(player.getBoundingCircle().x,
        		player.getBoundingCircle().y, player.getBoundingCircle().radius);
        for (int i = 0; i < blocks.size(); i++) {
    		Scrollable c = (Scrollable) blocks.get(i);
			if(BoxObject.class==c.getClass()){
				BoxObject d=(BoxObject) c;
				shapeRenderer.rect(d.getBoundingRect().x, d.getBoundingRect().y,
						d.getBoundingRect().width,d.getBoundingRect().height	);
			}
		}
        shapeRenderer.end();*/
        //long delta2 = (System.currentTimeMillis() - ltime1);
        
    	
    }
    private String timeString(int time){
    	String temp="";
 		int temp2=0;
 		//temp2=(int)time/3600;
 		//temp+=((temp2<10)?"0":"")+temp2+":";//hours
 		temp2=(int)time/60;
 		temp+=((temp2<10)?"0":"")+temp2+":";//minutes
 		temp2=(int)time%60;
 		temp+=((temp2<10)?"0":"")+temp2;//seconds
 		//temp2=(int)(time*100)%60;
 		//temp+=((temp2<10)?"0":"")+temp2;//milliseconds
 		return temp;
     }

	private Color colorFromHex(long hex){
            float a = (hex & 0xFF000000L) >> 24;
            float r = (hex & 0xFF0000L) >> 16;
            float g = (hex & 0xFF00L) >> 8;
            float b = (hex & 0xFFL);   
            return new Color(r/255f, g/255f, b/255f, a/255f);
    }
    public void resize(int width, int height) {
    	//this.gameWidth=width;
    	//this.gameHeight=height;
        //viewport = new Rectangle(this.gameWidth, this.gameHeight, 0, 0);
    }
    //PERSPECTIVE
    public float getWdith(float y){
    	return (y)*5;
    }
}