package com.biz.db_addr.helper;

/*
    DB 사용을 위한 static 상수들을 선언
 */
public class AdddrDBContract {

    final public static String DB_NAME ="mydb";
    final public static String ADDR_TABLE = "tbl_addr";

    final public static String COL_ID = "id";
    final public static String COL_NAME = "name";
    final public static String COL_PHONE = "phone";
    final public static String COL_EMAIL = "email";

    final public static String CREATE_TABLE = " CREATE TABLE IF EXISTS "+ADDR_TABLE+" ( "+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_NAME+" TEXT, "+COL_PHONE+" TEXT, "
                                                + COL_EMAIL+" TEXT ) ";

}
