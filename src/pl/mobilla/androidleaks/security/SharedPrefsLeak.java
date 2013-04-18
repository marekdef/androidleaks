package pl.mobilla.androidleaks.security;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import pl.mobilla.androidleaks.R;

/**
 * Created with IntelliJ IDEA.
 * User: marekdef
 * Date: 17.04.13
 * Time: 20:35
 * To change this template use File | Settings | File Templates.
 */
public class SharedPrefsLeak extends Activity {

    public static final String IS_LICENSED = "isLicensed";
    private TextView textView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_prefs);

        SharedPreferences sharedPreferences = this.getSharedPreferences(SharedPrefsLeak.class.getSimpleName() + "_private", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences2 = this.getSharedPreferences(SharedPrefsLeak.class.getSimpleName() + "_readable", Context.MODE_WORLD_READABLE);
        SharedPreferences sharedPreferences3 = this.getSharedPreferences(SharedPrefsLeak.class.getSimpleName() + "_writable", Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
        if(!sharedPreferences.contains(IS_LICENSED)) {
            initializeLicenced(sharedPreferences);
        }

        if(!sharedPreferences2.contains(IS_LICENSED)) {
            initializeLicenced(sharedPreferences2);
        }

        if(!sharedPreferences3.contains(IS_LICENSED)) {
            initializeLicenced(sharedPreferences3);
        }


    }

    private void initializeLicenced(SharedPreferences sharedPreferences) {
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(IS_LICENSED, false);
        edit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        textView = (TextView)findViewById(R.id.textView);
        SharedPreferences sharedPreferences = this.getSharedPreferences(SharedPrefsLeak.class.getSimpleName(), Context.MODE_PRIVATE);
        boolean isLicensed = sharedPreferences.getBoolean(IS_LICENSED, false);
        textView.setText("You are " + (isLicensed ? "licenced" : "not licenced"));
    }
}