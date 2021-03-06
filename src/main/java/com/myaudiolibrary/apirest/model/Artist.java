package com.myaudiolibrary.apirest.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("artist")
    private Set<Album> albums = new HashSet<>();

    public Artist(){}

    public Artist(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Artist(Long id, String name, Set<Album> albums){
        this.id = id;
        this.name = name;
        this.albums = albums;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
