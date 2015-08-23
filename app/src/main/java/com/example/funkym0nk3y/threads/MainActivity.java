package com.example.funkym0nk3y.threads;

import android.app.Activity;
import android.os.Message;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends Activity {

    private static final int SLEEP = 1000;
    private static final int INCREMENT = 5;
    private String msgEnd = "La ejecución ha terminado.";

    private ProgressBar bar;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            bar.incrementProgressBy(INCREMENT);
            if (bar.getProgress() == 100) {
                bar.setProgress(0);
                Toast.makeText(MainActivity.this, msgEnd, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (ProgressBar) findViewById(R.id.progress);
    }

    public void onStart() {
        super.onStart();
        bar.setProgress(0);

        Thread background = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0; i<20; i++) {
                        Thread.sleep(SLEEP);
                        handler.sendMessage(handler.obtainMessage());
                    }
                } catch (Throwable t) {

                }
            }
        });
        background.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
