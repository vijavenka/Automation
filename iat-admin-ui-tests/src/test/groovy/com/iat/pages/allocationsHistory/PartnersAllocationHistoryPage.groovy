package com.iat.pages.allocationsHistory

import com.iat.stepdefs.utils.HelpFunctions

class PartnersAllocationHistoryPage extends DepartmentsAllocationHistoryPage {

    static url = '/hr/history/partners-allocation'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("partners-allocation") } }

    static content = {

    }

    def returnAllocationTableLocator(String column, int position) {
        switch (column) {
            case "Date send":
                waitFor { allocationTableRowBasic[position].find('td', 0).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 0)
                break
            case "Partner name":
                waitFor { allocationTableRowBasic[position].find('td', 1).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 1)
                break
            case "Who":
                waitFor { allocationTableRowBasic[position].find('td', 2).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 2)
                break
            case "Comment":
                waitFor { allocationTableRowBasic[position].find('td', 3).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 3)
                break
            case "Points awarded":
                waitFor { allocationTableRowBasic[position].find('td', 4).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 4)
                break
            case "Value of points":
                waitFor { allocationTableRowBasic[position].find('td', 5).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 5)
                break
        }
    }

    def selectFilterOption(filterName) {
        waitFor { filtersInDDL.isDisplayed() }; filtersInDDL.click()
        switch (filterName) {
            case "Comment text":
                waitFor { filtersInDDLOption[4].isDisplayed() }; filtersInDDLOption[4].click()
                break
            case "Who allocated":
                waitFor { filtersInDDLOption[3].isDisplayed() }; filtersInDDLOption[3].click()
                break
            case "Partner":
                waitFor { filtersInDDLOption[2].isDisplayed() }; filtersInDDLOption[2].click()
                break
            case "Value":
                waitFor { filtersInDDLOption[1].isDisplayed() }; filtersInDDLOption[1].click()
                break
            case "Points amount":
                waitFor { filtersInDDLOption[0].isDisplayed() }; filtersInDDLOption[0].click()
                break
        }
    }

    def clickSortOptionOfChosenColumn(String sortOption, String columnName) {
        def columnNumber
        switch (columnName) {
            case "Date send":
                columnNumber = 0
                break
            case "Partner name":
                columnNumber = 1
                break
            case "Who":
                columnNumber = 2
                break
            case "Comment":
                columnNumber = 3
                break
            case "Points awarded":
                columnNumber = 4
                break
            case "Value of points":
                columnNumber = 5
                break
        }

        if (sortOption == 'ascending') {
            waitFor { allocationsTableHeadersRowSortUpElementBasic.size() == 6 }
            waitFor { allocationsTableHeadersRowSortUpElementBasic[columnNumber].isDisplayed() }
            allocationsTableHeadersRowSortUpElementBasic[columnNumber].click()
        } else if (sortOption == 'descending') {
            waitFor { allocationsTableHeadersRowSortDownElementBasic.size() == 6 }
            waitFor { allocationsTableHeadersRowSortDownElementBasic[columnNumber].isDisplayed() }
            allocationsTableHeadersRowSortDownElementBasic[columnNumber].click()
        }
    }

    def chckIfcolumnWasProperlySorted(String columnName, String sortOption) {
        def helpFunctions = new HelpFunctions()
        for (int i = 1; i < allocationTableRowBasic.size() - 2; i++) {
            if (returnAllocationTableLocator(columnName, i).text() == '') {
                continue
            }

            switch (columnName) {
                case "Date send":
                    if (sortOption == 'ascending')
                        assert helpFunctions.parseDateFromStringToMiliseconds(returnAllocationTableLocator(columnName, i).text(), 'yyyy-MM-dd') <= helpFunctions.parseDateFromStringToMiliseconds(returnAllocationTableLocator(columnName, i + 1).text(), 'yyyy-MM-dd')
                    else if (sortOption == 'descending')
                        assert helpFunctions.parseDateFromStringToMiliseconds(returnAllocationTableLocator(columnName, i).text(), 'yyyy-MM-dd') >= helpFunctions.parseDateFromStringToMiliseconds(returnAllocationTableLocator(columnName, i + 1).text(), 'yyyy-MM-dd')
                    break
                case "Partner name":
                    if (sortOption == 'ascending')
                        assert returnAllocationTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnAllocationTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) <= 0
                    else if (sortOption == 'descending')
                        assert returnAllocationTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnAllocationTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) >= 0
                    break
                case "Who":
                    if (sortOption == 'ascending')
                        assert returnAllocationTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnAllocationTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) <= 0
                    else if (sortOption == 'descending')
                        assert returnAllocationTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnAllocationTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) >= 0
                    break
                case "Comment":
                    if (sortOption == 'ascending')
                        assert returnAllocationTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnAllocationTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) <= 0
                    else if (sortOption == 'descending')
                        assert returnAllocationTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnAllocationTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) >= 0
                    break
                case "Points awarded":
                    if (sortOption == 'ascending')
                        assert Integer.parseInt(returnAllocationTableLocator(columnName, i).text().replace(',', '')) <= Integer.parseInt(returnAllocationTableLocator(columnName, i + 1).text().replace(',', ''))
                    else if (sortOption == 'descending')
                        assert Integer.parseInt(returnAllocationTableLocator(columnName, i).text().replace(',', '')) >= Integer.parseInt(returnAllocationTableLocator(columnName, i + 1).text().replace(',', ''))
                    break
                case "Value of points":
                    if (sortOption == 'ascending')
                        assert Float.parseFloat(returnAllocationTableLocator(columnName, i).text().substring(1)) <= Float.parseFloat(returnAllocationTableLocator(columnName, i + 1).text().substring(1))
                    else if (sortOption == 'descending')
                        assert Float.parseFloat(returnAllocationTableLocator(columnName, i).text().substring(1)) >= Float.parseFloat(returnAllocationTableLocator(columnName, i + 1).text().substring(1))
                    break
            }
        }
    }
}

