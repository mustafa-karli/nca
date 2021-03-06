CREATE TABLE PROJECT (
  PROJECT_ID          INTEGER      NOT NULL,
  OWNER_ID            INTEGER      NOT NULL,
  CAPTION             VARCHAR(100) NOT NULL,
  STATUS              VARCHAR(20)  DEFAULT 'INITIAL' NOT NULL,
  COUNTRY             CHAR(2)      NOT NULL,
  LOCATION            VARCHAR(50),
  CUSTOMER            VARCHAR(100), 
  CONTRACT_DATE       TIMESTAMP,
  AREA_HANDOVER       DATE,
  DURATION            INTEGER,
  REVIZED_DURATION    INTEGER,
  DURATION_TYPE       CHAR(1),
  REVIZED_COMPLETION  TIMESTAMP,
  EXPECTED_COMPLETION TIMESTAMP,
  END_OF_WARRANTY     TIMESTAMP,
  CONTRACTED_AMOUNT   INTEGER,
  CONTRACT_EXCHANGE   VARCHAR(3),
  EXPECTED_COST       INTEGER,
  ADVANCE_PERCENT     SMALLINT,
  LETTER_OF_ADVANCE   SMALLINT,
  LETTER_OF_WARRANTY  SMALLINT,
  ORGANIZATION_ID     INTEGER,
    CONSTRAINT PROJECT_PK  PRIMARY KEY (PROJECT_ID),
    CONSTRAINT PROJECT_CK1 CHECK (CONTRACT_DATE <= AREA_HANDOVER)
);



CREATE TABLE CATEGORY (
  CATEGORY_ID       INTEGER       NOT NULL,
  PARENT_ID         INTEGER,
  CAPTION           VARCHAR(100)  NOT NULL,
  CAT_INDEX         VARCHAR(2)    NOT NULL,
  DETAILS           VARCHAR(1000),
  UNIT              VARCHAR(20),
  CAT_LEVEL         SMALLINT      NOT NULL,
  TREE_CODE         VARCHAR(20)   NOT NULL,
  ACCOUNT_SCHEMA_ID INTEGER,
  PROJECT_ID        INTEGER,
  MAIN_FLAG         CHAR(1)       NOT NULL,
  UNIT2             VARCHAR(3),
    CONSTRAINT CATEGORY_PK PRIMARY KEY (CATEGORY_ID),
    CONSTRAINT CATEGORIES  FOREIGN KEY (PARENT_ID) REFERENCES CATEGORY (CATEGORY_ID)
);



CREATE TABLE CATEGORY_TEXT (
  CATEGORY_ID       INTEGER       NOT NULL,
  LANGCODE          CHAR(2)       NOT NULL,
  CAPTION           VARCHAR(100)  NOT NULL,
    CONSTRAINT CATEGORY_TEXT_PK PRIMARY KEY (CATEGORY_ID, LANGCODE),
    CONSTRAINT CATEGORY_TEXTS   FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY (CATEGORY_ID) ON DELETE CASCADE
);



CREATE TABLE WORKER (
  WORKER_ID            INTEGER      NOT NULL,
  CAPTION              VARCHAR(100) NOT NULL,
  BUSINESS_PARTNER_ID  INTEGER,  
  PERSON_ID            INTEGER,
  CITIZENSHIP          CHAR(2),
    CONSTRAINT WORKER_PK             PRIMARY KEY (WORKER_ID),
    CONSTRAINT SUBCONTRACTOR_WORKERS FOREIGN KEY (BUSINESS_PARTNER_ID) REFERENCES SUBCONTRACTOR (BUSINESS_PARTNER_ID)
);



CREATE TABLE PROJECT_TEAM (
  PROJECT_ID    INTEGER       NOT NULL,
  TEAM_ID       SMALLINT      NOT NULL,
  CAPTION       VARCHAR(100)  NOT NULL,
    CONSTRAINT PROJECT_TEAM_PK PRIMARY KEY (PROJECT_ID, TEAM_ID),
    CONSTRAINT PROJECT_TEAMS   FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID)
);



