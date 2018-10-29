package com.biz.naver_v2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 대리자 없이 직접 호출
        // naver_movie();

        // 대리자를 통해서 호출
        Naver_Search_Delegate nd = new Naver_Search_Delegate();
        nd.execute(100); //doInBackground를 실행하라

    } // onCreate 끝나는 부분

    /*
        naver_movie() 메서드를 실행할 대리자 생성
        1. AsyncTask 클래스를 상속받는 class를 생성한다.
        2. doInBackground() 메서드를 implement 한다.
            => 강제로 implement를 해야한다.
        3. onPostExecute() 메서드를 implement한다.
            => 임의로 implement를 한다.
            => alt-insert > Override Method를 실행하고 선택
        4. onPostExecute와 doInBackground() 메서드는 대리자에게 일을 시킬 때 사용할 method()

        * doInBackground : 실제로 naver_movie() 메서드를 사용해서 naver로 부터 영화 정보를 조회 할 심부름꾼
        * onPostExecute : doInBackground()가 일을 모두 마치면 main에게 결과를 보고 할 method
     */
    class Naver_Search_Delegate extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            Log.i("네이버:","영화검색 시작");
            naver_movie();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Log.i("네이버:","영화 검색 완료");
        }
    }

    /*
        실제 naver에 연결해서 naver로 부터 영화정보를 가져올 method
        우리는 직접 이 method를 호출하지 않고 대리자를 통해서 method 호출을 심부를 시킬 예정

        1. naver의 요청 URL 문자열을 생성
        2. URL 객체 생성
        3. URL 객체를 통해 연결을 시도
            연결 시도 전에 연결을 위한 key 값 설정

        4. 연결정보를 획득하고
        5. 연결정보를 통해서 query를 보내고
        6. inputStream으로 결과를 받고
        7. bufferedReader를 사용해서 문자열을 추출

     */
    public void naver_movie(){

        // naver 영화 요청 URL
        String movie_url = "https://openapi.naver.com/v1/search/movie.json";
        String movie_name = "신과함께";   // 찾을(검색할) 문자열

        String api_url = movie_url;
        try {
            // 1. 요청 URL 문자열 생성
            api_url += "?query=" + URLEncoder.encode(movie_name,"UTF-8");

            // 2. URL 객체로 변환
            URL url = new URL(api_url);

            // 3. 연결 시도
            // 3-1. Http 객체를 생성
            // 3-2. url.openConnection을 HttpURLConnection으로 형변환
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 3-3. 연결 key값을 설정
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Naver-Client-Id", Naver_Key.CLIENT_ID);
            conn.setRequestProperty("X-Naver-Client-Secret", Naver_Key.CLIENT_SECRET);

            // 3-4. 실제 연결 실행, 시도
            int resCode = conn.getResponseCode();
            // 연결이 성공하면 resCode값은 200이라는 정수값을 갖는다.
            if(resCode == 200){
                // 성공했으면
                Log.i("네이버 res OK!!",String.valueOf(resCode));
                InputStreamReader is = new InputStreamReader(conn.getInputStream());
                BufferedReader buffer = new BufferedReader(is);

                String reader = new String();
                while(true){
                    reader = buffer.readLine();
                    if(reader == null) break;
                    Log.i("네이버 영화", reader);
                }


            } else {
                Log.i("네이버 res Error", String.valueOf(resCode));
            }


        } catch (UnsupportedEncodingException e) {
            // Encoding 예외처리
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // URL 객체 생성 예외 처리
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
