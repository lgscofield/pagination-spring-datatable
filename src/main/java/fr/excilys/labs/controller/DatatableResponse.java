/*
 * Copyright (C) 2012, Excilys
 */
package fr.excilys.labs.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Object that gets returned to jQuery datatables AJAX calls in JSON.
 *
 */
public class DatatableResponse<T> {

    private Integer sEcho;

    /* Total count of results to display in table */
    private Integer iTotalDisplayRecords;

    /* List of results
     */
    private List<T> aaData = new ArrayList<T>();

    public DatatableResponse() {
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

    public List<T> getAaData() {
        return aaData;
    }

    public void setAaData(List<T> aaData) {
        this.aaData = aaData;
    }

}
