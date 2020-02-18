package com.iat.actions;


import com.iat.controller.DepartmentsStructureController;
import com.iat.domain.departmentsStructure.CRUDDepartment;
import com.iat.domain.departmentsStructure.Department;
import com.iat.domain.departmentsStructure.Structure;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;

import java.util.List;

import static com.iat.utils.ResponseContainer.initResponseContainer;


public class DepartmentsStructureActions {

    private DepartmentsStructureController departmentsStructureController = new DepartmentsStructureController();
    private DataExchanger dataExchanger = DataExchanger.getInstance();


    public ResponseContainer createNewDepartment(CRUDDepartment jsonBody, int status) {
        return initResponseContainer(departmentsStructureController.createNewDepartment(jsonBody, status));
    }

    public ResponseContainer updateDepartmentName(CRUDDepartment jsonBody, String departmentId, int status) {
        return initResponseContainer(departmentsStructureController.updateDepartmentName(jsonBody, departmentId, status));
    }

    public ResponseContainer updateDepartmentName(CRUDDepartment jsonBody, int status) {
        return updateDepartmentName(jsonBody, jsonBody.getId(), status);
    }

    public ResponseContainer moveDepartment(CRUDDepartment jsonBody, String departmentId, int status) {
        return initResponseContainer(departmentsStructureController.moveDepartment(jsonBody, departmentId, status));
    }

    public ResponseContainer moveDepartment(CRUDDepartment jsonBody, int status) {
        return moveDepartment(jsonBody, jsonBody.getId(), status);
    }

    public ResponseContainer deleteDepartment(String departmentId, int status) {
        return initResponseContainer(departmentsStructureController.deleteDepartment(departmentId, status));
    }

    public ResponseContainer getDepartmentsStructure(String withRoot, int status) {
        return initResponseContainer(departmentsStructureController.getDepartmentsStructure(withRoot, status));
    }

    public ResponseContainer getDepartmentsValidStructure(int status) {
        return initResponseContainer(departmentsStructureController.getDepartmentsValidStructure(status));
    }

    public void getUserDepartmentsScope() throws Throwable {
        Structure structure = getDepartmentsValidStructure(200).getAsObject(Structure.class);

        searchForDepartments(structure.getDepartments());
    }

    private void searchForDepartments(List<Department> departments) {
        for (Department department : departments) {

            dataExchanger.getValidDepartmentsNamesScope().add(department.getName());

            if (department.getSubdepartments().size() > 0)
                searchForDepartments(department.getSubdepartments());
        }
    }
}