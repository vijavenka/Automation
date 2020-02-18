package com.iat.actions.rewards;

import com.iat.controller.rewards.DepartmentsController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class DepartmentsActions {

    private DepartmentsController departmentsController = new DepartmentsController();

    public ResponseContainer getDepartmentsByBusinessId(String businessId, int status) {
        return initResponseContainer(departmentsController.getDepartmentsByBusinessId(businessId, status));
    }

    public ResponseContainer getDepartmentsByBusinessId(String businessId) {
        return getDepartmentsByBusinessId(businessId, 200);
    }

}