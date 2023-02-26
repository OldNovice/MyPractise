package com.winfo.mypractise.javabean;

public class Book {
    private int name;
    private String pages;

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public Book(int name, String pages) {
        this.name = name;
        this.pages = pages;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

}
