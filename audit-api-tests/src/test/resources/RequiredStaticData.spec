Audit cms Requirements
=========================
# memberId, wholesaler_name
'7', 'API_WHOLESALER_1'

# memberId, supplier_name
'12', 'API_SUPPLIER_1'
'13', 'API_SUPPLIER_2'

# memberId, brand_name, supplier_id
'14', 'API_BRAND_1.1', '12'
'15', 'API_BRAND_2.1', '13'
'16', 'API_COCA_COLA', '12'


# memberId, product_name, url, description, category_id, brand_id
'1250', 'API cola 200ml', NULL, NULL, NULL, '16'
'1249', 'API cola 150ml', NULL, NULL, NULL, '16'
'1248', 'API cola 100ml', NULL, NULL, NULL, '16'
'1247', 'API_PRODUCT_1_BRAND_2.1', 'www.api.product_1_brand_2.1', NULL, NULL, '15'
'1246', 'API_PRODUCT_2_BRAND_1.1', 'www.api.product_2_brand_1.1', NULL, NULL, '14'
'1245', 'API_PRODUCT_1_BRAND_1.1', 'www.api.product_1_brand_1.1', NULL, NULL, '14'



# memberId, question_text, adhoc_ext_id, question_type, placement, images_number, ext_rel_id, product_id, category_id
'18', 'is product available', NULL, 'PRODUCT', 'FREEZER', NULL, NULL, '1245', NULL
'19', 'is product available', NULL, 'PRODUCT', 'FREEZER', NULL, NULL, '1247', NULL
# memberId, question_text, adhoc_ext_id, question_type, placement, images_number, ext_rel_id, product_id, category_id
'20', 'Is available', NULL, 'PRODUCT', 'FREEZER', NULL, NULL, '1248', NULL
'21', 'Is available', NULL, 'PRODUCT', 'FREEZER', NULL, NULL, '1249', NULL
'22', 'Is available', NULL, 'PRODUCT', 'FREEZER', NULL, NULL, '1250', NULL



# memberId, retailer_name, email, epoints_uuid, wholesaler_id
'158', 'Test1 Test', 'retailer_1@test.test.pl', 'a3891238-2bcf-4a25-9de8-258f6080dd4a', '7'
'1618', 'Test2 Test', 'retailer_2@test.test.pl', '7064e702-e448-4283-be36-b36141667494', '7'


# memberId, store_name, licensed, active, audit_group, store_type, address_line_1, address_line_2, address_line_3, address_line_4, post_code, country, ext_rel_id, big_dl_branch_id, chain_id, retailer_id
'431', 'API_TESTS_STORE_1', NULL, '1', 'A', 'API Tests type', 'Address Line 1 test', 'Address Line 2 test', 'Address Line 3 test', 'Address Line 4 test', '22-400', 'PL', '10005001000', NULL, '1', '158'
'444', 'API_TESTS_STORE_2', NULL, '1', 'A', 'Type_API', 'Line1', 'Line 2', 'Line  3', 'Line   4', '22400', 'PL', '10005001001', NULL, '1', NULL


# memberId, audit_name, audit_start, audit_end, ext_rel_id
'2', 'API_AUDIT_1', '2016-05-25', '2016-06-30', NULL

#Direct queries to populate cms database data needed for automated tests

#WHOLESALER TABLE
INSERT INTO audit.wholesaler (memberId,wholesaler_name) VALUES ('7','API_WHOLESALER_1')

#SUPPLIER TABLE
INSERT INTO audit.supplier (memberId,supplier_name,partner_api_key) VALUES ('12','API_SUPPLIER_1','NkGlw5fesuJHNSqXMOx3hpXRq')
INSERT INTO audit.supplier (memberId,supplier_name,partner_api_key) VALUES ('13','API_SUPPLIER_2','ZWL69j9cWTv9KVkYAdqknoMSh')

