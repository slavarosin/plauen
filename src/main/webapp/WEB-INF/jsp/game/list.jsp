<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<table width="100%" border="0" cellspacing="26" cellpadding="0">
	<tbody>
		<tr>
			<td height="320" colspan="3" rowspan="2" align="left" valign="top" background="<c:url value='/images/bg_dark.png' />" bgcolor="#CCCCCC">
				<c:if test="${not empty games[0]}">
					<t:game game="${games[0]}" type="main" page="list" mode="${mode}" />
				</c:if>
			</td>

			<td width="25%" height="147" align="center" valign="top">
				<c:if test="${not empty games[1]}">
					<t:game game="${games[1]}" type="other" page="list" mode="${mode}" />
				</c:if>
			</td>
		</tr>

		<tr>
			<td width="25%" height="147" align="center" valign="top">
				<c:if test="${not empty games[2]}">
					<t:game game="${games[2]}" type="other" page="list" mode="${mode}" />
				</c:if>
			</td>
		</tr>

		<tr>
			<td width="25%" height="147" align="left" valign="top">
				<c:if test="${not empty games[3]}">
					<t:game game="${games[3]}" type="other" page="list" mode="${mode}" />
				</c:if>
			</td>
			<td width="25%" height="147" align="center" valign="top">
				<c:if test="${not empty games[4]}">
					<t:game game="${games[4]}" type="other" page="list" mode="${mode}" />
				</c:if>
			</td>
			<td width="25%" height="147" align="center" valign="top">
				<c:if test="${not empty games[5]}">
					<t:game game="${games[5]}" type="other" page="list" mode="${mode}" />
				</c:if>
			</td>
			<td width="25%" height="147" align="center" valign="top">
				<c:if test="${not empty games[6]}">
					<t:game game="${games[6]}" type="other" page="list" mode="${mode}" />
				</c:if>
			</td>
		</tr>
	</tbody>
</table>