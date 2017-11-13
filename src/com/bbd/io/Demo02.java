package com.bbd.io;

/**
 * 装饰者模式
 */
public class Demo02 {

    public static void main(String[] args) {
        Man man = new Man();
        ManDecoratorA md1 = new ManDecoratorA();
        ManDecoratorB md2 = new ManDecoratorB();

        md1.setPerson(man);
        md2.setPerson(md1);
        md2.eat();
    }
}

// 接口
interface Person {
    void eat();
}

// 被装饰类
class Man implements Person {
    public void eat() {
        System.out.println("男人在吃");
    }
}

// 装饰这类，要与被装饰者类继承同样的接口，是为了客户端调用时，如同调用被装饰对象一样
abstract class Decorator implements Person {

    protected Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    public void eat() {
        person.eat();
    }
}

// 具体的装饰类A
class ManDecoratorA extends Decorator {

    public void eat() {
        super.eat();
        reEat();
        System.out.println("ManDecoratorA类");
    }

    public void reEat() {
        System.out.println("再吃一顿饭");
    }
}

// 具体的装饰类B
class ManDecoratorB extends Decorator {

    public void eat() {
        super.eat();
        System.out.println("===============");
        System.out.println("ManDecoratorB类");
    }
}