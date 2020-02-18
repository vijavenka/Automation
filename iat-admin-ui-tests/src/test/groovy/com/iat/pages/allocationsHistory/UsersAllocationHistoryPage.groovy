package com.iat.pages.allocationsHistory

import com.iat.stepdefs.utils.HelpFunctions

class UsersAllocationHistoryPage extends DepartmentsAllocationHistoryPage {

    static url = '/hr/history/users-allocation'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("users-allocation") } }

    static content = {
        ecardsSentCard(wait: true, required: false) { $('.zmdi-star').closest('div') }
        ecardsSentValue(wait: true, required: false) { ecardsSentCard.find('.size-h2') }
    }

    def returnAllocationTableLocator(String column, int position) {
        switch (column) {
            case "ID":
                waitFor { allocationTableRowBasic[position].find('td', 0).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 0)
                break
            case "Date send":
                waitFor { allocationTableRowBasic[position].find('td', 1).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 1)
                break
            case "Send from":
                waitFor { allocationTableRowBasic[position].find('td', 2).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 2)
                break
            case "Environment Used":
                waitFor { allocationTableRowBasic[position].find('td', 3).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 3)
                break
            case "Send to":
                waitFor { allocationTableRowBasic[position].find('td', 4).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 4)
                break
            case "Reason":
                waitFor { allocationTableRowBasic[position].find('td', 5).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 5)
                break
            case "Points awarded":
                waitFor { allocationTableRowBasic[position].find('td', 6).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 6)
                break
            case "Value of points":
                waitFor { allocationTableRowBasic[position].find('td', 7).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 7)
                break
            case "Approval status":
                return allocationTableRowBasic[position].find('td', 8)
                break
        }
    }

    def selectFilterOption(filterName) {
        waitFor { filtersInDDL.isDisplayed() }; filtersInDDL.click()
        switch (filterName) {
            case "Points amount":
                waitFor { filtersInDDLOption[0].isDisplayed() }; filtersInDDLOption[0].click()
                break
            case "Value":
                waitFor { filtersInDDLOption[1].isDisplayed() }; filtersInDDLOption[1].click()
                break
            case "Receiver":
                waitFor { filtersInDDLOption[2].isDisplayed() }; filtersInDDLOption[2].click()
                break
            case "Sender":
                waitFor { filtersInDDLOption[3].isDisplayed() }; filtersInDDLOption[3].click()
                break
            case "Sender department":
                waitFor { filtersInDDLOption[4].isDisplayed() }; filtersInDDLOption[4].click()
                break
            case "Receiver department":
                waitFor { filtersInDDLOption[5].isDisplayed() }; filtersInDDLOption[5].click()
                break
            case "Approval status":
                waitFor { filtersInDDLOption[6].isDisplayed() }; filtersInDDLOption[6].click()
                break
        }
    }

    def clickChosenColumnSelectedSortOption(String sortOption, String column) {
        switch (column) {
            case "Approval status":
                if (sortOption == 'ascending')
                    allocationsTableHeadersRowSortUpElementBasic[7].click()
                else
                    allocationsTableHeadersRowSortDownElementBasic[7].click()
        }
    }

    def clickSortOptionOfChosenColumn(String sortOption, String columnName) {
        def columnNumber
        switch (columnName) {
            case "ID":
                columnNumber = 0
                break
            case "Date send":
                columnNumber = 1
                break
            case "Send from":
                columnNumber = 2
                break
            case "Send to":
                columnNumber = 3
                break
            case "Reason":
                columnNumber = 4
                break
            case "Points awarded":
                columnNumber = 5
                break
            case "Value of points":
                columnNumber = 6
                break
            case "Approval status":
                columnNumber = 7
                break
        }

        if (sortOption == 'ascending') {
            waitFor {
                allocationsTableHeadersRowSortUpElementBasic.size() == 7 || allocationsTableHeadersRowSortUpElementBasic.size() == 8
            }
            waitFor { allocationsTableHeadersRowSortUpElementBasic[columnNumber].isDisplayed() }
            allocationsTableHeadersRowSortUpElementBasic[columnNumber].click()
        } else if (sortOption == 'descending') {
            waitFor {
                allocationsTableHeadersRowSortDownElementBasic.size() == 7 || allocationsTableHeadersRowSortDownElementBasic.size() == 8
            }
            waitFor { allocationsTableHeadersRowSortDownElementBasic[columnNumber].isDisplayed() }
            allocationsTableHeadersRowSortDownElementBasic[columnNumber].click()
        }
    }

    def chckIfcolumnWasProperlySorted(String columnName, String sortOption) {
        def helpFunctions = new HelpFunctions()
        for (int i = 1; i < allocationTableRowBasic.size() - 2; i++) {
            if (returnAllocationTableLocator(columnName, i).text() == '')
                continue

            switch (columnName) {
                case "ID":
                    if (sortOption == 'ascending')
                        assert Integer.parseInt(returnAllocationTableLocator(columnName, i).text()) <= Integer.parseInt(returnAllocationTableLocator(columnName, i + 1).text())
                    else if (sortOption == 'descending')
                        assert Integer.parseInt(returnAllocationTableLocator(columnName, i).text()) >= Integer.parseInt(returnAllocationTableLocator(columnName, i + 1).text())
                    break
                case "Date send":
                    if (sortOption == 'ascending')
                        assert helpFunctions.parseDateFromStringToMiliseconds(returnAllocationTableLocator(columnName, i).text(), 'yyyy-MM-dd') <= helpFunctions.parseDateFromStringToMiliseconds(returnAllocationTableLocator(columnName, i + 1).text(), 'yyyy-MM-dd')
                    else if (sortOption == 'descending')
                        assert helpFunctions.parseDateFromStringToMiliseconds(returnAllocationTableLocator(columnName, i).text(), 'yyyy-MM-dd') >= helpFunctions.parseDateFromStringToMiliseconds(returnAllocationTableLocator(columnName, i + 1).text(), 'yyyy-MM-dd')
                    break
                case "Send from":
                    def firstCharOfFirstElement = returnAllocationTableLocator(columnName, i).text().charAt(0).toLowerCase()
                    def firstCharOfSecondElement = returnAllocationTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()
                    if (firstCharOfFirstElement == '(') {
                        //it means that element begins with department which is not taken into consideration by search engine
                        firstCharOfFirstElement = returnAllocationTableLocator(columnName, i).text().substring(returnAllocationTableLocator(columnName, i).text().indexOf(')' + 1)).replaceAll("(?m)^[ \t]*\r?\n", "").charAt(0).toLowerCase()
                    }
                    if (firstCharOfSecondElement == '(') {
                        //it means that element begins with department which is not taken into consideration by search engine
                        firstCharOfSecondElement = returnAllocationTableLocator(columnName, i).text().substring(returnAllocationTableLocator(columnName, i + 1).text().indexOf(')') + 1).replaceAll("(?m)^[ \t]*\r?\n", "").charAt(0).toLowerCase()
                    }
                    if (sortOption == 'ascending')
                        assert firstCharOfFirstElement.toLowerCase().compareTo(firstCharOfSecondElement.toLowerCase()) <= 0
                    else if (sortOption == 'descending')
                        assert firstCharOfFirstElement.toLowerCase().compareTo(firstCharOfSecondElement.toLowerCase()) >= 0
                    break
                case "Send to":
                    def firstCharOfFirstElement = returnAllocationTableLocator(columnName, i).text().charAt(0).toLowerCase()
                    def firstCharOfSecondElement = returnAllocationTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()
                    if (firstCharOfFirstElement == '(') {
                        //it means that element begins with department which is not taken into consideration by search engine
                        firstCharOfFirstElement = returnAllocationTableLocator(columnName, i).text().substring(returnAllocationTableLocator(columnName, i).text().indexOf(')') + 1).replaceAll("(?m)^[ \t]*\r?\n", "").charAt(0).toLowerCase()
                    }
                    if (firstCharOfSecondElement == '(') {
                        //it means that element begins with department which is not taken into consideration by search engine
                        firstCharOfSecondElement = returnAllocationTableLocator(columnName, i).text().substring(returnAllocationTableLocator(columnName, i + 1).text().indexOf(')') + 1).replaceAll("(?m)^[ \t]*\r?\n", "").charAt(0).toLowerCase()
                    }
                    if (sortOption == 'ascending')
                        assert firstCharOfFirstElement.toLowerCase().compareTo(firstCharOfSecondElement.toLowerCase()) <= 0
                    else if (sortOption == 'descending')
                        assert firstCharOfFirstElement.toLowerCase().compareTo(firstCharOfSecondElement.toLowerCase()) >= 0
                    break
                case "Reason":
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
                case "Approval status":
                    if (sortOption == 'ascending')
                        assert helpFunctions.parseDateFromStringToMiliseconds(returnAllocationTableLocator(columnName, i).text(), 'yyyy-MM-dd') <= helpFunctions.parseDateFromStringToMiliseconds(returnAllocationTableLocator(columnName, i + 1).text(), 'yyyy-MM-dd')
                    else if (sortOption == 'descending')
                        assert helpFunctions.parseDateFromStringToMiliseconds(returnAllocationTableLocator(columnName, i).text(), 'yyyy-MM-dd') >= helpFunctions.parseDateFromStringToMiliseconds(returnAllocationTableLocator(columnName, i + 1).text(), 'yyyy-MM-dd')
                    break
            }
        }
    }
}