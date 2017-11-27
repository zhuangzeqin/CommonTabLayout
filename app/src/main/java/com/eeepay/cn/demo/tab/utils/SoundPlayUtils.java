package com.eeepay.cn.demo.tab.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.eeepay.cn.demo.tab.R;


/**
 * 描述：封装一个简单音频播放类；用于切换TAB 页面进行播放
 * 作者：zhuangzeqin
 * 时间: 2017/9/12-9:56
 * 邮箱：zzq@eeepay.cn
 */
public class SoundPlayUtils {
    private static volatile SoundPlayUtils mInstance = null;
    // SoundPool对象AudioManager.STREAM_RING
    private static SoundPool mSoundPlayer = new SoundPool(10, AudioManager.STREAM_RING, 5);
    private static SoundPlayUtils soundPlayUtils;
    private SoundPlayUtils() {
    }
    public static SoundPlayUtils getInstance() {
        if (mInstance == null) {
            synchronized (SoundPlayUtils.class) {
                if (mInstance == null) {
                    mInstance = new SoundPlayUtils();
                }
            }
        }
        return mInstance;
    }
    /**
     * 初始化默认的声音加载
     * @param context
     */
    public void initDefaultSoundPool(Context context) {
        if (soundPlayUtils == null) {
            soundPlayUtils = new SoundPlayUtils();
        }
        // 初始化声音
        mSoundPlayer.load(context, R.raw.click, 1);
    }
    /**
     * 播放声音
     * <p>
     * 参数	解释
     * soundID	声音的id(即：load到SoundPool的顺序，从1开始)
     * leftVolume\rightVolume	左\右声道的音量控制, 0.0 到 1.0
     * priority	优先级，0是最低优先级
     * loop	是否循环播放，0为不循环，-1为循环
     * rate	播放比率，从0.5到2，一般为1，表示正常播放
     */
    public void play() {
        if (mSoundPlayer!=null)
            mSoundPlayer.play(1, 1, 1, 0, 0, 1);
    }
}