package com.iat.validators.points;

import com.iat.actions.SOLRActions;
import com.iat.actions.search.SearchActions;
import com.iat.domain.vouchers.VouchersList;
import com.iat.utils.ResponseContainer;

import java.io.IOException;
import java.util.List;

import static org.exparity.hamcrest.date.DateMatchers.sameOrAfter;
import static org.exparity.hamcrest.date.DateMatchers.sameOrBefore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SearchVouchersValidator {

    private SearchActions searchActions = new SearchActions();
    private SOLRActions solrActions = new SOLRActions();
    private ResponseContainer response;

    public void validateVoucherSearchCorrectness(int page, int pageSize, String sort, VouchersList vouchersList, String filterMerchant, String filterDepartment, String filterCategory, String filterValue) throws IOException {
        if (page > 0) {
            VouchersList vouchersList1 = searchActions.getVoucherItems("null", 0, pageSize + 1, "null", 200).getAsObject(VouchersList.class);
            assertThat("The same page is returned with different page parameter", vouchersList.getResults().get(0).getId(), is(not(vouchersList1.getResults().get(0).getId())));
            assertThat("Page element are not properly calculated", vouchersList.getResults().get(0).getId(), is(vouchersList1.getResults().get(vouchersList1.getResults().size() - 1).getId()));
        }

        if (pageSize > 0 && pageSize != 999) {
            assertThat("Wrong pageSize in response", vouchersList.getPageSize(), is(pageSize));
            assertThat("Wrong vouchers count in response", vouchersList.getResults(), hasSize(pageSize));
        }

//                Only expiryDate used on epoints side if more options available it needs to be updated

        String voucherId;
        for (int i = 0; i < vouchersList.getResults().size() - 1; i++) {
            if (!sort.equals("null")) {
                if (sort.contains("asc"))
                    assertThat("Wrong order desc for elements: " + i + " and " + i + 1, vouchersList.getResults().get(i).getValidToDate(), is(sameOrBefore(vouchersList.getResults().get(i + 1).getValidToDate())));
                else if (sort.contains("desc"))
                    assertThat("Wrong order asc for elements: " + i + " and " + i + 1, vouchersList.getResults().get(i).getValidToDate(), is(sameOrAfter(vouchersList.getResults().get(i + 1).getValidToDate())));
            }

            voucherId = vouchersList.getResults().get(i).getId();

            if (!filterMerchant.equals("null"))
                assertThat("Wrong voucher merchant for voucher id: " + voucherId, filterValue, is(vouchersList.getResults().get(i).getMerchantName()));

            if (filterDepartment.equals("random") || filterCategory.equals("random"))
                response = solrActions.getVouchersForQuery(voucherId, 200);

            if (!filterDepartment.equals("null"))
                assertThat("Wrong voucher department for voucher id: " + voucherId, filterValue, is(response.getString("response.docs[0].s_department")));

            if (!filterCategory.equals("null")) {
                List<String> categories = response.getList("response.docs[0].s_categoryFromFeedExtracted_multiVal");
                assertThat("Wrong voucher category for voucher id: " + voucherId, categories, hasItem(filterValue));
            }
        }
    }

}