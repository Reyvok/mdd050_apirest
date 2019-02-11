package com.myaudiolibrary.apirest.model;


import javax.persistence.*;

@Entity
@Table(name = "album")
public class Album {

    @ManyToOne
    @JoinColumn(name = "artistId")
    private Artist artist;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    public Album(){}

    public Album(Long id, String name, Artist artist){
        this.id = id;
        this.title = name;
        this.artist = artist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
