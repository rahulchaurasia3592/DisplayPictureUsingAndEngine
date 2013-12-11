package com.rahul.displaypictureusingandengine;


import org.anddev.andengine.engine.Engine;//Main processor of the program
import org.anddev.andengine.engine.camera.Camera;//Which part of the scene we are interested in 
import org.anddev.andengine.engine.options.EngineOptions;//To setup Our Engine
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;//I tells about Screen Orientation. It may be Portrait or Landscape Mode  
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;//It tells that we want full screen for our Game
import org.anddev.andengine.entity.scene.Scene;//A container that contains other scenes
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.util.FPSLogger;//It logs Frame Per Second
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;//It is place on the screen whrer we put our items
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;//It is of the activity 

//This class simply display a .gif file on the screen
public class DisplayImage extends BaseGameActivity{
	//Declare a variable 'myEngine' of type Engine 
	Engine myEngine;
	Camera myCamera;
	BitmapTextureAtlas atlas;
	BuildableBitmapTextureAtlas mbuilableBitmapTextureAtlas;
	TiledTextureRegion mTiledTextureRegion;
	//This method is the entry point for the program
	//Here I create a engine and set the EngineOptions needed by the constructor of the engine
	//return the engine to the environment
	@Override
	public Engine onLoadEngine() {
		//Create a Camera Object 
		myCamera=new Camera(0, 0, 500, 500);
		//Create a Engine for my Game
	    myEngine=new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT, new FillResolutionPolicy(), myCamera));
		return myEngine;
	}
	//This class loads resources such as sound,text,image etc.
	//This method loads one .gif image 
	@Override
	public void onLoadResources() {
		//Set the path of the resource under the asset folder
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		//Create one Texture Atlas
	    this.atlas=new BitmapTextureAtlas(512,256);
	    this.mbuilableBitmapTextureAtlas=new BuildableBitmapTextureAtlas(512,256);
		this.mTiledTextureRegion=BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(atlas, this, "banana_tiled.png", 0, 0, 4, 2);
		//Load the atlas
		myEngine.getTextureManager().loadTextures(atlas);
	}
	//This simple creates the scene
	//That can hold other objects too
	@Override
	public Scene onLoadScene() {
		mEngine.registerUpdateHandler(new FPSLogger());
		//Create a scene 'myscene' object
		Scene myscene=new Scene();
		//Create one animated Sprite 
		AnimatedSprite banana=new AnimatedSprite(100, 100, mTiledTextureRegion);
		//Attach sprite to the scene
		myscene.attachChild(banana);
		//set the time in milisec for each picture
		banana.animate(100);
		return myscene;
	}	
	@Override
	public void onLoadComplete() {
		System.out.println("++++++++onLoadComplete++++++++++");
	}
}
