package pl.mobilla.androidleaks;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: marekdef
 * Date: 18.04.13
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
public class StaticLeak extends Activity {
    private static StaticLeak instance;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        instance = null;
    }
}