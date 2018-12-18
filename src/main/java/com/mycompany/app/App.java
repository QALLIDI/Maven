package com.mycompany.app;

/**
 * Hello world!
 */
public class App
{

    private final String message = "Hello World!";
    private final String coucou = "coucou";
    private final String coucou2 = "coucou 2";
    private final String coucou3 = "coucou 3";
    public App() {}

    public static void main(String[] args) {
        System.out.println(new App().getMessage());
    }

    private final String getMessage() {
        return message;
    }

}
