package pl.mobilla.androidleaks;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class LeakListActivity extends ListActivity {
    private ArrayAdapter<Class<?>> arrayAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arrayAdapter = new ArrayAdapter<Class<?>>(this, android.R.layout.simple_list_item_1);
        setListAdapter(arrayAdapter);

        arrayAdapter.add(DialogActivity.class);
        arrayAdapter.add(RomanianGuyLeak.class);
        arrayAdapter.add(AnimationActivity.class);
        arrayAdapter.add(BitmapLeak.class);
        arrayAdapter.add(BitmapOptimizedLeak.class);
        arrayAdapter.add(ContentProviderLeak.class);
        arrayAdapter.add(HandlerLeak.class);


        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Class<?> cls = arrayAdapter.getItem(i);
                Intent intent = new Intent(LeakListActivity.this.getApplicationContext(), cls);
                startActivity(intent);
            }
        });

    }
}
