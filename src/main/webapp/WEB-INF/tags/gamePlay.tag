<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@ attribute name="game" type="net.gobro.plauen.web.beans.GameBean" required="true" %>
<%@ attribute name="page" type="java.lang.String" required="true" %>
<%@ attribute name="type" type="java.lang.String" required="true" %>

<c:set var="playStatus">
	<c:choose>
		<c:when test="${not empty game.userPlay}">notAgreed</c:when>
		<c:otherwise>notAgreed</c:otherwise>
	</c:choose>
</c:set>

<c:set var="joinUrl">
	<sec:authorize ifAllGranted="ROLE_ANONYMOUS">
		<c:url value="/do/user/login?gameId=${game.id}" />
	</sec:authorize>
	<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
		<c:url value="join?id=${game.id}" />
	</sec:authorize>
</c:set>

<c:choose>
	<c:when test="${type eq 'main'}">
		<c:choose>
			<c:when test="${not empty game.userPlay}">
                <c:choose>
                    <c:when test="${game.biddingOpened}">
				        <table width="80%" border="0" cellpadding="0" cellspacing="0">
		                    <tr>
		                        <td height="10"><img src="<c:url value='/images/10px_green_top_l.png' />" width="10" height="10"/></td>
		                        <td width="100%" height="5" bgcolor="#59BC5B"/>
		                        <td height="10"><img src="<c:url value='/images/10px_green_top_r.png' />" width="10" height="10"/></td>
		                    </tr>
		                    <tr bgcolor="#59BC5B">
		                        <td>&nbsp;</td>
		                        <td align="center" bgcolor="#59BC5B" class="HeaderBIG">
                                    <fmt:message key="game.view.yourAlias"><fmt:param value="${game.userPlay.alias}" /></fmt:message>
								         </td>
								   <td>&nbsp;</td>
								</tr>
								<tr>
								    <td height="10"><img src="<c:url value='/images/10px_green_bot_l.png' />" width="10" height="10"/></td>
								    <td height="5" bgcolor="#59BC5B"/>
								    <td height="10"><img src="<c:url value='/images/10px_green_bot_r.png' />" width="10" height="10"/></td>
								</tr>
		                </table>
                    </c:when>
                    <c:when test="${game.gameClosed}">
                        <table width="80%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td height="10"><img src="<c:url value='/images/10px_red_top_l.png' />" width="10" height="10"/></td>
                                <td width="100%" height="5" bgcolor="#FF0000"/>
                                <td height="10"><img src="<c:url value='/images/10px_red_top_r.png' />" width="10" height="10"/></td>
                            </tr>
                            <tr bgcolor="#FF0000">
                                <td>&nbsp;</td>
                                <td class="registrationClosed"><fmt:message key="game.finished"/></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td height="10"><img src="<c:url value='/images/10px_red_bot_l.png' />" width="10" height="10"/></td>
                                <td height="5" bgcolor="#FF0000"/>
                                <td height="10"><img src="<c:url value='/images/10px_red_bot_r.png' />" width="10" height="10"/></td>
                            </tr>
                        </table>
					</c:when>
                    <c:otherwise>
                        <table width="80%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td height="10"><img src="<c:url value='/images/10px_red_top_l.png' />" width="10" height="10"/></td>
                                <td width="100%" height="5" bgcolor="#FF0000"/>
                                <td height="10"><img src="<c:url value='/images/10px_red_top_r.png' />" width="10" height="10"/></td>
                            </tr>
                            <tr bgcolor="#FF0000">
                                <td>&nbsp;</td>
                                <td align="center" bgcolor="#FF0000" class="HeaderBIG">
                                    <fmt:message key="game.start"/>&nbsp;<fmt:formatDate value="${game.startedAt.time}" pattern="dd.MM.yy hh:mm" />
                                 </td>
                                 <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td height="10"><img src="<c:url value='/images/10px_red_bot_l.png' />" width="10" height="10"/></td>
                                <td height="5" bgcolor="#FF0000"/>
                                <td height="10"><img src="<c:url value='/images/10px_red_bot_r.png' />" width="10" height="10"/></td>
                            </tr>
                        </table>
                    </c:otherwise>
                </c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${game.biddingOpened}">
						<div class="button_container">
							<div
								class="button_s1"
								onMouseOut="this.className='button_s1'; return false;"
								onMouseOver="this.className='button_s1_over'; return false;"
								onClick="javascript:window.location = '${joinUrl}'; return false;">
									<div class="button_s1_text">
										<fmt:message key="game.join"/>
									</div>
							</div>
						</div>
					</c:when>
					<c:when test="${game.gameClosed}">
                        <table width="80%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td height="10"><img src="<c:url value='/images/10px_red_top_l.png' />" width="10" height="10"/></td>
                                <td width="100%" height="5" bgcolor="#FF0000"/>
                                <td height="10"><img src="<c:url value='/images/10px_red_top_r.png' />" width="10" height="10"/></td>
                            </tr>
                            <tr bgcolor="#FF0000">
                                <td>&nbsp;</td>
                                <td class="registrationClosed"><fmt:message key="game.finished"/></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td height="10"><img src="<c:url value='/images/10px_red_bot_l.png' />" width="10" height="10"/></td>
                                <td height="5" bgcolor="#FF0000"/>
                                <td height="10"><img src="<c:url value='/images/10px_red_bot_r.png' />" width="10" height="10"/></td>
                            </tr>
                        </table>
					</c:when>
				</c:choose>
			</c:otherwise>
		</c:choose>
		<span class="Small_text style3"><fmt:message key="game.list.present.plays.number"><fmt:param value="${game.members}" /></fmt:message></span>
	</c:when>
	<c:when test="${type eq 'other'}">
		<table width="80%" border="0" cellpadding="0" cellspacing="0">
			<col width="5" />
			<col width="100%" />
			<col width="5" />
			<tbody>
				<tr>
					<td colspan="3" align="center" valign="middle" class="${playStatus}" style="padding: 0pt;"><c:choose>
							<c:when test="${not empty game.userPlay}">
								<c:if test="${page eq 'list'}">
								    <c:choose>
								        <c:when test="${game.biddingOpened}">
											<table width="100%" border="0" cellpadding="0" cellspacing="0">
											    <tr>
											        <td height="10" bgcolor="white"><img src="<c:url value='/images/10px_green_top_l.png' />" width="10" height="10"/></td>
											        <td width="100%" height="5" bgcolor="#59BC5B"/>
											        <td height="10" bgcolor="white"><img src="<c:url value='/images/10px_green_top_r.png' />" width="10" height="10"/></td>
											    </tr>
											    <tr bgcolor="#59BC5B">
											        <td>&nbsp;</td>
											        <td class="yourAlias">
		                                                <fmt:message key="game.view.yourAlias"><fmt:param value="${game.userPlay.alias}" /></fmt:message>
											        </td>
											        <td>&nbsp;</td>
											    </tr>
											    <tr>
											        <td height="10" bgcolor="white"><img src="<c:url value='/images/10px_green_bot_l.png' />" width="10" height="10"/></td>
											        <td height="5" bgcolor="#59BC5B"/>
											        <td height="10" bgcolor="white"><img src="<c:url value='/images/10px_green_bot_r.png' />" width="10" height="10"/></td>
											    </tr>
											</table>
										</c:when>
										<c:when test="${game.gameClosed}">
					                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
					                            <tr>
					                                <td height="10" bgcolor="white"><img src="<c:url value='/images/10px_red_top_l.png' />" width="10" height="10"/></td>
					                                <td width="100%" bgcolor="#FF0000"/>
					                                <td height="10" bgcolor="white"><img src="<c:url value='/images/10px_red_top_r.png' />" width="10" height="10"/></td>
					                            </tr>
					                            <tr bgcolor="#FF0000">
					                                <td>&nbsp;</td>
					                                <td class="registrationClosed"><fmt:message key="game.finished"/></td>
					                                <td>&nbsp;</td>
					                            </tr>
					                            <tr>
					                                <td height="10" bgcolor="white"><img src="<c:url value='/images/10px_red_bot_l.png' />" width="10" height="10"/></td>
					                                <td height="5" bgcolor="#FF0000"/>
					                                <td height="10" bgcolor="white"><img src="<c:url value='/images/10px_red_bot_r.png' />" width="10" height="10"/></td>
					                            </tr>
					                        </table>
	                                    </c:when>
                                        <c:otherwise>
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td height="10" bgcolor="white"><img src="<c:url value='/images/10px_red_top_l.png' />" width="10" height="10"/></td>
                                                    <td width="100%" height="5" bgcolor="#FF0000"/>
                                                    <td height="10" bgcolor="white"><img src="<c:url value='/images/10px_red_top_r.png' />" width="10" height="10"/></td>
                                                </tr>
                                                <tr bgcolor="#FF0000">
                                                    <td>&nbsp;</td>
                                                    <td class="yourAlias">
                                                        <fmt:message key="game.start"/><br><fmt:formatDate value="${game.startedAt.time}" pattern="dd.MM" />
                                                    </td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td height="10" bgcolor="white"><img src="<c:url value='/images/10px_red_bot_l.png' />" width="10" height="10"/></td>
                                                    <td height="5" bgcolor="#FF0000"/>
                                                    <td height="10" bgcolor="white"><img src="<c:url value='/images/10px_red_bot_r.png' />" width="10" height="10"/></td>
                                                </tr>
                                            </table>
                                        </c:otherwise>
                                    </c:choose>
								</c:if>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${game.biddingOpened}">
                                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
				                            <tr>
				                                <td height="5" bgcolor="white"><img src="<c:url value='/images/5px_gray_top_l.png' />" width="5" height="5"/></td>
				                                <td class="${playStatus}"/>
				                                <td height="5" bgcolor="white"><img src="<c:url value='/images/5px_gray_top_r.png' />" width="5" height="5"/></td>
				                            </tr>
				                            <tr>
				                                <td>&nbsp;</td>
				                                <td width="100%" class="${playStatus}" valign="middle" align="center"><div class="button_container"><div
													class="button_s3"
													onMouseOut="this.className='button_s3'; return false;"
													onMouseOver="this.className='button_s3_over'; return false;"
													onClick="javascript:window.location = '${joinUrl}'; return false;"><div
													class="button_s3_text"><fmt:message key="game.join"/></div></div></div></td>
				                                <td>&nbsp;</td>
				                            </tr>
				                            <tr>
				                                <td height="5" bgcolor="white"><img src="<c:url value='/images/5px_gray_bot_l.png' />" width="5" height="5"/></td>
				                                <td class="${playStatus}"/>
				                                <td height="5" bgcolor="white"><img src="<c:url value='/images/5px_gray_bot_r.png' />" width="5" height="5"/></td>
				                            </tr>
				                        </table>
				                    </c:when>
				                    <c:when test="${game.gameClosed}">
				                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
				                            <tr>
				                                <td height="10" bgcolor="white"><img src="<c:url value='/images/10px_red_top_l.png' />" width="10" height="10"/></td>
				                                <td width="100%" bgcolor="#FF0000"/>
				                                <td height="10" bgcolor="white"><img src="<c:url value='/images/10px_red_top_r.png' />" width="10" height="10"/></td>
				                            </tr>
				                            <tr bgcolor="#FF0000">
				                                <td>&nbsp;</td>
				                                <td class="registrationClosed"><fmt:message key="game.finished"/></td>
				                                <td>&nbsp;</td>
				                            </tr>
				                            <tr>
				                                <td height="10" bgcolor="white"><img src="<c:url value='/images/10px_red_bot_l.png' />" width="10" height="10"/></td>
				                                <td height="5" bgcolor="#FF0000"/>
				                                <td height="10" bgcolor="white"><img src="<c:url value='/images/10px_red_bot_r.png' />" width="10" height="10"/></td>
				                            </tr>
				                        </table>
                                    </c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center" valign="middle" style="padding: 0pt;">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td height="5" align="left" valign="middle"><img src="<c:url value='/images/5px_gray_top_l.png' />" border="0" width="5" height="5" /></td>
									<td class="${playStatus}" width="100%"/>
									<td height="5" align="right" valign="middle"><img src="<c:url value='/images/5px_gray_top_r.png' />" border="0" width="5" height="5" /></td>
								</tr>
								<tr>
									<td class="gameMembers ${playStatus}">&nbsp;</td>
									<td class="gameMembers ${playStatus}" valign="middle"><fmt:message key="game.list.present.plays.number"><fmt:param value="${game.members}" /></fmt:message></td>
									<td class="gameMembers ${playStatus}">&nbsp;</td>
								</tr>
								<tr>
									<td height="5" align="left" valign="middle"><img src="<c:url value='/images/5px_gray_bot_l.png' />" border="0" width="5" height="5" /></td>
									<td class="${playStatus}" width="100%"/>
									<td height="5" align="right" valign="middle"><img src="<c:url value='/images/5px_gray_bot_r.png' />" border="0" width="5" height="5" /></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</c:when>
</c:choose>