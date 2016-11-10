package ru.ryuzmukhametov.modernuiapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        int colors[] = new int[] {R.color.colorLightBlue, R.color.colorLightRed, R.color.colorRed, R.color.colorBlue};
        LinearLayout layouts[] = new LinearLayout[] {mLeftTopRectangle, mLeftBottomRectangle, mRightTopRectangle, mRightBottomRectangle};
        int index = 0;
        for (LinearLayout layout : layouts) {
            int color = ContextCompat.getColor(this, colors[index++]);
            int r = Color.red(color);
            int g = Color.green(color);
            int b = Color.blue(color);
            int a = Color.alpha(color);
            g = (g + progress) % 255;
            color = Color.argb(a, r, g, b);
            layout.setBackgroundColor(color);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showDialog();
        return true;
    }

    private void openWebBrowser() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
        startActivity(browserIntent);
    }

    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);

        builder.setPositiveButton(R.string.visit_moma, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                openWebBrowser();
            }
        });
        builder.setNegativeButton(R.string.not_now, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