CREATE TABLE PROJECT_TEAM_PERSON (
  PROJECT_ID    INTEGER   NOT NULL,
  TEAM_ID       SMALLINT  NOT NULL,
  WORKER_ID     INTEGER   NOT NULL,
  TEAM_LEAD     CHAR(1)   DEFAULT '0' NOT NULL,
    CONSTRAINT PROJECT_TEAM_PERSON_PK PRIMARY KEY (PROJECT_ID, TEAM_ID, WORKER_ID),
    CONSTRAINT PROJECT_TEAM_PERSONNEL FOREIGN KEY (PROJECT_ID, TEAM_ID) REFERENCES PROJECT_TEAM (PROJECT_ID, TEAM_ID) ON DELETE CASCADE,
    CONSTRAINT WORKERS_PROJECT_TEAMS  FOREIGN KEY (WORKER_ID) REFERENCES WORKER (WORKER_ID)
);



CREATE TABLE PROJECT_WBS (
  PROJECT_ID           INTEGER      NOT NULL,
  CATEGORY_ID          INTEGER      NOT NULL,
  ACCOUNT_SCHEMA_ID    INTEGER,
  UNIT                 VARCHAR(3)   NOT NULL,
  METRIC               DECIMAL(8,2) NOT NULL,
  QUANTITY             DECIMAL(8,2) NOT NULL,
  PUP_METRIC           DECIMAL(8,2),
  PUP_QUANTITY         DECIMAL(8,2),
  PLANNED_METRIC       DECIMAL(8,2),
  PLANNED_QUANTITY     DECIMAL(8,2),
  CUSTOMER_WBS_CODE    VARCHAR(20),
  CUSTOMER_WBS_CAPTION VARCHAR(100),
    CONSTRAINT PROJECT_WBS_PK    PRIMARY KEY (PROJECT_ID, CATEGORY_ID),
    CONSTRAINT PROJECT_WBSES     FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID),
    CONSTRAINT CATEGORY_IN_WBSES FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY (CATEGORY_ID)
);



CREATE TABLE PROJECT_TEAM_TEMPLATE (
  PROJECT_ID    INTEGER  NOT NULL,
  TEAM_ID       SMALLINT  NOT NULL,
  CATEGORY_ID   INTEGER  NOT NULL,
    CONSTRAINT PROJECT_TEAM_TEMPLATE_PK   PRIMARY KEY (PROJECT_ID, TEAM_ID, CATEGORY_ID),
    CONSTRAINT PROJECT_TEAM_TEMPLATES     FOREIGN KEY (PROJECT_ID, TEAM_ID) REFERENCES PROJECT_TEAM (PROJECT_ID, TEAM_ID) ON DELETE CASCADE,
    CONSTRAINT PROJECT_WBS_TEAM_TEMPLATES FOREIGN KEY (PROJECT_ID, CATEGORY_ID) REFERENCES PROJECT_WBS (PROJECT_ID, CATEGORY_ID) ON DELETE CASCADE
);



CREATE TABLE PROJECT_WBS_MANHOUR (
  PROJECT_ID       INTEGER      NOT NULL,
  CATEGORY_ID      INTEGER      NOT NULL,
  TEAM_ID          SMALLINT      NOT NULL,
  WORKER_ID        INTEGER      NOT NULL,
  ACTIVITY_DATE    TIMESTAMP    NOT NULL,
  MANHOUR          SMALLINT     NOT NULL,
  OVERTIME         SMALLINT,
  LOCAL_MH         SMALLINT,
  FOREIGN_MH       SMALLINT,
  TR_MH            SMALLINT,
  STATUS           VARCHAR(20) DEFAULT 'INITIAL' NOT NULL,
    CONSTRAINT PROJECT_WBS_MANHOUR_PK         PRIMARY KEY (PROJECT_ID, CATEGORY_ID, TEAM_ID, WORKER_ID, ACTIVITY_DATE),
    CONSTRAINT PROJECT_WBS_MANHOURS           FOREIGN KEY (PROJECT_ID, CATEGORY_ID)        REFERENCES PROJECT_WBS (PROJECT_ID, CATEGORY_ID),
    CONSTRAINT PROJECT_TEAM_PERSONNEL_MANHOUR FOREIGN KEY (PROJECT_ID, TEAM_ID, WORKER_ID) REFERENCES PROJECT_TEAM_PERSON (PROJECT_ID, TEAM_ID, WORKER_ID)
);



