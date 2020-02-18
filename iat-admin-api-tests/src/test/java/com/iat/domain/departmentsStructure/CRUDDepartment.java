package com.iat.domain.departmentsStructure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.Config;
import com.iat.domain.AbstractDomain;
import com.iat.utils.DataExchanger;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CRUDDepartment extends AbstractDomain {

    private String id;
    private String name;
    private String parentId;
    private String active;

    public void setName(String name) {
        if (name != null) {
            if (name.equals("random"))
                name = "randomDepartmentName_" + new Date().getTime();
            if (name.equals("duplicated"))
                name = "Department level 2 [D.1]";
        }
        this.name = name;
    }

    public void setParentId(String parentId) {
        if (parentId != null && parentId.equals("previous_call"))
            parentId = DataExchanger.getInstance().getDepartmentObject().getId();
        this.parentId = Config.getDepartmentIdForName(parentId);
    }

}