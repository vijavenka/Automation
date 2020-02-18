package modules.Epoints_modules.yourAccountSection

import geb.Module

/**
 * Created by kbaranowski on 2015-02-25.
 */

class emailChangedConfirmationPage extends Module{
    static content = {
        pageContent{ $('.grid-row.mainContent') }
        accountDashboardButton{ $('.btn-grey') }
        earnMoreEpointsButton{ $('.btn-yellow') }
    }
}