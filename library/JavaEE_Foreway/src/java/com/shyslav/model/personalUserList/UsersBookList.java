package com.shyslav.model.personalUserList;
// Generated 16.03.2016 7:55:22 by Hibernate Tools 4.3.1

import com.shyslav.model.User;
import com.shyslav.model.Books;




/**
 * UsersBookList generated by hbm2java
 */
public class UsersBookList  implements java.io.Serializable {


     private Integer id;
     private User user;
     private Books books;

    public UsersBookList() {
    }

    public UsersBookList(User user, Books books) {
       this.user = user;
       this.books = books;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Books getBooks() {
        return this.books;
    }
    
    public void setBooks(Books books) {
        this.books = books;
    }




}


