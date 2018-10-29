package com.biz.photo_memo.db;

/*
    DB에 CRUD를 실행 할 때,
    복잡한 과정을 거치지 않고 단순하게 처리 할 수 있도록 도와주는 클래스
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.biz.photo_memo.vo.MemoVO;

import java.util.ArrayList;
import java.util.List;

public class MemoDBHelper extends SQLiteOpenHelper {

    public MemoDBHelper(Context context) {
        super(context, DBContract.DB_NAME, null, 1);
    }

    public MemoDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // DB를 사용하기 위해서 있어야 하는 Table을 생성하는 부분
        db.execSQL(DBContract.Memo.TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // 전체 데이터의 리스트를 추출해서 사용하고자 하는 곳으로
    // return하는 method
    public List<MemoVO> selectAll(){

        // select를 이용해서 값을 추출해준다.

        // 1. DB연결하기
        SQLiteDatabase db = getReadableDatabase();

        // 2. SELECT * => 칼럼을 지정해서 사용하는 것을 권장
        String[] colums = {

            DBContract.Memo.COL_ID,      //0































            DBContract.Memo.COL_DATE,    //1
            DBContract.Memo.COL_MEMO     //2

        };

        // select id, date, memo from tbl_memo;
        // Cursor : JDBC의 ResultSet과 비슷한 역할
        Cursor cursor = db.query(DBContract.Memo.TABLE_NAME, colums,null,null,null,null,null);

        // Cursor에 담긴 데이터를 List로 변환해서 보낸다.
        List<MemoVO> memos = new ArrayList<MemoVO>();
        while(true){
            if(!cursor.moveToNext())break;

            MemoVO vo = new MemoVO(cursor.getString(1), cursor.getString(2));

            memos.add(vo);
        }

        return memos;

    }

    public long insertMemo(MemoVO vo) {

        SQLiteDatabase db = getWritableDatabase();

        // insert 할 각각의 값들을 ContentValues 클래스의 도움을 받아서
        // insert SQL 명령으로 변환시킨다.

        ContentValues insertValues = new ContentValues();

        // COL_ID 는 AUTOINCREMENT로 되어있기 때문에 INSERT 할 때 값을 연결하지 않아도 자동으로 생성된다.
        insertValues.put(DBContract.Memo.COL_DATE, vo.getDate());
        insertValues.put(DBContract.Memo.COL_MEMO, vo.getMemo());

        // insertValues를 이용해서 insert 명령을 실행
        // insert를 실행하고 나서 자동으로 생성된 id 값을 return 해준다.
        long id = db.insert(DBContract.Memo.TABLE_NAME, null, insertValues);

        return id;

    }

}
