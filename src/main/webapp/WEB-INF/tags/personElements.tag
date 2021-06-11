<%@ attribute name="veu" type="com.nauticana.personnel.model.ViewEmployeeUser" required="true"%>
<%@ attribute name="language" type="com.nauticana.basis.utils.PortalLanguage" required="true"%>
<%@ attribute name="title" type="String" required="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="box-header with-border">
	<h3 class="box-title">${title}</h3>
</div>

<div class="box-body">

	<div class="col-md-4">
		<div class="form-group">
			<label for="firstName" class="col-md-4 col-sm-4 control-label">${language.getText("FIRST_NAME")} </label>
			<div class="col-md-8  col-sm-8">
				<input type="text" class="form-control required" name="firstName" id="firstName" value="${veu.firstName}" />
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group">
			<label for="middleName" class="col-md-4 col-sm-4 control-label">${language.getText("MIDDLE_NAME")} </label>
			<div class="col-md-8  col-sm-8">
				<input type="text" class="form-control" name="middleName" id="middleName" value="${veu.middleName}" />
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group">
			<label for="lastName" class="col-md-4 col-sm-4 control-label">${language.getText("LAST_NAME")} </label>
			<div class="col-md-8  col-sm-8">
				<input type="text" class="form-control required" name="lastName" id="lastName" value="${veu.lastName}" />
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group">
			<label for="birthday" class="col-md-4 col-sm-4 control-label">${language.getText("BIRTHDAY")} </label>
			<div class="col-md-8  col-sm-8">
				<input type="text" class="form-control required date" name="birthday" id="birthday" placeholder="DD-MM-YYYY" value='<fmt:formatDate value="${veu.birthday}" pattern="dd-MM-yyyy" />' />
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group">
			<label for="gender" class="col-md-4 col-sm-4 control-label">${language.getText("GENDER")} </label>
			<div class="col-md-8  col-sm-8">
				<select name="gender" id="gender" class="form-control select2 required">
					<c:forEach var="opt" items="${genders}" varStatus="status">
						<c:choose>
							<c:when test="${opt.key == veu.gender}">
								<option value="${opt.key}" selected>${opt.value}</option>
							</c:when>
							<c:otherwise>
								<option value="${opt.key}">${opt.value}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group">
			<label for="nationality" class="col-md-4 col-sm-4 control-label">${language.getText("NATIONALITY")} </label>
			<div class="col-md-8  col-sm-8">
				<select name="nationality" id="nationality" class="form-control select2 required">
					<c:forEach var="opt" items="${countries}" varStatus="status">
						<c:choose>
							<c:when test="${opt.key == veu.nationality}">
								<option value="${opt.key}" selected>${opt.value}</option>
							</c:when>
							<c:otherwise>
								<option value="${opt.key}">${opt.value}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group">
			<label for="governmentId" class="col-md-4 col-sm-4 control-label">${language.getText("GOVERNMENT_ID")} </label>
			<div class="col-md-8  col-sm-8">
				<input type="text" class="form-control required" name="governmentId" id="governmentId" value="${veu.governmentId}" />
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group">
			<label for="personelMail" class="col-md-4 col-sm-4 control-label">${language.getText("PERSONAL_MAIL")} </label>
			<div class="col-md-8  col-sm-8">
				<input type="text" class="form-control" name="personelMail" id="personelMail" value="${veu.personelMail}" />
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group">
			<label for="cellPhone" class="col-md-4 col-sm-4 control-label">${language.getText("CELL_PHONE")} </label>
			<div class="col-md-8  col-sm-8">
				<input type="text" class="form-control required" name="cellPhone" id="cellPhone" value="${veu.cellPhone}" />
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group">
			<label for="position" class="col-md-4 col-sm-4 control-label">${language.getText("POSITION")} </label>
			<div class="col-md-8  col-sm-8">
				<input type="text" class="form-control required" name="position" id="position" value="${veu.position}" />
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group">
			<label for="emailAddress" class="col-md-4 col-sm-4 control-label">${language.getText("EMAIL_ADDRESS")} </label>
			<div class="col-md-8  col-sm-8">
				<input type="text" class="form-control required" name="emailAddress" id="emailAddress" value="${veu.emailAddress}" />
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group">
			<label for="workPhone" class="col-md-4 col-sm-4 control-label">${language.getText("WORK_PHONE")} </label>
			<div class="col-md-8  col-sm-8">
				<input type="text" class="form-control" name="workPhone" id="workPhone" value="${veu.workPhone}" />
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group">
			<label for="username" class="col-md-4 col-sm-4 control-label">${language.getText("USERNAME")} </label>
			<div class="col-md-8  col-sm-8">
				<input type="text" class="form-control required" name="username" id="username" value="${veu.username}" />
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<div class="form-group">
			<label for="userMail" class="col-md-4 col-sm-4 control-label">${language.getText("USER_MAIL")} </label>
			<div class="col-md-8  col-sm-8">
				<input type="text" class="form-control" name="userMail" id="userMail" value="${veu.userMail}" />
			</div>
		</div>
	</div>
</div>
