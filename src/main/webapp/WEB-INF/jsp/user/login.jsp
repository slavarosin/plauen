<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form id="loginForm" name="loginForm" action="<c:url value='/j_spring_security_check' />" method="post">
    <table width="100%" border="0" cellspacing="26" cellpadding="0">
		<tbody>
			<tr>
				<td align="center" valign="top">
					<table width="100%" border="0" cellpadding="0" cellspacing="26" bgcolor="#BCC6CA" style="background-image: url(<c:url value='/images/bg_left_column_bot.png' />);background-repeat: repeat-x;background-position: top left;">
						<tr>
							<td align="center" valign="top">
								<table width="70%" border="0" cellpadding="0" cellspacing="0">
									<col width="50%" />
									<col width="50%" />
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
														<td align="center" bgcolor="#687B83" class="HeaderBIG"><fmt:message key="login.page.header" /></td>
														<td>&nbsp;</td>
													</tr>
													<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
														<tr bgcolor="#687B83">
													   		<td colspan="3" align="center" bgcolor="#687B83" class="errorfield">
																<fmt:message key="error.login" />
				    										</td>
				    									</tr>
													</c:if>
													<tr>
														<td height="10"><img src="<c:url value='/images/10px_gray_bot_l.png' />" width="10" height="10"/></td>
														<td height="5" bgcolor="#687B83"/>
														<td height="10"><img src="<c:url value='/images/10px_gray_bot_r.png' />" width="10" height="10"/></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td align="right">
												<span class="usertable"><fmt:message key="login.username" />:</span>
											</td>
											<td class="usertable">
												<input
													type="text"
													name="j_username"
													size="16"
													maxlength="16"
													title="<fmt:message key='login.username' />"
													value="<c:out value='${SPRING_SECURITY_LAST_USERNAME}' />"
												/>
											</td>
										</tr>
										<tr>
											<td align="right">
												<span class="usertable"><fmt:message key="login.password" />:</span>
											</td>
											<td class="usertable">
												<input type="password" name="j_password" size="16" maxlength="16" title="<fmt:message key='login.password' />" />
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center">
												<br/>
												<div class="button_container">
												<div class="button_s2" onMouseOut="this.className='button_s2'; return false;" onMouseOver="this.className='button_s2_over'; return false;" onClick="javascript:document.forms['loginForm'].submit(); return false;">
												<span class="button_s2_text"><fmt:message key="enter.button.label"/></span></div>
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
</form>

<script>
	window.onload = function() { document.loginForm.j_username.focus(); }
</script>