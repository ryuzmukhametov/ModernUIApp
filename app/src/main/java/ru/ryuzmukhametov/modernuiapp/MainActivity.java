package ru.ryuzmukhametov.modernuiapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLeftTopRectangle;
    private LinearLayout mLeftBottomRectangle;
    private LinearLayout mRightTopRectangle;
    private LinearLayout mRightBottomRectangle;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLeftTopRectangle = (LinearLayout) findViewById(R.id.leftTopRectangle);
        mLeftBottomRectangle = (LinearLayout) findViewById(R.id.leftBottomRectangle);
        mRightTopRectangle = (LinearLayout) findViewById(R.id.rightTopRectangle);
        mRightBottomRectangle = (LinearLayout) findViewById(R.id.rightBottomRectangle);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MainActivity.this.changeBackgroundColorAccordingToProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    void changeBackgroundColorAccordingToProgress(int progress) {
        String color = "#FF40";
        String hex = Integer.toHexString(progress);
        if (hex.length() == 1) {
            hex = "0" + hex;
        }

        mLeftTopRectangle.setBackgroundColor(Color.parseColor(color + hex));
    }
}
