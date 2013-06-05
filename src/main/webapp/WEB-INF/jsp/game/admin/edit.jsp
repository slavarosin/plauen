<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form:form commandName="game" enctype="multipart/form-data" acceptCharset="utf-8" method="post">
	<form:hidden path="id"/>
	<table width="100%" border="0" cellspacing="26" cellpadding="0" id="editGame">
		<tbody>
			<tr>
				<td align="center" valign="top">
					<table width="100%" border="0" cellpadding="0" cellspacing="26" bgcolor="#BCC6CA" style="background-image: url(<c:url value='/images/bg_left_column_bot.png' />);background-repeat: repeat-x;background-position: top left;">
						<tr>
							<td align="center" valign="top">
								<table width="70%" border="0" cellpadding="0" cellspacing="0">
									<col width="20%" />
									<col width="80%" />
									<tbody>
										<tr>
											<td colspan="2" align="center" valign="top">
												<table width="80%" border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td height="10"><img src="<c:url value='/images/10px_gray_top_l.png' />" width="10" height="10"/></td>
														<td width="100%" height="5" bgcolor="#687B83"/>
														<td height="10"><img src="<c:url value='/images/10px_gray_top_r.png' />" width="10" height="10"/></td>
													</tr>
													<tr bgcolor="#687B83">
														<td>&nbsp;</td>
														<td align="center" bgcolor="#687B83" class="HeaderBIG"><fmt:message key="game.edit.page.title" /></td>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td height="10"><img src="<c:url value='/images/10px_gray_bot_l.png' />" width="10" height="10"/></td>
														<td height="5" bgcolor="#687B83"/>
														<td height="10"><img src="<c:url value='/images/10px_gray_bot_r.png' />" width="10" height="10"/></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td colspan="2">&nbsp;</td>
										</tr>
										<tr bgcolor="#687B83">
										    <td colspan="2" align="center" bgcolor="#687B83">
										        <form:errors path="activateAt" cssClass="errorfield"/>
										        <form:errors path="startedAt" cssClass="errorfield"/>
										        <form:errors path="price" cssClass="errorfield"/>
										        <form:errors path="name" cssClass="errorfield"/>
										        <form:errors path="description" cssClass="errorfield"/>
										        <form:errors path="image1" cssClass="errorfield"/>
										    </td>
										</tr>
										<tr>
											<td class="usertable">
												<label><fmt:message key="game.create.country.label" />:</label>
											</td>
											<td align="left" class="usertable">
												<fmt:message key="country.${game.country}" />
											</td>
										</tr>
										<tr>
											<td class="usertable">
												<label><fmt:message key="game.create.internalName.label" />:</label>
											</td>
											<td align="left" class="usertable">
												<form:input path="internalName" maxlength="32" size="32" />
											</td>
										</tr>
										<tr>
											<td class="usertable">
												<label><fmt:message key="game.create.internalDescription.label" />:</label>
											</td>
											<td align="left" class="usertable">
												<form:input path="internalDescription" maxlength="32" size="32" />
											</td>
										</tr>
										<tr>
											<td class="usertable">
												<label><fmt:message key="game.create.activateAt.label" />:</label>
											</td>
											<td align="left" class="usertable">
												<form:input path="activateAt" id="activateAt" maxlength="10" size="10" readonly="true" cssStyle="background-color: lightgrey"/>
												<img src="<c:url value="/images/calendar.gif" />" id="activateAt_trigger_c" style="cursor: pointer; border: 1px solid red;" onmouseover="this.style.background='red';" onmouseout="this.style.background=''" />
											</td>
										</tr>
										<tr>
											<td class="usertable">
												<label><fmt:message key="game.create.startedAt.label" />:</label>
											</td>
											<td align="left" class="usertable">
												<form:input path="startedAt" id="startedAt" maxlength="10" size="10" readonly="true" cssStyle="background-color: lightgrey"/>
												<img src="<c:url value="/images/calendar.gif" />" id="startedAt_trigger_c" style="cursor: pointer; border: 1px solid red;" onmouseover="this.style.background='red';" onmouseout="this.style.background=''" />
											</td>
										</tr>
										<tr>
											<td class="usertable">
												<label><fmt:message key="game.create.price.label" />:</label>
											</td>
											<td align="left" class="usertable">
												<form:input path="price" maxlength="10" size="10" />
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<fieldset>
													<legend><fmt:message key="game.create.name.label" />:</legend>
													<table width="100%" cellpadding="0" cellspacing="0" border="0">
														<col width="20%" />
														<col width="80%" />
														<tbody>
															<c:forEach items="${game.languages}" var="language" varStatus="status">
																<tr>
																	<td class="usertable">
																		<img
																			src="<c:url value="/images/flags/language/${language.code}.gif"/>"
																			alt="<fmt:message key="language.${language.code}" />" border="0"
																			align="top" />
																	</td>
																	<td align="left" class="usertable">
																		<form:textarea id="name_${status.index}" path="name[${status.index}]" cssStyle="width: 100%" rows="1" cols="64" />
																	</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</fieldset>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<fieldset>
													<legend><fmt:message key="game.create.description.label" />:</legend>
													<table width="100%" cellpadding="0" cellspacing="0" border="0">
														<col width="20%" />
														<col width="80%" />
														<tbody>
															<c:forEach items="${game.languages}" var="language" varStatus="status">
																<tr>
																	<td class="usertable">
																		<img
																			src="<c:url value="/images/flags/language/${language.code}.gif"/>"
																			alt="<fmt:message key="language.${language.code}" />" border="0"
																			align="top" />
																	</td>
																	<td align="left" class="usertable">
																		<form:textarea id="description_${status.index}" path="description[${status.index}]" cssStyle="width: 100%" rows="5" />
																	</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</fieldset>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<fieldset>
													<legend><fmt:message key="game.create.image.label" />:</legend>
													<table width="100%" cellpadding="0" cellspacing="0" border="0">
														<col width="20%" />
														<col width="80%" />
														<caption class="usertable" style="text-align: left">
															<fmt:message key="game.edit.image.replace" />
															<br />
															<fmt:message key="game.create.image.maxsize">
																<fmt:param value="60 Kb"/>
															</fmt:message>
														</caption>
														<thead>
															<tr>
																<td class="usertable"><fmt:message key="game.create.image.1.label" /></td>
																<td class="usertable"><fmt:message key="game.create.image.2.label" /></td>
																<td class="usertable"><fmt:message key="game.create.image.3.label" /></td>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td align="center" valign="middle">
																	<c:choose>
																		<c:when test="${not empty game.images[0]}">
																			<img src="<c:url value='/do/image?id=${game.images[0]}' />" width="60" height="90"/>
																		</c:when>
																	</c:choose>
																</td>
																<td align="center" valign="middle">
																	<c:choose>
																		<c:when test="${not empty game.images[1]}">
																			<img src="<c:url value='/do/image?id=${game.images[1]}' />" width="60" height="90"/>
																		</c:when>
																	</c:choose>
																</td>
																<td align="center" valign="middle">
																	<c:choose>
																		<c:when test="${not empty game.images[2]}">
																			<img src="<c:url value='/do/image?id=${game.images[2]}' />" width="60" height="90"/>
																		</c:when>
																	</c:choose>
																</td>
															</tr>
															<tr>
																<td align="left" class="usertable">
																	<input type="file" name="image1" />
																</td>
																<td align="left" class="usertable">
																	<input type="file" name="image2" />
																</td>
																<td align="left" class="usertable">
																	<input type="file" name="image3" />
																</td>
															</tr>
														</tbody>
													</table>
												</fieldset>
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center">
												<div class="button_container">
													<div class="button_s2" onMouseOut="this.className='button_s2'; return false;" onMouseOver="this.className='button_s2_over'; return false;" onClick="javascript:document.forms[0].submit(); return false;">
													<span class="button_s2_text"><fmt:message key='game.edit.submit' /></span></div>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</form:form>

