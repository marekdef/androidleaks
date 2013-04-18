package pl.mobilla.androidleaks;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: marekdef
 * Date: 14.04.13
 * Time: 17:40
 * To change this template use File | Settings | File Templates.
 */
public class DialogActivity extends Activity implements View.OnClickListener {

    private static final String TAG = DialogActivity.class.getSimpleName();
    private Button button;
    private TextView textView;
    private AlertDialog dialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, String.format("%d.onCreate()", System.identityHashCode(this)));

        setContentView(R.layout.dialog_activity);
        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);

        button.setOnClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialog = builder.setMessage("This will present dialog leak")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        textView.setText("Yes pressed");
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final TextView textView = (TextView) DialogActivity.this.findViewById(R.id.textView);
                textView.setText("Cancel pressed");
            }
        }).create();
    }

    @Override
    public void onClick(View view) {
        dialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
        dialog.dismiss();
    }
}