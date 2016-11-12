package com.mygdx.game.DemoLoading;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DemoMenu.MenuScreen;
import com.mygdx.game.Music.MusicSetter;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.GlobalClasses.*;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Settings.SettingsStage;


public class LoadingScreen extends MyScreen {


	private Stage stage;
	private OneSpriteStaticActor text;
	private float elapsedTime = 0;
	private OneSpriteAnimatedActor pic;

    public LoadingScreen(MyGdxGame game) {
		super(game);
		stage = new Stage();
		pic = new OneSpriteAnimatedActor("load.txt")
		{
			@Override
			public void init() {
				super.init();
				setFps(12);
			}
		};
		stage.addActor(pic);

		pic.setPosition(stage.getWidth()/2-pic.getWidth()/2,stage.getHeight()/2-pic.getHeight()/2);

		text = new OneSpriteStaticActor("justszoveg.png");
		stage.addActor(text);
		text.setPosition(stage.getWidth()/2-text.getWidth()/2,stage.getHeight()/2-text.getHeight()/2);
    }


    @Override
	public void show() {
	    Assets.manager.finishLoading();
		Assets.load();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		stage.act(delta);
		stage.draw();
		if (elapsedTime > 4.0 && Assets.manager.update()) {
			Assets.afterLoaded();

			new MusicSetter(true); //menüzene hívás


			game.setScreen(new MenuScreen(game));
		}
		elapsedTime+=delta;

	}

	@Override
	public void hide() {

	}

	@Override
	public void init() {
		setBackGroundColor(0f, 0f, 0f);


	}
}