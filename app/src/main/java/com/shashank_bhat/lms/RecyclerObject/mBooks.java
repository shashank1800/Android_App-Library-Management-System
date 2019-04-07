package com.shashank_bhat.lms.RecyclerObject;

public class mBooks {
    String Book_Name;
    String Book_Author;
    String Book_Publisher;
    String Dep_Id;
    public mBooks(String Book_Name, String Book_Author, String Book_Publisher,String Dep_Id){
        this.Book_Name = Book_Name;
        this.Book_Author = Book_Author;
        this.Book_Publisher = Book_Publisher;
        this.Dep_Id = Dep_Id;

    }

    public String getBook_Name() {
        return Book_Name;
    }

    public void setBook_Name(String book_Name) {
        Book_Name = book_Name;
    }

    public String getBook_Author() {
        return Book_Author;
    }

    public void setBook_Author(String book_Author) {
        Book_Author = book_Author;
    }

    public String getBook_Publisher() {
        return Book_Publisher;
    }

    public void setBook_Publisher(String book_Publisher) {
        Book_Publisher = book_Publisher;
    }

    public String getDep_Id() {
        return Dep_Id;
    }

    public void setDep_Id(String dep_Id) {
        Dep_Id = dep_Id;
    }
}
