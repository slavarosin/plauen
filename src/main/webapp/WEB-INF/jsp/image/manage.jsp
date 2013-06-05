<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<table width="100%" border="0" cellspacing="26" cellpadding="0">
	<tbody>
		<tr>
			<td align="center" valign="top">
				<table style="background-image: url(<c:url value='/images/bg_dark.png'/>); background-repeat: repeat-x; background-position: left top;" bgcolor="#171b1c" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>
						<tr>
							<td align="right" valign="middle"><a
href="<c:url value='/do/game/view?id=${game.id}' />" class="white" style="padding: 10pt"><fmt:message
key="menu.image.manage.back"/></a>|<a
href="<c:url value='/do/game/edit?id=${game.id}'/>" class="white" style="padding: 10pt"><fmt:message key="menu.image.manage.editGame"/></a></td>
						</tr>
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
															<td><img src="<c:url value='/images/10px_gray_top_r.png'/>" width="10" height="10"></td>
														</tr>
														<tr bgcolor="#687b83">
															<td>&nbsp;</td>
															<td class="HeaderBIG" align="center" bgcolor="#687b83"><div class="white"><fmt:message key="menu.image.manage" /></div></td>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td><img src="<c:url value='/images/10px_gray_bot_l.png'/>" width="10" height="10"></td>
															<td bgcolor="#687b83"></td>
															<td><img src="<c:url value='/images/10px_gray_bot_r.png'/>" width="10" height="10"></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td>
																<table width="100%" cellpadding="5" cellspacing="10" border="0">
																	<col width="20%" />
																	<col width="80%" />
																	<tbody>
																		<c:choose>
																			<c:when test="${not empty game.present.images[0]}">
																				<tr>
																					<td align="center" valign="middle">
																								<img src="<c:url value='/do/image?id=${game.present.images[0].id}' />" width="60" height="90"/>
																					</td>
																					<td>
																						<a href="delete?gameId=${game.id}&id=${game.present.images[0].id}" onclick="return confirm('<fmt:message key="menu.image.manage.delete.confirm" />');" class="white"><fmt:message key="menu.image.manage.delete" /></a>
																					</td>
																				</tr>
																			</c:when>
																		</c:choose>
																		<c:choose>
																			<c:when test="${not empty game.present.images[1]}">
																				<tr>
																					<td align="center" valign="middle">
																						<img src="<c:url value='/do/image?id=${game.present.images[1].id}' />" width="60" height="90"/>
																					</td>
																					<td>
																						<a href="delete?gameId=${game.id}&id=${game.present.images[0].id}" onclick="return confirm('<fmt:message key="menu.image.manage.delete.confirm" />');" class="white"><fmt:message key="menu.image.manage.delete" /></a>
																					</td>
																				</tr>
																			</c:when>
																		</c:choose>
																		<c:choose>
																			<c:when test="${not empty game.present.images[2]}">
																				<tr>
																					<td align="center" valign="middle">
																						<img src="<c:url value='/do/image?id=${game.present.images[2].id}' />" width="60" height="90"/>
																					</td>
																					<td>
																						<a href="delete?gameId=${game.id}&id=${game.present.images[0].id}" onclick="return confirm('<fmt:message key="menu.image.manage.delete.confirm" />');" class="white"><fmt:message key="menu.image.manage.delete" /></a>
																					</td>
																				</tr>
																			</c:when>
																		</c:choose>
																	</tbody>
																</table>
															</td>
															<td>&nbsp;</td>
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