<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ attribute name="game" type="net.gobro.plauen.web.beans.GameBean" required="true" %>
<%@ attribute name="page" type="java.lang.String" required="true" %>
<%@ attribute name="type" type="java.lang.String" required="true" %>
<%@ attribute name="mode" type="java.lang.String" required="true" description="The mode the component is worked in: shows active games or future games" %>

<c:choose>
	<c:when test="${type eq 'main'}">
		<table width="100%" height="320" border="0" cellspacing="10" cellpadding="0">
			<tbody>
				<tr>
					<td width="75%" align="left" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td colspan="2">
										<t:gameDate game="${game}" type="${type}" mode="${mode}" />
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center" valign="top">
										<table width="80%" border="0" cellpadding="0" cellspacing="0">
											<tbody>
												<tr>
													<td height="10"><img src="<c:url value='/images/10px_gray_top_l.png' />" width="10" height="10" alt=""/></td>
													<td width="100%" height="5" bgcolor="#687B83"></td>
													<td height="10"><img src="<c:url value='/images/10px_gray_top_r.png' />" width="10" height="10"/></td>
												</tr>
												<tr bgcolor="#687B83">
													<td/>
													<td align="center" bgcolor="#687B83" class="HeaderBIG">
														<nobr>
															<a href="view?id=${game.id}" class="white">${game.name}</a>
														</nobr>
													</td>
													<td/>
												</tr>
												<tr>
													<td height="10"><img src="<c:url value='/images/10px_gray_bot_l.png' />" width="10" height="10"/></td>
													<td height="5" bgcolor="#687B83"></td>
													<td height="10"><img src="<c:url value='/images/10px_gray_bot_r.png' />" width="10" height="10"/></td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2" class="price">
										<fmt:message key="game.present.price"><fmt:param value="${game.price}" /></fmt:message>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center" valign="middle" style="padding: 10pt">
										<t:gamePlay game="${game}" type="${type}" page="${page}" />
									</td>
								</tr>
								<tr>
									<td colspan="2" class="MainText">
									   <c:choose>
										   <c:when test="${not empty mode}">
	                                            ${game.shortDescription}<a href="view?id=${game.id}" class="white">...</a>
											</c:when>
											<c:otherwise>
                                                ${game.description}
                                            </c:otherwise>
										</c:choose>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
					<td width="25%" align="center" valign="middle">
						<t:gameImage game="${game}" type="${type}" />
					</td>
				</tr>
			</tbody>
		</table>
	</c:when>
	<c:when test="${type eq 'other'}">
		<table border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<td colspan="3">
						<t:gameDate game="${game}" type="${type}" mode="${mode}" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<t:gameImage game="${game}" type="${type}" />
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center" valign="top">
						<t:gamePresent game="${game}" type="${type}" />
						<t:gamePlay game="${game}" type="${type}" page="${page}" />
					</td>
				</tr>
			</tbody>
		</table>
	</c:when>
</c:choose>