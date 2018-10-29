package com.gkdtkd562.woo_project2.helper;

import android.os.AsyncTask;
import android.util.Log;

import com.gkdtkd562.woo_project2.database.GoDataVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class GetGoData extends AsyncTask<String,String,List<GoDataVO>> {


    StringBuilder goDataXML ;
    List<GoDataVO> goDataList ;

    private void getGoData(String mainTitle, String pageTitle) {

        StringBuilder urlBuilder
                = new StringBuilder(GoDataKey.GO_API_URL) ;

        try {
            urlBuilder.append("?"
                    + URLEncoder.encode("crtiKey","UTF-8")
                    + "=" + GoDataKey.GO_API_KEY ) ;

            urlBuilder.append("&"
                    + URLEncoder.encode("callTp","UTF-8")
                    + "=" + URLEncoder.encode("L","UTF-8"));

            urlBuilder.append("&"
                    + URLEncoder.encode("pageNo","UTF-8")
                    + "=" + URLEncoder.encode("1", "UTF-8")); /**/
            urlBuilder.append("&"
                    + URLEncoder.encode("numOfRows","UTF-8")
                    + "=" + URLEncoder.encode("1000","UTF-8"));

            // mainTitle에 따라서 임산부, 영유아 데이터를 추출하도록 설정
            if(mainTitle.equalsIgnoreCase("PREG")) {
                urlBuilder.append("&"
                        + URLEncoder.encode("charTrgterArray","UTF-8") +
                        "=" + URLEncoder.encode("002","UTF-8")) ;
            }
            if(mainTitle.equalsIgnoreCase("BABY")) {
                urlBuilder.append("&"
                        + URLEncoder.encode("lifeArray","UTF-8") +
                        "=" + URLEncoder.encode("001","UTF-8")) ;
            }

//            urlBuilder.append("&"
//                    + URLEncoder.encode("srchKeyCode","UTF-8") +
//                    "=" + URLEncoder.encode("003","UTF-8" )) ; //001 제목 002 내용 003 제목+내용 ", "UTF-8")); /**/
//            urlBuilder.append("&"
//                    + URLEncoder.encode("searchWrd","UTF-8") +
//                    "=" + URLEncoder.encode("산후", "UTF-8")); /**/


//            urlBuilder.append("&" + URLEncoder.encode("obstKiArray","UTF-8") + "=" + URLEncoder.encode("* 대상특성에 장애 클릭시 10, 지체 20, 시각 30, 청각 40, 언어 50, 지적 60, 뇌병변 70, 자폐성 80, 정신 90, 신장 A0, 심장 B0, 호흡기 C0, 간 D0, 안면 E0, 장루 F0, 간질 ", "UTF-8")); /**/
//            urlBuilder.append("&" + URLEncoder.encode("obstLvArray","UTF-8") + "=" + URLEncoder.encode("* 대상특성에 장애 클릭시 1, 1급 2, 2급 3, 3급 4, 4급 5, 5급 6, 6급 ", "UTF-8")); /**/
//            urlBuilder.append("&" + URLEncoder.encode("trgterIndvdlArray","UTF-8") + "=" + URLEncoder.encode("001, 해당없음 002, 한부모 003, 다문화 004, 조손 005, 새터민 006, 소년소녀가장 007, 독거노인 ", "UTF-8")); /**/
//            urlBuilder.append("&" + URLEncoder.encode("desireArray","UTF-8") + "=" + URLEncoder.encode("0000000, 안전 1000000, 건강 2000000, 일상생활유지 3000000, 가족관계 4000000, 사회적 관계 5000000, 경제 6000000, 교육 7000000, 고용 8000000, 생활환경 9000000, 법률 및 권익보장 A000000, 기타 ", "UTF-8")); /**/



            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // conn.setRequestProperty("Content-type", "application/json");

            Log.d("Response code" , ":" + conn.getResponseCode());
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300)
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            goDataXML = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                goDataXML.append(line);
            }
            rd.close();
            conn.disconnect();
            Log.d("결과:" , goDataXML.toString());

            XmlToJson xmlToJson
                    = new XmlToJson.Builder( goDataXML.toString() ).build();

            JSONObject jsonObject = xmlToJson.toJson();

            try {
                JSONObject wanted = jsonObject.getJSONObject(GoConst.WANTED_LIST);
                JSONArray jsonArray = wanted.getJSONArray(GoConst.SERV_LIST);

//                List<GoDataVO> tempList = new ArrayList<>();

                goDataList = new ArrayList<>();


                //                    <string-array name="title_preg">
//                     <item>산모건강</item>
//                      <item>아이건강</item>
//                        <item>재직자</item>
//                          <item>생활지원</item>
//                  </string-array>


//                <item>보육지원</item>
//        <item>생활지원</item>
//        <item>건강지원</item>
//        <item>보호</item>
//        <item>입양위탁</item>


                String searchText = "pageTitle";
                String searchText1 = "pageTitle";

                String[] searArray = new String[3];


                if(pageTitle.equals("산모건강")){
                    searchText = "산모";
                    searchText1 = "출산";

                    searArray[0] = "산모";
                    searArray[1] = "출산";
                    searArray[2] = "임신";
                }
                if(pageTitle.equals("아이건강")) {
                    searchText= "태아";

                    searArray[0] = "태아";
                    searArray[1] = "아이";
                    searArray[2] = "건강";
                }
                if(pageTitle.equals("재직자")) searchText="재직";
                if(pageTitle.equals("생활지원")) searchText="생활";
                if(pageTitle.equals("보육지원")) searchText="보육";
                if(pageTitle.equals("건강지원")) searchText="건강";
                if(pageTitle.equals("입양위탁")) searchText="위탁";


                for(int i = 0 ; i< jsonArray.length() ; i ++){

                    JSONObject item = jsonArray.getJSONObject(i);
                    GoDataVO vo = new GoDataVO();
                    vo.setServId(item.getString(GoConst.SERV_ID)); // 서비스 ID
                    vo.setServNm(item.getString(GoConst.SERV_NM)); // 서비명
                    vo.setJurMnofNm(item.getString(GoConst.JUR_M_NOF_NM)); // 소관부처
                    vo.setJurOrgNm(item.getString(GoConst.JUR_ORG_NM)); // 소관 조직
                    vo.setInqNum(item.getString(GoConst.INQ_NUM)); // 조회수
                    vo.setServDgst(item.getString(GoConst.SERV_DGST)); //서비스 요약
                    vo.setServDtlLink(item.getString(GoConst.SERV_DTL_LINK)); // 서비스 링크
                    vo.setSvcfrstRegTs(item.getString(GoConst.SVC_FRST_REG_TS)); // 서비스 등록일

                    if(searArray != null){
                        for(String s : searArray){
                            if(vo.getServDgst().contains(s)){

                                String s1 = vo.getServDgst().replace(s, "<font color=blue><b>" + s + "</b></font>");

                                vo.setServDgst(s1);
                                goDataList.add(vo);
                            }
                        }
                    }else if(vo.getServDgst().contains(searchText)){

                        String s1 = vo.getServDgst().replace(searchText, "<font color=blue><b>" + searchText + "</b></font>");

                        vo.setServDgst(s1);
                        goDataList.add(vo);

                    }

//                    for(String s : searArray){
//                        if(vo.getServDgst().contains(s)){
//                            goDataList.add(vo);
//                        }
//                    }

//                    if(vo.getServDgst().contains(searchText) || vo.getServDgst().contains(searchText1)){
//                        goDataList.add(vo);
//                    }
//                    goDataList.add(vo);

                }

                // pageTitle 문자열에 해당하는 데이터만 추출해서 보여주기
                // goDataList에는 mainTitle에 해당하는 데이터들이 모여있다
                // 여기에서 pageTitle에 해당하는 데이터만 추출한다


                // 임시로 list를 생성
                /*
                List<GoDataVO> tempList = new ArrayList<>();
                for(GoDataVO vo : goDataList){

                    // contains : 문자열이 포함되어있는지 검사
//                    if(vo.getServDgst().contains(pageTitle)){
                    if(vo.getServDgst().contains("산모")){
                        tempList.add(vo);


                    }
                }
                goDataList = tempList;
                */

            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<GoDataVO> doInBackground(String... strings) {

        String mainTitle = strings[0];
        String pageTitle = strings[1];


        this.getGoData(mainTitle, pageTitle);
        return goDataList;
    }
}