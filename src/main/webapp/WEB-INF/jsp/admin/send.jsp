<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form:form commandName="messageBean">
	<table width="100%" border="0" cellspacing="26" cellpadding="0">
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
														<td align="center" bgcolor="#687B83" class="HeaderBIG"><fmt:message key="admin.send.title" /></td>
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
											<td colspan="2">
												<fieldset>
													<legend><fmt:message key="admin.send.text.label" /></legend>
													<table width="100%" cellpadding="0" cellspacing="0" border="0">
														<col width="20%" />
														<col width="80%" />
														<tbody>
															<c:forEach items="${messageBean.languages}" var="language" varStatus="status">
																<tr>
																	<td class="usertable">
																		<img
																			src="<c:url value="/images/flags/language/${language.code}.gif"/>"
																			alt="<fmt:message key="language.${language.code}" />" border="0"
																			align="top" />
																	</td>
																	<td align="left" class="usertable">
																		<form:textarea id="text_${status.index}" path="text[${status.index}]" cssStyle="width: 100%" rows="5" />
																	</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</fieldset>
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center">
												<div class="button_container">
												<div class="button_s2" onMouseOut="this.className='button_s2'; return false;" onMouseOver="this.className='button_s2_over'; return false;" onClick="javascript:document.forms[0].submit(); return false;">
												<span class="button_s2_text"><fmt:message key='admin.send.submit' /></span></div>
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