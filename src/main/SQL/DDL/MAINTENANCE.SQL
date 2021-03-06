CREATE TABLE EQUIPMENT (
  MATERIAL_ID          INTEGER       NOT NULL,
  SERIAL_NO            VARCHAR(20)   NOT NULL,
  MODEL_YEAR           SMALLINT      NOT NULL,
  USAGE_TYPE           CHAR(1)       NOT NULL,
    CONSTRAINT EQUIPMENT_PK PRIMARY KEY (MATERIAL_ID, SERIAL_NO),
    CONSTRAINT EQUIPMENTS   FOREIGN KEY(MATERIAL_ID) REFERENCES MATERIAL(MATERIAL_ID)
);



CREATE TABLE EQUIPMENT_ASSIGNMENT (
  PERSON_ID            INTEGER       NOT NULL,
  MATERIAL_ID          INTEGER       NOT NULL,
  SERIAL_NO            VARCHAR(20)   NOT NULL,
  BEGDA                TIMESTAMP     NOT NULL,
  ENDDA                TIMESTAMP     NOT NULL,
  ASSIGNMENT_TYPE      CHAR(1),
    CONSTRAINT EQUIPMENT_ASSIGNMENT_PK       PRIMARY KEY (PERSON_ID, MATERIAL_ID, SERIAL_NO, BEGDA),
    CONSTRAINT EQUIPMENTS_PERSON_ASSIGNMENTS FOREIGN KEY(MATERIAL_ID, SERIAL_NO) REFERENCES EQUIPMENT(MATERIAL_ID, SERIAL_NO) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT PERSONS_EQUIPMENT_ASSIGNMENTS FOREIGN KEY(PERSON_ID)              REFERENCES PERSON(PERSON_ID)
);

CREATE INDEX EQUIPMENT_ASSIGNMENT_IND1 ON EQUIPMENT_ASSIGNMENT (MATERIAL_ID, SERIAL_NO);



CREATE TABLE EQUIPMENT_ATTRIBUTE (
  MATERIAL_ID          INTEGER      NOT NULL,
  SERIAL_NO            VARCHAR(20)  NOT NULL,
  MAG_ID               VARCHAR(8)   NOT NULL,
  VALUE                VARCHAR(8)   NOT NULL,
    CONSTRAINT EQUIPMENT_ATTRIBUTE_PK        PRIMARY KEY (MATERIAL_ID, SERIAL_NO, MAG_ID),
    CONSTRAINT EQUIPMENT_ATTRIBUTES          FOREIGN KEY (MATERIAL_ID, SERIAL_NO) REFERENCES EQUIPMENT(MATERIAL_ID, SERIAL_NO) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT MATERIAL_ATTRIBUTE_EQUIPMENTS FOREIGN KEY (MAG_ID) REFERENCES MATERIAL_ATTRIBUTE_GROUP(MAG_ID)
);

CREATE INDEX EQUIPMENT_ATTRIBUTE_IND1 ON EQUIPMENT_ATTRIBUTE (MAG_ID);



