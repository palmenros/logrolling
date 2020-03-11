package com.logrolling.server.transfer;

public class TransferHelloWorld {

    private long id;
    private String name;
    private String surname;
    private String city;
    private int age;

    //IMPORTANT!: Empty constructor for JSON construction
    public TransferHelloWorld() {
    }

    public TransferHelloWorld(long id, String name, String surname, String city, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
