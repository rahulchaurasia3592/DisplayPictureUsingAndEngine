package com.rahul.displaypictureusingandengine;

import org.anddev.andengine.engine.Engine;//Engine the control the whole Game
import org.anddev.andengine.engine.camera.Camera;//Tells which part of the scene we want to see
import org.anddev.andengine.engine.options.EngineOptions;//It is used to customize the Engine Object 
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;//Which type of orientation we want (Landscape or Portrait) 
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;//It tells that we want full screen mode
import org.anddev.andengine.entity.scene.Scene;//The main scene object
import org.anddev.andengine.entity.scene.background.AutoParallaxBackground;//It move the background
import org.anddev.andengine.entity.sprite.AnimatedSprite;//It plays animation
import org.anddev.andengine.entity.sprite.Sprite;//A Sprite that displays a Banana
import org.anddev.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;//A Entity Object For the Background
import org.anddev.andengine.entity.util.FPSLogger;//Which logs Frame Per Second change
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;//A logical place on the scene
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;//A region on the atlas
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;//Titled version of the region
import org.anddev.andengine.ui.activity.BaseGameActivity;//A sub-type of Activity 
/*
 * This class will move the background image
 * as well as it plays animation of a banana in the fore ground
*/
public class MoveBackground extends BaseGameActivity {
	//Declare variables for Camera,Engine,BitmapTextureRegion,TextureRegion
	//It tells us that which part of the scene we want to see
	private static int WIDTH=720;
	private static int height=480;
	Engine mEngine;
	BitmapTextureAtlas mBitmapParralaxTextureAtlas,mBitmapTextureAtlas;
	TextureRegion mTextureRegion;
	TiledTextureRegion bananaRegion;

	//This method is the entry point for the program
	//Here I create a engine and set the EngineOptions needed by the constructor of the engine
	//return the engine to the environment
	@Override
	public Engine onLoadEngine(){
		mEngine=new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT, new FillResolutionPolicy(), new Camera(0, 0, MoveBackground.WIDTH, MoveBackground.height)));
		return mEngine;
	}

	/*
	 * This class loads resources such as sound,text,image etc.
	*Here it simple loads the asset path, then it create two atlas objects
	*One for the background and other for the foreground
	*Then we create regions on the atlas and finally load the both TextureAtlas objects 
	 * */
	@Override
	public void onLoadResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");		
		mBitmapParralaxTextureAtlas=new BitmapTextureAtlas(1024,1024);
		mBitmapTextureAtlas=new BitmapTextureAtlas(512, 512);
		mTextureRegion=BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapParralaxTextureAtlas, this, "parallax_background_layer_back.png", 0, 0);
		bananaRegion=BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mBitmapTextureAtlas, this, "banana_tiled.png", 0, 0, 4,2);
		mEngine.getTextureManager().loadTextures(mBitmapParralaxTextureAtlas,mBitmapTextureAtlas);
	}
	/*
	 *Here we create the scene
	 *First we create the AutoParallelaxBackgound Object which has the feature to move the background
	 *then we attach one entity to this object which holds the background image
	 *We also create on animated sprite 'banana' which animates in the foreground 
	  */
	@Override
	public Scene onLoadScene() {
		mEngine.registerUpdateHandler(new FPSLogger());
		Scene scene=new Scene();
		AutoParallaxBackground autoParallaxBackground=new AutoParallaxBackground(0, 0, 0, 5);
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-10.0f, new Sprite(0, 0, mTextureRegion)));
		scene.setBackground(autoParallaxBackground);
		//Position of the Sprite in the region
		AnimatedSprite banana=new AnimatedSprite(200,200,bananaRegion);
		banana.animate(100);
		scene.attachChild(banana);
		return scene;
	}
	@Override
	public void onLoadComplete() {
		//Do nothing
	}
}
