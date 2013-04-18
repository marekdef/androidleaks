package pl.mobilla.androidleaks;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: marekdef
 * Date: 18.04.13
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
public class HandlerLeak extends Activity {
    public static final int DELAY_MILLIS = 10000;
    private Handler handler = new Handler();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TextView textView = new TextView(this);
        textView.setId(12345);
        setContentView(textView);

        HandlerRunnable r = new HandlerRunnable(this, handler);
        handler.postDelayed(r, DELAY_MILLIS);
    }

    //    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        handler.removeCallbacksAndMessages(null);
//        handler = null;
//    }

    private static class HandlerRunnable implements  Runnable{
        private static final String TAG = HandlerRunnable.class.getSimpleName();
        private final Handler handler;
        private final Activity activity;
        private long counter;

        public HandlerRunnable(Activity handlerLeak, Handler handler) {
            this.activity = handlerLeak;
            this.handler = handler;
        }

        @Override
        public void run() {
            Log.d(TAG, String.format("%d.run()",System.identityHashCode(this)));
            counter++;
            ((TextView)activity.findViewById(12345)).setText(String.format("%d", counter));
        }
    }
}