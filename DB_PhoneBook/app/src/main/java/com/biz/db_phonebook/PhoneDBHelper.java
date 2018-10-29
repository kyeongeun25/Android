package com.biz.db_phonebook;

import android.content.ContentUris;
import android.content.Context;
import android.content.IntentSender;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PhoneDBHelper {
    public static List<PhoneVO> getPhoneBook(Context context) {
        List<PhoneVO> phones = new ArrayList<PhoneVO>();

        // phone에 있는 전화번호부를 불러오기 위해서
        // 칼럼리스트를 정의

        //ContactsContract
        // phoneContract 라고 보통 표현하고
        // phone의 주소록을 가져오기 위해 사용한다.

        // 순수하게 전화번호만 가져오는 방법
        //   초기 : Phone.NUMBER 항목이 있었는데 지금은 사라졌다
        //   ContactsContract.Data.DATA1에 DATA1.NUMBER 비 권장사항
        //   최근에 권장하는 방법
        String num = ContactsContract.CommonDataKinds.Phone.NUMBER;

        String[] columns = {

                ContactsContract.CommonDataKinds.Phone._ID,
                ContactsContract.CommonDataKinds.Phone.NUMBER, // 전화번호
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, // 이름
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI, // 사진정보
                ContactsContract.CommonDataKinds.Email.ADDRESS, // 이메일주소
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,

                ContactsContract.Data.CONTACT_ID, //

                ContactsContract.Data.DISPLAY_NAME, // 전화번호부의 이름
                ContactsContract.Data.PHONETIC_NAME,
                ContactsContract.Data.PHOTO_ID, // 이미지에 대한 주소정보
                ContactsContract.Data.PHOTO_FILE_ID, // 이미지에 대한 파일정보
                ContactsContract.Data.DATA1, // 전화번호가 저장된 객체
                ContactsContract.Data.DATA2,

        };
        Cursor phoneCursor = context.getContentResolver()
                .query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        columns,
                        null,
                        null,
//                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " COLLATE LOCALIZED ASC" //
                        ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC"
                );

        int nameColumn = phoneCursor.getColumnIndex(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        );
        int phoneColumn = phoneCursor.getColumnIndex(
                ContactsContract.CommonDataKinds.Phone.NUMBER
        );

        // 처음부터 읽겠다
        phoneCursor.moveToFirst();
        do {

            String name = phoneCursor.getString(nameColumn);
            String phone = phoneCursor.getString(phoneColumn);

            /**
             *
             * 아래 코드는 전화번호와 연결된 사용자 이미지를
             * 추출해서 ImagaView에 보여주기 위해
             * bitmap으로 생성하는 과정이다.
             *
             * 실제 상황에서는 오류가 발생할수 있는 부분에서
             * 각 값들을 점검하고, 해당 값이 null인지 아닌지 검사하고
             * 그리고 다음 단계를 실행해야 하는 코드가 추가되어야 한다.
             *
             * 만약 이미지를 편집하는 app을 만들려고 하면
             * 아래 과정을 좀더 detail 하게 익혀야 하지만
             *
             * 단수히 이미지를 불러서 ImageView에 담기 위해서는
             * Picasso 같은 3rd party 라이브러리를 사용하는 것이
             * 여러모로 편리하다.
             *
             */

            int contactColumn = phoneCursor.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID
            );


            // 전화번호부의 실제 이미지 정보를 가져오기
            // 1. 이미지가 있는 고유ID를 추출
            long imageId = phoneCursor.getLong(contactColumn);

            // 2. 고유ID를 가지고 실제 이미지가 저장된
            // 저장소 주소(URI)를 가져온다.
            Uri uri = ContentUris.withAppendedId(
                    ContactsContract.Contacts.CONTENT_URI, imageId
            );

            Log.e("###", +contactColumn + " | " + imageId + " | " + name);
            Log.e("###", "URI:" + uri);

            // 3. 사진 읽기
            InputStream inputStream = null;

            File files = new File(uri.getPath());

            // 4. Bitmap으로 변환
            Bitmap bitmap = null;

            //파일 유무를 확인합니다.
            if (files.exists() == true) {
                //파일이 있을시
                Log.e("file:", uri.getPath());
            } else {
                Log.e("file:", "파일없음");
            }

            inputStream = ContactsContract
                    .Contacts.openContactPhotoInputStream(
                            context.getContentResolver(), uri
                    );

            if (inputStream != null) {
                bitmap = BitmapFactory.decodeStream(inputStream);
                Log.e("name:", name);

                Drawable dt = new BitmapDrawable(context.getResources(), bitmap);

            } else {

                // Drawable을 비트맵으로 변환
//                Drawable drawable = context.getResources().getDrawable(R.drawable.ic_account_circle_black_24dp,context.getTheme());
//                BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
//                bitmap = bitmapDrawable.getBitmap();
                bitmap = null;
            }


            PhoneVO vo = new PhoneVO();
            vo.name = name;
            vo.phone = phone;
            vo.image = bitmap;

            phones.add(vo);

        } while (phoneCursor.moveToNext());
        return phones;

    }


}
