<!--
Copyright (C) 2012, Excilys.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <spring:url value="/webjars/jquery/1.9.1/jquery.js" var="jQuery"/>
    <script src="${jQuery}"></script>

    <spring:url value="/webjars/datatables/1.9.4/media/js/jquery.dataTables.js" var="jQueryDatatables"/>
    <script src="${jQueryDatatables}"></script>
    <spring:url value="/webjars/datatables/1.9.4/media/css/jquery.dataTables.css" var="jQueryDatatablesCss"/>
    <link href="${jQueryDatatablesCss}" rel="stylesheet"></link>

    <title>Recherche Articles</title>
    <style type="text/css">
        #contenu {
            width : 80%;
            margin-left :auto;
            margin-right: auto;
        }

        #criteres , #resultats {
            margin : 5px auto 10px auto;
        }

        caption {
           font-size: 1.1 em;
           font-weight: bold;
        }

        input[type="text"] {
           width : 100%;
        }

        table#resultats tbody tr:hover {
            background-color: #6471FC;
            cursor: pointer;
        }

    </style>
    <script type="text/javascript">
    function search() {
        $("#resultats").dataTable({
            "iDisplayLength" : 5,
            "bFilter" : false,
            "bInfo" : false,
            "bSort" : false,
            "sPaginationType" : "full_numbers",
            "sDom" : '<"#top"p>rt<"#bottom"p>',
            "oLanguage" : {
                "oPaginate" : {
                    "sFirst" : "<<",
                    "sLast" : ">>",
                    "sNext" : ">",
                    "sPrevious" : "<"
                }
            },
            "bServerSide" : true,
            "bProcessing" : true,
            "bDestroy" : true,
            "sAjaxSource" : "search",
            "fnServerData" : function(sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType" : 'json',
                    "type" : "POST",
                    "url" : sSource,
                    "data" : aoData,
                    "success" : fnCallback,
                    "timeout" : 30000
                });
            },
            "fnServerParams" : function(aoData) {
                $.each($("#form-search").serializeArray(),
                        function(index, elt) {
                            aoData.push(elt);
                        });
            },
            "aoColumns" : [ {
                "mDataProp" : "titre"
            }, {
                "mDataProp" : "auteur"
            }, {
                "mDataProp" : "rubrique"
            }, {
                "mDataProp" : "datePublication"
            }
            ]
        });
    }

    /*
     * Permet de lier l'action du bouton "Rechercher"
     * ainsi que la touche Entrée sur la recherche.
     */
    function bindSubmitEvent() {
        $("#boutonRechercher").click(function(){
            search();
        });

        $("form#form-search").off('submit');
        $("form#form-search").submit(function() {
            search();
            return false;
        });
    }

    /*
     * La changement d'état des checkbox relance la recherche
     */
    function addOnChangeBehaviour() {
        $("form#form-search input:checkbox").change(function() {
            search();
        });
    }

    $(document).ready(function(){
        search();
        bindSubmitEvent();
        addOnChangeBehaviour();
    });
    </script>
</head>
<body>
    <div id="contenu">
     <form:form id="form-search" commandName="searchBean" method="POST" >
         <table id="criteres">
            <caption>Recherche d'articles</caption>
            <tr>
                <td>
                    <form:label path="titre">Titre :</form:label>
                </td>
                <td colspan="2">
                    <form:input path="titre"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="rubriques">Rubriques : </form:label>
                </td>
                <td>
                    <form:checkboxes items="${rubriques}" path="rubriques" />
                </td>
                <td>
                    <input id="boutonRechercher" type="submit" value="Rechercher" />
                </td>
            </tr>
         </table>
     </form:form>

     <table id="resultats" width="100%">
        <caption>Liste des articles</caption>
        <thead>
            <tr>
                <th>Titre</th>
                <th>Auteur</th>
                <th>Catégorie</th>
                <th>Date de publication</th>
            </tr>
        </thead>
        <tbody>
            <!-- Contenu géré par dataTable -->
        </tbody>
     </table>

     </div>
</body>
</html>
