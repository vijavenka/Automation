package com.iat.validators.pointsAllocation;

import com.iat.domain.ExternalTransactionId;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class PointsStaticChecksValidator {

    public void checkIfNoDuplicatedExternalTransactionIdsInResponse(List<ExternalTransactionId> externalTransactionIds) {
        boolean duplicated = false;

        String currentId;
        int counter;
        String duplications = "";
        List<String> duplicatedIds = new ArrayList<>();
        duplicatedIds.add("");

        for (int i = 0; i < externalTransactionIds.size(); i++) {

            currentId = externalTransactionIds.get(i).getExternalTransactionId();
            counter = 0;

            for (int j = 0; j < externalTransactionIds.size(); j++) {
                if (externalTransactionIds.get(j).getExternalTransactionId() == null)
                    continue;

                duplicated = externalTransactionIds.get(j).getExternalTransactionId().equals(currentId) && i != j;
                if (duplicated) counter++;
            }

            boolean found = false;

            if (counter > 0) {
                for (String duplicatedId : duplicatedIds) {
                    found = duplicatedId.contains(externalTransactionIds.get(i).getExternalTransactionId());
                    if (found) break;
                }
                if (!found)
                    duplicatedIds.add(externalTransactionIds.get(i).getExternalTransactionId() + "::" + counter);
            }
        }

        for (String duplicatedId : duplicatedIds)
            duplications += duplicatedId + " ";
        assertThat("There was duplicated externalTransactionIds in last period:" + duplications, !duplicated);
    }
}