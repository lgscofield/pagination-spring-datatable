/*
 * Copyright (C) 2012, Excilys
 */
package fr.excilys.labs.controller;

import java.util.ArrayList;
import java.util.List;

import fr.excilys.labs.modele.Article;

public class ArticleReponseJson {

    /* Paramètre de controle */
    private Integer sEcho;

    /* Nombre total de résultats correspondant aux critères de recherche */
    private Integer iTotalDisplayRecords;

    /* Liste des resultats
       Par défaut dataTable cherche les résultats dans une propriété nommée aaData.
       On peut configurer le nom de la propriété.
     */
    private List<Article> aaData = new ArrayList<Article>();

    public ArticleReponseJson() {
        super();
    }

    public Integer getsEcho() {
        return sEcho;
    }

    public void setsEcho(Integer sEcho) {
        this.sEcho = sEcho;
    }

    public Integer getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public List<Article> getAaData() {
        return aaData;
    }

    public void setAaData(List<Article> aaData) {
        this.aaData = aaData;
    }

}
