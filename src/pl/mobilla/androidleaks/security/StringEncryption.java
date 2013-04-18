package pl.mobilla.androidleaks.security;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import pl.mobilla.androidleaks.R;

/**
 * Created with IntelliJ IDEA.
 * User: marekdef
 * Date: 17.04.13
 * Time: 21:51
 * To change this template use File | Settings | File Templates.
 */
public class StringEncryption extends Activity {
    String hello_encrypted = "\u5CE5\u5C1E\u5C02\u5C14\u5C1A\u5CD1\u5CF6\u5C14" +
            "\u5C0A\u5CE0\u5C0C\u5C16\u5C09\u5C1D\u5CD9\u5CDB" +
            "\u5CEF\u5CC5\u5C05\u5C08\u5CC0\u5C06\u5C0C\u5C1E" +
            "\u5C1E\u5C16\u5C1E\u5C1B\u5C0F\u5C0D\u5CC9";

    String hello =  decryptString(hello_encrypted);

    String hello_unencrypted = "Hello DevCrowd! I am unencrypted!";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.string_encryption);

        ((TextView)findViewById(R.id.textViewUnencrypted)).setText(hello_unencrypted);
        ((TextView)findViewById(R.id.textViewEncrypted)).setText(decryptString(hello_encrypted));
    }

    public static String decryptString(String string) {
        for (int sguRA = 0, CZKtr = 0; sguRA < 31; sguRA++)
        {
            CZKtr = string.charAt(sguRA);
            CZKtr ^= 0x9F83;
            CZKtr ^= 0xFFFF;
            CZKtr ^= 0x591A;
            CZKtr ^= sguRA;
            CZKtr += 0x2021;
            CZKtr ^= 0x85F3;
            string = string.substring(0, sguRA) + (char)(CZKtr & 0xFFFF) + string.substring(sguRA + 1);
        }
        return string;
    }
}