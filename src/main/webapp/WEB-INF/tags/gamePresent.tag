<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ attribute name="game" type="net.gobro.plauen.web.beans.GameBean" required="true" %>
<%@ attribute name="type" type="java.lang.String" required="true" %>

<c:set var="playStatus">
	<c:choose>
		<c:when test="${not empty game.userPlay}">notAgreed</c:when>
		<c:otherwise>notAgreed</c:otherwise>
	</c:choose>
</c:set>

<table border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td><img src="<c:url value='/images/5px_gray_top_l.png' />" border="0"/></td>
			<td class="${playStatus}"/>
			<td><img src="<c:url value='/images/5px_gray_top_r.png' />" border="0"/></td>
		</tr>
		<tr>
			<td align="center" valign="middle" class="${playStatus}"/>
			<td align="center" valign="middle" class="${playStatus} blacktext12">
				&nbsp;&nbsp;&nbsp;<a href="view?id=${game.id}" class="black">${game.name}</a>&nbsp;&nbsp;&nbsp;
			</td>
			<td align="center" valign="middle" class="${playStatus}"/>
		</tr>
		<tr>
			<td><img src="<c:url value='/images/5px_gray_bot_l.png' />" border="0"/></td>
			<td class="${playStatus}"/>
			<td><img src="<c:url value='/images/5px_gray_bot_r.png' />" border="0"/></td>
		</tr>
	</tbody>
</table>