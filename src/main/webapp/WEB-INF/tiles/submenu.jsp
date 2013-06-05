<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize ifAllGranted="ROLE_ADMIN">
	<table width="100%" height="26" border="0" cellspacing="0" cellpadding="0">
		<tbody>
			<tr>
				<td align="center" valign="top">
					<table width="80%" height="26" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
						<tbody>
							<tr>
								<td height="21" align="center" valign="middle" class="Small_text"></td>
								<td align="center" valign="middle" class="Small_text">
									<a href="<c:url value="/do/game/list"/>" class="menu"><fmt:message key="menu.game.list" /></a>
									&nbsp;|&nbsp;<a href="<c:url value="/do/game/create"/>" class="menu"><fmt:message key="menu.game.create" /></a>
									&nbsp;|&nbsp;<a href="<c:url value="/do/send"/>" class="menu"><fmt:message key="menu.admin.send" /></a>
								</td>
								<td align="center" valign="middle" class="Small_text"></td>
							</tr>
							<tr>
								<td height="5" align="left" valign="bottom" class="Small_text"><img src="<c:url value='/images/5px_white_bot_l_invert.png' />" width="5" height="5" alt="" /></td>
								<td align="center" valign="middle" class="Small_text"></td>
								<td align="right" valign="bottom" class="Small_text"><img src="<c:url value='/images/5px_white_bot_r_invert.png' />" width="5" height="5" alt="" /></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</sec:authorize>
<c:if test="${not empty message}">
    <table width="100%" height="26" border="0" cellspacing="0" cellpadding="0">
        <tbody>
            <tr>
                <td align="center" valign="top">
                    <table width="80%" height="26" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                        <tbody>
                            <tr>
                                <td height="21" align="center" valign="middle" class="Small_text"></td>
                                <td align="center" valign="middle" class="Small_text">${message}</td>
                                <td align="center" valign="middle" class="Small_text"></td>
                            </tr>
                            <tr>
                                <td height="5" align="left" valign="bottom" class="Small_text"><img src="<c:url value='/images/5px_white_bot_l_invert.png' />" width="5" height="5" alt="" /></td>
                                <td align="center" valign="middle" class="Small_text"></td>
                                <td align="right" valign="bottom" class="Small_text"><img src="<c:url value='/images/5px_white_bot_r_invert.png' />" width="5" height="5" alt="" /></td>
                            </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
        </tbody>
    </table>
    <c:remove var="message" scope="session"/>
</c:if>