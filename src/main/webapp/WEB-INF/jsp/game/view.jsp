<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<table width="100%" border="0" cellspacing="26" cellpadding="0">
	<tr>
		<td align="center" valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#171B1C" style="background-image: url(<c:url value='/images/bg_dark.png' />);background-repeat: repeat-x;background-position: top left;">
				<sec:authorize ifAllGranted="ROLE_ADMIN">
					<tr>
						<td colspan="2" align="right" valign="middle"><a
							href="<c:url value='/do/image/manage?gameId=${game.id}' />" class="white" style="padding: 10pt"><fmt:message
							key="game.view.editPicture"/></a>|<a
							href="edit?id=${game.id}" class="white" style="padding: 10pt"><fmt:message key="game.view.editGame"/></a></td>
					</tr>
				</sec:authorize>
				<tr>
					<td width="50%" align="left" valign="top">
						<table width="100%" border="0" cellspacing="10">
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td colspan="2"><t:gameDate game="${game}" type="main" mode="active" /></td>
										</tr>
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
														<td align="center" bgcolor="#687B83" class="HeaderBIG">${game.name}</td>
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
											<td colspan="2" class="price">
												<fmt:message key="game.present.price"><fmt:param value="${game.price}" /></fmt:message>
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center" valign="middle" style="padding: 10pt">
												<table width="100%" border="0" cellspacing="2">
													<tr>
														<c:choose>
															<c:when test="${not empty game.images[0]}">
																<td align="center" valign="middle">
																	<img src="<c:url value='/do/image?id=${game.images[0]}' />" width="60" height="90"/>
																</td>
															</c:when>
														</c:choose>
														<c:choose>
															<c:when test="${not empty game.images[1]}">
																<td align="center" valign="middle">
																	<img src="<c:url value='/do/image?id=${game.images[1]}' />" width="60" height="90"/>
																</td>
															</c:when>
														</c:choose>
														<c:choose>
															<c:when test="${not empty game.images[2]}">
																<td align="center" valign="middle">
																	<img src="<c:url value='/do/image?id=${game.images[2]}' />" width="60" height="90"/>
																</td>
															</c:when>
														</c:choose>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center" valign="middle" style="padding: 10pt">
												<t:gamePlay game="${game}" type="main" page="view" />
											</td>
										</tr>
										<tr>
											<td colspan="2" class="MainText">${game.description}</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
					<td width="50%" align="left" valign="top">
						<table width="100%" border="0" cellspacing="10">
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td height="49">&nbsp;</td>
										</tr>
										<tr>
											<td align="center" valign="top">
												<c:if test="${not empty game.userPlay}">
													<c:if test="${not empty game.userPosition}">
														<br/>
														<span class="HeaderBIG"><fmt:message key="game.view.yourPosition"><fmt:param value="${game.userPosition}" /></fmt:message></span>
														<br/>
														<br/>
													</c:if>
												</c:if>
												<c:if test="${not empty chart.rows}">
													<table width="20%" border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td height="10"><img src="<c:url value='/images/10px_gray_top_l.png' />" width="10" height="10"/></td>
															<td width="100%" height="5" bgcolor="#687B83"/>
															<td height="10"><img src="<c:url value='/images/10px_gray_top_r.png' />" width="10" height="10"/></td>
														</tr>
														<tr bgcolor="#687B83">
															<td>&nbsp;</td>
															<td align="center" bgcolor="#687B83" class="HeaderBIG"><fmt:message key="game.view.players" /></td>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td height="10"><img src="<c:url value='/images/10px_gray_bot_l.png' />" width="10" height="10"/></td>
															<td height="5" bgcolor="#687B83"/>
															<td height="10"><img src="<c:url value='/images/10px_gray_bot_r.png' />" width="10" height="10"/></td>
														</tr>
													</table>
													<br/>
													<table width="80%" border="0" cellpadding="0" cellspacing="0" bgcolor="#687B83">
														<sec:authorize ifAllGranted="ROLE_ADMIN">
															<col width="50%" />
															<col width="20%" />
														</sec:authorize>
														<sec:authorize ifNotGranted="ROLE_ADMIN">
															<col width="70%" />
														</sec:authorize>
														<col width="30%" />
														<tbody>
															<c:forEach items="${chart.rows}" var="row" varStatus="status">
																<c:set var="bgcolor">
																	<c:choose>
																		<c:when test="${status.count eq game.userPosition}">#FF0000</c:when>
																		<c:otherwise></c:otherwise>
																	</c:choose>
																</c:set>
																<tr>
																	<td height="18" valign="middle" bgcolor="${bgcolor}" class="usertable"><fmt:message key="chart.row.username">
																			<fmt:param value="${status.count}" />
																			<fmt:param value="${row.username}" />
																		</fmt:message></td>
																	<sec:authorize ifAllGranted="ROLE_ADMIN">
																		<td class="usertable" bgcolor="${bgcolor}" align="center"><c:if test="${row.bot}"><c:forEach
																			items="${scoreRules}" var="scoreRule" varStatus="status"><a
																			href="<c:url value='/do/sms/bot?gameId=${game.id}&scoreRuleId=${scoreRule.id}&message=${row.alias}' />">+${scoreRule.score}</a><c:if
																			test="${not status.last}">&nbsp;</c:if></c:forEach></c:if></td>
																	</sec:authorize>
																	<td class="usertable" bgcolor="${bgcolor}"><fmt:message key="chart.row.points">
																			<fmt:param value="${row.points}" />
																		</fmt:message></td>
																</tr>
															</c:forEach>
														</tbody>
														<sec:authorize ifAllGranted="ROLE_ADMIN">
															<tfoot>
																<form:form commandName="bot" action="bot">
																	<form:hidden path="gameId" />
																	<tr>
																		<td colspan="3" class="usertable">
																			<form:errors path="gameId" cssClass="errorfield"/>
																			<form:errors path="botId" cssClass="errorfield"/>
																			<form:errors path="botName" cssClass="errorfield"/>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2" class="usertable">
																			<form:select path="botId">
	    																		<form:option label="Choose existing bot" value=""/>
	    																		<form:options items="${bot.bots}" itemValue="id" itemLabel="login" />
																			</form:select>
																			<br />
																			<nobr>or add new bot:&nbsp;<form:input path="botName" size="32" maxlength="32" /></nobr>
																		</td>
																		<td><input type="submit" value="Add" /></td>
																	</tr>
																</form:form>
																<form:form commandName="bot" action="bots">
                                                                    <form:hidden path="gameId" />
                                                                        <tr>
	                                                                        <td colspan="2" class="usertable">
	                                                                           <input type="submit" value="Add many bots" />
	                                                                       </td>
	                                                                   </tr>
                                                                </form:form>
                                                                <form:form commandName="bot" action="bots/points">
                                                                    <form:hidden path="gameId" />
                                                                        <tr>
                                                                            <td colspan="2" class="usertable">
                                                                               <input type="submit" value="Add random points to all bots" />
                                                                           </td>
                                                                       </tr>
                                                                </form:form>
															</tfoot>
														</sec:authorize>
													</table>
												</c:if>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
