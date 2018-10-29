package com.biz.photogallery.data;

public class GalleryVO {

    // imageUri : 폰에 저장된 이미지가 어디에 있는가? 에 대한 정보
    // 파일을 열때 필요하다.
    private String imageUri ;
    private String imageName ;

    public GalleryVO() {
    }

    public GalleryVO(String imageUri, String imageName) {
        this.imageUri = imageUri;
        this.imageName = imageName;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
