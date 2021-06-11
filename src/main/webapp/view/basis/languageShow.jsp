<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>
	<div class="box-body">
		<p> ${LANGUAGE} : <span class="flag-icon ${record.flag}"></span>  ${record.id} ${record.caption}
			<c:if test="${!empty UPDATE_ALLOWED}">
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('language/edit?id=${record.id}');"> ${EDIT} </a>
			</c:if>
			<c:if test="${!empty RELOAD_TRANSLATION}">
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('language/reloadTranslation?id=${record.id}');"> ${RELOAD_TRANSLATION} </a>
			</c:if>
		</p>
	</div>
</div>

<div class="box box-info">
	<div class="box-header with-border">
		<h3 class="box-title"> ${CAPTION_TRANSLATION} </h3>
		<a class="btn btn-primary" href="#" onClick="doAjaxGet('captionTranslation/new?parentKey=${record.id}');"> ${NEW} </a>
	</div>
	
	<div class="box-body">

    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th>${CAPTION}</th>
        <th>${LABELUPPER}</th>
        <th>${LABELLOWER}</th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="captionTranslation" items="${record.captionTranslations}" varStatus="status">
        <tr>
          <td>${captionTranslation.id.caption}</td>
          <td>${captionTranslation.labelupper}</td>
          <td>${captionTranslation.labellower}</td>
          <td>
          	<a class="btn btn-primary" href="#" onClick="doAjaxGet('captionTranslation/edit?id=${captionTranslation.id.caption},${captionTranslation.id.langcode}');"> <i class="${EDIT_ICON}"> </i> ${EDIT} </a> &nbsp;
          	<a class="btn btn-danger" href="#" onClick="doAjaxGet('captionTranslation/delete?id=${captionTranslation.id.caption},${captionTranslation.id.langcode}');"> <i class="${DELETE_ICON}"> </i> ${DELETE} </a>
          </td>
        </tr>
      </c:forEach>
    </table>
    </div>
</div>