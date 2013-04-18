package pl.mobilla.androidleaks;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;

/**
 * Created with IntelliJ IDEA.
 * User: marekdef
 * Date: 13.04.13
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
public class AnimationActivity extends Activity implements View.OnClickListener {

    private View blackdrop;
    private View wrench;
    private View screwdriver;
    private View android;

    private boolean gcOn = false;
    private Handler handler;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation);

        android = findViewById(R.id.imageViewAndroid);
        blackdrop = findViewById(R.id.imageViewBlackDrop);
        screwdriver = findViewById(R.id.imageViewBlackScrewdriver);
        wrench = findViewById(R.id.imageViewBlackWrench);

        android.setOnClickListener(this);

        blackdrop.setAnimation(AnimationUtils.loadAnimation(this, R.anim.blackdrop));
        wrench.setAnimation(AnimationUtils.loadAnimation(this, R.anim.wrench));
        screwdriver.setAnimation(AnimationUtils.loadAnimation(this, R.anim.screwdriver));

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(gcOn)
                    System.gc();
                handler.post(this);
            }
        }, 2000);
    }

    @Override
    public void onClick(View view) {
        gcOn = !gcOn;
    }
}