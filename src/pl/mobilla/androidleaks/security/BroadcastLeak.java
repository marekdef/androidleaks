package pl.mobilla.androidleaks.security;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: marekdef
 * Date: 17.04.13
 * Time: 23:08
 * To change this template use File | Settings | File Templates.
 */
public class BroadcastLeak extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getDataString(), Toast.LENGTH_LONG).show();
    }
}
