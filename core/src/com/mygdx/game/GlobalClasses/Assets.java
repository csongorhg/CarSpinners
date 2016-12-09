//https://github.com/tuskeb/mester
package com.mygdx.game.GlobalClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.mygdx.game.DemoMenu.MenuScreen;


public class Assets {

	// https://github.com/libgdx/libgdx/wiki/Managing-your-assets
	// http://www.jacobplaster.net/using-libgdx-asset-manager
	// https://www.youtube.com/watch?v=JXThbQir2gU
	// https://github.com/Matsemann/libgdx-loading-screen/blob/master/Main/src/com/matsemann/libgdxloadingscreen/screen/LoadingScreen.java

	public static AssetManager manager;
	public static final String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[],-.";



	static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
	static {
		fontParameter.fontFileName = "c64.ttf";
		fontParameter.fontParameters.size = 30;
		fontParameter.fontParameters.characters = CHARS;
		fontParameter.fontParameters.color = Color.WHITE;
	}
	public static final AssetDescriptor<BitmapFont> FONT_C64_30
			= new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);

	public static final AssetDescriptor<TextureAtlas> EXPLOSION_TEXTUREATLAS
			= new AssetDescriptor<TextureAtlas>("explosion.atlas", TextureAtlas.class);
	public static final AssetDescriptor<TextureAtlas> MONEY_TEXTUREATLAS
			= new AssetDescriptor<TextureAtlas>("mozgopenz.atlas", TextureAtlas.class);
	public static final AssetDescriptor<TextureAtlas> WALK_TEXTUREATLAS
			= new AssetDescriptor<TextureAtlas>("seta.atlas", TextureAtlas.class);
	public static final AssetDescriptor<TextureAtlas> SZIRENA_TEXTUREATLAS
			= new AssetDescriptor<TextureAtlas>("szirena.atlas", TextureAtlas.class);

	public static final AssetDescriptor<Texture> CURSOR_TEXTURE
			= new AssetDescriptor<Texture>("images/cursor.png", Texture.class);
	public static final AssetDescriptor<Texture> TEXTBOX_TEXTURE
			= new AssetDescriptor<Texture>("images/textbox.png", Texture.class);
	public static final AssetDescriptor<Texture> BACKGROUND_TEXTURE
			= new AssetDescriptor<Texture>("background2.png", Texture.class);
	public static final AssetDescriptor<Texture> CAR_TEXTURE_1
			= new AssetDescriptor<Texture>("car-1.png", Texture.class);
	public static final AssetDescriptor<Texture> CAR_TEXTURE_2
			= new AssetDescriptor<Texture>("car-2.png", Texture.class);
	public static final AssetDescriptor<Texture> CAR_TEXTURE_3
			= new AssetDescriptor<Texture>("car-3.png", Texture.class);
	public static final AssetDescriptor<Texture> CAR_TEXTURE_4
			= new AssetDescriptor<Texture>("car-4.png", Texture.class);
	public static final AssetDescriptor<Texture> MONEY_TEXTURE
			= new AssetDescriptor<Texture>("money.png", Texture.class);
	public static final AssetDescriptor<Texture> MUTE_ICON
			= new AssetDescriptor<Texture>("mute.png", Texture.class);
	public static final AssetDescriptor<Texture> SOUND_ICON
			= new AssetDescriptor<Texture>("sound.png", Texture.class);
	public static final AssetDescriptor<Texture> CITY_ACTION_BACKGROUND
			= new AssetDescriptor<Texture>("city.png", Texture.class);
	public static final AssetDescriptor<Texture> GAZ_ICON
			= new AssetDescriptor<Texture>("gaz.png", Texture.class);
	public static final AssetDescriptor<Texture> FEK_ICON
			= new AssetDescriptor<Texture>("fek.png", Texture.class);
	public static final AssetDescriptor<Texture> CONF_ICON
			= new AssetDescriptor<Texture>("cog.png", Texture.class);
	public static final AssetDescriptor<Texture> ROAD_BLOCK
			= new AssetDescriptor<Texture>("ut.png", Texture.class);
	public static final AssetDescriptor<Texture> ROAD_MENU
			= new AssetDescriptor<Texture>("felulet.png", Texture.class);
	public static final AssetDescriptor<Texture> HEART
			= new AssetDescriptor<Texture>("heart.png", Texture.class);
	public static final AssetDescriptor<Texture> HEARTONGOURND
			= new AssetDescriptor<Texture>("heartonground.png", Texture.class);
	public static final AssetDescriptor<Texture> NOHEART
			= new AssetDescriptor<Texture>("noheart.png", Texture.class);
	public static final AssetDescriptor<Texture> PLUS_VOL
			= new AssetDescriptor<Texture>("vol/splusz.png", Texture.class);
	public static final AssetDescriptor<Texture> MINUS_VOL
			= new AssetDescriptor<Texture>("vol/sminusz.png", Texture.class);
	public static final AssetDescriptor<Texture> FILLED_VOL
			= new AssetDescriptor<Texture>("vol/sfull.png", Texture.class);
	public static final AssetDescriptor<Texture> EMPTY_VOL
			= new AssetDescriptor<Texture>("vol/sempty.png", Texture.class);
	public static final AssetDescriptor<Texture> BLOCAKDE_0
			= new AssetDescriptor<Texture>("blockade-0.png", Texture.class);
	public static final AssetDescriptor<Texture> BLOCAKDE_1
			= new AssetDescriptor<Texture>("blockade-1.png", Texture.class);
	public static final AssetDescriptor<Texture> BLOCAKDE_2
			= new AssetDescriptor<Texture>("blockade-2.png", Texture.class);
	public static final AssetDescriptor<Texture> BLOCAKDE_3
			= new AssetDescriptor<Texture>("blockade-3.png", Texture.class);
	public static final AssetDescriptor<Texture> BATTERY
			= new AssetDescriptor<Texture>("battery.png", Texture.class);
	public static final AssetDescriptor<Texture> ARROW
			= new AssetDescriptor<Texture>("nyil.png", Texture.class);
	public static final AssetDescriptor<Texture> BREAK
			= new AssetDescriptor<Texture>("break.png", Texture.class);
	public static final AssetDescriptor<Texture> TEXT_1_1
			= new AssetDescriptor<Texture>("text-1-1.png", Texture.class);
	public static final AssetDescriptor<Texture> TEXT_1_2
			= new AssetDescriptor<Texture>("text-1-2.png", Texture.class);
	public static final AssetDescriptor<Texture> TEXT_1_3
			= new AssetDescriptor<Texture>("text-1-3.png", Texture.class);
	public static final AssetDescriptor<Texture> TEXT_2_1
			= new AssetDescriptor<Texture>("text-2-1.png", Texture.class);
	public static final AssetDescriptor<Texture> TEXT_2_2
			= new AssetDescriptor<Texture>("text-2-2.png", Texture.class);
	public static final AssetDescriptor<Texture> TEXT_2_3
			= new AssetDescriptor<Texture>("text-2-3.png", Texture.class);
	public static final AssetDescriptor<Texture> TEXT_3_1
			= new AssetDescriptor<Texture>("text-3-1.png", Texture.class);
	public static final AssetDescriptor<Texture> TEXT_3_2
			= new AssetDescriptor<Texture>("text-3-2.png", Texture.class);
	public static final AssetDescriptor<Texture> TEXT_3_3
			= new AssetDescriptor<Texture>("text-3-3.png", Texture.class);
	public static final AssetDescriptor<Texture> TEXT_4_1
			= new AssetDescriptor<Texture>("text-4-1.png", Texture.class);
	public static final AssetDescriptor<Texture> TEXT_4_2
			= new AssetDescriptor<Texture>("text-4-2.png", Texture.class);
	public static final AssetDescriptor<Texture> TEXT_4_3
			= new AssetDescriptor<Texture>("text-4-3.png", Texture.class);
	public static final AssetDescriptor<Texture> SKNOB
			= new AssetDescriptor<Texture>("knob.png", Texture.class);
	public static final AssetDescriptor<Texture> SBACKGROUND
			= new AssetDescriptor<Texture>("sliderbackground.png", Texture.class);
	public static final AssetDescriptor<Texture> HOUSE
			= new AssetDescriptor<Texture>("kornyezet/haz.png", Texture.class);
	public static final AssetDescriptor<Texture> ENV_0
			= new AssetDescriptor<Texture>("kornyezet/korny-0.png", Texture.class);
	public static final AssetDescriptor<Texture> ENV_1
			= new AssetDescriptor<Texture>("kornyezet/korny-1.png", Texture.class);
	public static final AssetDescriptor<Texture> ENV_2
			= new AssetDescriptor<Texture>("kornyezet/korny-2.png", Texture.class);
	public static final AssetDescriptor<Texture> ENV_3
			= new AssetDescriptor<Texture>("kornyezet/korny-3.png", Texture.class);
	public static final AssetDescriptor<Texture> ENV_4
			= new AssetDescriptor<Texture>("kornyezet/korny-4.png", Texture.class);
	public static final AssetDescriptor<Texture> ENV_5
			= new AssetDescriptor<Texture>("kornyezet/korny-5.png", Texture.class);
	public static final AssetDescriptor<Texture> ENV_6
			= new AssetDescriptor<Texture>("kornyezet/korny-6.png", Texture.class);
	public static final AssetDescriptor<Texture> ENV_7
			= new AssetDescriptor<Texture>("kornyezet/korny-7.png", Texture.class);
	public static final AssetDescriptor<Texture> ENV_8
			= new AssetDescriptor<Texture>("kornyezet/korny-8.png", Texture.class);
	public static final AssetDescriptor<Texture> ENV_9
			= new AssetDescriptor<Texture>("kornyezet/korny-9.png", Texture.class);

	//zenék
	public static final AssetDescriptor<Music> EXTREMEACTION
			= new AssetDescriptor<Music>("music/bensound-extremeaction.mp3", Music.class);
	public static final AssetDescriptor<Music> HAPPYROCK
			= new AssetDescriptor<Music>("music/bensound-happyrock.mp3", Music.class);
	public static final AssetDescriptor<Music> MOOSE
			= new AssetDescriptor<Music>("music/bensound-moose.mp3", Music.class);
	public static final AssetDescriptor<Music> POPDANCE
			= new AssetDescriptor<Music>("music/bensound-popdance.mp3", Music.class);
	public static final AssetDescriptor<Music> DANCE
			= new AssetDescriptor<Music>("music/bensound-dance.mp3", Music.class);
	public static final AssetDescriptor<Music> BADASS
			= new AssetDescriptor<Music>("music/bensound-badass.mp3", Music.class);
	public static final AssetDescriptor<Music> CARENGINE
			= new AssetDescriptor<Music>("music/carEngine.mp3", Music.class);
	public static final AssetDescriptor<Music> STARTCAR
			= new AssetDescriptor<Music>("music/startcar.mp3", Music.class);

	public static void prepare() {
		manager = new AssetManager();
		Texture.setAssetManager(manager);
	}

	public static void load() {
		FileHandleResolver resolver = new InternalFileHandleResolver();
		manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
		manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));

		manager.load(FONT_C64_30);
		manager.load(TEXTBOX_TEXTURE);
		manager.load(CURSOR_TEXTURE);
		manager.load(EXTREMEACTION);
		manager.load(HAPPYROCK);
		manager.load(MOOSE);
		manager.load(POPDANCE);
		manager.load(BADASS);
		manager.load(DANCE);
		manager.load(BACKGROUND_TEXTURE);
		manager.load(MONEY_TEXTURE);
		manager.load(MUTE_ICON);
		manager.load(SOUND_ICON);
		manager.load(CITY_ACTION_BACKGROUND);
		manager.load(GAZ_ICON);
		manager.load(FEK_ICON);
		manager.load(CONF_ICON);
		manager.load(PLUS_VOL);
		manager.load(MINUS_VOL);
		manager.load(FILLED_VOL);
		manager.load(EMPTY_VOL);
		manager.load(ROAD_BLOCK);
		manager.load(ROAD_MENU);
		manager.load(HEARTONGOURND);
		manager.load(HEART);
		manager.load(NOHEART);
		manager.load(BLOCAKDE_0);
		manager.load(BLOCAKDE_1);
		manager.load(BLOCAKDE_2);
		manager.load(BLOCAKDE_3);
		manager.load(BATTERY);
		manager.load(ARROW);
		manager.load(EXPLOSION_TEXTUREATLAS);
		manager.load(MONEY_TEXTUREATLAS);
		manager.load(BREAK);
		manager.load(CAR_TEXTURE_1);
		manager.load(CAR_TEXTURE_2);
		manager.load(CAR_TEXTURE_3);
		manager.load(CAR_TEXTURE_4);
		manager.load(TEXT_1_1);
		manager.load(TEXT_1_2);
		manager.load(TEXT_1_3);
		manager.load(TEXT_2_1);
		manager.load(TEXT_2_2);
		manager.load(TEXT_2_3);
		manager.load(TEXT_3_1);
		manager.load(TEXT_3_2);
		manager.load(TEXT_3_3);
		manager.load(TEXT_4_1);
		manager.load(TEXT_4_2);
		manager.load(TEXT_4_3);
		manager.load(SBACKGROUND);
		manager.load(SKNOB);
		manager.load(HOUSE);
		manager.load(ENV_0);
		manager.load(ENV_1);
		manager.load(ENV_2);
		manager.load(ENV_3);
		manager.load(ENV_4);
		manager.load(ENV_5);
		manager.load(ENV_6);
		manager.load(ENV_7);
		manager.load(ENV_8);
		manager.load(ENV_9);
		manager.load(WALK_TEXTUREATLAS);
		manager.load(SZIRENA_TEXTUREATLAS);
		manager.load(STARTCAR);
		manager.load(CARENGINE);
	}

    public static void afterLoaded() {
        //manager.get(MUSIC).setLooping(true);
    }

	public static void unload() {
		manager.dispose();
	}

}
