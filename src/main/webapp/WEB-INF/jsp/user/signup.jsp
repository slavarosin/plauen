<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form:form commandName="signup">
	<table width="100%" border="0" cellspacing="26" cellpadding="0">
		<tbody>
			<tr>
				<td align="center" valign="top">
					<table width="100%" border="0" cellpadding="0" cellspacing="26" bgcolor="#BCC6CA"
					    style="background-image: url(<c:url value='/images/bg_left_column_bot.png' />);background-repeat: repeat-x;background-position: top left;">
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
													<td align="center" bgcolor="#687B83" class="HeaderBIG"><fmt:message key="signup.title" /></td>
													<td>&nbsp;</td>
												</tr>
												<tr bgcolor="#687B83">
	                                                <td colspan="3" align="center" bgcolor="#687B83" class="verysmall"><fmt:message key="form.allrequired" /></td>
	                                            </tr>
	                                            <tr bgcolor="#687B83">
	                                                <td colspan="3" align="center" bgcolor="#687B83">
	                                                    <form:errors path="login" cssClass="errorfield"/>
	                                                    <form:errors path="email" cssClass="errorfield"/>
	                                                    <form:errors path="captcha" cssClass="errorfield"/>
	                                                    <form:errors path="firstName" cssClass="errorfield"/>
	                                                    <form:errors path="lastName" cssClass="errorfield"/>
	                                                    <form:errors path="password" cssClass="errorfield"/>
	                                                    <form:errors path="phone" cssClass="errorfield"/>
	                                                    <form:errors path="zip" cssClass="errorfield"/>
	                                                    <form:errors path="city" cssClass="errorfield"/>
	                                                    <form:errors path="street" cssClass="errorfield"/>
	                                                    <form:errors path="agreedWithTermsOfUse" cssClass="errorfield"/>
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
										<td colspan="2" align="center">
											<span class="HeaderBIG">
												<br/><fmt:message key="signup.title.personal" /></span>
										</td>
									</tr>
									<tr>
										<td width="50%" align="right">
											<div class="usertable ${class}"><fmt:message key="signup.title.personal.login" /></div>
										</td>
										<td width="50%" class="usertable">
											<form:input path="login"/>
										</td>
									</tr>
									<tr>
										<td align="right">
											<div class="usertable"><fmt:message key="signup.title.personal.email" /></div>
										</td>
										<td class="usertable">
											<form:input path="email"/>
										</td>
									</tr>
									<tr>
										<td align="right">
											<div class="usertable"><fmt:message key="signup.title.personal.password" /></div>
										</td>
										<td class="usertable">
											<form:password path="password" showPassword="true"/>
										</td>
									</tr>
									<tr>
										<td align="right">
											<div class="usertable"><fmt:message key="signup.title.personal.password.confirm" /></div>
										</td>
										<td class="usertable">
											<form:password path="passwordConfirm" showPassword="true"/>
										</td>
									</tr>
									<tr>
										<td align="right">
											<div class="usertable"><fmt:message key="signup.title.personal.phone" /></div>
										</td>
										<td class="usertable">
											<form:input path="phone"/>
										</td>
									</tr>
									<tr>
										<td colspan="2" align="center">
											<div class="HeaderBIG">
												<br/><fmt:message key="signup.title.address" /></div>
										</td>
									</tr>
									<tr>
	                                    <td align="right">
	                                        <div class="usertable"><fmt:message key="signup.title.address.firstname" /></div>
	                                    </td>
	                                    <td class="usertable">
	                                        <form:input path="firstName"/>
	                                    </td>
	                                </tr>
	                                    <tr>
	                                    <td align="right">
	                                        <div class="usertable"><fmt:message key="signup.title.address.lastname" /></div>
	                                    </td>
	                                    <td class="usertable">
	                                        <form:input path="lastName"/>
	                                    </td>
	                                </tr>
									<tr>
										<td align="right">
											<div class="usertable"><fmt:message key="signup.title.address.city" /></div>
										</td>
										<td class="usertable">
											<form:input path="city"/>
										</td>
									</tr>
									<tr>
										<td align="right">
											<div class="usertable"><fmt:message key="signup.title.address.zip" /></div>
										</td>
										<td class="usertable">
											<form:input path="zip"/>
										</td>
									</tr>
									<tr>
										<td align="right">
											<div class="usertable"><fmt:message key="signup.title.address.street" /></div>
										</td>
										<td class="usertable">
											<form:input path="street"/>
										</td>
									</tr>
									<tr>
										<td align="right">
											<div class="usertable">
                                                <fmt:message key="signup.title.agreedWithTermsOfUsePrefix" />
                                                <a title="<fmt:message key="signup.terms" />" href="<c:url value='/do/terms'/>" target="_terms"><fmt:message key="signup.title.agreedWithTermsOfUse" /></a>
                                                <fmt:message key="signup.title.agreedWithTermsOfUsePostfix" />
											</div>
										</td>
										<td class="usertable">
											<form:checkbox path="agreedWithTermsOfUse" />
										</td>
									</tr>
									<tr>
	                                    <td colspan="2" align="center">
	                                        <div class="HeaderBIG">
	                                            <br/><fmt:message key="signup.title.securitycode" /></div>
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td align="right">
	                                        <img src="<c:url value='/do/captcha' />"/>
	                                    </td>
	                                    <td class="usertable">
	                                        <form:input path="captcha"/>
	                                    </td>
	                                </tr>
									<tr>
										<td colspan="2" align="center">
											<br/>
											<div class="button_container">
											<div class="button_s2" onMouseOut="this.className='button_s2'; return false;" onMouseOver="this.className='button_s2_over'; return false;" onClick="javascript:document.forms[0].submit(); return false;">
											<div class="button_s2_text"><fmt:message key="signup.button.label"/></div></div>
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