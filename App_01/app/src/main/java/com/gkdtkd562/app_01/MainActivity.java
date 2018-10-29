package com.gkdtkd562.app_01;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_plus;
    Button btn_min;
    Button btn_mul;
    Button btn_div;

    TextView input_text;

    EditText num1 ;
    EditText num2 ;

    int num_1;
    int num_2;

    String result = "";



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_plus = findViewById(R.id.btn_plus);
        btn_min = findViewById(R.id.btn_min);
        btn_mul = findViewById(R.id.btn_mul);
        btn_div = findViewById(R.id.btn_div);

        btn_plus.setOnClickListener(this);
        btn_min.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);

        input_text = findViewById(R.id.input_text);

        num1 = findViewById(R.id.input_num1);
        num2 = findViewById(R.id.input_num2);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        num_1 = Integer.parseInt(num1.getText().toString());
        num_2 = Integer.parseInt(num2.getText().toString());

        switch (id) {
            case R.id.btn_plus:

                result = Integer.toString(num_1+num_2);

                input_text.setText(btn_plus.getText() + "결과 : " + result) ;

                break;

            case R.id.btn_min:

                result = Integer.toString(num_1-num_2);

                input_text.setText(btn_min.getText() + "결과 : " + result) ;

                break;

            case R.id.btn_mul:

                result = Integer.toString(num_1*num_2);

                input_text.setText(btn_mul.getText() + "결과 : " + result) ;

                break;

            case R.id.btn_div :

                result = Integer.toString(num_1/num_2);

                input_text.setText(btn_div.getText() + "결과 : " + result) ;

                break;

        }


    }
}
