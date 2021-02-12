package com.nm.exam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nm.exam.model.Annonce;

import java.util.List;

@Repository
public interface AnnonceRepository extends CrudRepository<Annonce, Long> {

    List<Annonce> findByTitre(String titre);

    List<Annonce> findAll();

    @Query("SELECT a FROM Annonce a WHERE a.titre = ?1")
    List<Annonce> findAnnonceAtTitreWithHql(String titre);


    @Query(value = "SELECT a FROM Annonce a ORDER BY id")
    Page<Annonce> findAllAnnonceWithPagination(Pageable pageable);


    Annonce findById(long id);
}

