<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<table width="200" border="0" cellpadding="0" cellspacing="0" background="<c:url value='/images/bg_left_column_top.png' />">
	<tbody>
		<tr>
			<td height="40" align="center" valign="middle">
				<table width="160" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr><td align="center" valign="middle">
							<c:forEach items="${supportedLocales}" var="locale" varStatus="status">
								<a href="?lang=${locale}<c:if test='${not empty gameId}'>&id=${gameId}</c:if>"><img
									src="<c:url value="/images/flags/language/${locale.language}.gif"/>"
									alt="<fmt:message key="language.${locale.language}" />" border="0"
									align="top" /></a>
									<c:if test="${not status.last}">&nbsp;&nbsp;</c:if>
							</c:forEach>
						</td></tr>
					</tbody>
				</table>
			</td>
		</tr>
		<sec:authorize ifAllGranted="ROLE_ANONYMOUS">
			<c:choose>
				<c:when test="${empty hideEmbeddedLogin}">
					<form id="embeddedLoginForm" name="loginForm" action="<c:url value='/j_spring_security_check' />" method="post">
						<tr>
							<td height="40" align="center" valign="middle">
								<div class="whitetitle12"><fmt:message key="login.username" /></div>
								<input
									type="text"
									name="j_username"
									size="16"
									maxlength="16"
									title="<fmt:message key='login.username' />"
								/>
							</td>
						</tr>
						<tr>
							<td height="40" align="center" valign="middle">
								<div class="whitetitle12"><fmt:message key="login.password" /></div>
								<input type="password" name="j_password" size="16" maxlength="16" title="<fmt:message key='login.password' />" />
							</td>
						</tr>
						<tr>
							<td height="76" align="center" valign="top">
		                        <div>
		                            <br/>
		                            <div class="button_container">
		                                <div class="button_s2" onMouseOut="this.className='button_s2'; return false;" onMouseOver="this.className='button_s2_over'; return false;" onClick="javascript:document.forms['embeddedLoginForm'].submit(); return false;">
			                                <span class="button_s2_text"><fmt:message key="enter.button.label"/></span>
			                            </div>
			                        </div>
		                            <br/>
		                            <span class="Small_text">
		                                <a href="<c:url value="/do/user/forgotPassword" />" class="gray"><fmt:message key="login.forgotPassword" /></a>
		                            </span>
		                        </div>
								<p class="whitetext16bold">
									<a href="<c:url value="/do/user/signup" />" class="white"><fmt:message key="login.signup" /></a>
								</p>
		                        <img src="<c:url value='/images/left_column_corner.gif' />" style="background-color: white;"/></td>
						</tr>
					</form>
				</c:when>
				<c:otherwise>
					<tr>
						<td height="40" align="center" valign="middle" />
					</tr>
					<tr>
						<td height="40" align="center" valign="middle" />
					</tr>
					<tr>
						<td height="76" align="center" valign="top">
	                        <div>
	                        	<span class="Small_text">
	                                <a href="<c:url value="/do/user/forgotPassword" />" class="gray"><fmt:message key="login.forgotPassword" /></a>
	                            </span>
	                        </div>
							<p class="whitetext16bold">
								<a href="<c:url value="/do/user/signup" />" class="white"><fmt:message key="login.signup" /></a>
							</p>
	                        <img src="<c:url value='/images/left_column_corner.gif' />" style="background-color: white;"/></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
			<tr>
				<td height="40" align="center" valign="middle">&nbsp;</td>
			</tr>
			<tr>
				<td height="40" align="center" valign="middle">&nbsp;</td>
			</tr>
			<tr>
				<td height="76" align="center" valign="top">
					<p class="whitetext16bold"><fmt:message key="welcome.user"><fmt:param><sec:authentication property="principal.username" /></fmt:param></fmt:message></p>
					<p class="whitetext16bold"><a href="<c:url value='/j_spring_security_logout' />" class="white"><fmt:message key="logout" /></a></p>
                       <img src="<c:url value='/images/left_column_corner.gif' />" style="background-color: white"/></td>
			</tr>
		</sec:authorize>
	</tbody>
</table>