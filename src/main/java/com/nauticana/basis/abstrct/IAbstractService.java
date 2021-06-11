package com.nauticana.basis.abstrct;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

// Service interface designed for database table modeled by ModelBean class type having primary key as ModelIdclas type

public interface IAbstractService <ModelBean, ModelId> {

  // CUD operations

  ModelBean       create(ModelBean entity) throws Exception;
  void            save(ModelBean entity) throws Exception;
  void            remove(ModelId id) throws Exception;


  // R operations

  ModelBean       findById(ModelId id);
  List<ModelBean> findByIds(List<String> ids);
  List<ModelBean> findByIdArray(ModelId[] ids);
  List<ModelBean> findAll();
  List<ModelBean> findAll(int client);
  List<ModelBean> search(ArrayList<String> fields, ArrayList<String> filters, ArrayList<Integer> types) throws ParseException;


  // Model objects

  ModelBean        newEntityWithParentId(String parentKey);
  ModelBean        newEntityWithId(String strId);


  // HTTP calls send parameter values as strings. To achieve abstraction, we need to convert the string parameter to/from the ID of an entity.

  ModelId          strToId(String id);
  String           idToStr(ModelId id);
  String           idAsStr(ModelBean entity);


  // This method allows UI designers to create back navigation to the parent of an entity

  String           parentLink(ModelBean entity);

  Class<ModelBean> modelClass();


  // Selection list format. If the table is assumed to be a lookup table, this method generates a list of <value,caption> to be used in a dropdown. Default method returns null. Should be implemeted by override as needed.

  String[][]      findAllForLookup(int client);


  // Name of database table modelled by ModelBean class

  String          tableName();
  

  // Some tables contain records that are specific to a client and should not be accessed by other users no matter how wide their authorizations are. These tables contain a column named OWNER_ID and decided to be client specific by design. customerSpecific() method returns true for these tables and false for all other tables. client variable is the value of the primary key in BUSINESS_PARTNER table.

  boolean         customerSpecific();
  void            setClient(ModelBean entity, int client);
  int             getClient(ModelBean entity);


  // As the same concept with client specific, also some tables contain organization specific information. For instance material inventory, employment, production orders etc. Authorization concept handles access to these records as well. Top levels reporting requirements should be designed seperately.

  boolean         organizationFiltered();


  // Though ORGANIZATION_ID is an integer field in all tables, parameter <organizations> is a comma seperated list of organization IDs. The string value can only contain numeric values and commas like "3,802,7,273,42,987678"

  List<ModelBean> findByOrganization(int client, String organizations);

}