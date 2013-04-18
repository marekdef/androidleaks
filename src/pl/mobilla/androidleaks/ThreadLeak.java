package pl.mobilla.androidleaks;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: marekdef
 * Date: 18.04.13
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
public class ThreadLeak extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new Thread() {
            @Override
            public void run() {
                try {
                    //Holding activity for 2000
                    Thread.sleep(20000);
                } catch (InterruptedException ignore) {

                }
            }
        }.start();
    }
}