package com.bbd.clone;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Sing s1 = new Sing();
        s1.setName("daoxiang");
        Person p1 = new Person("zhoujielun");
        Person p2 = new Person("fangwenshang");
        Person[] persons = new Person[2];
        persons[0] = p1; persons[1] = p2;
        s1.setPersons(persons);

        Sing s2 = (Sing) s1.clone();
        Person[] persons2 = s2.getPersons();
        persons2[0] = new Person("liuweibo");
        System.out.println(s1 == s2);

    }
}
