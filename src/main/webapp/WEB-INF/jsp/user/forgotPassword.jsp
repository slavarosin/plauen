<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form:form commandName="forgot">
	<table width="100%" border="0" cellspacing="26" cellpadding="0">
		<tbody>
			<tr>
				<td align="center" valign="top">
					<table width="100%" border="0" cellpadding="0" cellspacing="26" bgcolor="#BCC6CA" style="background-image: url(<c:url value='/images/bg_left_column_bot.png' />);background-repeat: repeat-x;background-position: top left;">
						<tr>
							<td align="center" valign="top">
								<table width="70%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td colspan="2" align="center" valign="top">
											<table width="80%" border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td height="10"><img src="<c:url value='/images/10px_gray_top_l.png' />" width="10" height="10"/></td>
													<td width="100%" height="5" bgcolor="#687B83"/>
													<td height="10"><img src="<c:url value='/images/10px_gray_top_r.png' />" alt="" width="10" height="10"/></td>
												</tr>
												<tr bgcolor="#687B83">
													<td>&nbsp;</td>
													<td align="center" bgcolor="#687B83" class="HeaderBIG"><fmt:message key="forgot.title" /></td>
													<td>&nbsp;</td>
												</tr>
												<tr bgcolor="#687B83">
                                                    <td colspan="3" align="center" bgcolor="#687B83" class="verysmall"><fmt:message key="form.onerequired" /></td>
                                                </tr>
                                                <tr bgcolor="#687B83">
                                                    <td colspan="3" align="center" bgcolor="#687B83">
                                                        <form:errors path="login" cssClass="errorfield"/>
                                                        <form:errors path="email" cssClass="errorfield"/>
                                                    </td>
                                                </tr>
												<tr>
													<td height="10"><img src="<c:url value='/images/10px_gray_bot_l.png' />" alt="" width="10" height="10"/></td>
													<td height="5" bgcolor="#687B83"/>
													<td height="10"><img src="<c:url value='/images/10px_gray_bot_r.png' />" alt="" width="10" height="10"/></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
                                        <td align="right">
                                            <span class="usertable"><fmt:message key="forgot.title.email" /></span>
                                        </td>
                                        <td class="usertable">
                                            <form:input path="email"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" align="center">
                                            <span class="HeaderBIG"><fmt:message key="forgot.title.or" /></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right">
                                            <span class="usertable"><fmt:message key="forgot.title.login" /></span>
                                        </td>
                                        <td class="usertable">
                                            <form:input path="login"/>
                                        </td>
                                    </tr>
									<tr>
                                        <td colspan="2" align="center">
                                        	<div class="button_container">
											<div class="button_s2" onMouseOut="this.className='button_s2'; return false;" onMouseOver="this.className='button_s2_over'; return false;" onClick="javascript:document.forms[0].submit(); return false;">
											<span class="button_s2_text"><fmt:message key="forgot.send"/></span></div>
											</div>
                                        </td>
                                    </tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</form:form>