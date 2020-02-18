package com.iat.actions;


import com.iat.controller.SolrController;
import com.iat.utils.ResponseContainer;

public class SolrCheckActions {

    private SolrController solrController = new SolrController();

    public ResponseContainer getAllProducts(int indexPort) {
        return new ResponseContainer(solrController.getAllProducts(indexPort));
    }

    public void checkIfSolrIndexIsResponding(int indexPort) {
        solrController.checkHealthCheckResponse(indexPort);
    }
}
