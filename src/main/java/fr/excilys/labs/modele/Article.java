/*
 * Copyright (C) 2012, Excilys
 */
package fr.excilys.labs.modele;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Article implements Comparable<Article> {

    private long id;
    private String titre;
    private ERubriqueArticle rubrique = ERubriqueArticle.POLITIQUE;
    private String auteur;
    private Date datePublication;

    public Article() {
        super();
    }

    public Article(long id, String titre, ERubriqueArticle rubrique, String auteur, Date datePublication) {
        super();
        this.id = id;
        this.titre = titre;
        this.rubrique = rubrique;
        this.auteur = auteur;
        this.datePublication = datePublication;
    }

    /* GETTERS AND SETTERS */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public ERubriqueArticle getRubrique() {
        return rubrique;
    }

    public void setRubrique(ERubriqueArticle rubrique) {
        this.rubrique = rubrique;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    @Override
    public int compareTo(Article o) {
        return this.datePublication.compareTo(o.getDatePublication());
    }
}
