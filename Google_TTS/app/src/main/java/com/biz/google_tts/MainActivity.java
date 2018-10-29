package com.biz.google_tts;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText txt_input;
    TextToSpeech myTTS;

    int REQ_CODE_SPEECH_ID = 1 ;

    BroadcastReceiver bRecv ;

    // 필요한 임의 상수 선언부분
    public enum TTS_RECV {
        INIT,   // 0
        SEARCH
    }

    TTS_RECV tts_recv = TTS_RECV.INIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txt_input = findViewById(R.id.txt_input);

        myTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                myTTS.setLanguage(Locale.getDefault());
                myTTS.setPitch(1.0f);
                myTTS.setSpeechRate(1.0f);

                myTTS.speak("안녕하세요", TextToSpeech.QUEUE_FLUSH,null,null);
            }
        }) ;

        // 마이크 버튼 클릭
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int DRAWABLE_LEFT = 0;
               int DRAWABLE_TOP = 1;
               int DRAWABLE_RIGHT = 2;
               int DRAWABLE_BOTTOM = 3;

                /**
                 * IntentFilter
                 * 내 App는 없지만, 내 phone에 설치된 기본 기능,
                 * 또는 다른사람이 만든 App의 기능중에서 공개한 Activity를 사용하고자 할때 쓰는 Intent 클래스이다.
                 */
               IntentFilter filter = new IntentFilter();
               filter.addAction(TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);

               myTTS.speak("말하기 창이 나타나면 검색어를 말하세요", TextToSpeech.QUEUE_FLUSH, null,null);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
