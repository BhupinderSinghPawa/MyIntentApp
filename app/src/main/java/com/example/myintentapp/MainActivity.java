package com.example.myintentapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_CONTACT_SUBACTIVITY = 2;

    public static final String EXTRA_MESSAGE = "com.example.mypehlapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("myLog", "MainActivity onCreate Called");
    }

    /** called when user clicks the button */
    public void sendMessage(View view){

        // get the message present in the textView
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        Log.d("myLog", message);


/*
        //  Example - 1
        // start an instance of DisplayMessageActivity specified by Intent - Explicit
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


        // Example - 2 - Implicit

        // Implicitly starting an activity - phone dialer
        // Action & Data, MIME:URI
        // ART will do intent resolution
        Intent myIntent = new Intent (Intent.ACTION_DIAL, Uri.parse("tel:555-2368"));

        // check if an activitiy exists to perform this action
        PackageManager pm = getPackageManager();
        ComponentName cn = myIntent.resolveActivity(pm);
        Log.i("myLog", cn.getClassName());
        if (cn != null)
           startActivity(myIntent);



        // Example - 3
        // Executed in an Activity, so 'this' is the Context
        // The fileUrl is a string URL, such as "http://www.example.com/image.png"
        Intent downloadIntent = new Intent(this, DownloadService.class);
        downloadIntent.setData(Uri.parse(fileUrl));
        startService(downloadIntent);


        // Example - 4 - Implicit, Multiple Applications with Intent
        // Create the text message with a string
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");

        // Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
            //  system examines all of the installed apps to determine which ones can handle this kind of intent
            //  (an intent with the ACTION_SEND action and that carries "text/plain" data).
        }


*/

        // Example - 5
        // Implicitly starting a sub-activity for a result
        // onActivityResult() event handler is fired

        startSubActivityImplicitly();

/*
        // Example - 6 - Go Crazy, Explore
        Intent newIntent;
        newIntent = new Intent (Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        newIntent = new Intent (Intent.ACTION_VIEW, Uri.parse("tel:555-2368"));
        newIntent = new Intent (Intent.ACTION_VIEW, Uri.parse("geo:47.6,-122.3"));
        newIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        startActivity(newIntent);

*/

    }

    // Example -5
    private void startSubActivityImplicitly() {
        Uri uri = Uri.parse("content://contacts/people");
        Intent intent = new Intent (Intent.ACTION_PICK, uri);
        startActivityForResult(intent, PICK_CONTACT_SUBACTIVITY);
    }

    // Example -5
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null)
            Log.i("myLog", "onActivityResult" + ", " + requestCode + ", " + resultCode + ", " + data.getData());
        else
            Log.i ("myLog", "No Results");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i("myLog", "MainActivity onSaveInstanceState");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.i("myLog", "MainActivity onRestoreInstanceState");
    }
}
