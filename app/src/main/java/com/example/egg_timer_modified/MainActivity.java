package com.example.egg_timer_modified;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean counterIsActive=false;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar seekbar=findViewById(R.id.seekbar);
        seekbar.setProgress(30);
        TextView t=findViewById(R.id.textView);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int seconds, boolean fromUser) {
                int minutes=seconds/60;
                int new_seconds=seconds-(minutes*60);
                Log.i("seconds ",new_seconds+"");
                String new_seconds_modified=new_seconds<10?"0"+new_seconds:""+new_seconds;
                t.setText(minutes+":"+new_seconds_modified);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void clickFunction(View view){
        Button btn=findViewById(R.id.button);
        SeekBar seekbar=findViewById(R.id.seekbar);
        TextView t=findViewById(R.id.textView);
        if(counterIsActive){
            counterIsActive=false;
            countDownTimer.cancel();
            seekbar.setEnabled(true);
            t.setText("0:30");
            btn.setText("GO!");
            seekbar.setProgress(30);
        }else{
            seekbar.setEnabled(false);
            btn.setText("STOP!");
            counterIsActive=true;
            countDownTimer=new CountDownTimer(seekbar.getProgress()*1000,1000){
                @Override
                public void onTick(long secondsLeft) {
                    long seconds=secondsLeft/1000;
                    long minutes=seconds/60;
                    long new_seconds=seconds-(minutes*60);
                    String new_seconds_modified=new_seconds<10?"0"+new_seconds:""+new_seconds;
                    t.setText(minutes+":"+new_seconds_modified);
                }
                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.alarm);
                    mediaPlayer.start();
                    t.setText("0:30");
                    btn.setText("GO!");
                    counterIsActive=false;
                    countDownTimer.cancel();
                    seekbar.setEnabled(true);
                    seekbar.setProgress(30);
                }
            }.start();
        }
           // Log.i("total_Progress",seekbar.getProgress()+"");

    }
}