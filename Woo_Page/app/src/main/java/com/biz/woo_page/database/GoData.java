package com.biz.woo_page.database;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class GoData {

    String goApiKey = "WPuqWhazuVkTKMY9KCRta4luDtzCVAyuAwVLZY4gFtaThQK4qCnS2%2B7mUyEorCRjeACx9Y877of1KYE%2FLLID1Q%3D%3D";
    String url = "http://www.bokjiro.go.kr/openapi/rest/gvmtWelSvc";

    public void getData() {

        StringBuilder urlBuilder = new StringBuilder();

        urlBuilder.append(url);
        try {
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + goApiKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("crtiKey","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
            urlBuilder.append("&" + URLEncoder.encode("callTp","UTF-8") + "=" + URLEncoder.encode("L","UTF-8"));//"호출할 페이지 타입을 반드시 설정합니다.(L: 목록, D:상세)", "UTF-8")); /**/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("100","UTF-8"));//"기본값 1, 최대 1000 검색 시작위치를 지정할 수 있습니다", "UTF-8")); /**/
            urlBuilder.append("&" + URLEncoder.encode("srchKeyCode","UTF-8") + "=" + URLEncoder.encode("001","UTF-8"));//"001 제목 002 내용 003 제목+내용 ", "UTF-8")); /**/
            urlBuilder.append("&" + URLEncoder.encode("lifeArray","UTF-8") + "=" + URLEncoder.encode("001","UTF-8"));//"001, 영유아 002, 아동 003, 청소년 004, 청년 005, 중장년 006, 노년 ", "UTF-8")); /**/
            urlBuilder.append("&" + URLEncoder.encode("charTrgterArray","UTF-8") + "=" + URLEncoder.encode("004","UTF-8"));//"001, 해당없음 002, 여성 003, 임산부 004, 장애 005, 국가유공자등 보훈대상자 006, 실업자 ", "UTF-8")); /**///            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("출력건수, 기본값 10, 최대 100 까지 가능합니다.", "UTF-8")); /**/
//            urlBuilder.append("&" + URLEncoder.encode("searchWrd","UTF-8") + "=" + URLEncoder.encode("검색어", "UTF-8")); /**/
//            urlBuilder.append("&" + URLEncoder.encode("obstKiArray","UTF-8") + "=" + URLEncoder.encode("* 대상특성에 장애 클릭시 10, 지체 20, 시각 30, 청각 40, 언어 50, 지적 60, 뇌병변 70, 자폐성 80, 정신 90, 신장 A0, 심장 B0, 호흡기 C0, 간 D0, 안면 E0, 장루 F0, 간질 ", "UTF-8")); /**/
//            urlBuilder.append("&" + URLEncoder.encode("obstLvArray","UTF-8") + "=" + URLEncoder.encode("* 대상특성에 장애 클릭시 1, 1급 2, 2급 3, 3급 4, 4급 5, 5급 6, 6급 ", "UTF-8")); /**/
//            urlBuilder.append("&" + URLEncoder.encode("trgterIndvdlArray","UTF-8") + "=" + URLEncoder.encode("001, 해당없음 002, 한부모 003, 다문화 004, 조손 005, 새터민 006, 소년소녀가장 007, 독거노인 ", "UTF-8")); /**/
//            urlBuilder.append("&" + URLEncoder.encode("desireArray","UTF-8") + "=" + URLEncoder.encode("0000000, 안전 1000000, 건강 2000000, 일상생활유지 3000000, 가족관계 4000000, 사회적 관계 5000000, 경제 6000000, 교육 7000000, 고용 8000000, 생활환경 9000000, 법률 및 권익보장 A000000, 기타 ", "UTF-8")); /**/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            // System.out.println(sb.toString());
            Log.d("data",sb.toString());

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

}
