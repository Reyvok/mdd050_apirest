package com.myaudiolibrary.apirest.controller;

import com.myaudiolibrary.apirest.exception.ConflictException;
import com.myaudiolibrary.apirest.model.Artist;
import com.myaudiolibrary.apirest.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}"
    )
    public Artist findById(@PathVariable("id") Long id) throws EntityNotFoundException {
        Artist artist = artistRepository.findOne(id);
        if(artist == null){
            throw new EntityNotFoundException("L'artiste d'identifiant " + id + " n'a pas été trouvé");
        }
        return artist;
    }

    @RequestMapping(params = {"name", "page", "size", "sortProperty", "sortDirection"})
    public Page<Artist> findByName(@RequestParam("name") String name) throws EntityNotFoundException {
        PageRequest pageRequest = new PageRequest(0, 10);
        return artistRepository.findByNameContainingIgnoreCase(name, pageRequest);
    }

    @RequestMapping(params = {"page", "size", "sortProperty", "sortDirection"})
    public Page<Artist> findAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("sortProperty") String sortProperty , @RequestParam("sortDirection") Sort.Direction sortDirection){
        PageRequest pageRequest = new PageRequest(page, size, sortDirection, sortProperty);
        return artistRepository.findAll(pageRequest);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addArtist(@RequestBody Artist artist) throws ConflictException {
        if(artistRepository.findByName(artist.getName()) != null) {
            throw new ConflictException(HttpStatus.CONFLICT, "Il existe déjà un artiste du nom de " + artist.getName());
        }
        artistRepository.save(artist);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = "application/json",
            produces = "application/json",
            value = "/{id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtist(@RequestBody Artist artist){
        artistRepository.save(artist);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/{id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable("id") Long id){
        artistRepository.delete(id);
    }

}
