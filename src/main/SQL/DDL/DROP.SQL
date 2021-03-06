DROP VIEW V_PRODUCTION_COMMITMENT_SALES;
DROP VIEW V_PRODUCTION_COMMITMENT;
DROP VIEW V_RFP_ITEM_PROPOSAL;
DROP VIEW PROJECT_SUBCONTRACTOR_SUMMARY;
DROP VIEW PROJECT_WBS_MH_TO_APR;
DROP VIEW PROJECT_WBS_MH_INITIAL;
DROP VIEW PROJECT_WBS_MH_APPROVED;
DROP VIEW PROJECT_WBS_QTY_TO_APR;
DROP VIEW PROJECT_WBS_QTY_INITIAL;
DROP VIEW PROJECT_WBS_QTY_APPROVED;
DROP VIEW PROJECT_WBS_TEAM_SUMMARY;
DROP VIEW PROJECT_WBS_TEAM_STATUS;
DROP VIEW PROJECT_WBS_TEAM_MH_AGGR;
DROP VIEW PROJECT_WBS_TEAM_QTY_AGGR;
DROP VIEW PROJECT_PROGRESS;
DROP VIEW PROJECT_WBS_STATUS;
DROP VIEW PROJECT_WBS_MANHOUR_AGGR;
DROP VIEW PROJECT_WBS_QUANTITY_AGGR;
DROP VIEW USER_MENU_PERMISSION;
DROP TABLE PRODUCT_BY_DEFINE;
DROP TABLE PRODUCT_TYPE_BY_DEFINE;
DROP TABLE MATERIAL_REQUEST_ITEM_ATTR;
DROP TABLE MATERIAL_REQUEST_ITEM;
DROP TABLE MATERIAL_REQUEST;
DROP TABLE SHIPMENT_LINE;
DROP TABLE SHIPMENT;
DROP TABLE SALES_ACCOUNT_BALANCE;
DROP TABLE SALES_CAMPAIGN_ITEM;
DROP TABLE SALES_CAMPAIGN;
DROP TABLE SALES_ORDER_COMMITMENT;
DROP TABLE PRODUCT_PRICE_COMMITMENT_ITEM;
DROP TABLE PRODUCT_PRICE_COMMITMENT;
DROP TABLE SALES_ORDER_TAX;
DROP TABLE SALES_ORDER_ITEM_ATTR;
DROP TABLE SALES_ORDER_ITEM;
DROP TABLE SALES_DELIVERY_ADDRESS;
DROP TABLE SALES_ORDER;
DROP TABLE MATERIAL_SALE_TAX;
DROP TABLE MATERIAL_SALE_PRICE;
DROP TABLE PURCHASE_DELIVERY_LINE;
DROP TABLE PURCHASE_DELIVERY_ADDRESS;
DROP TABLE PURCHASE_DELIVERY;
DROP TABLE PURCHASE_REASON;
DROP TABLE PURCHASE_ORDER_TAX;
DROP TABLE PURCHASE_ORDER_ITEM_ATTR;
DROP TABLE PURCHASE_ORDER_ITEM;
DROP TABLE PURCHASE_ORDER;
DROP TABLE PROPOSAL_TO_RFP_DIALOG;
DROP TABLE PROPOSAL_TO_RFP_ITEM;
DROP TABLE PROPOSAL_TO_RFP;
DROP TABLE RFP_PUBLISHMENT;
DROP TABLE REQUEST_FOR_PROPOSAL_ITEM;
DROP TABLE REQUEST_FOR_PROPOSAL;
DROP TABLE PROJECT_APPROVAL_HISTORY;
DROP TABLE PROJECT_WBS_QUANTITY;
DROP TABLE PROJECT_WBS_MANHOUR;
DROP TABLE PROJECT_TEAM_TEMPLATE;
DROP TABLE PROJECT_WBS;
DROP TABLE PROJECT_TEAM_PERSON;
DROP TABLE PROJECT_TEAM;
DROP TABLE WORKER;
DROP TABLE CATEGORY_TEXT;
DROP TABLE CATEGORY;
DROP TABLE PROJECT;
DROP TABLE PRODUCTION_LINE_REASON;
DROP TABLE PRODUCTION_JOB_RESOURCE;
DROP TABLE PRODUCTION_JOB;
DROP TABLE PRODUCTION_ORDER_ITEM;
DROP TABLE PRODUCTION_ORDER;
DROP TABLE BOM_OPERATION_STEP_RESOURCE;
DROP TABLE BOM_OPERATION_STEP;
DROP TABLE BOM_OPERATION;
DROP TABLE PRODUCTION_RESOURCE;
DROP TABLE MAINTENANCE_COUNTER;
DROP TABLE MAINTENANCE_STEP;
DROP TABLE MAINTENANCE;
DROP TABLE MX_COUNTER_INTERVAL;
DROP TABLE MX_COUNTER_TYPE;
DROP TABLE SERVICE_CHARGE;
DROP TABLE SERVICE_TYPE_STEP;
DROP TABLE SERVICE_TYPE;
DROP TABLE FINAL_EQUIPMENT_PART;
DROP TABLE EQUIPMENT_LOCATION;
DROP TABLE EQUIPMENT_ATTRIBUTE;
DROP TABLE EQUIPMENT_ASSIGNMENT;
DROP TABLE EQUIPMENT;
DROP TABLE MATERIAL_RESERVATION;
DROP TABLE MATERIAL_PACK_UNIT;
DROP TABLE MATERIAL_MOVEMENT;
DROP TABLE MATERIAL_INVENTORY_HISTORY;
DROP TABLE LOT_INVENTORY;
DROP TABLE MATERIAL_INVENTORY;
DROP TABLE MATERIAL_GROUP_ASSIGNMENT;
DROP TABLE MATERIAL_ATTRIBUTE;
DROP TABLE MATERIAL;
DROP TABLE MATERIAL_GROUP;
DROP TABLE MATERIAL_TYPE_ATTRIBUTE;
DROP TABLE MATERIAL_ATTRIBUTE_OPTION;
DROP TABLE MATERIAL_ATTRIBUTE_GROUP;
DROP TABLE MATERIAL_TYPE;
DROP TABLE MANUFACTURER;
DROP TABLE POSITION_ASSIGNMENT;
DROP TABLE POSITION_QUALIFICATION;
DROP TABLE POSITION;
DROP TABLE POSITION_TYPE;
DROP TABLE PERSON_QUALIFICATION;
DROP TABLE EMPLOYEE;
DROP TABLE ORGANIZATION;
DROP TABLE USER_ACCOUNT_OWNER;
DROP TABLE QUALIFICATION;
DROP TABLE CREDIT_CARD;
DROP TABLE PERSON;
DROP TABLE TICKET_EVENT;
DROP TABLE SUPPORT_TICKET;
DROP TABLE SUPPORT_AGENT;
DROP TABLE SUPPORT_GROUP;
DROP TABLE SUPPORT_AREA;
DROP TABLE SERVICE_LEVEL_AGREEMENT;
DROP TABLE CONTENT_RELATION;
DROP TABLE CONTENT_DATA;
DROP TABLE VENDOR_BUSINESS;
DROP TABLE PARTNER_CONVERSATION;
DROP TABLE PARTNER_CERTIFICATION;
DROP TABLE PARTNER_CERTIFICATION_TYPE;
DROP TABLE APPLICATION_CONFIG;
DROP TABLE BUSINESS_CONTRACT;
DROP TABLE VENDOR;
DROP TABLE SUBCONTRACTOR;
DROP TABLE CUSTOMER;
DROP TABLE BUSINESS_OWNER;
DROP TABLE PARTNER_CREDIT;
DROP TABLE PARTNER_ADDRESS;
DROP TABLE BUSINESS_PARTNER;
DROP TABLE BUSINESS_SERVICE_PAGE;
DROP TABLE BUSINESS_SERVICE;
DROP TABLE ACCOUNT_TRANSACTION_ITEM;
DROP TABLE ACCOUNT_TRANSACTION;
DROP TABLE ACCOUNT_TX_TEMPLATE_ITEM;
DROP TABLE ACCOUNT_TRANSACTION_TEMPLATE;
DROP TABLE ACCOUNT_BALANCE;
DROP TABLE BANK_ACCOUNT;
DROP TABLE ACCOUNT_SCHEMA;
DROP TABLE ACCOUNT_MASTER;
DROP TABLE BANK_BRANCH;
DROP TABLE EXCHANGE_RATE;
DROP TABLE BANK;
DROP TABLE TAX_TYPE;
DROP TABLE TREE_DATA;
DROP TABLE TABLE_VIEW_SCENARIO_ITEM;
DROP TABLE TABLE_VIEW_SCENARIO;
DROP TABLE NOTIFICATION_RECIPIENT;
DROP TABLE USER_NOTIFICATION;
DROP TABLE NOTIFICATION_TYPE;
DROP TABLE MASTER_DETAIL_RELATION;
DROP TABLE TABLE_ACTION;
DROP TABLE TABLE_CONTENT_TYPE;
DROP TABLE TABLE_FIELD_FACE;
DROP TABLE TABLE_CONTROLLER_STATIC;
DROP TABLE UNIT_CONVERSION;
DROP TABLE COUNTRY;
DROP TABLE OBJECT_AUTHORIZATION;
DROP TABLE AUTHORITY_OBJECT_ACTION;
DROP TABLE AUTHORITY_OBJECT;
DROP TABLE CAPTION_TRANSLATION;
DROP TABLE LANGUAGE(
DROP TABLE DOMAIN_VALUE(
DROP TABLE DOMAIN_NAME;
DROP TABLE USER_AUTHORIZATION;
DROP TABLE SCREEN_PAGE;
DROP TABLE MAIN_MENU;
DROP TABLE AUTHORITY_GROUP;
DROP TABLE USER_FAVORITE;
DROP TABLE USER_ACCOUNT;

DROP SEQUENCE BUSINESS_PARTNER_ID_SEQ ;
DROP SEQUENCE PARTNER_CERTIFICATION_ID_SEQ ;
DROP SEQUENCE ACCOUNT_SCHEMA_ID_SEQ ;
DROP SEQUENCE ACCOUNT_TRANSACTION_ID_SEQ ;
DROP SEQUENCE ACCOUNT_TX_TEMPLATE_ID_SEQ ;
DROP SEQUENCE SUPPORT_AREA_ID_SEQ ;
DROP SEQUENCE SUPPORT_GROUP_ID_SEQ ;
DROP SEQUENCE SUPPORT_TICKET_ID_SEQ ;
DROP SEQUENCE SERVICE_TYPE_ID_SEQ ;
DROP SEQUENCE MATERIAL_ID_SEQ ;
DROP SEQUENCE MATERIAL_GROUP_ID_SEQ ;
DROP SEQUENCE MATERIAL_MOVEMENT_ID_SEQ ;
DROP SEQUENCE MATERIAL_RESERVATION_ID_SEQ ;
DROP SEQUENCE ORGANIZATION_ID_SEQ ;
DROP SEQUENCE PERSON_ID_SEQ ;
DROP SEQUENCE QUALIFICATION_ID_SEQ ;
DROP SEQUENCE BOM_OPERATION_ID_SEQ ;
DROP SEQUENCE PRODUCTION_JOB_ID_SEQ ;
DROP SEQUENCE PRODUCTION_ORDER_ID_SEQ ;
DROP SEQUENCE CATEGORY_ID_SEQ ;
DROP SEQUENCE PROJECT_ID_SEQ ;
DROP SEQUENCE SUBCONTRACTOR_ID_SEQ ;
DROP SEQUENCE WORKER_ID_SEQ ;
DROP SEQUENCE PROPOSAL_ID_SEQ ;
DROP SEQUENCE PURCHASE_DELIVERY_ID_SEQ ;
DROP SEQUENCE PURCHASE_ORDER_ID_SEQ ;
DROP SEQUENCE RFP_ID_SEQ ;
DROP SEQUENCE MATERIAL_REQUEST_ID_SEQ ;
DROP SEQUENCE SALES_CAMPAIGN_ID_SEQ ;
DROP SEQUENCE SALES_ORDER_ID_SEQ ;
DROP SEQUENCE CONTENT_DATA_ID_SEQ ;
DROP SEQUENCE NOTIFICATION_TYPE_ID_SEQ ;
DROP SEQUENCE USER_NOTIFICATION_ID_SEQ ;
DROP SEQUENCE TREE_DATA_SEQ ;
DROP SEQUENCE SHIPMENT_ID_SEQ ;
