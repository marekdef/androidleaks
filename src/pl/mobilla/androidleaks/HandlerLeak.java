package pl.mobilla.androidleaks;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: marekdef
 * Date: 18.04.13
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
public class HandlerLeak extends Activity {
    private Handler handler = new Handler();
    private HandlerRunnable r;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TextView textView = new TextView(this);
        setContentView(textView);

        r = new HandlerRunnable(textView, handler);
        handler.postDelayed(r, 1000);
    }


    private static class HandlerRunnable implements  Runnable{
        private final Handler handler;
        private final TextView activity;
        private long counter;

        public HandlerRunnable(TextView handlerLeak, Handler handler) {
            this.activity = handlerLeak;
            this.handler = handler;
        }
        @Override
        public void run() {
            counter++;
            this.activity.setText(String.format("Called %d times", counter));
            handler.postDelayed(this, 1000);
        }


    }
}