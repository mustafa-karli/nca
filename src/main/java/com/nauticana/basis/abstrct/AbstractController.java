package com.nauticana.basis.abstrct;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.basis.model.ContentRelation;
import com.nauticana.basis.service.ContentDataService;
import com.nauticana.basis.service.ContentRelationService;
import com.nauticana.basis.service.BasisService;
import com.nauticana.basis.utils.ControllerStatic;
import com.nauticana.basis.utils.ControllerStatic.TableContentType;
import com.nauticana.basis.utils.ControllerStatic.TableNavAction;
import com.nauticana.basis.utils.DataCache;
import com.nauticana.basis.utils.FieldType;
import com.nauticana.basis.utils.Labels;
import com.nauticana.basis.utils.PortalLanguage;
import com.nauticana.basis.utils.Utils;

public abstract class AbstractController<ModelBean, ModelId extends Serializable> implements IAbstractController<ModelBean> {

    public static final String						DEFULT_LIST_VIEW	= "abstract/list";
    public static final String						DEFULT_EDIT_VIEW	= "abstract/edit";
    public static final String						DEFULT_SHOW_VIEW	= "abstract/show";
    public static final String						DEFULT_SELECT_VIEW	= "abstract/select";
    public static final String						DEFULT_SEARCH_VIEW	= "abstract/search";

    @Autowired
    protected AbstractService<ModelBean, ModelId>	modelService;

    @Autowired
    protected BasisService						basisService;

    @Autowired
    protected ContentRelationService				contentRelationService;

    @Autowired
    protected DataCache								dataCache;

    @Override
    public String prevPage(ModelBean entity) {
        String s = modelService.parentLink(entity);
        if (s != null)
            return s;
        return getControllerStatic().getRootMapping() + "/list";
    }

    //
    //
    // Do N O T modify code below this line //
    //
    //

    public ModelAndView listView(ControllerStatic controllerStatic, PortalLanguage language) {
        String view = controllerStatic.getListView();
        if (Utils.emptyStr(view))
            view = DEFULT_LIST_VIEW;
        return new ModelAndView(view)
        		.addObject("controller", controllerStatic)
        		.addObject("language", language);

    };
    
    public ModelAndView showView(ControllerStatic controllerStatic, PortalLanguage language) {
        String view = controllerStatic.getShowView();
        if (Utils.emptyStr(view))
            view = DEFULT_SHOW_VIEW;
        return new ModelAndView(view)
        		.addObject("controller", controllerStatic)
        		.addObject("language", language);
    };
    
    public ModelAndView editView(ControllerStatic controllerStatic, PortalLanguage language) {
        String view = controllerStatic.getEditView();
        if (Utils.emptyStr(view))
            view = DEFULT_EDIT_VIEW;
        return new ModelAndView(view)
        		.addObject("controller", controllerStatic)
        		.addObject("language", language);
    };
    
    public ModelAndView selectView(ControllerStatic controllerStatic, PortalLanguage language) {
        String view = controllerStatic.getSelectView();
        if (Utils.emptyStr(view))
            view = DEFULT_SELECT_VIEW;
        return new ModelAndView(view)
        		.addObject("controller", controllerStatic)
        		.addObject("language", language);
    };
    
    public ModelAndView searchView(ControllerStatic controllerStatic, PortalLanguage language) {
        String view = controllerStatic.getSearchView();
        if (Utils.emptyStr(view))
            view = DEFULT_SEARCH_VIEW;
        return new ModelAndView(view)
        		.addObject("controller", controllerStatic)
        		.addObject("language", language);
    };
    
    public FieldType[] getFieldTypes() {
        return basisService.fieldTypes(tableName());
    }

    public List<ModelBean> findAuthorized(String username, String authorityObject, String action) {
        List<String> ids = basisService.authorizedIds(username, authorityObject, action);
        List<ModelBean> entities = new ArrayList<ModelBean>();
        for (String s : ids) {
            if (s.equals("*"))
                return modelService.findAll();
            ModelId id = modelService.strToId(s);
            ModelBean entity = modelService.findById(id);
            entities.add(entity);
        }
        return entities;
    }

