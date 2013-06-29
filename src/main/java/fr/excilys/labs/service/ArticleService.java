/*
 * Copyright (C) 2012, Excilys
 */
package fr.excilys.labs.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import fr.excilys.labs.modele.Article;
import fr.excilys.labs.modele.ERubriqueArticle;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ArticleService implements IArticleService {

    /**
     * Liste d'exemple contenant plusieurs articles.
     */
    public static List<Article> ARTICLES = new ArrayList<Article>();

    static {
        ARTICLES.add(new Article(1, "Hollande face aux journalistes", ERubriqueArticle.POLITIQUE, "Barbier", getDate(2012, 11, 13)));
        ARTICLES.add(new Article(2, "C'est la crise", ERubriqueArticle.ECONOMIE, "Doze", getDate(2008, 1, 1)));
        ARTICLES.add(new Article(3, "PSG - Troyes : 4-0", ERubriqueArticle.SPORTS, "Marchand", getDate(2012, 11, 24)));
        ARTICLES.add(new Article(4, "Sont-ils aussi nuls?", ERubriqueArticle.POLITIQUE, "Giesbert", getDate(2012, 10, 1)));
        ARTICLES.add(new Article(5, "Le PSG à la peine!", ERubriqueArticle.SPORTS, "Menes", getDate(2012, 12, 1)));
        ARTICLES.add(new Article(6, "Guerre à l'UMP", ERubriqueArticle.POLITIQUE, "Mazerolle", getDate(2012, 11, 25)));
        ARTICLES.add(new Article(7, "Free dérange!", ERubriqueArticle.ECONOMIE, "Jeudy", getDate(2012, 12, 4)));
        ARTICLES.add(new Article(8, "Le régime de Bachar menacé", ERubriqueArticle.POLITIQUE, "Bonficace", getDate(2012, 7, 1)));
        ARTICLES.add(new Article(9, "Départ du Vendée Globe", ERubriqueArticle.SPORTS, "François-Xavier", getDate(2012, 11, 10)));
    }
    @Override
    public List<Article> search(ArticleSearchBean searchBean, int offset, int nbResults) {
        List<Article> res = search(searchBean);
        int end = offset + nbResults < res.size() ? offset + nbResults : res.size();
        res = res.subList(offset, end);
        Collections.sort(res);
        return res;
    }

    @Override
    public int count(ArticleSearchBean searchBean) {
        return search(searchBean).size();
    }

    private List<Article> search(ArticleSearchBean searchBean) {
        List<Article> res = new ArrayList<Article>();
        for (Article a : ARTICLES) {
            boolean add = true;
            // Titre ou auteur
            if (StringUtils.isNotEmpty(searchBean.getTitre())) {
                add = a.getTitre().contains(searchBean.getTitre());
            }
            //Catégorie
            if (searchBean.getRubriques() != null) {
                add = add && searchBean.getRubriques().contains(a.getRubrique());
            }

            if (add) {
                res.add(a);
            }
        }
        return res;
    }

    private static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        return cal.getTime();
    }
}
