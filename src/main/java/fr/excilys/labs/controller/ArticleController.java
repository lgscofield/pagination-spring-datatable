/*
 * Copyright (C) 2012, Excilys
 */
package fr.excilys.labs.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.excilys.labs.modele.Article;
import fr.excilys.labs.modele.ERubriqueArticle;
import fr.excilys.labs.service.ArticleSearchBean;
import fr.excilys.labs.service.IArticleService;

@Controller
@RequestMapping("articles/search.htm")
public class ArticleController {

    private static final String COMMAND_NAME = "searchBean";
    private static final String RUBRIQUES = "rubriques";

    @Autowired
    private IArticleService articleService;

    @ModelAttribute(COMMAND_NAME)
    public ArticleSearchBean getCommand() {
        return new ArticleSearchBean();
    }

    @ModelAttribute(RUBRIQUES)
    public List<ERubriqueArticle> getRubriques() {
        return Arrays.asList(ERubriqueArticle.values());
    }

    @RequestMapping(method = GET)
    public String load(Model model) {
        return "articles";
    }

    @RequestMapping(method = POST)
    @ResponseBody
    public DatatableResponse<Article> search(@ModelAttribute(COMMAND_NAME) ArticleSearchBean searchBean,
            @RequestBody MultiValueMap<String, String> parametresAjax) {
        int count = articleService.count(searchBean);
        Integer debut = getIntFirstValue(parametresAjax, "iDisplayStart");
        Integer nbElements = getIntFirstValue(parametresAjax, "iDisplayLength");
        List<Article> listeArticles = articleService.search(searchBean, debut, nbElements);
        return prepareJsonResponse(listeArticles, count, parametresAjax);
    }

    private DatatableResponse<Article> prepareJsonResponse(List<Article> articles, int count, MultiValueMap<String, String> parametresAjax) {
        DatatableResponse<Article> reponseJson = new DatatableResponse<Article>();
        reponseJson.setsEcho(getIntFirstValue(parametresAjax, "sEcho"));
        reponseJson.setiTotalDisplayRecords(count);
        reponseJson.setAaData(articles);
        return reponseJson;
    }

    private Integer getIntFirstValue(MultiValueMap<String, String> parametres, String key) {
        Integer res = null;
        String s = parametres.getFirst(key);
        if (StringUtils.isNotEmpty(s)) {
            res = Integer.parseInt(s);
        }
        return res;
    }
}
