/*
 * Copyright (C) 2012, Excilys
 */
package fr.excilys.labs.service;

import java.util.List;

import fr.excilys.labs.modele.Article;

public interface IArticleService {

    /**
     * Récupère la liste des articles correspondant aux critères de recherches.
     * Seuls les "nbResults" articles à partir de l'index "offset" sont réelement retournés.
     * @param searchBean - critères de recherche
     * @param offset - index du premier element à retourner
     * @param nbResults - nombre maximum d'élements à retourner
     * @return
     */
    List<Article> search(ArticleSearchBean searchBean, int offset, int nbResults);

    /**
     * Compte le nombre total d'articles correspondant aux critères de recherche
     * @param searchBean - critères de recherche.
     * @return
     */
    int count(ArticleSearchBean searchBean);
}
