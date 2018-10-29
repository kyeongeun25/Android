package com.biz.photogallery.data;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookLoader {

    public static List<PhoneBookVO> getPhoneBook(Context context){

        String[] columns = {

                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.PHOTO_ID,
                ContactsContract.CommonDataKinds.Phone._ID

        };

        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                                            columns, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

        List<PhoneBookVO> list = new ArrayList<PhoneBookVO>();
        if(cursor.moveToFirst()){

            do {

                PhoneBookVO vo = new PhoneBookVO();
                vo.setPhoneNumber(cursor.getString(0));
                vo.setUserName(cursor.getString(1));
                vo.setPhoto_id(cursor.getLong(3));
                vo.setPerson_id(cursor.getLong(4));
                list.add(vo);

            }while(cursor.moveToNext());

        }

        return list;

    }

}
