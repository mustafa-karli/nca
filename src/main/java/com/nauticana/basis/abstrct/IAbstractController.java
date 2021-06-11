package com.nauticana.basis.abstrct;

import com.nauticana.basis.utils.ControllerStatic;

public interface IAbstractController<ModelBean> {

	ControllerStatic getControllerStatic();

	String tableName();

//	String listView();

//	String editView();

//	String searchView();

//	String showView();

//	String selectView();

	String prevPage(ModelBean entity);
}
