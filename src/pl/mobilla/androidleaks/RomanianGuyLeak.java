package pl.mobilla.androidleaks;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: marekdef
 * Date: 14.04.13
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 */
public class RomanianGuyLeak extends Activity {
    private static Drawable sBackground;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView label = new TextView(this);
        label.setText("Leaks are bad");

        if (sBackground == null) {
            sBackground = getResources().getDrawable(R.drawable.android);
        }
        label.setBackgroundDrawable(sBackground);

        setContentView(label);
    }
}