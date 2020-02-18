package com.iat.actions.ecardsManagement;

import com.iat.Config;
import com.iat.controller.ecardsManagement.EcardsReasonsController;
import com.iat.domain.EcardsConfig.Reason;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;


public class EcardsReasonsActions {

    private EcardsReasonsController ecardsReasonsController = new EcardsReasonsController();
    private JdbcDatabaseConnector mysqlConnectionPoolIatAdmin = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin);

    public ResponseContainer getEcardsReasonsList(int status) {
        return initResponseContainer(ecardsReasonsController.getEcardsReasonsList(status));
    }


    public ResponseContainer createNewEcardsReason(String jsonBody, int status) {
        return initResponseContainer(ecardsReasonsController.createEcardsReason(jsonBody, status));
    }


    public ResponseContainer getEcardsReasonById(String id, int status) {
        return initResponseContainer(ecardsReasonsController.getEcardsReasonById(id, status));
    }


    public ResponseContainer deleteEcardsReasonById(String id, int status) {
        return initResponseContainer(ecardsReasonsController.deleteEcardsReasonById(id, status));
    }

    public boolean isEcardsReasonDeleted(String id) {
        deleteEcardsReasonById(id, 404);
        return true;
    }

    public String getRandomDeletedResonId(String partnerId) {
        return mysqlConnectionPoolIatAdmin.getSingleResult("SELECT id FROM ECardsReason where partnerId = " + partnerId + " AND deleted = 1 LIMIT 1");
    }

    public String getRandomActiveResonId(String partnerId) {
        return mysqlConnectionPoolIatAdmin.getSingleResult("SELECT id FROM ECardsReason where partnerId = " + partnerId + " AND deleted = 0 LIMIT 1");
    }

    public String getReasonIdForName(String reasonName) throws Throwable {
        Reason reason = getEcardsReasonsList(200).getAsObject(Reason.class, "find{it.name == '" + reasonName + "'}");
        if (reason == null)
            return reasonName;
        return reason.getId();
    }

}
