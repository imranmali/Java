<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload/Download/Delete Documents</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						//add more file components if Add is clicked
						$('#addFile')
								.click(
										function() {
											var fileIndex = $('#fileTable tr')
													.children().length - 1;
											$('#fileTable')
													.append(
															'<br/><tr><td>'
																	+ '	<input type="file" name="files['+ fileIndex +']" class="form-control input-sm"/>'
																	+ '</td></tr>');
										});

					});
</script>
</head>

<body>
	<c:forEach var="message" items="${messages}">
		<span class="${message.key}">${message.value}"</span>
		<br />
	</c:forEach>
	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->

			<div class="panel-heading">
				<span class="lead">List of Documents </span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>No.</th>
							<th>File Name</th>
							<th>Type</th>
							<th>Size</th>
							<th width="100"></th>
							<th width="100"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${documents}" var="doc" varStatus="counter">
							<tr>
								<td>${counter.index + 1}</td>
								<td>${doc.filename}</td>
								<td>${doc.mimetype}</td>
								<td>${doc.filesize}</td>
								<td><a href="<c:url value='/download-file-${doc.id}' />"
									class="btn btn-success custom-width">download</a></td>
								<td><a href="<c:url value='/delete-file-${doc.id}' />"
									class="btn btn-danger custom-width">delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="panel panel-default">

			<div class="panel-heading">
				<span class="lead">Upload New Document</span>
			</div>
			<div class="uploadcontainer">
				<form:form method="POST" modelAttribute="uploadForm"
					action="save.html" enctype="multipart/form-data"
					class="form-horizontal">

					<div class="row">
						<div class="form-group col-md-12">
							<br> <label class="col-md-3 control-lable" for="file"
								style="margin-left: 10px">Upload a document</label>
							<div class="col-md-7">
								<br>
								<table id="fileTable">
									<tr>

										<td><input type="file" name="files[0]" id="files[0]"
											class="form-control input-sm" />
											<div class="has-error">
												<form:errors path="files[0]" class="help-inline" />
											</div></td>
										<td><input id="addFile" type="button" value="Add File"
											class="btn btn-primary btn-sm" style="margin-left: 10px" /></td>
									</tr>
								</table>

							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Upload"
								class="btn btn-primary btn-sm">
						</div>
					</div>

				</form:form>
			</div>
		</div>
	</div>
</body>
</html>