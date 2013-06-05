<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ attribute name="game" type="net.gobro.plauen.web.beans.GameBean" required="true" %>
<%@ attribute name="type" type="java.lang.String" required="true" %>

<a href="view?id=${game.id}">
	<c:choose>
		<c:when test="${type eq 'other'}">
			<div class="image">
				<img src="<c:url value='/do/image?id=${game.images[0]}' />" width="60" height="90" border="0" />
			</div>
		</c:when>
		<c:when test="${type eq 'main'}">
			<table width="100%" height="320" border="0" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td><img src="<c:url value='/images/10px_white_top_l.png' />" width="10" height="10" border="0" /></td>
						<td bgcolor="#FFFFFF"/>
						<td><img src="<c:url value='/images/10px_white_top_r.png' />" width="10" height="10" border="0" /></td>
					</tr>
					<tr bgcolor="#FFFFFF">
						<td/>
						<td height="100%" class="image"><div class="bigimage"><img src="<c:url value='/do/image?id=${game.images[0]}' />" border="0" height="270" width="180"/></div></td>
						<td/>
					</tr>
					<tr>
						<td><img src="<c:url value='/images/10px_white_bot_l.png' />" width="10" height="10" border="0" /></td>
						<td bgcolor="#FFFFFF"/>
						<td><img src="<c:url value='/images/10px_white_bot_r.png' />" width="10" height="10" border="0" /></td>
					</tr>
				</tbody>
			</table>
		</c:when>
	</c:choose>
</a>