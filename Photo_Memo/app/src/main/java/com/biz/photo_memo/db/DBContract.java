package com.biz.photo_memo.db;

// DBContract 클래스를 만드는 것은 DB구현을 하면서 오류를 최소한으로 줄이기 위해서
// 문자열들을 static 변수로 선언한다.
public class DBContract {
    final public static String DB_NAME = "mydb";

    // DB는 현재 프로젝트에서 공통으로 공유
    class Memo {

        // 프로젝트에서 사용할 상수(전역상수)를 선언을 한다.
        // final : 한번 변수 값이 정해지면 그 값은 어떤 연산으로도 변경 할 수 없다.


        final public static String TABLE_NAME = "tbl_memo";

        final public static String COL_ID = "id" ;
        final public static String COL_MEMO = "memo";
        final public static String COL_DATE = "date";

        final public static String TABLE_CREATE = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +  COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + COL_DATE + " TEXT, " + COL_MEMO + " TEXT ) ";

    }

    // 1. SQL에서 사용할 Table 이름정의
    // 2. 각 칼럼들 문자열을 static 변수에 할당하여 상수로 사용 할 수 있도록 선언
    // 3. CREATE TABLE SQL 문을 문법에 맞도록 문자열로 작성해서
    //    TABLE_CREATE(CREATE_TABLE) 변수로 선언하고 static으로 정의하여 상수로 사용 할 수 있도록 준비비
   class Grade {

        final public static String TABLE_NAME = "tbl_grade";

        final public static String COL_ID = "id";
        final public static String COL_NUM = "number";
        final public static String COL_NAME = "name";
        final public static String COL_KOR = "kor";
        final public static String COL_ENG = "eng";
        final public static String COL_MATH = "math";

        final public static String TABLE_CREATE = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " + COL_ID + " INTEGER P RIMARY KEY AUTOINCREMENT, "
                            + COL_NUM + " TEXT, " +  COL_NAME + " TEXT, " +
                            COL_KOR + " INTEGER, " + COL_ENG + " INTEGER, " + COL_MATH + " INTEGER ) " ;

        // String TABLE_CREATE1 = " CREATE TABLE IF NOT EXISTS "+" tbl_grade ( " + " id INCREMENT PRIMARY KEY AUTOINCREMENT "
                                //+ " num TEXT, " ;

    }

}
