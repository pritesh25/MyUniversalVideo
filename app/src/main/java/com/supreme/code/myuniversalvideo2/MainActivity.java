package com.supreme.code.myuniversalvideo2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.supreme.code.myuniversalvideo2.Library.UniversalMediaController;
import com.supreme.code.myuniversalvideo2.Library.UniversalVideoView;

public class MainActivity extends AppCompatActivity implements UniversalVideoView.VideoViewCallback{

    private static final String TAG = MainActivity.class.getSimpleName();
    //private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
    private static final String VIDEO_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    //private static final String VIDEO_URL = "http://192.168.1.131/selfielife/media/swapnil25/post_video/MP4_20180611_12162334307708.mp4";

    UniversalVideoView mVideoView;
    UniversalMediaController mMediaController;

    View mBottomLayout;
    View mVideoLayout;
    TextView mStart;

    private int mSeekPosition;
    private int cachedHeight;
    //private boolean isFullscreen;
    //no idnt want that

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoLayout    = findViewById(R.id.video_layout);
        mBottomLayout   = findViewById(R.id.bottom_layout);
        mVideoView      = (UniversalVideoView) findViewById(R.id.videoView);
        mMediaController = (UniversalMediaController) findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);
        //setVideoAreaSize();
        mVideoView.setVideoPath(VIDEO_URL);
        mVideoView.setVideoViewCallback(this);
        mStart = (TextView) findViewById(R.id.start);

//        mStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mSeekPosition > 0) {
//                    mVideoView.seekTo(mSeekPosition);
//                }
//                mVideoView.start();
//                mMediaController.setTitle("Big Buck Bunny");
//            }
//        });

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG, "onCompletion ");
            }
        });

        mVideoView.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause ");
        if (mVideoView != null && mVideoView.isPlaying()) {
            mSeekPosition = mVideoView.getCurrentPosition();
            Log.d(TAG, "onPause mSeekPosition=" + mSeekPosition);
            mVideoView.pause();
        }
    }


    private void setVideoAreaSize() {
        mVideoLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = mVideoLayout.getWidth();
                cachedHeight = (int) (width * 405f / 720f);
//                cachedHeight = (int) (width * 3f / 4f);
//                cachedHeight = (int) (width * 9f / 16f);
                ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cachedHeight;
                //mVideoLayout.setLayoutParams(videoLayoutParams);
                mVideoView.setVideoPath(VIDEO_URL);
                mVideoView.requestFocus();
            }
        });
    }

    /*
    @Override
    public void onScaleChange(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoLayout.setLayoutParams(layoutParams);
            mBottomLayout.setVisibility(View.GONE);

        } else {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = this.cachedHeight;
            mVideoLayout.setLayoutParams(layoutParams);
            mBottomLayout.setVisibility(View.VISIBLE);
        }

        switchTitleBar(!isFullscreen);
    }


    private void switchTitleBar(boolean show) {
        android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            if (show) {
                supportActionBar.show();
            } else {
                supportActionBar.hide();
            }
        }
    }
    */

    @Override
    public void onPause(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onPause UniversalVideoView callback");
    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onStart UniversalVideoView callback");
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onBufferingStart UniversalVideoView callback");
    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onBufferingEnd UniversalVideoView callback");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        if (this.isFullscreen) {
//            mVideoView.setFullscreen(false);
//        } else {
//            super.onBackPressed();
//        }
    }

}
