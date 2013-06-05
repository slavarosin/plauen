<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table width="100%" height="26" border="0" cellpadding="0" cellspacing="0" background='<c:url value="/images/bg_menu.png" />' bgcolor="#333333">
	<tbody>
		<tr>
			<td width="30" align="left" valign="middle">
				<table width="5" height="26" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td align="left" valign="top"><img src='<c:url value="/images/5px_white_top_l_invert.png" />' width="5" height="5" alt="" /></td>
						</tr>
						<tr>
							<td align="left" valign="bottom"><img src='<c:url value="/images/5px_white_bot_l_invert.png" />' width="5" height="5" alt="" /></td>
						</tr>
					</tbody>
				</table>
			</td>
			<td align="left" valign="middle" class="whitetext12bold">
				<a href="<c:url value="/do/game/active"/>" class="menu"><fmt:message key="menu.game.active" /></a>
				<a href="<c:url value="/do/rules"/>" class="menu"><fmt:message key="menu.rules" /></a>
				<a href="<c:url value="/do/questions"/>" class="menu"><fmt:message key="menu.questions" /></a>
				<a href="<c:url value="/do/contacts"/>" class="menu"><fmt:message key="menu.contacts" /></a>
			</td>
		</tr>
	</tbody>
</table>
