package com.nm.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.nm.exam.model.Annonce;
import com.nm.exam.services.AnnonceService;

import javax.validation.Valid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView annonce() {
        List<Annonce> annonces = this.annonceService.getAnnonces();
        ModelAndView mv = new ModelAndView("annonce/list");
        mv.addObject("annonces", annonces);
        return mv;
    }

    @RequestMapping(value = "/{annonce}", method = RequestMethod.GET)
    public ModelAndView annonceDetail(@PathVariable(name="annonce") Annonce annonce) {

        if(annonce == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Annonce Introuvable");
        }

        ModelAndView mv = new ModelAndView("annonce/detail");
        mv.addObject("annonce", annonce);
        return mv;
    }


    @RequestMapping(value = "test/{annonce}", method = RequestMethod.GET)
    public ModelAndView annonceTest(@PathVariable(name="annonce") Annonce annonce) {

        if(annonce == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Annonce Introuvable");
        }

        ModelAndView mv = new ModelAndView("annonce/detail");
        mv.addObject("annonce", annonce);
        return mv;
    }

    @RequestMapping(value = "/delete/{annonce}", method = RequestMethod.GET)
    public String deleteAnnonce(@PathVariable(name = "annonce") Annonce annonce) {
        this.annonceService.deleteAnnonce(annonce);
        return "redirect:/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addAnnonceForm() throws ParseException {
        Annonce annonce = new Annonce();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        annonce.setDatePublication(formatter.parse(formatter.format(date)));
        ModelAndView mv = new ModelAndView("annonce/form");
        mv.addObject("annonce", annonce);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveAnnonce(@Valid Annonce annonce, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "annonce/form";
        } else {
            this.annonceService.saveOrUpdateAnnonce(annonce);
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/edit/{annonce}", method = RequestMethod.GET)
    public ModelAndView editAnnonce(@PathVariable(name = "annonce") Annonce annonce) {

        ModelAndView mv = new ModelAndView("annonce/form");
        mv.addObject("annonce", annonce);

        return mv;
    }

    @RequestMapping(value = "/edit/{annonce}", method = RequestMethod.POST)
    public String editAnnonce(@Valid Annonce annonce, BindingResult bindingResult) {

        this.annonceService.saveOrUpdateAnnonce(annonce);

        if (bindingResult.hasErrors()) {
            return "annonce/form";
        } else {
            this.annonceService.saveOrUpdateAnnonce(annonce);
            return "redirect:/";
        }
    }


}
