package com.nm.exam;


import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.nm.exam.model.Annonce;
import com.nm.exam.repository.AnnonceRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class PreLoadDataApplication {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PreLoadDataApplication.class);

    public static void main(String[] args) {
    	SpringApplication.run(PreLoadDataApplication.class);
    }

    @Bean
    public CommandLineRunner PreLoadDataApplication(AnnonceRepository repository) {
        return (args) -> {

            List<Annonce> existingannonces = repository.findAll();

            if (existingannonces.size() == 0) {
                log.info("- - New Annonce - -");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date date1 = formatter.parse("12/02/2021");
                repository.save(new Annonce("Titre Annonce 1", "Contenu de au moins 20 caracteres", date1, "https://images.unsplash.com/photo-1612300330496-9616c05e06aa?ixid=MXwxMjA3fDB8MHx0b3BpYy1mZWVkfDF8dG93SlpGc2twR2d8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=60"));
                log.info("- - New Annonce : DONE - -");

                log.info("- - New Annonces : - -");
                List<Annonce> annonces = new ArrayList<Annonce>();
                Date date2 = formatter.parse("11/02/2021");
                Date date3 = formatter.parse("10/02/2021");

                annonces.add(new Annonce("Titre Annonce 2", "Contenu de au moins 20 caracteres", date1, "https://images.unsplash.com/photo-1613005341945-35e159e522f1?ixid=MXwxMjA3fDB8MHx0b3BpYy1mZWVkfDJ8dG93SlpGc2twR2d8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=60"));
                annonces.add(new Annonce("Titre Annonce 3", "Contenu de au moins 20 caracteres", date2, "https://images.unsplash.com/photo-1570751057249-92751f496ee3?ixid=MXwxMjA3fDB8MHx0b3BpYy1mZWVkfDR8dG93SlpGc2twR2d8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=60"));
                annonces.add(new Annonce("Titre Annonce 4", "Contenu de au moins 20 caracteres", date3, "https://images.unsplash.com/photo-1609543563735-d7cfaf71addb?ixid=MXwxMjA3fDB8MHx0b3BpYy1mZWVkfDZ8dG93SlpGc2twR2d8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=60"));

                repository.saveAll(annonces);

                log.info("- - " + annonces.size() + " Annonces chargees - - ");
            } else {
                log.info("! - - ! Donnees deja chargees ! - - !");
            }

            log.info("- - Toutes les annonces : - -");
            for (Annonce annonce : repository.findAll()) {
                log.info(annonce.toString());
            }

            log.info("- - Test Pagination : - -");
            Pageable page = PageRequest.of(0, 2);
            Page<Annonce> annoncesWithPagination = repository.findAllAnnonceWithPagination(page);

            while (annoncesWithPagination.getContent().size() != 0) {
                log.info(" - Page " + annoncesWithPagination.getNumber());
                log.info(annoncesWithPagination.getContent().toString());
                page = page.next();
                annoncesWithPagination = repository.findAllAnnonceWithPagination(page);
            }

        };
    }
}