CREATE TABLE PROJECT_WBS_QUANTITY (
  PROJECT_ID        INTEGER      NOT NULL,
  CATEGORY_ID       INTEGER      NOT NULL,
  TEAM_ID           SMALLINT     NOT NULL,
  BEGDA             TIMESTAMP    NOT NULL,
  ENDDA             TIMESTAMP    NOT NULL,
  QUANTITY          DECIMAL(8,2) NOT NULL,
  STATUS            VARCHAR(20)  DEFAULT 'INITIAL' NOT NULL,
    CONSTRAINT PROJECT_WBS_QUANTITY_PK     PRIMARY KEY (PROJECT_ID, CATEGORY_ID, BEGDA),
    CONSTRAINT PROJECT_WBS_QUANTITIES      FOREIGN KEY (PROJECT_ID, CATEGORY_ID)  REFERENCES PROJECT_WBS (PROJECT_ID, CATEGORY_ID),
    CONSTRAINT PROJECT_TEAM_WBS_QUANTITIES FOREIGN KEY (PROJECT_ID, TEAM_ID)    REFERENCES PROJECT_TEAM (PROJECT_ID, TEAM_ID),
    CONSTRAINT PROJECT_WBS_QUANTITY_CK1    CHECK ( ENDDA > BEGDA )
);



CREATE TABLE PROJECT_APPROVAL_HISTORY (
  PROJECT_ID       INTEGER     NOT NULL,
  USERNAME         VARCHAR(30) NOT NULL,
  APPROVAL         TIMESTAMP   NOT NULL,
  STATUS           VARCHAR(20) NOT NULL,
    CONSTRAINT PROJECT_APPROVAL_HISTORY_PK   PRIMARY KEY (PROJECT_ID, USERNAME, APPROVAL),
    CONSTRAINT PROJECT_APPROVAL_HISTORIES    FOREIGN KEY (PROJECT_ID) REFERENCES PROJECT (PROJECT_ID),
    CONSTRAINT PROJECT_APPROVAL_USER_HISTORY FOREIGN KEY (USERNAME)   REFERENCES USER_ACCOUNT (USERNAME)
);



CREATE VIEW PROJECT_WBS_QUANTITY_AGGR AS
SELECT PROJECT_ID, CATEGORY_ID, MAX(BEGDA) AS MAX_BEGDA, MAX(ENDDA) AS MAX_ENDDA, SUM(QUANTITY) AS SUM_QUANTITY
  FROM PROJECT_WBS_QUANTITY
 GROUP BY PROJECT_ID, CATEGORY_ID;



CREATE VIEW PROJECT_WBS_MANHOUR_AGGR AS
SELECT PROJECT_ID, CATEGORY_ID, SUM(MANHOUR) AS SUM_MANHOUR
  FROM PROJECT_WBS_MANHOUR
 GROUP BY PROJECT_ID, CATEGORY_ID;



CREATE VIEW PROJECT_WBS_STATUS AS
SELECT P.PROJECT_ID, C.CATEGORY_ID, P.CAPTION AS PRJ_CAPTION, C.PARENT_ID, C.TREE_CODE, C.CAPTION AS CAT_CAPTION, W.UNIT,
       W.METRIC, W.QUANTITY, W.PUP_METRIC, W.PUP_QUANTITY, Q.SUM_QUANTITY, M.SUM_MANHOUR
  FROM ( ( PROJECT AS P
             INNER JOIN (CATEGORY AS C
                            INNER JOIN PROJECT_WBS AS W
                               ON C.CATEGORY_ID = W.CATEGORY_ID)
                ON P.PROJECT_ID = W.PROJECT_ID)
  LEFT JOIN PROJECT_WBS_MANHOUR_AGGR AS M
    ON W.CATEGORY_ID = M.CATEGORY_ID
   AND W.PROJECT_ID = M.PROJECT_ID )
  LEFT JOIN PROJECT_WBS_QUANTITY_AGGR AS Q
    ON W.CATEGORY_ID = Q.CATEGORY_ID
   AND W.PROJECT_ID = Q.PROJECT_ID;



