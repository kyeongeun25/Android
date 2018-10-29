package com.biz.photogallery.data;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

// 폰의 이미지들을 찾아서 imageUri와 imageName을 추출하고,
// List로 만들 도우미 클래스
public class GalleryLoadHelper {

    /*
        안드로이드에서 image, 음악, 동영상, 폰에 저장된 주소록(전화번호부) 등은
        마치 SQLite의 DB에 저장된 data를 불러오는 것 처럼 데이터 정보를 가져 올 수 있도록 설계되어 있다.
     */

    static String[] columns = {
            MediaStore.Images.Media.DISPLAY_NAME,       // imageName
            MediaStore.Images.Media.DATA                // imageUri
    } ;

    public static List<GalleryVO> getImageList (Context context){

        // 폰에 있는 이미지에 대한 모든 정보가 galleryCursor에 담기게 된다.
        Cursor galleryCursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, null);

        Log.i("커서정보:", galleryCursor.toString());

        // Cursor에 담긴 정보를 List<GalleryVO>에 옮긴다.
        List<GalleryVO> list = new ArrayList<GalleryVO>();

        // 갤러리에서 가져온 cursor는 현재 위치가 맨 처음이 아닐 수 있으므로
        // cursor를 맨 처음으로 먼저 옮겨준다.
        if(galleryCursor.moveToFirst()){
            while(true){
                // 다 읽었으면
                if(!galleryCursor.moveToNext())break;

                GalleryVO vo = new GalleryVO();

                int uriColumn = galleryCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                int nameColumn = galleryCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);

                vo.setImageUri(galleryCursor.getString(uriColumn));
                vo.setImageName(galleryCursor.getString(nameColumn));

                list.add(vo);

            }
        }

        for(GalleryVO v : list){
            Log.i("List 정보 : ", v.getImageName());
        }

        return list;

    }

}
