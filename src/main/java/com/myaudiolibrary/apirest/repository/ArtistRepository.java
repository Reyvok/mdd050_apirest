package com.myaudiolibrary.apirest.repository;

import com.myaudiolibrary.apirest.model.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Page<Artist> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Artist> findAll(Pageable pageable);

    Artist findByName(String name);

    Artist findById(Long id);

}