CREATE VIEW PROJECT_PROGRESS AS
SELECT W.PROJECT_ID, W.CATEGORY_ID, W.UNIT,
       W.METRIC, W.QUANTITY, W.METRIC * W.QUANTITY AS WORKFORCE,
       W.PUP_METRIC, W.PUP_QUANTITY, W.PUP_METRIC * W.PUP_QUANTITY AS PUP_WORKFORCE,
       W.PLANNED_METRIC, W.PLANNED_QUANTITY, W.PLANNED_METRIC * W.PLANNED_QUANTITY AS PLANNED_WORKFORCE, 
       A.SUM_QUANTITY, B.SUM_MANHOUR
  FROM (PROJECT_WBS AS W
               LEFT JOIN PROJECT_WBS_QUANTITY_AGGR AS A
                 ON A.PROJECT_ID  = W.PROJECT_ID
                AND A.CATEGORY_ID = W.CATEGORY_ID)
  LEFT JOIN PROJECT_WBS_MANHOUR_AGGR AS B
    ON B.PROJECT_ID  = W.PROJECT_ID
   AND B.CATEGORY_ID = W.CATEGORY_ID;



CREATE VIEW PROJECT_WBS_TEAM_QTY_AGGR AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, MAX(BEGDA) AS MAX_BEGDA, MAX(ENDDA) AS MAX_ENDDA, SUM(QUANTITY) AS SUM_QUANTITY
  FROM PROJECT_WBS_QUANTITY
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID;

CREATE VIEW PROJECT_WBS_TEAM_MH_AGGR AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, SUM(MANHOUR) AS SUM_MANHOUR
  FROM PROJECT_WBS_MANHOUR
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID;

CREATE VIEW PROJECT_WBS_TEAM_STATUS AS
SELECT P.PROJECT_ID, C.CATEGORY_ID, T.TEAM_ID, P.CAPTION AS PRJ_CAPTION, C.PARENT_ID, C.TREE_CODE, C.CAPTION AS CAT_CAPTION, T.CAPTION AS TEAM_CAPTION,
       W.UNIT, W.METRIC, W.QUANTITY, A.SUM_QUANTITY, B.SUM_MANHOUR, Q.BEGDA, Q.ENDDA, Q.QUANTITY AS LAST_QUANTITY
  FROM ( (CATEGORY AS C
             INNER JOIN (PROJECT AS P
                           INNER JOIN (PROJECT_WBS AS W
                                             INNER JOIN (PROJECT_TEAM AS T
                                                                 INNER JOIN PROJECT_WBS_TEAM_MH_AGGR AS B
                                                                    ON T.TEAM_ID = B.TEAM_ID)
                                                ON W.PROJECT_ID  = B.PROJECT_ID
                                               AND W.CATEGORY_ID = B.CATEGORY_ID)
                              ON P.PROJECT_ID = W.PROJECT_ID
                             AND P.PROJECT_ID = T.PROJECT_ID)
                ON C.CATEGORY_ID = W.CATEGORY_ID)
              LEFT JOIN PROJECT_WBS_TEAM_QTY_AGGR AS A
                ON B.TEAM_ID     = A.TEAM_ID
               AND B.CATEGORY_ID = A.CATEGORY_ID
               AND B.PROJECT_ID  = A.PROJECT_ID)
              LEFT JOIN PROJECT_WBS_QUANTITY AS Q
                ON Q.BEGDA       = A.MAX_BEGDA
               AND Q.TEAM_ID     = A.TEAM_ID
               AND Q.CATEGORY_ID = A.CATEGORY_ID
               AND Q.PROJECT_ID  = A.PROJECT_ID;



