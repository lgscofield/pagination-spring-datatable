/*
 * Copyright (C) 2012, Excilys
 */
package fr.excilys.labs.service;

import java.util.Arrays;
import java.util.List;

import fr.excilys.labs.modele.ERubriqueArticle;

/**
 * Critères de recherche (commande au sens Spring).
 */
public class ArticleSearchBean {

    private String titre;
    private List<ERubriqueArticle> rubriques = Arrays.asList(ERubriqueArticle.values());

    public ArticleSearchBean() {
        super();
    }

    /* GETTERS AND SETTERS */
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<ERubriqueArticle> getRubriques() {
        return rubriques;
    }

    public void setRubriques(List<ERubriqueArticle> rubriques) {
        this.rubriques = rubriques;
    }
}
