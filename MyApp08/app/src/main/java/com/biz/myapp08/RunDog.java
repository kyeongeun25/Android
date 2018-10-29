package com.biz.myapp08;

public class RunDog {

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();
        dog.run();

        // 상속된 클래스는 부모클래스로 선언하고 자신의 생성자로 생성을 할 수 있다.
        Animal dog1 = new Dog();

        // 부모 클래스를 매개변수로 받는 method를 하나만 정의하면
        // 같은 기능을 하는 method를 중복선언 할 필요가 없어진다.
        // 다만 자신의 클래스에서 추가로 생성한 method를 사용하려면
        // 다소 불편하지만 아래처럼 형변환을 시켜서 사용 할 수 있다.
        // Boxing
        ((Dog)dog1).run();

        Animal a = new Animal();
        Dog b = new Dog();
        RunDog r = new RunDog();

        a(a);
        a(b);
        a(r);
    }

    static public void a(Object o){

        ((Animal)o).eat();
        ((Dog)o).run();
    }

    /*
    static public void a (Dog d){
        d.run();
    }

    static public void a (Animal a){
        a.eat();
    }

    static public void a (RunDog r){

    }
    */
}