#BRAND TABLE
INSERT INTO audit.brand (memberId,brand_name,supplier_id) VALUES ('14','API_BRAND_1.1','12')
INSERT INTO audit.brand (memberId,brand_name,supplier_id) VALUES ('15','API_BRAND_2.1','13')
INSERT INTO audit.brand (memberId,brand_name,supplier_id) VALUES ('16','API_COCA_COLA','12')

#PRODUCT TABLE
INSERT INTO audit.product (memberId,product_name,url,description,category_id,brand_id) VALUES ('1250', 'API cola 200ml', NULL, NULL, NULL, '16')
INSERT INTO audit.product (memberId,product_name,url,description,category_id,brand_id) VALUES ('1249', 'API cola 150ml', NULL, NULL, NULL, '16')
INSERT INTO audit.product (memberId,product_name,url,description,category_id,brand_id) VALUES ('1248', 'API cola 100ml', NULL, NULL, NULL, '16')
INSERT INTO audit.product (memberId,product_name,url,description,category_id,brand_id) VALUES ('1247', 'API_PRODUCT_1_BRAND_2.1', 'www.api.product_1_brand_2.1', NULL, NULL, '15')
INSERT INTO audit.product (memberId,product_name,url,description,category_id,brand_id) VALUES ('1246', 'API_PRODUCT_2_BRAND_1.1', 'www.api.product_2_brand_1.1', NULL, NULL, '14')
INSERT INTO audit.product (memberId,product_name,url,description,category_id,brand_id) VALUES ('1245', 'API_PRODUCT_1_BRAND_1.1', 'www.api.product_1_brand_1.1', NULL, NULL, '14')

#QUESTION TABLE
INSERT INTO audit.question (memberId, question_text, adhoc_ext_id, question_type, placement, images_number, ext_rel_id, product_id, category_id) VALUES ('18', 'is product available', NULL, 'PRODUCT', 'FREEZER', NULL, NULL, '1245', NULL)
INSERT INTO audit.question (memberId, question_text, adhoc_ext_id, question_type, placement, images_number, ext_rel_id, product_id, category_id) VALUES ('19', 'is product available', NULL, 'PRODUCT', 'FREEZER', NULL, NULL, '1247', NULL)
INSERT INTO audit.question (memberId, question_text, adhoc_ext_id, question_type, placement, images_number, ext_rel_id, product_id, category_id) VALUES ('20', 'Is available hi', NULL, 'PRODUCT', 'FREEZER', NULL, NULL, '1248', NULL)

#RETAILER TABLE
INSERT INTO audit.retailerId (memberId, retailer_name, email, epoints_uuid, wholesaler_id) VALUES ('158', 'Test1 Test', 'retailer_1@test.test.pl', 'a3891238-2bcf-4a25-9de8-258f6080dd4a', '7')

#STORE TABLE
INSERT INTO audit.store (memberId, store_name, licensed, active, audit_group, store_type, address_line_1, address_line_2, address_line_3, address_line_4, post_code, country, ext_rel_id, big_dl_branch_id, chain_id, retailer_id) VALUES ('431', 'API_TESTS_STORE_1', NULL, '1', 'A', 'API Tests type', 'Address Line 1 test', 'Address Line 2 test', 'Address Line 3 test', 'Address Line 4 test', '22-400', 'PL', '10005001000', NULL, '1', '158')
INSERT INTO audit.store (memberId, store_name, licensed, active, audit_group, store_type, address_line_1, address_line_2, address_line_3, address_line_4, post_code, country, ext_rel_id, big_dl_branch_id, chain_id, retailer_id) VALUES ('444', 'API_TESTS_STORE_2', NULL, '1', 'A', 'Type_API', 'Line1', 'Line 2', 'Line  3', 'Line   4', '22400', 'PL', '10005001001', NULL, '1', NULL)

#AUDIT TABLE
INSERT INTO audit.audit (memberId, audit_name, audit_start, audit_end, ext_rel_id) VALUES ('2', 'API_AUDIT_1', '2016-05-25', '2016-06-30', NULL)