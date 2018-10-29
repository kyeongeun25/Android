package com.biz.photo_memo.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.biz.photo_memo.vo.GradeVO;

import java.util.ArrayList;
import java.util.List;

// 두번째 절차
// DB를 사용하기 위해서는
//  DB연결, SQL 전송, 결과값 받기, DB연결 종료 등의 절차를 수행해야 하는데
//  그 과정에서 같은 코드들이 중복되고 번거롭게 된다.
// 그래서 SQLOpenHelper클래스를 상속받아서 Helper 클래스를 정의하여 DB연결에 관한 코드를 최소한으로 줄이고
// 좀 더 쉽게 DB의 CRUD를 구현 할 수 있도록 한다.
public class GradeDBHelper extends SQLiteOpenHelper {

    /*
        DBHelper를 동작하는 기본 절차
        1. 외부 ( MainActivity)에서 new 키워드를 통해서 DBHelper를 객체로 생성
            이때 DBHelper(Context cotext) method가 호출된다.
            DB_NAME을 세팅하는 절차

        2. 어디선가 누군가 DB를 사용하기 위해서 시도를 하면
                    >> getReadableDatabase(), 이나 getWritableDatabase() 메서드를 호출을 하면
            DBHelper의 onCreate() 메서드가 자동으로 호출된다.

        3. onCreate 메서드는 사용하고자하는 TABLE이 없으면 CREATE_TABLE(TABLE_CREATE) SQL문을 실행해서
            TABLE을 준비해 둔다.
            그래서 onCreate 메서드에 Table을 생성 할 수 있는 코드를 작성한다.
     */

    public GradeDBHelper(Context context) {
        // DB_NAME하고, version 숫자를 super에 알려주는 역할을 한다.
        // super ? : SQLiterOpenHelper가 관리하는 SQLDataBase
        super(context, DBContract.DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // CREATE TABLE SQL을 실행한다
        // 이때 어디선가(super, 안드로이드 App , Os) 전달받은 DB값을 사용해서 SQL문을 실행한다.
        db.execSQL(DBContract.Grade.TABLE_CREATE ); // sql을 실행하라고 알려줌

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*
        DBHelper를 사용해서 CRUD를 구현하는 절차
        1. 이미 DBHelper는 DB에 접속해서 사용 할 수 있도록
           연결 설정등을 실행해서 연결(에 관한)정보를 메모리 어딘가에 보관을 하고 있다.
        2. 메모리에 보관된 DB 연결정보를 변수로 가져온다.
     */
    public List<GradeVO> selectAll(){
        // selectAll() 메서드를 모든 데이터들을 추출(Read)해서 사용 할 수 있도록
        // 준비 해주는 method이다.

        // 2번에 해당한 DB 연결정보를 변수로 가져오는 절차
        SQLiteDatabase db = getReadableDatabase();  // SELECT를 수행 할 때 사용
        // db = getWritableDatabase(); INSERT, UPDATE, DELETE를 수행할 때 사용하는 method

        // selectAll을 하는 절차
        // * selectAll은 아무 조건없이 모든 데이터를 추출한다.
        // 1. 어떤 칼럼들을 가져 올 것인가를 설정
        //    select에 정의할 칼럼 리스틀르 문자열 배열로 선언한다.
        //    select * from [table] 형식으로 사용 할 때는 필요 엇으나
        //    SQLite DB에서는 가급적 * 보다는 칼럼 리스트를 지정하는 것이 좋다.

        String[] columns = {

                DBContract.Grade.COL_ID,
                DBContract.Grade.COL_NUM,
                DBContract.Grade.COL_NAME,
                DBContract.Grade.COL_KOR,
                DBContract.Grade.COL_ENG,
                DBContract.Grade.COL_MATH

        };

        // 2. 실제 사용 할 SQL을 생성하는 절차
        //    * 엄밀히 말하면 SQL을 생성하는 절차가 아니고 SQlDataBase의 정보를 사용해서 DB에 보낼
        //      특별한 형태의 객체를 생성하는 것
        Cursor cursor = db.query(DBContract.Grade.TABLE_NAME, // 어떤 테이블을 조회 할래?
                 columns,   // 어떤 칼럼들을 볼래?
                 null, null, // 어떤 where를 실행할래?
                null, // groupBy로 어떤 칼럼을 묶을래?
                 null, // groupBy로 묶인 값들에 조건부여하기
                  null // 정렬을 어떻게 할래?
                 );
        List<GradeVO> list = new ArrayList<GradeVO>();

        // db.query() 메서드의 결과값이 Cursor형태이기 때문에 Cursor로 받아서 list로 변환해준다.
        // 실제 형태가 변하는 것이 아니라 내용을 옮겨 담는 것.
        // RecyclerView는 리스트밖에 처리 할 수 없어서 Cursor를 list로 변환해서 보낸다.

        // db.query() 메서드는 내부적으로 select를 실행하고 그 결과(List)를 return 해주도록 되어 있다.
        // 그래서 그 list를 변수에 받아야 한다.
        // db.query() 메서드는 select를 실행하고 list를 Cursor라는 자료형으로 만들어서 return 한다.
        // 이 결과를 바로 외부러 return으로 전달 할 수도 있다. return list;
        // 하지만 Cursor 자료형은 일반적인 클래스(List, ArrayList등)가 아니어서
        // 사용을 하려면 결국 List나 ArrayList등으로 변환을 시켜야 한다.
        // 그래서 helter에서 Cursor를 List로 변환시켜서 보내는 것이 밖에서 사용하기가 편리하다.

        // Cursor를 List로 변환하는 절차
        // 1. VO를 품은 List를 정의한다.

        // 2. Cursor전체를 반복적으로 순회하면서 값을 추출하여 list에 저장한다.
        while(true){

            if(!cursor.moveToNext()) break;

            GradeVO vo = new GradeVO();

            int col = cursor.getColumnIndex(DBContract.Grade.COL_ID);
            vo.setId(cursor.getLong(col));

            col = cursor.getColumnIndex(DBContract.Grade.COL_NUM);
            vo.setNumber(cursor.getString(col));

            col = cursor.getColumnIndex(DBContract.Grade.COL_NAME);
            vo.setName(cursor.getString(col));

            vo.setKor(cursor.getInt(cursor.getColumnIndex(DBContract.Grade.COL_KOR)));
            vo.setEng(cursor.getInt(cursor.getColumnIndex(DBContract.Grade.COL_ENG)));
            vo.setMath(cursor.getInt(cursor.getColumnIndex(DBContract.Grade.COL_MATH)));

            list.add(vo);
        }
        return list;
    }

}
