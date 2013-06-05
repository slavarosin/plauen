<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><fmt:message key="app.title" /></title>
	<link href='<c:url value="/styles/default.css"/>' type="text/css" rel="stylesheet">
	<link rel="icon" href="<c:url value="/images/favicon_b.ico"/>" type="image/x-icon" />
</head>
<body>
	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="26">
		<tbody>
			<tr>
				<td height="33%" align="center" valign="bottom"><img src="<c:url value="/images/logo.png" />" width="207" height="140" alt="GoBro"></td>
			</tr>
			<tr>
				<td height="33%" align="center" valign="middle" bgcolor="#BCC6CA" style='background-image: url(<c:url value="/images/bg_left_column_bot.png" />); background-repeat: repeat-x; background-position: top left;'>
					<table width="80%" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td height="10"><img src="<c:url value="/images/10px_gray_top_l.png" />" width="10" height="10" alt=""></td>
								<td width="100%" height="5" bgcolor="#687B83"></td>
								<td height="10"><img src="<c:url value="/images/10px_gray_top_r.png" />" alt="" width="10" height="10"></td>
							</tr>
							<tr bgcolor="#687B83">
								<td>&nbsp;</td>
								<td align="center" bgcolor="#687B83" class="HeaderBIG">
									<c:forEach items="${countries}" var="country" varStatus="status">
										<c:if test="${!status.first}">/</c:if>
										<a class="white" href="?country=${country}<c:if test="${not empty continuePath}">&continuePath=${continuePath}</c:if>"><fmt:message key="country.${country}" /></a>
									</c:forEach>
								</td>
								<td>&nbsp;</td>
							</tr>
							<!-- tr>
                                <td colspan="3"  align="center" bgcolor="#687B83" class="HeaderBIG">Latvian and Lithuanian games are temporary closed by technical reasons!</td>
                            </tr -->
							<tr>
								<td height="10"><img src="<c:url value="/images/10px_gray_bot_l.png" />" alt="" width="10" height="10"></td>
								<td height="5" bgcolor="#687B83"></td>
								<td height="10"><img src="<c:url value="/images/10px_gray_bot_r.png" />" alt="" width="10" height="10"></td>
							</tr>
						</tbody>
					</table>
					<br>
					<table width="160" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<c:forEach items="${countries}" var="country">
									<td width="5">&nbsp;</td>
									<td align="center" valign="middle"><a
										href="?country=${country}<c:if test="${not empty continuePath}">&continuePath=${continuePath}</c:if>"><img
										src="<c:url value="/images/flags/country/${country}.png"/>"
										alt="<fmt:message key="country.${country}" />" border="0"
										align="top" /></a></td>
								</c:forEach>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td height="34%" align="center" valign="bottom"><span class="Small_text">&copy; GoBro OÜ</span></td>
			</tr>
		</tbody>
	</table>
    <tiles:insertDefinition name="ga"/>
</html>