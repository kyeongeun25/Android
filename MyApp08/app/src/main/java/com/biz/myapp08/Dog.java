package com.biz.myapp08;

public class Dog extends Animal {

    @Override
    public void eat() {
        System.out.println("멍이가 먹습니다.");
    }

    public void run(){
        System.out.println("멍이가 달립니다.");
    }
}
