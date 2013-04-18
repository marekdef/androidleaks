package pl.mobilla.androidleaks.security;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import pl.mobilla.androidleaks.*;

/**
 * Created with IntelliJ IDEA.
 * User: marekdef
 * Date: 17.04.13
 * Time: 20:11
 * To change this template use File | Settings | File Templates.
 */
public class SecurityLeaks extends ListActivity {
    private ArrayAdapter<Class<?>> arrayAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arrayAdapter = new ArrayAdapter<Class<?>>(this, android.R.layout.simple_list_item_1);
        setListAdapter(arrayAdapter);

        arrayAdapter.add(SharedPrefsLeak.class);
        arrayAdapter.add(StringEncryption.class);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Class<?> cls = arrayAdapter.getItem(i);
                Intent intent = new Intent(SecurityLeaks.this.getApplicationContext(), cls);
                startActivity(intent);
            }
        });
    }
}