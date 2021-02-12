package com.nm.exam.services;

import com.nm.exam.model.Annonce;
import com.nm.exam.repository.AnnonceRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnonceService {

    private AnnonceRepository annonceRepository;

    public AnnonceService(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    public List<Annonce> getAnnonces() {
        return this.annonceRepository.findAll();
    }

    public Annonce getAnnonce(Integer id){
        return  this.annonceRepository.findById(id);
    }

    public void saveOrUpdateAnnonce(Annonce annonce) {
        this.annonceRepository.save(annonce);
    }

    public void deleteAnnonce(Annonce annonce) {
        this.annonceRepository.delete(annonce);
    }
}
