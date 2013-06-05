<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title><fmt:message key="app.title"/></title>
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

						<table width="100%" border="0" cellspacing="26" cellpadding="0">
							<tbody>
								<tr>
									<td align="center" valign="top">
									<table
										style="background-image: url(<c:url value='/images/bg_dark.png'/>); background-repeat: repeat-x; background-position: left top;"
										bgcolor="#171b1c" border="0" cellpadding="0" cellspacing="0"
										width="100%">
										<tbody>
											<tr>
												<td align="left" valign="top" width="50%">
												<table border="0" cellspacing="10" width="100%">
													<tbody>
														<tr>
															<td align="center">
															<table border="0" cellpadding="0" cellspacing="0" width="80%">
																<tbody>
																	<tr>
																		<td><img src="<c:url value='/images/10px_gray_top_l.png'/>" width="10" height="10"></td>
																		<td bgcolor="#687b83" width="100%"></td>
																		<td><img src="<c:url value='/images/10px_gray_top_r.png'/>" alt="" width="10" height="10"></td>
																	</tr>
																	<tr bgcolor="#687b83">
																		<td>&nbsp;</td>
																		<td class="HeaderBIG" align="center" bgcolor="#687b83"><div class="white"><fmt:message key="unsubscribed" /></div></td>
																		<td>&nbsp;</td>
																	</tr>
																	<tr>
																		<td><img src="<c:url value='/images/10px_gray_bot_l.png'/>" alt="" width="10" height="10"></td>
																		<td bgcolor="#687b83"></td>
																		<td><img src="<c:url value='/images/10px_gray_bot_r.png'/>" alt="" width="10" height="10"></td>
																	</tr>
																</tbody>
															</table>
															</td>
														</tr>
													</tbody>
												</table>
												</td>
											</tr>
										</tbody>
									</table>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>
