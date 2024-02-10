package org.example.blogtemplate.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private int id;

    @Column(name="name")
    private String name;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String name){
        this.name = name;
    }

    public Role(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
