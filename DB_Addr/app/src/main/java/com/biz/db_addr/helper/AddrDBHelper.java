package com.biz.db_addr.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.biz.db_addr.helper.AdddrDBContract.ADDR_TABLE;
import static com.biz.db_addr.helper.AdddrDBContract.CREATE_TABLE;

/*
    주소록을 DB 저장 할 때 도움을 줄 도우미 Class
    1. SQLOpenHelper를 상속받고
    2. onCreate method를 정의
 */
public class AddrDBHelper extends SQLiteOpenHelper{
    public AddrDBHelper(Context context) {
        super(context, ADDR_TABLE, null, 1);
    }

    // App이 실행해서 최초로 누군가가 DB에 접근하려고 할 때
    // DB 접근 : CRUD 중에 한가지
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
