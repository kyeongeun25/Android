package com.biz.db_memo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.biz.db_memo.vo.MemoVO;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    // 1. 최초에 실행하여 DATABASE 생성
    public DBHelper (Context context){
        super(context, DBContract.DB_NAME, null, 1);

    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBContract.DB_NAME, factory, 1);
    }

    // 2. TABLE 생성
    // DB 관련 App이 (최초 실행되서) DB에 접근하려는 순간 (한번만) 실행되는 method
    @Override
    public void onCreate(SQLiteDatabase db) {

        // 앞으로 사용할 TABLE을 생성하는 SQL문을 여기에서 실행한다.
        db.execSQL(DBContract.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<MemoVO> selectAll(){
        SQLiteDatabase db = getReadableDatabase();

        // select * => 보고자 하는 컬럼을 문자열 배열로 선언을 먼저한다.
        String[] colums = {
                DBContract.COL_SUBJECT,
                DBContract.COL_DATE,
                DBContract.COL_MEMO
        };

        // DB로 부터 select된 결과는 JDBC에서 ResultSet과 비슷한 List 자료로 return 된다.
        Cursor cursor = db.query(DBContract.TABLE_NAME, colums, null, null, null ,null, null);

        List<MemoVO> memos = new ArrayList<MemoVO>();
        while(true){
            if(!cursor.moveToNext()) break;

            String subject = cursor.getString(0);
            String date = cursor.getString(1);
            String memo = cursor.getString(2);

            MemoVO vo = new MemoVO();
            vo.setSubject(subject);
            vo.setDate(date);
            vo.setMemo(memo);
            memos.add(vo);

        }
        return memos;
    }

    public long insertDB(MemoVO vo) {

        // data를 쓰기 가능하도록 open, 연결하라
        SQLiteDatabase db = getWritableDatabase();

        // 보안을 위해서 ContentValues라는 클래스의 도움을 받아서
        // 내부적으로 SQL문을 생성하고 외부에 노출되는 것을 최소한으로 한다.
        ContentValues values = new ContentValues();
        values.put(DBContract.COL_DATE, vo.getDate());
        values.put(DBContract.COL_SUBJECT, vo.getSubject());
        values.put(DBContract.COL_MEMO, vo.getMemo());

        long insertID = db.insert(DBContract.TABLE_NAME, null, values);

        return insertID;
    }
}
