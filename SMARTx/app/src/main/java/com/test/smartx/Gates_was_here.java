package com.test.smartx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class Gates_was_here extends Activity implements TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener {

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gates_was_here);
        setTitle("SMARTx");

        tts = new TextToSpeech(Gates_was_here.this, Gates_was_here.this );
        final Button speak = (Button) findViewById(R.id.button2);
        //final TextView tv = (TextView) findViewById(R.id.textView);
        final String s = "I like it when you touch me there";

        speak.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if(!tts.isSpeaking()){
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "SampleText");
                    //tts.speak(tv.getText().toString(), TextToSpeech.QUEUE_ADD, params);
                    tts.speak(s, TextToSpeech.QUEUE_ADD, params);
                    speak.setVisibility(Button.GONE);
                } else {
                    tts.stop();
                }
            }
        });

    }

    public void buttonOnClick(View v)
    {
        Intent i = new Intent(Gates_was_here.this,MyActivity.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gates_was_here, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInit(int i) {
        tts.setOnUtteranceCompletedListener(this);
    }

    @Override
    public void onUtteranceCompleted(String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(Gates_was_here.this, "Utterence complete", Toast.LENGTH_SHORT).show();
                Button btn = (Button) findViewById(R.id.button2);
                btn.setVisibility(Button.VISIBLE);
            }
        });
    }

    @Override
    protected void onDestroy(){
        if(tts!=null){
            tts.stop();
            tts.shutdown();
            tts = null;
        }
        super.onDestroy();
    }
}
