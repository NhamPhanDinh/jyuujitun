package jp.ne.jyuujitunohyojyunsiyou.poppup;

import java.io.IOException;

import jp.ne.jyuujitunohyojyunsiyou.R;
import jp.ne.jyuujitunohyojyunsiyou.controller.VideoControllerView;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Class show poppup video
 * 
 * @author Nhampd
 * @version 0.1
 */
public class PoppupVideoPlayer extends Dialog implements
		android.view.View.OnClickListener, SurfaceHolder.Callback,
		MediaPlayer.OnPreparedListener, VideoControllerView.MediaPlayerControl {
	// In variable
	SurfaceView videoSurface;
	MediaPlayer player;
	VideoControllerView controller;
	String path = null;
	Button mClosePoppup;
	View mRoot;

	/**
	 * Contructor video poppup
	 * 
	 * @param context
	 * @param strContent
	 */
	public PoppupVideoPlayer(Context context) {
		super(context);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));

		setContentView(R.layout.poppup_video_player);
		// get path image
		path = "android.resource://" + context.getPackageName() + "/"
				+ R.raw.video1;
		// load video via surfaceView
		videoSurface = (SurfaceView) findViewById(R.id.videoSurfacePoppup);
		SurfaceHolder videoHolder = videoSurface.getHolder();
		videoHolder.addCallback(this);

		player = new MediaPlayer();
		controller = new VideoControllerView(context);

		// play video
		try {
			player.setAudioStreamType(AudioManager.STREAM_MUSIC);
			player.setDataSource(context, Uri.parse(path));
			player.setOnPreparedListener(this);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// add layout
		mClosePoppup = (Button) findViewById(R.id.closePoppup);

		mClosePoppup.setOnClickListener(this);
		// show controller
		controller.show();
	}

	/**
	 * Event click button
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == mClosePoppup) {
			dismiss();
			if (player.isPlaying()) {
				player.stop();
//				player.release();
			}

		}
	}

	/**
	 * Event touch screen
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		controller.show();
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.media.MediaPlayer.OnPreparedListener#onPrepared(android.media
	 * .MediaPlayer)
	 */
	@Override
	public void onPrepared(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		controller.setMediaPlayer(this);
		controller
				.setAnchorView((FrameLayout) findViewById(R.id.videoSurfaceContainer));
		controller.show(); // show bar at here
		player.start();
		controller.updatePausePlay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.SurfaceHolder.Callback#surfaceChanged(android.view.SurfaceHolder
	 * , int, int, int)
	 */
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.SurfaceHolder.Callback#surfaceCreated(android.view.SurfaceHolder
	 * )
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		player.setDisplay(holder);
		player.prepareAsync();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.SurfaceHolder.Callback#surfaceDestroyed(android.view.
	 * SurfaceHolder)
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demoui.controller.VideoControllerView.MediaPlayerControl#
	 * canPause()
	 */
	@Override
	public boolean canPause() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demoui.controller.VideoControllerView.MediaPlayerControl#
	 * canSeekBackward()
	 */
	@Override
	public boolean canSeekBackward() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demoui.controller.VideoControllerView.MediaPlayerControl#
	 * canSeekForward()
	 */
	@Override
	public boolean canSeekForward() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demoui.controller.VideoControllerView.MediaPlayerControl#
	 * getBufferPercentage()
	 */
	@Override
	public int getBufferPercentage() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demoui.controller.VideoControllerView.MediaPlayerControl#
	 * getCurrentPosition()
	 */
	@Override
	public int getCurrentPosition() {
		return player.getCurrentPosition();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demoui.controller.VideoControllerView.MediaPlayerControl#
	 * getDuration()
	 */
	@Override
	public int getDuration() {
		return player.getDuration();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demoui.controller.VideoControllerView.MediaPlayerControl#
	 * isPlaying()
	 */
	@Override
	public boolean isPlaying() {
		return player.isPlaying();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demoui.controller.VideoControllerView.MediaPlayerControl#
	 * pause()
	 */
	@Override
	public void pause() {
		player.pause();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demoui.controller.VideoControllerView.MediaPlayerControl#
	 * seekTo(int)
	 */
	@Override
	public void seekTo(int i) {
		player.seekTo(i);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demoui.controller.VideoControllerView.MediaPlayerControl#
	 * start()
	 */
	@Override
	public void start() {
		player.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demoui.controller.VideoControllerView.MediaPlayerControl#
	 * isFullScreen()
	 */
	@Override
	public boolean isFullScreen() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.demoui.controller.VideoControllerView.MediaPlayerControl#
	 * toggleFullScreen()
	 */
	@Override
	public void toggleFullScreen() {

	}

	/**
	 * CLose poppup
	 */
	protected void closePoppup() {
		dismiss();
	}

}