CREATE VIEW PROJECT_WBS_TEAM_SUMMARY AS
SELECT P.PROJECT_ID, C.CATEGORY_ID, T.TEAM_ID, P.CAPTION AS PRJ_CAPTION, C.PARENT_ID, C.TREE_CODE, C.CAPTION AS CAT_CAPTION, T.CAPTION AS TEAM_CAPTION, W.UNIT, W.METRIC, W.QUANTITY,
       A.SUM_QUANTITY, B.SUM_MANHOUR
  FROM ( ( (CATEGORY AS C
               INNER JOIN (PROJECT AS P
                             INNER JOIN PROJECT_WBS AS W
                                ON P.PROJECT_ID = W.PROJECT_ID)
                  ON C.CATEGORY_ID = W.CATEGORY_ID)
               INNER JOIN PROJECT_TEAM AS T
                  ON P.PROJECT_ID = T.PROJECT_ID)
               INNER JOIN PROJECT_WBS_TEAM_MH_AGGR AS B
                  ON T.TEAM_ID     = B.TEAM_ID
                 AND W.CATEGORY_ID = B.CATEGORY_ID
                 AND W.PROJECT_ID  = B.PROJECT_ID)
                LEFT JOIN PROJECT_WBS_TEAM_QTY_AGGR AS A
                  ON B.TEAM_ID     = A.TEAM_ID
                 AND B.CATEGORY_ID = A.CATEGORY_ID
                 AND B.PROJECT_ID  = A.PROJECT_ID;



CREATE VIEW PROJECT_WBS_QTY_APPROVED AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, SUM(QUANTITY) AS APPROVED_QUANTITY
  FROM PROJECT_WBS_QUANTITY
 WHERE STATUS = 'APPROVE_QUANTITY'
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID;



CREATE VIEW PROJECT_WBS_QTY_INITIAL AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, SUM(QUANTITY) AS QUANTITY_TO_APPROVE
  FROM PROJECT_WBS_QUANTITY
 WHERE STATUS = 'INITIAL'
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID;



CREATE VIEW PROJECT_WBS_QTY_TO_APR AS
SELECT P.PROJECT_ID, C.CATEGORY_ID, T.TEAM_ID, P.CAPTION AS PROJECT_CAPTION, C.TREE_CODE, C.CAPTION AS CATEGORY_CAPTION, T.CAPTION AS TEAM_CAPTION, W.UNIT, W.METRIC, W.QUANTITY, A.APPROVED_QUANTITY, B.BEGDA, B.ENDDA, B.QUANTITY AS QUANTITY_TO_APPROVE
  FROM ( ( (CATEGORY AS C
               INNER JOIN (PROJECT AS P
                             INNER JOIN PROJECT_WBS AS W
                                ON P.PROJECT_ID = W.PROJECT_ID)
                  ON C.CATEGORY_ID = W.CATEGORY_ID)
               INNER JOIN PROJECT_TEAM AS T
                  ON P.PROJECT_ID = T.PROJECT_ID)
               INNER JOIN PROJECT_WBS_QUANTITY AS B
                  ON B.TEAM_ID     = T.TEAM_ID
                 AND B.CATEGORY_ID = W.CATEGORY_ID
                 AND B.PROJECT_ID  = W.PROJECT_ID
                 AND B.STATUS      = 'INITIAL')
                LEFT JOIN PROJECT_WBS_QTY_APPROVED AS A
                  ON B.TEAM_ID     = A.TEAM_ID
                 AND B.CATEGORY_ID = A.CATEGORY_ID
                 AND B.PROJECT_ID  = A.PROJECT_ID;



CREATE VIEW PROJECT_WBS_MH_APPROVED AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, SUM(MANHOUR) AS APPROVED_MANHOUR
  FROM PROJECT_WBS_MANHOUR
 WHERE STATUS = 'APPROVE_MANHOUR'
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID;



