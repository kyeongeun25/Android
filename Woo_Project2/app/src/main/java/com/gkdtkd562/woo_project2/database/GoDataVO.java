package com.gkdtkd562.woo_project2.database;

public class GoDataVO {

    private String numOfRows ;// 한페이지 결과수
    private String pageNum ;// 페이지번호
    private String totalCount ;// 전체 데이터수
    private String servList ;// 데이터 목록

    private String servId ;// 서비스ID
    private String servNm ;// 서비스명
    private String jurMnofNm ;// 소관부처명
    private String jurOrgNm ;// 소관조직명
    private String inqNum ;// 조회수
    private String servDgst ;// 서비스 요약
    private String servDtlLink ;// 서비스상세링크
    private String svcfrstRegTs ;// 서비스등록일

    public String getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(String numOfRows) {
        this.numOfRows = numOfRows;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getServList() {
        return servList;
    }

    public void setServList(String servList) {
        this.servList = servList;
    }

    public String getServId() {
        return servId;
    }

    public void setServId(String servId) {
        this.servId = servId;
    }

    public String getServNm() {
        return servNm;
    }

    public void setServNm(String servNm) {
        this.servNm = servNm;
    }

    public String getJurMnofNm() {
        return jurMnofNm;
    }

    public void setJurMnofNm(String jurMnofNm) {
        this.jurMnofNm = jurMnofNm;
    }

    public String getJurOrgNm() {
        return jurOrgNm;
    }

    public void setJurOrgNm(String jurOrgNm) {
        this.jurOrgNm = jurOrgNm;
    }

    public String getInqNum() {
        return inqNum;
    }

    public void setInqNum(String inqNum) {
        this.inqNum = inqNum;
    }

    public String getServDgst() {
        return servDgst;
    }

    public void setServDgst(String servDgst) {
        this.servDgst = servDgst;
    }

    public String getServDtlLink() {
        return servDtlLink;
    }

    public void setServDtlLink(String servDtlLink) {
        this.servDtlLink = servDtlLink;
    }

    public String getSvcfrstRegTs() {
        return svcfrstRegTs;
    }

    public void setSvcfrstRegTs(String svcfrstRegTs) {
        this.svcfrstRegTs = svcfrstRegTs;
    }


    @Override
    public String toString() {
        return "GoDataVO{" +
                "numOfRows='" + numOfRows + '\'' +
                ", pageNum='" + pageNum + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", servList='" + servList + '\'' +
                ", servId='" + servId + '\'' +
                ", servNm='" + servNm + '\'' +
                ", jurMnofNm='" + jurMnofNm + '\'' +
                ", jurOrgNm='" + jurOrgNm + '\'' +
                ", inqNum='" + inqNum + '\'' +
                ", servDgst='" + servDgst + '\'' +
                ", servDtlLink='" + servDtlLink + '\'' +
                ", svcfrstRegTs='" + svcfrstRegTs + '\'' +
                '}';
    }
}