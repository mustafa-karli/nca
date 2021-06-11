<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form name="f" enctype="multipart/form-data" method="POST">
	<div class="col-md-12">
		<div class="form-group">
			<label for="caption">${language.getText("RELATION_TYPE")}</label>
			<select id="otype" name="otype" class="required">
				<c:forEach var="ot" items="${CONTENT_RELATION_OBJECT_TYPE}">
					<option value="${ot.key}">${ot.value}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="caption">${language.getText("CAPTION")} </label>
			<input type="text" id="caption" name="caption" class="required">
		</div>
		<div class="form-group">
			<label for="purpose">${language.getText("PURPOSE")} </label>
			<select id="purpose" name="purpose" class="required">
				<c:forEach var="ot" items="${CONTENT_RELATION_PURPOSE}">
					<option value="${ot.key}">${ot.value}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="priority">${language.getText("PRIORITY")} </label>
			<input type="text" id="priority" name="priority" class="required">
		</div>
		<div class="form-group">
			<label for="binFiles">${language.getText("FILES")} </label>
			<input type="file" id="binFiles" name="binFiles" onchange="updateSize();" multiple class="required"> selected files: <span id="fileNum">0</span>; total size: <span id="fileSize">0</span>
		</div>
		<div class="form-group">
			<a href="#" onclick="dooAjaxBinPost();" class="btn btn-primary">${language.getIconText("UPLOAD")}</a>
		</div>
	</div>
</form>

<script>

$(document).ready(function() {
	$('.required').attr({
		'placeholder' : '${REQUIRED}'
	});
});


function dooAjaxBinPost() {
	debugger;
	var ot = document.getElementById("otype").value;
	var infiles = document.getElementById('binFiles');

	for (var i = 0; i < infiles.files.length; i++) {
		var formData=new FormData();
		formData.append("binFile", infiles.files[i]);
		formData.append("caption", document.getElementById("caption").value);
		formData.append("purpose", document.getElementById("purpose").value);
		formData.append("priority", document.getElementById("priority").value);

		var l = infiles.files[i].name.split(".");
		var objid=l[0];

		$.ajax({
			type: "POST",
			url : "contentRelation/postBinaryData/" + ot + "/" + objid + "/",
			data: formData,
			processData: false,
			contentType: false,
			cache: false,
			timeout: 600000,
			success : function(response) {
				$("#binContent").text( response );
				console.log("SUCCESS : ", formData);
				$('#btnSubmit').prop("disabled", false);
			},
			error : function(e) {
				$("#binContent").text(e.responseText);
				console.log("ERROR : ", e);
				$('#btnSubmit').prop("disabled", false);
			}
		});

	}
}

function updateSize() {
	var nBytes = 0, oFiles = document.getElementById("binFiles").files;
	nFiles = oFiles.length;
	for (var nFileId = 0; nFileId < nFiles; nFileId++) {
		nBytes += oFiles[nFileId].size;
	}
	var sOutput = nBytes + " bytes";
	document.getElementById("fileNum").innerHTML = nFiles;
	document.getElementById("fileSize").innerHTML = sOutput;
}

</script>