CREATE TABLE EQUIPMENT_LOCATION (
  MATERIAL_ID          INTEGER     NOT NULL,
  SERIAL_NO            VARCHAR(20) NOT NULL,
  ORGANIZATION_ID      INTEGER     NOT NULL,
  BEGDA                TIMESTAMP   NOT NULL,
  ENDDA                TIMESTAMP   NOT NULL,
    CONSTRAINT EQUIPMENT_LOCATION_PK   PRIMARY KEY (MATERIAL_ID, SERIAL_NO, ORGANIZATION_ID),
    CONSTRAINT ORGANIZATIONS_EQUIPMENT FOREIGN KEY (ORGANIZATION_ID)        REFERENCES ORGANIZATION (ORGANIZATION_ID),
    CONSTRAINT EQUIPMENT_LOCATIONS     FOREIGN KEY (MATERIAL_ID, SERIAL_NO) REFERENCES EQUIPMENT (MATERIAL_ID, SERIAL_NO) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX EQUIPMENT_LOCATION_IND1 ON EQUIPMENT_LOCATION (ORGANIZATION_ID);



CREATE TABLE FINAL_EQUIPMENT_PART (
  MATERIAL_ID          INTEGER       NOT NULL,
  MATERIAL_TYPE_ID     VARCHAR(30)   NOT NULL,
  QUANTITY             INTEGER       NOT NULL,
  UNIT                 VARCHAR(3)    NOT NULL,
    CONSTRAINT FINAL_EQUIPMENT_PART_PK     PRIMARY KEY (MATERIAL_ID, MATERIAL_TYPE_ID),
    CONSTRAINT MATERIAL_TYPE_IN_EQUIPMENTS FOREIGN KEY(MATERIAL_TYPE_ID) REFERENCES MATERIAL_TYPE (MATERIAL_TYPE_ID),
    CONSTRAINT EQUIPMENT_COMPONENTS        FOREIGN KEY(MATERIAL_ID)      REFERENCES MATERIAL (MATERIAL_ID)
);

CREATE INDEX FINAL_EQUIPMENT_PART_IND1 ON FINAL_EQUIPMENT_PART (MATERIAL_ID);



CREATE TABLE SERVICE_TYPE (
  SERVICE_TYPE_ID      INTEGER       NOT NULL,
  OWNER_ID             INTEGER       NOT NULL,
  BUSINESS_SERVICE_ID  VARCHAR(20)   NOT NULL,
  CAPTION              VARCHAR(80)   NOT NULL,
    CONSTRAINT SERVICE_TYPE_PK PRIMARY KEY (SERVICE_TYPE_ID),
    CONSTRAINT SERVICE_TYPES   FOREIGN KEY(BUSINESS_SERVICE_ID) REFERENCES BUSINESS_SERVICE (BUSINESS_SERVICE_ID)
);

CREATE INDEX SERVICE_TYPE_IND1 ON SERVICE_TYPE (BUSINESS_SERVICE_ID);
CREATE INDEX SERVICE_TYPE_IND2 ON SERVICE_TYPE (OWNER_ID);



CREATE TABLE SERVICE_TYPE_STEP (
  SERVICE_TYPE_ID      INTEGER       NOT NULL,
  MATERIAL_TYPE_ID     VARCHAR(30)   NOT NULL,
  QUANTITY             INTEGER       NOT NULL,
  UNIT                 VARCHAR(3)    NOT NULL,
  DURATION             INTEGER       NOT NULL,
    CONSTRAINT SERVICE_TYPE_STEP_PK        PRIMARY KEY (SERVICE_TYPE_ID, MATERIAL_TYPE_ID),
    CONSTRAINT SERVICE_TYPE_STEPS          FOREIGN KEY(SERVICE_TYPE_ID)  REFERENCES SERVICE_TYPE(SERVICE_TYPE_ID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT MATERIAL_TYPE_SERVICE_TYPES FOREIGN KEY(MATERIAL_TYPE_ID) REFERENCES MATERIAL_TYPE(MATERIAL_TYPE_ID)
);

CREATE INDEX SERVICE_TYPE_STEP_IND1 ON SERVICE_TYPE_STEP (MATERIAL_TYPE_ID);



CREATE TABLE SERVICE_CHARGE (
  SERVICE_TYPE_ID      INTEGER         NOT NULL,
  BEGDA                TIMESTAMP       NOT NULL,
  ENDDA                TIMESTAMP       NOT NULL,
  CHARGE               DECIMAL(12,2)   NOT NULL,
    CONSTRAINT SERVICE_CHARGE_PK PRIMARY KEY (SERVICE_TYPE_ID, BEGDA),
    CONSTRAINT SERVICE_CHARGES   FOREIGN KEY(SERVICE_TYPE_ID) REFERENCES SERVICE_TYPE(SERVICE_TYPE_ID)
);



CREATE TABLE MX_COUNTER_TYPE (
  COUNTER_TYPE         VARCHAR(8)   NOT NULL,
  CAPTION              VARCHAR(30)   NOT NULL,
    CONSTRAINT MX_COUNTER_TYPE_PK PRIMARY KEY (COUNTER_TYPE)
);



CREATE TABLE MX_COUNTER_INTERVAL (
  SERVICE_TYPE_ID      INTEGER       NOT NULL,
  MATERIAL_TYPE_ID     VARCHAR(30)   NOT NULL,
  COUNTER_TYPE         VARCHAR(8)    NOT NULL,
  INTERVAL             INTEGER       NOT NULL,
    CONSTRAINT MX_COUNTER_INTERVAL_PK         PRIMARY KEY (SERVICE_TYPE_ID, MATERIAL_TYPE_ID, COUNTER_TYPE),
    CONSTRAINT SERVICE_TYPE_COUNTER_INTERVALS FOREIGN KEY (SERVICE_TYPE_ID, MATERIAL_TYPE_ID) REFERENCES SERVICE_TYPE_STEP(SERVICE_TYPE_ID, MATERIAL_TYPE_ID),
    CONSTRAINT MX_COUNTER_TYPE_INTERVALS      FOREIGN KEY(COUNTER_TYPE)                       REFERENCES MX_COUNTER_TYPE(COUNTER_TYPE) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX MX_COUNTER_INTERVAL_IND1 ON MX_COUNTER_INTERVAL (COUNTER_TYPE);



CREATE TABLE MAINTENANCE (
  MATERIAL_ID          INTEGER         NOT NULL,
  SERIAL_NO            VARCHAR(20)     NOT NULL,
  SERVICE_DATE         TIMESTAMP       NOT NULL,
  ORGANIZATION_ID      INTEGER         NOT NULL,
  LABOR                DECIMAL(12,2)   NOT NULL,
  CHARGE               DECIMAL(12,2)   NOT NULL,
    CONSTRAINT MAINTENANCE_PK            PRIMARY KEY (MATERIAL_ID, SERIAL_NO, SERVICE_DATE),
    CONSTRAINT EQUIPMENT_MAINTENANCES    FOREIGN KEY (MATERIAL_ID, SERIAL_NO) REFERENCES EQUIPMENT (MATERIAL_ID, SERIAL_NO),
    CONSTRAINT ORGANIZATION_MAINTENANCES FOREIGN KEY (ORGANIZATION_ID)        REFERENCES ORGANIZATION (ORGANIZATION_ID)
);

CREATE INDEX MAINTENANCE_IND1 ON MAINTENANCE (ORGANIZATION_ID);



CREATE TABLE MAINTENANCE_STEP (
  MATERIAL_ID          INTEGER       NOT NULL,
  SERIAL_NO            VARCHAR(20)   NOT NULL,
  SERVICE_DATE         TIMESTAMP     NOT NULL,
  MATERIAL_TYPE_ID     VARCHAR(30)   NOT NULL,
  QUANTITY             INTEGER       NOT NULL,
  UNIT                 VARCHAR(3)    NOT NULL,
  ACTION_TIME          TIMESTAMP     NOT NULL,
  COST                 DECIMAL(12,2) NOT NULL,
    CONSTRAINT MAINTENANCE_STEP_PK    PRIMARY KEY (MATERIAL_ID, SERIAL_NO, SERVICE_DATE, MATERIAL_TYPE_ID),
    CONSTRAINT MAINTENANCE_STEPS      FOREIGN KEY(SERVICE_DATE, SERIAL_NO, MATERIAL_ID) REFERENCES MAINTENANCE   (SERVICE_DATE, SERIAL_NO, MATERIAL_ID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT MATERIAL_TYPE_MX_STEPS FOREIGN KEY(MATERIAL_TYPE_ID)                     REFERENCES MATERIAL_TYPE (MATERIAL_TYPE_ID)
);

CREATE INDEX MAINTENANCE_STEP_IND1 ON MAINTENANCE_STEP (MATERIAL_TYPE_ID);



CREATE TABLE MAINTENANCE_COUNTER (
  MATERIAL_ID          INTEGER       NOT NULL,
  SERIAL_NO            VARCHAR(20)   NOT NULL,
  SERVICE_DATE         TIMESTAMP     NOT NULL,
  COUNTER_TYPE         VARCHAR(8)    NOT NULL,
  VALUE                INTEGER       NOT NULL,
    CONSTRAINT MAINTENANCE_COUNTER_PK    PRIMARY KEY (MATERIAL_ID, SERIAL_NO, SERVICE_DATE, COUNTER_TYPE),
    CONSTRAINT MAINTENANCE_COUNTERS      FOREIGN KEY (MATERIAL_ID, SERIAL_NO, SERVICE_DATE) REFERENCES MAINTENANCE (MATERIAL_ID, SERIAL_NO, SERVICE_DATE) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT COUNTER_TYPE_MAINTENANCES FOREIGN KEY (COUNTER_TYPE)                         REFERENCES MX_COUNTER_TYPE (COUNTER_TYPE)
);

CREATE INDEX MAINTENANCE_COUNTER_IND1 ON MAINTENANCE_COUNTER (COUNTER_TYPE);
