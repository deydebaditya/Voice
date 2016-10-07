package com.kishor.voice;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private  TextView string_go ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        string_go=(TextView)findViewById(R.id.result);
    }

    public void setButtonclick(View view) {
        if (view.getId() == R.id.button) ;

        promptsearchinput();


    }

    public void promptsearchinput() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.EXTRA_LANGUAGE_MODEL);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, " say something");
        try {
            startActivityForResult(i, 100);

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "sorry! your device doesnt support this", Toast.LENGTH_LONG).show();

        }
    }

    public void onActivityResult(int request_code, int result_code, Intent i) {
        String input ;
        super.onActivityResult(request_code, result_code, i);
        switch (request_code) {
            case 100:
                if (result_code == RESULT_OK && i != null) {
                    ArrayList<String> string_result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    string_go.setText(string_result.get(0));
                    input=string_result.get(0);

                }
                break;
        }
    }
}