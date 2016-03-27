package android.activity;

import android.activity.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {

	//protected int splashTime = 5000;
	protected int splashTime = 100;

	private Thread splashTread;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		splashTread = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						wait(splashTime);
					}

				} catch (InterruptedException e) {
				} finally {

					try {
						Intent i = new Intent();
						i.setClass(SplashScreen.this, ProjetoActivity.class);
						startActivity(i);

						finish();

					} catch (Exception e2) {
					}
				}
			}
		};

		splashTread.start();
	}
}
