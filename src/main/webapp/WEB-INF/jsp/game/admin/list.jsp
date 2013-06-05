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
															<td class="HeaderBIG" align="center" bgcolor="#687b83"><div class="white"><fmt:message key="menu.game.list" /></div></td>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td><img src="<c:url value='/images/10px_gray_bot_l.png'/>" width="10" height="10"></td>
															<td bgcolor="#687b83"></td>
															<td><img src="<c:url value='/images/10px_gray_bot_r.png'/>" width="10" height="10"></td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td align="center" valign="top" style="padding: 1em">
																<sec:authorize ifAllGranted="ROLE_ADMIN">
																	<div style="float: left; margin-left: 0em; margin-bottom: 1em;" id="calendar-container"></div>
																	<script type="text/javascript">
																		function dateIsSpecial(year, month, day) {
																			<c:forEach items="${games}" var="game">
																				if (year == <fmt:formatDate value="${game.activateAt.time}" pattern="yyyy" /> && month == (<fmt:formatDate value="${game.activateAt.time}" pattern="MM" /> - 1) && day == <fmt:formatDate value="${game.activateAt.time}" pattern="dd" />) return true;
																			</c:forEach>
																			else return false;
																		};

																		function dateChanged(calendar) {
																		  // Beware that this function is called even if the end-user only
																		  // changed the month/year.  In order to determine if a date was
																		  // clicked you can use the dateClicked property of the calendar:
																		  if (calendar.dateClicked) {
																		    // OK, a date was clicked, redirect to /yyyy/mm/dd/index.php
																		    var y = calendar.date.getFullYear();
																		    var m = calendar.date.getMonth();     // integer, 0..11
																		    var d = calendar.date.getDate();      // integer, 1..31

																		    // redirect...
																		    if (dateIsSpecial(y, m, d)) {
																		    	window.location = "<c:url value='view?activateAt=" + d + "." + (m + 1) + "." + y + "'/>";
																		    } else {
																		    	window.location = "<c:url value='create?activateAt=" + d + "." + (m + 1) + "." + y + "'/>";
																		    }
																		  }
																		};

																		function ourDateStatusFunc(date, y, m, d) {
																		    if (dateIsSpecial(y, m, d))
																		      return "special";
																		    else
																		      return false; // other dates are enabled
																		      // return true if you want to disable other dates
																		};

																		Calendar.setup(
																		  {
																		    flat         : "calendar-container", // ID of the parent element
																		    flatCallback : dateChanged,           // our callback function
																		    dateStatusFunc : ourDateStatusFunc
																		  }
																		);
																	</script>
																</sec:authorize>
															</td>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td>
																<table width="100%" border="0" cellspacing="0" cellpadding="0" style="color:white">
																	<thead>
																		<tr style="font-weight: bold; background-color: white; color: black">
																			<th>ID</th>
																			<th>Activated At</th>
																			<th>Started At</th>
																			<th>Finished At</th>
																			<th>Name</th>
																			<th>Comment</th>
																			<th>Registration opened?</th>
																			<th>Bidding started?</th>
																			<th>Closed?</th>
																		</tr>
																	</thead>
																	<tbody>
																		<c:forEach items="${games}" var="game">
																			<tr>
																				<td>
																					<a href="<c:url value='/do/game/view?id=${game.id}'/>" style="color:white">${game.id}</a>
																				</td>
																				<td>
																					<fmt:formatDate value="${game.activateAt.time}" dateStyle="medium" type="both" />
																				</td>
																				<td>
																					<fmt:formatDate value="${game.startedAt.time}" dateStyle="medium" type="both" />
																				</td>
																				<td>
																					<fmt:formatDate value="${game.finishedAt.time}" dateStyle="medium" type="both" />
																				</td>
																				<td>
																					${game.present.name}
																				</td>
																				<td>
																					${game.present.comment}
																				</td>
																				<td>
																					<c:choose><c:when test="${game.registrationOpened}"><span style="color: green">Y</span></c:when><c:otherwise><span style="color: gray">N</span></c:otherwise></c:choose>
																				</td>
																				<td>
																					<c:choose><c:when test="${game.biddingOpened}"><span style="color: green">Y</span></c:when><c:otherwise><span style="color: gray">N</span></c:otherwise></c:choose>
																				</td>
																				<td>
																					<c:choose><c:when test="${game.gameClosed}"><span style="color: green">Y</span></c:when><c:otherwise><span style="color: gray">N</span></c:otherwise></c:choose>
																				</td>
																			</tr>
																		</c:forEach>
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