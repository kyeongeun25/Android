package com.biz.drawerlayout2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnOpen;
    Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnOpen = findViewById(R.id.btn_open);
        btnClose = findViewById(R.id.btn_close);

        btnOpen.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        DrawerLayout dr = findViewById(R.id.drawer);
        if(id==R.id.btn_open) {

            // Gravity_START, Gravity_LEFT 항목을 열기 위해서는
            // xml에 해당하는 layout_gravity가 선언되어 있어야 한다.
            if(dr.isDrawerOpen(Gravity.START)){
                dr.openDrawer(Gravity.START);
            }

        }
        if(id==R.id.btn_close){

            // Gravity_START, Gravity_LEFT 항목을 열기 위해서는
            // xml에 해당하는 layout_gravity가 선언되어 있어야 한다.
            if(dr.isDrawerOpen(Gravity.RIGHT)){
                dr.closeDrawer(Gravity.START);
            }

        }
        if(id==R.id.btn_open) {


            if(dr.isDrawerOpen(Gravity.START)){
                dr.openDrawer(Gravity.START);
            }

        }
        if(id==R.id.btn_close){

            if(dr.isDrawerOpen(Gravity.START)){
                dr.closeDrawer(Gravity.START);
            }

        }
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
