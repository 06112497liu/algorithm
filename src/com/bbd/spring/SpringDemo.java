package com.bbd.spring;

import com.bbd.bean.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Liuweibo
 * @version Id: SpringDemo.java, v0.1 2018/1/29 Liuweibo Exp $$
 */
public class SpringDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:resource/spring.xml");
        Book book = context.getBean("book", Book.class);
        System.out.println(book.getName() + "::" + book.getAuthor());
    }


}
    
    