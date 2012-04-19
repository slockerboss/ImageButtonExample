package com.slockerboss.imagebutton;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

public class Main extends Activity {

	SoundPool soundPool;
	int miSonido;
	protected boolean primeraVez = true;
	private View wraper;
	private ImageButton imageButtonAltavoz;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.principal);
		cargarSonido("sonidoprueba.ogg");

		imageButtonAltavoz = (ImageButton) findViewById(R.id.ImageButtonAltavoz);

		imageButtonAltavoz.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				soundPool.play(miSonido, 1, 1, 0, 0, 1);
			}
		});

		wraper = findViewById(R.id.LayoutPadre);
		wraper.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {

				
				return false;
			}
		});

	}

	private void cargarSonido(String sonido) {
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		try {
			AssetManager assetManager = this.getAssets();
			AssetFileDescriptor assetFd = assetManager.openFd(sonido);
			miSonido = soundPool.load(assetFd, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}