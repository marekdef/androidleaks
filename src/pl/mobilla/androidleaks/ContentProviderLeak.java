package pl.mobilla.androidleaks;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

/**
 * Created with IntelliJ IDEA.
 * User: marekdef
 * Date: 17.04.13
 * Time: 18:55
 * To change this template use File | Settings | File Templates.
 */
public class ContentProviderLeak extends Activity {

    private Cursor cursor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cursor = this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);


    }
}