    protected Object[] getSelectOptions(PortalLanguage language, int client, ControllerStatic controller) {
        Object[] o = new Object[controller.getFields().length];
        for (int i = 0; i < o.length; i++) {
            o[i] = dataCache.getLookupOptions(controller.getFields()[i].getEditStyle(), client);
            if (o[i] == null)
                o[i] = dataCache.getDomainOptions(controller.getFields()[i].getEditStyle(), language);
        }
        return o;
    }

    protected Object[] getDomainOptions(PortalLanguage language, int client, ControllerStatic controller) {
        Object[] o = new Object[controller.getFields().length];
        for (int i = 0; i < o.length; i++) {
        	o[i] = dataCache.getDomainOptions(controller.getFields()[i].getEditStyle(), language);
        }
        return o;
    }

    public static int getClient(HttpSession session) {
        try {
            return (int) session.getAttribute(Labels.OWNER_ID);
        } catch (Exception e) {
            return -1;
        }
    }

    public ModelAndView errorPage(PortalLanguage language, String ERRCODE, String msgText) {
        ModelAndView model = new ModelAndView("basis/errorPage");
        if (language == null)
            model.addObject("ERRCODE", ERRCODE);
        else
        	model.addObject("ERRCODE", language.getText(ERRCODE));
        model.addObject("MSGTEXT", msgText);
        return model;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editGet(HttpServletRequest request) {

        // Check for user and update authorization on table
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/");

        // Read language of session
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        if (!basisService.authorityChk(username, Labels.TABLE, Labels.UPDATE, tableName()))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.UPDATE + " ON " + tableName());

        // Read data record and assign to model and view object
        String id = request.getParameter("id");
        ModelBean record = modelService.findById(modelService.strToId(id));
        if (record == null)
            return errorPage(language, Labels.ERR_RECORDNOTFOUND, tableName() + " WITH ID : " + id);

        ControllerStatic controllerStatic = getControllerStatic();
        ModelAndView model = editView(controllerStatic, language);

        model.addObject("record", record);
        model.addObject("INPUTMODE", "EDIT");

        List<ContentRelation> content = null;
        for (TableContentType contentType : controllerStatic.getContentTypes()) {
            try {
                List<ContentRelation> list = contentRelationService.findByOtype(getClient(session), contentType.objectType, id);
                if (content == null)
                    content = list;
                else
                    content.addAll(list);
            } catch (ParseException e) {
            }
        }
        if (content != null)
            model.addObject("binaryContent", content);

        // Assign text objects from session language
        model.addObject(Labels.PAGETITLE, language.getText(tableName()));
        model.addObject("CONTENT_RELATION_PURPOSE", dataCache.getDomainOptions("CONTENT_RELATION_PURPOSE"));
        model.addObject(Labels.PREVPAGE, prevPage(record));
        model.addObject(Labels.POSTLINK, controllerStatic.getRootMapping() + "/edit");
        model.addObject(Labels.SELECT_OPTIONS, getSelectOptions(language, getClient(session), controllerStatic));
        return model;
    }

    @Autowired
    ContentDataService cds;

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editPost(@ModelAttribute ModelBean record, BindingResult result, HttpServletRequest request) {

        // Check for user and insert/update authorization on table
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/");

        // Read language of session
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        if (!basisService.authorityChk(username, Labels.TABLE, Labels.UPDATE, tableName()))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.UPDATE + " ON " + tableName());

        if (result.hasErrors()) {
            String msgText = "";
            for (ObjectError e : result.getAllErrors()) {
                msgText = msgText + "\n" + e.toString();
            }
            return errorPage(language, Labels.ERR_BINDING, msgText);
        }

        // Update record in database
        try {
            modelService.save(record);
        } catch (Exception e) {
            return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
        }
        String nextpage = request.getParameter(Labels.NEXTPAGE);
        if (Utils.emptyStr(nextpage))
            nextpage = modelService.parentLink(record);
        if (Utils.emptyStr(nextpage))
            return new ModelAndView("redirect:/" + getControllerStatic().getRootMapping() + "/list");
        else
            return new ModelAndView("redirect:/" + nextpage);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteGet(HttpServletRequest request) {

        // Check for user and delete authorization on table
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/");

        // Read language of session
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        if (!basisService.authorityChk(username, Labels.TABLE, Labels.DELETE, tableName()))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.DELETE + " ON " + tableName());

