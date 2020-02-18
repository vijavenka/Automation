Feature: Category page

   As a user
       I need a page to go to
       Where I can see product from chosen before category

       #1
       Scenario:FRONT END REFACTOR - add epoints search solution to WLS CATEGORY pages (Rd-1968) - checking of breadcrumb correctness
            Given Not registered user open WLS page
            When User chose some department from DDL
            And Will navigate through department/categories options
            Then He will be redirected to correct category page
            And Breadcrumb component will show proper path