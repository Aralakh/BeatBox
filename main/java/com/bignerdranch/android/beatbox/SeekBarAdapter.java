package com.bignerdranch.android.beatbox;

import android.databinding.BaseObservable;
import android.util.Log;
import android.widget.SeekBar;

/**
 * Created by lawren on 18/09/17.
 */

public class SeekBarAdapter extends BaseObservable {
    private BeatBox mBeatBox;
    private int mMinSeekBar;
    private int mMaxSeekBar;
    private int mRangeSeekBar;
    private float mMinSpeed;
    private float mMaxSpeed;
    private float mRangeSpeed;

    public SeekBarAdapter(BeatBox beatBox, SeekBar seekBar){
        mBeatBox = beatBox;
        mMinSeekBar = 0;
        mMaxSeekBar = seekBar.getMax();
        mRangeSeekBar = mMaxSeekBar - mMinSeekBar;
        mMinSpeed = BeatBox.MIN_PLAYBACK_SPEED;
        mMaxSpeed = BeatBox.MAX_PLAYBACK_SPEED;
        mRangeSpeed = mMaxSpeed - mMinSpeed;
    }

    public float getSpeed(){
        return mBeatBox.getPlayBackSpeed();
    }

    public int getSeekBarValue(){
        int seekBarValue = Math.round((mBeatBox.getPlayBackSpeed() - mMinSpeed) / mRangeSpeed * mRangeSeekBar + mMinSeekBar);

        if(seekBarValue > mMaxSeekBar){
            return mMaxSeekBar;
        }else if(seekBarValue < mMinSeekBar){
            return mMinSeekBar;
        }else{
            return seekBarValue;
        }
    }

    public void setSpeed(int seekBarValue){
        float speed = ((float) seekBarValue - mMinSeekBar) / mRangeSeekBar * mRangeSpeed + mMinSpeed;
        mBeatBox.setPlayBackSpeed(speed);
        notifyChange();
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
        if(fromUser) {
            setSpeed(progress);
        }else{
            Log.d("BeatBox,", "Got progress change of: " + progress + " but not from user.");
        }
    }

}
