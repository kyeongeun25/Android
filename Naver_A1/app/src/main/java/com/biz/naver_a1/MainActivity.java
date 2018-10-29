package com.biz.naver_a1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.biz.naver_a1.helper.BookViewAdapter;
import com.biz.naver_a1.helper.Naver_Book_item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    TextInputEditText txt_search;
    List<Naver_Book_item> books;
    RecyclerView bookListView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txt_search = findViewById(R.id.txt_book_search);

        // 키보드의 검색(돋보기) 버튼을 사용해서 검색을 실행하기 위해
        // 이벤트를 설정한다.
        txt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId== EditorInfo.IME_ACTION_SEARCH){

                    // 검색을 실행
                    // Toast.makeText(MainActivity.this,"검색버튼눌림",Toast.LENGTH_SHORT).show();
                    // naverSearch();

                    // 대리자를 통해서 naverSearch를 실행
                    NaverSearch ns = new NaverSearch(); // 대리자 생성
                    ns.execute(100);    // 대리자한테 임무 부여
                    return true;

                } else {

                    // search 버튼 이외 다른 버튼이 눌리면 기본동작만 하라
                    return false;
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    // naverSearch method를 실행할 대리자 클래스를 선언
    class NaverSearch extends AsyncTask<Integer, Integer, Void> {

        // 실제 naver에 데이터를 요청할 실질적인 심부름꾼
        @Override
        protected Void doInBackground(Integer... integers) {
            naverSearch();
            return null;
        }

        // doInBackground가 심부름을 모두 마치고 나면
        // 작동될 method
        // naverSearch()를 사용해서 가져온 데이터를 화면에 뿌리기 위해
        // view로 만드는 작업을 여기에서 실행
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            BookViewAdapter adapter = new BookViewAdapter(books);
            bookListView = findViewById(R.id.book_views);
            bookListView.setAdapter(adapter);
            StaggeredGridLayoutManager gl = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
            bookListView.setLayoutManager(gl);
        }
    }

    // 네이버 검색 method
    public void naverSearch() {

        // Naver 검색을 위한 Key값
        String Client_id  = "QA2bR5q9xMwdjE9JWV02";
        String Client_sec = "AJLNTkO9_B";


        // 검색 input box에 입력한 문자열을 가져와라
        String txt_book = txt_search.getText().toString();

        String api_url = "https://openapi.naver.com/v1/search/book.json";
        try {
            // 연결을 위한 준비단계
            api_url += "?query=" + URLEncoder.encode(txt_book,"UTF-8");

            // 문자열을 네이버에게 보내기 위해
            // java의 URL이라는 형식으로 변환시킨다.
            URL url = new URL(api_url);

            // 연결시작
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // 연결해서 헤더 정보 설정하기
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Naver-Client-Id",Client_id);
            conn.setRequestProperty("X-Naver-Client-Secret",Client_sec);

            // 연결시도
            int resCode = conn.getResponseCode();

            BufferedReader buffer;
            String reader = new String();
            StringBuffer sBuffer = new StringBuffer();

            if (resCode == 200) {  // 정상적으로 응답이 왔으면
                InputStreamReader is = new InputStreamReader(conn.getInputStream());
                buffer = new BufferedReader(is);
            } else {
                InputStreamReader is = new InputStreamReader(conn.getErrorStream());
                buffer = new BufferedReader(is);
            }

            // 실제 문자열을 읽기
            while(true){

                reader = buffer.readLine();
                if(reader == null) break;
                sBuffer.append(reader);

            }
            Log.i("Naver:",sBuffer.toString());

            // Naver로부터 응답된 값을 JSON으로 일단 변환
            try {
                JSONObject resJson = new JSONObject(sBuffer.toString());

                // items 이하 값맍 추출해서 배열로 만들기
                JSONArray items = resJson.getJSONArray("items");

                books = new ArrayList<Naver_Book_item>();
                for(int i = 0 ; i < items.length(); i++){
                    JSONObject js = (JSONObject) items.get(i);
                    Naver_Book_item vo = new Naver_Book_item();
                    vo.setTitle(js.getString("title"));
                    vo.setLink(js.getString("link"));
                    vo.setAuthor(js.getString("author"));
                    vo.setImage(js.getString("image"));

                    books.add(vo);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
