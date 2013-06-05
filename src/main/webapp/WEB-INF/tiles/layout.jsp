<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title><fmt:message key="app.title"/></title>
		<script type="text/javascript" src="<c:url value="/scripts/fckeditor/fckeditor.js"/>"></script>
		<link href="<c:url value="/styles/calendar/calendar-blue.css"/>" type="text/css" media="all" title="win2k-cold-1" rel="stylesheet" />
		<script type="text/javascript" src="<c:url value="/scripts/calendar/calendar.js"/>"></script>
 		<script type="text/javascript" src="<c:url value="/scripts/calendar/lang/calendar-en.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/scripts/calendar/calendar-setup.js"/>"></script>
		<link href="<c:url value="/styles/default.css"/>" type="text/css" rel="stylesheet">
		<link href="<c:url value="/images/favicon_b.ico"/>" type="image/x-icon" rel="icon"/>
	</head>
	<body>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<td width="80%" rowspan="2" align="center" valign="top">
						<tiles:insertDefinition name="header"/>

						<tiles:insertDefinition name="menu"/>

						<tiles:insertDefinition name="submenu"/>

						<tiles:insertAttribute name="content"/>

						<tiles:insertDefinition name="footer"/>
					</td>

					<td width="20%" align="left" valign="top" height="216">
						<tiles:insertDefinition name="user/login/embedded"/>
					</td>
				</tr>

                <tr>
                    <td align="left" valign="top">
                        <c:import url="/do/winners"/>
                    </td>
                </tr>
			</tbody>
		</table>
		<tiles:insertDefinition name="ga"/>
	</body>
</html>
