package jp.ne.jyuujitunohyojyunsiyou;

import jp.ne.jyuujitunohyojyunsiyou.poppup.PoppupVideoPlayer;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.VideoView;

public class Jyuujitunohyojyunsiyou_02_ol_01_Activity extends Activity
		implements OnClickListener {
	private String TAG = "MainActivity";
	// Image button
	private ImageButton btnPoppup;
	private ImageButton btnColorful;
	private ImageButton btnComfortable;
	private ImageButton btnNatural;
	private ImageButton btnFlexible;
	// Video View
	private VideoView myVideoView;
	// Path video
	private String viewSource = null;
	// Poppup video
	private PoppupVideoPlayer poppup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jyuujitunohyojyunsiyou_02_ol_01);
		// get data from XML
		btnPoppup = (ImageButton) findViewById(R.id.showPoppup);
		myVideoView = (VideoView) findViewById(R.id.videoSurface);
		btnColorful = (ImageButton) findViewById(R.id.btnColorful);
		btnComfortable = (ImageButton) findViewById(R.id.btnComfortable);
		btnNatural = (ImageButton) findViewById(R.id.btnNatural);
		btnFlexible = (ImageButton) findViewById(R.id.btnFlexible);
		// Load video View
		viewSource = "android.resource://" + getPackageName() + "/"
				+ R.raw.video1;
		myVideoView.setVideoURI(Uri.parse(viewSource));
		myVideoView.setMediaController(null);// new MediaController(this)
		myVideoView.requestFocus();
		myVideoView.suspend();
		myVideoView.start();
		// set onclick image button
		btnPoppup.setOnClickListener(this);
		btnColorful.setOnClickListener(this);
		btnComfortable.setOnClickListener(this);
		btnNatural.setOnClickListener(this);
		btnFlexible.setOnClickListener(this);
	}

	/**
	 * Event click image button
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// show popup
		if (v == btnPoppup) {
			Log.e("MainActivity", "Show poppup");
			// pause video background
			if (myVideoView.isPlaying()) {
				myVideoView.pause();
			}
			// show dialog poppup
			poppup = new PoppupVideoPlayer(
					Jyuujitunohyojyunsiyou_02_ol_01_Activity.this);
			poppup.show();

		} else if (v == btnColorful) {
			gotoActivity(Jyuujitunohyojyunsiyou_02_ol_05_Activity.class);
		} else if (v == btnComfortable) {
			gotoActivity(Jyuujitunohyojyunsiyou_02_ol_06_Activity.class);
		} else if (v == btnNatural) {
			gotoActivity(Jyuujitunohyojyunsiyou_02_ol_02_Activity.class);
		} else if (v == btnFlexible) {
			gotoActivity(Jyuujitunohyojyunsiyou_02_ol_07_Activity.class);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onWindowFocusChanged(boolean)
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		// check poppup showing
		if (poppup != null) {
			if (!poppup.isShowing()) {
				if (!myVideoView.isPlaying()) {
					Log.e(TAG, "Run focus change and play video");
					myVideoView.start();
				}
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (myVideoView != null) {
			Log.e(TAG, "Destroy video");
			myVideoView.stopPlayback();
			myVideoView.clearFocus();
		}

	}

	/**
	 * Go to activity
	 */
	public void gotoActivity(Class<?> cls) {
		Intent intent = new Intent(
				Jyuujitunohyojyunsiyou_02_ol_01_Activity.this, cls);
		startActivity(intent);
		// finish();
	}

}
