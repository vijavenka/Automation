package com.iat.actions.ecardsManagement;


import com.iat.controller.ecardsManagement.EcardsUsersSearchController;
import com.iat.domain.departmentsStructure.Department;
import com.iat.utils.ResponseContainer;

import java.util.ArrayList;
import java.util.List;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class EcardsUsersSearchActions {

    private EcardsUsersSearchController ecardsUsersSearchController = new EcardsUsersSearchController();


    public ResponseContainer getEcardsUser(String departmentId, String search, int status) {
        return initResponseContainer(ecardsUsersSearchController.getEcardsUser(departmentId, search, status));
    }


    public ResponseContainer searchCompanyUsers(String search, int status) {
        return initResponseContainer(ecardsUsersSearchController.searchCompanyUsers(search, status));
    }

    public List<Department> extractValidDepartmentsList(List<Department> departments) {
        List<Department> departmentList = new ArrayList<>();

        for (Department department : departments) {
            if (department.getSubdepartments().size() > 0)
                departmentList = extractValidDepartmentsList(department.getSubdepartments());
            departmentList.add(department);
        }
        return departmentList;
    }
}