CREATE VIEW PROJECT_WBS_MH_INITIAL AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, ACTIVITY_DATE, SUM(MANHOUR) AS MANHOUR_TO_APPROVE
  FROM PROJECT_WBS_MANHOUR
 WHERE STATUS = 'INITIAL'
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID, ACTIVITY_DATE;



CREATE VIEW PROJECT_WBS_MH_TO_APR AS
SELECT P.PROJECT_ID, C.CATEGORY_ID, T.TEAM_ID, P.CAPTION AS PROJECT_CAPTION, C.TREE_CODE, C.CAPTION AS CATEGORY_CAPTION, T.CAPTION AS TEAM_CAPTION, W.UNIT, W.METRIC, W.QUANTITY, A.APPROVED_MANHOUR, B.ACTIVITY_DATE, B.MANHOUR_TO_APPROVE
  FROM ( ( (CATEGORY AS C
               INNER JOIN (PROJECT AS P
                             INNER JOIN PROJECT_WBS AS W
                                ON P.PROJECT_ID = W.PROJECT_ID)
                  ON C.CATEGORY_ID = W.CATEGORY_ID)
               INNER JOIN PROJECT_TEAM AS T
                  ON P.PROJECT_ID = T.PROJECT_ID)
               INNER JOIN PROJECT_WBS_MH_INITIAL AS B
                  ON T.TEAM_ID     = B.TEAM_ID
                 AND W.CATEGORY_ID = B.CATEGORY_ID
                 AND W.PROJECT_ID  = B.PROJECT_ID)
                LEFT JOIN PROJECT_WBS_MH_APPROVED AS A
                  ON B.TEAM_ID     = A.TEAM_ID
                 AND B.CATEGORY_ID = A.CATEGORY_ID
                 AND B.PROJECT_ID  = A.PROJECT_ID;


CREATE VIEW PROJECT_SUBCONTRACTOR_SUMMARY AS
SELECT T.PROJECT_ID, O.BUSINESS_PARTNER_ID, C.CATEGORY_ID, C.TREE_CODE, C.CAPTION, W.CUSTOMER_WBS_CODE, W.CUSTOMER_WBS_CAPTION, W.ACCOUNT_SCHEMA_ID, W.UNIT, W.METRIC, W.QUANTITY, W.PUP_METRIC, W.PUP_QUANTITY, W.PLANNED_METRIC, W.PLANNED_QUANTITY,
       SUM(Q.APPROVED_QUANTITY) AS SUM_QUANTITY, SUM(M.APPROVED_MANHOUR) AS SUM_MANHOUR
  FROM CATEGORY C, PROJECT_WBS W, PROJECT_TEAM T, PROJECT_WBS_MH_APPROVED M, PROJECT_WBS_QTY_APPROVED Q, PROJECT_TEAM_PERSON P, WORKER O
 WHERE W.PROJECT_ID      = T.PROJECT_ID 
   AND W.CATEGORY_ID     = C.CATEGORY_ID 
   AND Q.PROJECT_ID      = W.PROJECT_ID 
   AND Q.CATEGORY_ID     = W.CATEGORY_ID 
   AND Q.TEAM_ID         = T.TEAM_ID
   AND M.PROJECT_ID      = W.PROJECT_ID 
   AND M.CATEGORY_ID     = W.CATEGORY_ID 
   AND M.TEAM_ID         = T.TEAM_ID
   AND P.PROJECT_ID      = T.PROJECT_ID
   AND P.TEAM_ID         = T.TEAM_ID
   AND P.TEAM_LEAD       = '1'
   AND O.WORKER_ID       = P.WORKER_ID
 GROUP BY T.PROJECT_ID, O.BUSINESS_PARTNER_ID, C.CATEGORY_ID, C.TREE_CODE, C.CAPTION, W.CUSTOMER_WBS_CODE, W.CUSTOMER_WBS_CAPTION, W.ACCOUNT_SCHEMA_ID, W.UNIT, W.METRIC, W.QUANTITY, W.PUP_METRIC, W.PUP_QUANTITY, W.PLANNED_METRIC, W.PLANNED_QUANTITY;