<script type="text/javascript">
	window.onload = function() {
		for(i = 0; i < 10; i++) {
			var objName = "description_" + i;
			var obj = document.getElementById(objName);

			if (obj) {
				var oFCKeditor = new FCKeditor(objName) ;
				oFCKeditor.BasePath = "<c:url value='/scripts/fckeditor/'/>" ;
				oFCKeditor.ToolbarSet = 'Basic' ;
				oFCKeditor.ReplaceTextarea() ;
			}
		}
	}

	function catcalc(cal) {
        var date = cal.date;
        var time = date.getTime()
        // use the _other_ field
        var field = document.getElementById("startedAt");
        if (field == cal.params.inputField) {
            field = document.getElementById("activateAt");
            time -= Date.WEEK; // substract one week
        } else {
            time += Date.WEEK; // add one week
        }
        var date2 = new Date(time);
        field.value = date2.print("%d.%m.%Y");
    }
    Calendar.setup({
        inputField     :    "activateAt",     // id of the input field
        ifFormat       :    "%d.%m.%Y",      // format of the input field
        button         :    "activateAt_trigger_c",  // trigger for the calendar (button ID)
        align          :    "Tl",           // alignment (defaults to "Bl")
        singleClick    :    true,
        onUpdate       :    catcalc
    });
    Calendar.setup({
        inputField     :    "startedAt",     // id of the input field
        ifFormat       :    "%d.%m.%Y",      // format of the input field
        button         :    "startedAt_trigger_c",  // trigger for the calendar (button ID)
        align          :    "Tl",           // alignment (defaults to "Bl")
        singleClick    :    true,
        onUpdate       :    catcalc
    });
</script>