package com.shyslav.menu;
// Generated 16.03.2016 7:55:22 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Role generated by hbm2java
 */
public class Role  implements java.io.Serializable {


     private Integer id;
     private String name;
     private char radd;
     private char rupdate;
     private char rdelete;
     private char rdownload;
     private Set<MenuRole> menuRoles = new HashSet<MenuRole>(0);

    public Role() {
    }

	
    public Role(String name, char radd, char rupdate, char rdelete, char rdownload) {
        this.name = name;
        this.radd = radd;
        this.rupdate = rupdate;
        this.rdelete = rdelete;
        this.rdownload = rdownload;
    }
    public Role(String name, char radd, char rupdate, char rdelete, char rdownload, Set<MenuRole> menuRoles) {
       this.name = name;
       this.radd = radd;
       this.rupdate = rupdate;
       this.rdelete = rdelete;
       this.rdownload = rdownload;
       this.menuRoles = menuRoles;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public char getRadd() {
        return this.radd;
    }
    
    public void setRadd(char radd) {
        this.radd = radd;
    }
    public char getRupdate() {
        return this.rupdate;
    }
    
    public void setRupdate(char rupdate) {
        this.rupdate = rupdate;
    }
    public char getRdelete() {
        return this.rdelete;
    }
    
    public void setRdelete(char rdelete) {
        this.rdelete = rdelete;
    }
    public char getRdownload() {
        return this.rdownload;
    }
    
    public void setRdownload(char rdownload) {
        this.rdownload = rdownload;
    }
    public Set<MenuRole> getMenuRoles() {
        return this.menuRoles;
    }
    
    public void setMenuRoles(Set<MenuRole> menuRoles) {
        this.menuRoles = menuRoles;
    }




}