        // Delete record from database
        try {
            String idStr = request.getParameter("id");
            ModelId id = modelService.strToId(idStr);
            ModelBean record = modelService.findById(id);
            String prevpage = prevPage(record);
            modelService.remove(id);
            String nextpage = request.getParameter(Labels.NEXTPAGE);
            if (Utils.emptyStr(nextpage))
                return new ModelAndView("redirect:/" + prevpage);
            else
                return new ModelAndView("redirect:/" + nextpage);
        } catch (Exception e) {
            return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
        }
    }
    
    public void addAuthorizations(ControllerStatic controllerStatic, ModelAndView model, String username, String tableName) {
        if (basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName))
            model.addObject(Labels.INSERT_ALLOWED, "X");
        if (basisService.authorityChk(username, Labels.TABLE, Labels.UPDATE, tableName))
            model.addObject(Labels.UPDATE_ALLOWED, "X");
        if (basisService.authorityChk(username, Labels.TABLE, Labels.DELETE, tableName))
            model.addObject(Labels.DELETE_ALLOWED, "X");

		if (controllerStatic.getActions() != null) {
			byte[] actAuth = new byte[controllerStatic.getActions().length];
			for (int i = 0; i < actAuth.length; i++) {
				TableNavAction a = controllerStatic.getActions()[i];
				if (a.enable.charAt(0) == 'Y') {
					if (a.authorityCheck.charAt(0) == 'N')
						actAuth[i] = 1;
					else {
						if (basisService.authorityChk(username, tableName, a.action))
							actAuth[i] = 1;
						else
							actAuth[i] = 0;
					}
				} else {
					actAuth[i] = 0;
				}
			}
			model.addObject("ACTIONS_ALLOWED", actAuth);
		}
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listGet(HttpServletRequest request) throws IOException {

        // Check for user and read authorization on table
    	HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/");
        int client = getClient(session);
        String tableName = tableName();

        // Read language of session
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName);

        // Read data and assign to model and view object
        List<ModelBean> records;

        String orgs = (String) session.getAttribute(Labels.ORGANIZATION_IDS);
        if (modelService.organizationFiltered() && !"*".equals(orgs)) {
            records = modelService.findByOrganization(client, orgs);
        } else {
            if (modelService.customerSpecific())
                records = modelService.findAll(client);
            else
                records = modelService.findAll();
        }

        ControllerStatic controllerStatic = getControllerStatic();
        ModelAndView model = listView(controllerStatic, language);

        model.addObject(Labels.dataTableNames[0], Labels.dataTableSettings[0]);
        model.addObject("records", records);
        model.addObject(Labels.DOMAIN_OPTIONS, getDomainOptions(language, getClient(session), controllerStatic));
        addAuthorizations(controllerStatic, model, username, tableName);

        // Assign text objects from session language
        model.addObject(Labels.PAGETITLE, language.getText(tableName));
        return model;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView showGet(HttpServletRequest request) throws IOException {

        // Check for user and read authorization on table
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/");
        int client = getClient(session);
        // Read language of session
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        String tableName = tableName();
        boolean[] siudAllowed = new boolean[4];
        siudAllowed[0] = basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName);
        if (!siudAllowed[0])
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName);
        siudAllowed[1] = basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName);
        siudAllowed[2] = basisService.authorityChk(username, Labels.TABLE, Labels.UPDATE, tableName);
        siudAllowed[3] = basisService.authorityChk(username, Labels.TABLE, Labels.DELETE, tableName);

        // Read data and assign to model and view object

        String id = request.getParameter("id");
        String prevpage = request.getParameter("prevpage");
        ModelBean record = modelService.findById(modelService.strToId(id));
        if (record == null)
            return errorPage(language, Labels.ERR_RECORDNOTFOUND, tableName() + " WITH ID : " + id);
        if (modelService.customerSpecific() && modelService.getClient(record) != client) {
        	siudAllowed[2] = false;
        	siudAllowed[3] = false;
        }
        
        ControllerStatic controllerStatic = getControllerStatic();
        ModelAndView model = showView(controllerStatic, language);

        model.addObject("record", record);
        model.addObject("siudAllowed", siudAllowed);
        model.addObject(Labels.DOMAIN_OPTIONS, getDomainOptions(language, client, controllerStatic));

        if (controllerStatic.getDetails() != null) {
            String[] detailSelectAllowed = new String[controllerStatic.getDetails().length];
            String[] detailInsertAllowed = new String[controllerStatic.getDetails().length];
            String[] detailUpdateAllowed = new String[controllerStatic.getDetails().length];
            String[] detailDeleteAllowed = new String[controllerStatic.getDetails().length];
            Object[][] detailOptions     = new Object[controllerStatic.getDetails().length][];
            for (int i = 0; i < controllerStatic.getDetails().length; i++) {
            	String dtable = controllerStatic.getDetails()[i].detailStatic.getTableName();
                if (siudAllowed[0] && basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, dtable))
                    detailSelectAllowed[i] = "X";
                else
                    detailSelectAllowed[i] = "";
                if (siudAllowed[1] && modelService.getClient(record) == client && basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, dtable))
                    detailInsertAllowed[i] = "X";
                else
                    detailInsertAllowed[i] = "";
                if (siudAllowed[2] && basisService.authorityChk(username, Labels.TABLE, Labels.UPDATE, dtable))
                    detailUpdateAllowed[i] = "X";
                else
                    detailUpdateAllowed[i] = "";
                if (siudAllowed[3] && basisService.authorityChk(username, Labels.TABLE, Labels.DELETE, dtable))
                    detailDeleteAllowed[i] = "X";
                else
                    detailDeleteAllowed[i] = "";
                detailOptions[i] = getDomainOptions(language, client, controllerStatic.getDetails()[i].detailStatic);
            }
            model.addObject("selectAllowed", detailSelectAllowed);
            model.addObject("insertAllowed", detailInsertAllowed);
            model.addObject("updateAllowed", detailUpdateAllowed);
            model.addObject("deleteAllowed", detailDeleteAllowed);
            model.addObject("DETAIL_OPTIONS", detailOptions);
        }

        List<ContentRelation> content = null;
        for (TableContentType contentType : controllerStatic.getContentTypes()) {
            try {
                List<ContentRelation> list = contentRelationService.findByOtype(client, contentType.objectType, id);
                if (content == null)
                    content = list;
                else
                    content.addAll(list);
            } catch (ParseException e) {
            }
        }
        if (content != null)
            model.addObject("binaryContent", content);

        model.addObject(Labels.PAGETITLE, language.getText(tableName));
        if (Utils.emptyStr(prevpage))
            model.addObject(Labels.PREVPAGE, prevPage(record));
        else
            model.addObject(Labels.PREVPAGE, prevpage);

		if (controllerStatic.getActions() != null) {
			byte[] actAuth = new byte[controllerStatic.getActions().length];
			for (int i = 0; i < actAuth.length; i++) {
				TableNavAction a = controllerStatic.getActions()[i];
				if (a.enable.charAt(0) == 'Y') {
					if (a.authorityCheck.charAt(0) == 'N')
						actAuth[i] = 1;
					else {
						if (a.recordSpecific.charAt(0) == 'N') {
							if (basisService.authorityChk(username, tableName, a.action))
								actAuth[i] = 1;
							else
								actAuth[i] = 0;
						} else {
							if (basisService.authorityChk(username, tableName, a.action, modelService.idAsStr(record)))
								actAuth[i] = 1;
							else
								actAuth[i] = 0;
						}
					}
				} else {
					actAuth[i] = 0;
				}
			}
			model.addObject("ACTIONS_ALLOWED", actAuth);
		}
		return model;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView newGet(HttpServletRequest request) {

        // Check for user and insert authorization on table
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/");
        int client = getClient(session);

        // Read language of session
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

        // Create empty bean and assign to model and view object
        String parentKey = request.getParameter(Labels.PARENTKEY);
        String id = request.getParameter("id");
        // System.out.println("Parent Key is : " + parentKey);
        ModelBean record;
        if (!Utils.emptyStr(id))
            record = modelService.newEntityWithId(id);
        else {
            record = modelService.newEntityWithParentId(parentKey);
            id = parentKey;
        }
        if (modelService.customerSpecific()) {
            modelService.setClient(record, client);
        }

        ControllerStatic controllerStatic = getControllerStatic();
        ModelAndView model = editView(controllerStatic, language);

        model.addObject("record", record);
        model.addObject("INPUTMODE", "NEW");

        model.addObject(Labels.PAGETITLE, language.getText(tableName()));
        model.addObject(Labels.PREVPAGE, prevPage(record));
        model.addObject(Labels.POSTLINK, controllerStatic.getRootMapping() + "/new");
        model.addObject(Labels.SELECT_OPTIONS, getSelectOptions(language, client, controllerStatic));

        return model;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ModelAndView newPost(@ModelAttribute ModelBean record, BindingResult result, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/");

        // Read language of session
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

        if (result.hasErrors()) {
            String msgText = "";
            for (ObjectError e : result.getAllErrors()) {
                msgText = msgText + "\n" + e.toString();
            }
            return errorPage(language, Labels.ERR_BINDING, msgText);
        }
        if (modelService.customerSpecific()) {
            modelService.setClient(record, getClient(session));
        }
        try {
            modelService.create(record);
        } catch (Exception e) {
            return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
        }
        String nextpage = request.getParameter(Labels.NEXTPAGE);
        if (Utils.emptyStr(nextpage))
            nextpage = modelService.parentLink(record);
        if (Utils.emptyStr(nextpage))
            return new ModelAndView("redirect:list");
        else
            return new ModelAndView("redirect:/" + nextpage);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchGet(HttpServletRequest request) {

        // Check for user and update authorization on table
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/");

        // Read language of session
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());

        ControllerStatic controllerStatic = getControllerStatic();
        ModelAndView model = searchView(controllerStatic, language);

        // Assign text objects from session language
        model.addObject(Labels.PAGETITLE, language.getText(tableName()));
        model.addObject(Labels.POSTLINK, controllerStatic.getRootMapping() + "/search");
        model.addObject(Labels.PREVPAGE, prevPage(null));
        model.addObject(Labels.SELECT_OPTIONS, getSelectOptions(language, getClient(session), controllerStatic));
        return model;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchPost(HttpServletRequest request) throws ParseException {

        // Check for user and insert/update authorization on table
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return new ModelAndView("redirect:/");

        // Read language of session
        PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

        String tableName=tableName();
        if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName))
            return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName);

        int client = getClient(session);

        ArrayList<String> fields = new ArrayList<String>();
        ArrayList<String> filters = new ArrayList<String>();
        ArrayList<Integer> types = new ArrayList<Integer>();

        if (modelService.customerSpecific()) {
            fields.add(Labels.OWNER_ID);
            filters.add(client + "");
            types.add(FieldType.T_INT);
        }

        FieldType[] fieldTypes = basisService.fieldTypes(tableName());
        for (FieldType fieldType : fieldTypes) {
            String fieldFilter = request.getParameter(fieldType.fieldName);
            if (fieldFilter != null) {
                fieldFilter = fieldFilter.trim();
                if (!Utils.emptyStr(fieldFilter)) {
                    fields.add(fieldType.fieldName);
                    filters.add(fieldFilter);
                    types.add(fieldType.getType());
                }
            }
        }

        List<ModelBean> records = modelService.search(fields, filters, types);

        ControllerStatic controllerStatic = getControllerStatic();
        ModelAndView model = searchView(controllerStatic, language);
        
        model.addObject("records", records);
        model.addObject(Labels.DOMAIN_OPTIONS, getDomainOptions(language, getClient(session), controllerStatic));

        if (basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName))
            model.addObject(Labels.INSERT_ALLOWED, "X");
        if (basisService.authorityChk(username, Labels.TABLE, Labels.UPDATE, tableName))
            model.addObject(Labels.UPDATE_ALLOWED, "X");
        if (basisService.authorityChk(username, Labels.TABLE, Labels.DELETE, tableName))
            model.addObject(Labels.DELETE_ALLOWED, "X");

		if (controllerStatic.getActions() != null) {
			byte[] actAuth = new byte[controllerStatic.getActions().length];
			for (int i = 0; i < actAuth.length; i++) {
				TableNavAction a = controllerStatic.getActions()[i];
				if (a.enable.charAt(0) == 'Y') {
					if (a.authorityCheck.charAt(0) == 'N')
						actAuth[i] = 1;
					else {
						if (basisService.authorityChk(username, tableName, a.action))
							actAuth[i] = 1;
						else
							actAuth[i] = 0;
					}
				} else {
					actAuth[i] = 0;
				}
			}
			model.addObject("ACTIONS_ALLOWED", actAuth);
		}
        model.addObject(Labels.PAGETITLE, language.getText(tableName));
        return model;
    }

	/*
	 * Rest Mappings
	 * 
	 */

    @GetMapping("/listAll")
    public List<ModelBean> listAll(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return null;

        if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
            return null;
        
        if (modelService.customerSpecific())
            return modelService.findAll(getClient(session));
        else
            return modelService.findAll();
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ModelBean> create(HttpServletRequest request, @RequestBody ModelBean entity) throws Exception {
        modelService.create(entity);
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return null;

        if (!basisService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
            return null;
        try {
            modelService.save(entity);
            return new ResponseEntity<ModelBean>(entity, HttpStatus.OK);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ModelBean> getById(HttpServletRequest request, @PathVariable("id") String id) {

//		HttpSession session = request.getSession(true);
//		if (session == null)
//			return null;
//		String username = (String) session.getAttribute(Labels.USERNAME);
//		if (Utils.emptyStr(username))
//			return null;
//
//		if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
//			return null;
        ModelId modelId = modelService.strToId(id);
        ModelBean entity = modelService.findById(modelId);
        if (entity == null) {
            return new ResponseEntity<ModelBean>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ModelBean>(entity, HttpStatus.OK);
    }

    
//    @GetMapping("/getJSON/{requestStr}")
//    public ResponseEntity<String> getByIdRequestStr(HttpServletRequest request, @PathVariable("requestStr") String requestStr) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, SecurityException, JSONException {
//
////		HttpSession session = request.getSession(true);
////		if (session == null)
////			return null;
////		String username = (String) session.getAttribute(Labels.USERNAME);
////		if (Utils.emptyStr(username))
////			return null;
////
////		if (!basisService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
////			return null;
//    	
//    	String requestObjectId="";
//    	requestObjectId = requestStr.substring(0, requestStr.indexOf("_"));
//    	System.out.println("ID: "+ requestObjectId);
//    	requestStr = requestStr.split("_")[1];
//    	System.out.println("Request Str: "+ requestStr);
//    	
//    	ModelId modelId = MODEL_SERVICES.strToId(requestObjectId);
//        ModelBean entity = MODEL_SERVICES.findById(modelId);
//
//    	String result = genericJsonConverter.convertObjectToJSON(entity, requestStr);
//
//        if (result == null) {
//            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<String>(result, HttpStatus.OK);
//    }
    
    
    
    @PutMapping("/put/{id}")
    public ResponseEntity<ModelBean> update(HttpServletRequest request, @PathVariable String id, @RequestBody ModelBean entity) throws Exception {
        HttpSession session = request.getSession(true);
        if (session == null)
            return null;
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return null;

        ModelId modelId = modelService.strToId(id);
        ModelBean bean = modelService.findById(modelId);
        if (bean == null) {
            return new ResponseEntity<ModelBean>(HttpStatus.NOT_FOUND);
        }
        modelService.save(entity);
        return new ResponseEntity<ModelBean>(entity, HttpStatus.OK);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<ModelBean> deleteRest(HttpServletRequest request, @PathVariable String id) throws Exception {
        HttpSession session = request.getSession(true);
        if (session == null)
            return null;
        String username = (String) session.getAttribute(Labels.USERNAME);
        if (Utils.emptyStr(username))
            return null;
        ModelId modelId = modelService.strToId(id);
        ModelBean entity = modelService.findById(modelId);
        if (entity == null) {
            return new ResponseEntity<ModelBean>(HttpStatus.NOT_FOUND);
        }
        modelService.remove(modelId);
        return new ResponseEntity<ModelBean>(HttpStatus.OK);
    }

    public String ModelBeanTablename() {
        Table t = modelService.modelClass().getAnnotation(Table.class);
        if (t == null)
            return modelService.modelClass().getSimpleName();
        return t.name();
    }

    @Override
    public ControllerStatic getControllerStatic() {
        return basisService.getControllerStatic(tableName());
    }

    @Override
    public String tableName() {
        return ModelBeanTablename();
    }


}