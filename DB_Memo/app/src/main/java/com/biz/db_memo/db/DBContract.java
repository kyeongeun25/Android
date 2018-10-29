package com.biz.db_memo.db;

// DB SQL 상수 선언부
public class DBContract {

    public static String DB_NAME = "MY_DB" ;

    // public으로 선언된 member변수는 별도로 getter와 setter가 없어도
    // 외부에서 직접 접근 할 수 있다.
    public static String TABLE_NAME = "MY_MEMO";

    public static String COL_ID = "id";
    public static String COL_SUBJECT = "subject";
    public static String COL_DATE = "date";
    public static String COL_MEMO = "memo";


    public static String CREATE_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +  COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_SUBJECT + " TEXT, "
                            + COL_DATE + " TEXT, " + COL_MEMO + " TEXT ) " ;

    public static String SQL_SELECT_ALL = " SELECT * FROM " + TABLE_NAME ;
    public static String SQL_INSERT = " INSERT INTO " + TABLE_NAME + " ( " + COL_ID + " , " + COL_SUBJECT + " , " + COL_DATE + " , " + COL_MEMO + " ) " ;

}
