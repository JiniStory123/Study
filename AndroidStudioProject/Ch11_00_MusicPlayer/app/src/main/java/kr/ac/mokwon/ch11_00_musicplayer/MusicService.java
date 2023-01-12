package kr.ac.mokwon.ch11_00_musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MusicService extends Service
{
    MediaPlayer player;

    @Override
    public void onCreate()
    {
        player = MediaPlayer.create(this, R.raw.suryeong);
        player.setLooping(false);
    }

    @Override
    public void onDestroy()
    {
        Toast.makeText(this, "Music Stop!", Toast.LENGTH_SHORT).show();
        player.stop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Toast.makeText(this, "Music Start!", Toast.LENGTH_SHORT).show();
        player.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
