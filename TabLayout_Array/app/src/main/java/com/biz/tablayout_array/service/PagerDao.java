package com.biz.tablayout_array.service;

import com.biz.tablayout_array.helper.TextViewAdapter;
import com.biz.tablayout_array.models.PagerVO;
import com.biz.tablayout_array.models.TextVO;

import java.util.ArrayList;
import java.util.List;

public class PagerDao {

    String[] bsc = new String[] {"mbc", "kbs", "sbs", "jtbc", "tvn"};

    public List<PagerVO> makeSB(){
        List<PagerVO> pageList = new ArrayList<>();
        for(String s : bsc){
            PagerVO pagerVO = new PagerVO();
            pagerVO.setPageTitle(s);

            List<TextVO> textList = new ArrayList<>();
            TextVO textVO = new TextVO();
            textVO.setTitle("유희열의 스케치북");
            String strText = "방청일자 : 2018-10-01 \n";
            strText += "장소:우리집 \n";
            textVO.setMainText(strText);

            textList.add(textVO);
            pagerVO.setTextList(textList);

            pageList.add(pagerVO);

        }

        return pageList;
    }

    public List<PagerVO> makePager(){

        List<PagerVO> pageList = new ArrayList<>();

        for(int i = 0 ; i < 10 ; i++){
            PagerVO pagerVO = new PagerVO();
            pagerVO.setPageTitle("Page " + (i + 1));
            List<TextVO> textList = new ArrayList<>();
            for(int j = 0 ; j <20 ; j++){
                TextVO textVO = new TextVO();
                textVO.setTitle("제목 : "+(j+1));
                textVO.setMainText("본문 : 대한민국 헌법 제 "+(j+1)+" 조");
                textList.add(textVO);
            }
            pagerVO.setTextList(textList);
            pageList.add(pagerVO);
        }
        return pageList;

    }

    public List<PagerVO> getPager(){

        List<PagerVO> pageList = new ArrayList<PagerVO>();
        // page1
        PagerVO pagerVO = new PagerVO();
        pagerVO.setPageTitle("Page1");

        List<TextVO> textList = new ArrayList<TextVO>();
        TextVO textVO = new TextVO();
        textVO.setTitle("대한민국은 우리나라");
        textVO.setMainText("대한민국은 민주 공화국입니다.");
        textList.add(textVO);

        textVO = new TextVO();
        textVO.setTitle("Republic of Korea");
        textVO.setMainText("나는 자랑스러운 태극기 앞에...");
        textList.add(textVO);

        pagerVO.setTextList(textList);
        pageList.add(pagerVO);


        // page2
        pagerVO = new PagerVO();
        pagerVO.setPageTitle("Page2");

        textList = new ArrayList<TextVO>();
        textVO = new TextVO();
        textVO.setTitle("대한민국은 우리나라");
        textVO.setMainText("대한민국은 민주 공화국입니다.");
        textList.add(textVO);

        textVO = new TextVO();
        textVO.setTitle("Republic of Korea");
        textVO.setMainText("나는 자랑스러운 태극기 앞에...");
        textList.add(textVO);

        pagerVO.setTextList(textList);
        pageList.add(pagerVO);

        return pageList;
    }

}
