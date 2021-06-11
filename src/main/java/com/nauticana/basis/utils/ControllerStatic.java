package com.nauticana.basis.utils;

import java.util.List;

public class ControllerStatic {

	private String				tableName;
	private String				rootMapping;
	private String				module;
	private Class<Object>		modelBeanClass;
	private Class<Object>		modelIdClass;
	private String				listView;
	private String				searchView;
	private String				editView;
	private String				showView;
	private String				selectView;
	private boolean             clientDependent;
	private FieldType[]			fields;
	private TableDetail[]		details			= null;
	private TableContentType[]	contentTypes	= null;
	private TableNavAction[]	actions			= null;

	public class TableContentType {
		public String	objectType;
		public String	caption;
		public String	mimetype;

		public String getObjectType() {
			return objectType;
		}

		public String getCaption() {
			return caption;
		}

		public String getMimetype() {
			return mimetype;
		}
	}

	public class TableNavAction {
		public String	action;
		public String	caption;
		public String	method;
		public String	enable;
		public String	authorityCheck;
		public String	recordSpecific;
		public String   longName;

		public String getAction() {
			return action;
		}

		public String getCaption() {
			return caption;
		}

		public String getMethod() {
			return method;
		}

		public String getEnable() {
			return enable;
		}

		public String getAuthorityCheck() {
			return authorityCheck;
		}

		public String getRecordSpecific() {
			return recordSpecific;
		}

		public String getLongName() {
			return longName;
		}
	}

	public class TableDetail {
		public String			detailAttribute;
		public String			detailTable;
		public String			enable;
		public String			paging;
		public String			filter;
		public String			order;
		public ControllerStatic	detailStatic;

		public String getDetailAttribute() {
			return detailAttribute;
		}

		public String getDetailTable() {
			return detailTable;
		}

		public String getEnable() {
			return enable;
		}

		public String getPaging() {
			return paging;
		}

		public String getFilter() {
			return filter;
		}

		public String getOrder() {
			return order;
		}

		public ControllerStatic getDetailStatic() {
			return detailStatic;
		}

	}

	public ControllerStatic(String tableName, String rootMapping, String module, String listView, String searchView, String editView, String showView, String selectView, boolean clientDependent) {
		super();
		this.tableName = tableName;
		this.rootMapping = rootMapping;
		this.module = module;
		this.listView = listView;
		this.searchView = searchView;
		this.editView = editView;
		this.showView = showView;
		this.selectView = selectView;
		this.clientDependent = clientDependent;
	}

	public String getModule() {
		return module;
	}

	public String getRootMapping() {
		return rootMapping;
	}

	public String getTableName() {
		return tableName;
	}

	public Class<Object> getModelBeanClass() {
		return modelBeanClass;
	}

	public void setModelBeanClass(Class<Object> modelBeanClass) {
		this.modelBeanClass = modelBeanClass;
	}

	public Class<Object> getModelIdClass() {
		return modelIdClass;
	}

	public void setModelIdClass(Class<Object> modelIdClass) {
		this.modelIdClass = modelIdClass;
	}

	public String getListView() {
		return listView;
	}

	public String getSearchView() {
		return searchView;
	}

	public String getEditView() {
		return editView;
	}

	public String getShowView() {
		return showView;
	}

	public String getSelectView() {
		return selectView;
	}
	
	public boolean isClientDependent() {
		return clientDependent;
	}

	public FieldType[] getFields() {
		return fields;
	}

	public void setFields(FieldType[] fields) {
		this.fields = fields;
	}

	public TableDetail[] getDetails() {
		return details;
	}

	public TableContentType[] getContentTypes() {
		return contentTypes;
	}

	public void setContentTypes(List<String[]> contentTypes) {
		this.contentTypes = new TableContentType[contentTypes.size()];
		for (int i = 0; i < contentTypes.size(); i++) {
			this.contentTypes[i] = new TableContentType();
			this.contentTypes[i].objectType = contentTypes.get(i)[0];
			this.contentTypes[i].caption = contentTypes.get(i)[1];
			this.contentTypes[i].mimetype = contentTypes.get(i)[2];
		}
	}

	public TableNavAction[] getActions() {
		return actions;
	}

	public void setActions(List<String[]> actions) {
		this.actions = new TableNavAction[actions.size()];
		for (int i = 0; i < actions.size(); i++) {
			this.actions[i] = new TableNavAction();
			this.actions[i].action = actions.get(i)[0];
			this.actions[i].caption = actions.get(i)[1];
			this.actions[i].method = actions.get(i)[2];
			this.actions[i].enable = actions.get(i)[3];
			this.actions[i].authorityCheck = actions.get(i)[4];
			this.actions[i].recordSpecific = actions.get(i)[5];
			this.actions[i].longName = tableName + "_" + this.actions[i].action + "_ALLOWED";
		}
	}

	public void setDetails(List<String[]> details, List<ControllerStatic> detailStatics) {
		this.details = new TableDetail[details.size()];
		for (int i = 0; i < details.size(); i++) {
			this.details[i] = new TableDetail();
			this.details[i].detailAttribute = details.get(i)[0];
			this.details[i].detailTable = details.get(i)[1];
			this.details[i].enable = details.get(i)[2];
			this.details[i].paging = details.get(i)[3];
			this.details[i].filter = details.get(i)[4];
			this.details[i].order = details.get(i)[5];
			this.details[i].detailStatic = detailStatics.get(i);
		}
	}

}
