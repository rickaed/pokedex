package com.pokemon.pokedex.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pokemon")
public class Pokemon {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "varchar(20) NOT NULL UNIQUE")
    private String name;
    @Column(columnDefinition = "varchar(20) NOT NULL")
    private String type;

    public Pokemon() {
    }

    public Pokemon(int id,
            String name,
            String type) {
    }

    public Pokemon(String name,
            String type) {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
