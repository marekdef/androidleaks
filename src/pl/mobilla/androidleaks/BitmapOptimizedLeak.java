package pl.mobilla.androidleaks;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created with IntelliJ IDEA.
 * User: marekdef
 * Date: 15.04.13
 * Time: 20:57
 * To change this template use File | Settings | File Templates.
 */
public class BitmapOptimizedLeak extends Activity {
    private static final String TAG = BitmapOptimizedLeak.class.getSimpleName();
    private LinearLayout horizontalScrollView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bitmap_activity);

        final int width = getWindowManager().getDefaultDisplay().getWidth();
        final int height = getWindowManager().getDefaultDisplay().getHeight();

        horizontalScrollView = (LinearLayout) findViewById(R.id.horizontalScrollView);


        Cursor c = this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,

                null, null, null, null);

        try {
            if(c!=null)
            while (c.moveToNext()) {
                String pathName = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                BitmapFactory.Options bo = new BitmapFactory.Options();
                bo.inJustDecodeBounds = true;
                Bitmap bitmap = BitmapFactory.decodeFile(pathName, bo);
                bo.inJustDecodeBounds = false;
                bo.inSampleSize = getScaleFactor(bo.outWidth, bo.outHeight,width, height);
                bitmap = BitmapFactory.decodeFile(pathName, bo);
                if(bitmap == null)
                    break;

                final ImageView child = new ImageView(this);
                child.setImageBitmap(bitmap);
                child.setAdjustViewBounds(true);
                horizontalScrollView.addView(child);
            }
        } catch (OutOfMemoryError e) {
            Log.e(TAG, "We can't host any more bitmaps", e);

        } finally {
            c.close();
        }
    }

    private int getScaleFactor(int outWidth, int outHeight, int width, int height) {
        int scale = 1;
        while(outWidth > width * scale || outHeight > height * scale) {
            scale *= 2;
        }
        return scale;
    }


}