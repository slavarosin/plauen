<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ attribute name="game" type="net.gobro.plauen.web.beans.GameBean" required="true" %>
<%@ attribute name="type" type="java.lang.String" required="true" %>
<%@ attribute name="mode" type="java.lang.String" required="true" description="The mode the component is worked in: shows active games or future games" %>

<c:choose>
    <c:when test="${mode eq 'future'}">
        <fmt:formatDate value="${game.activateAt.time}" pattern="E" var="day"/>
        <fmt:formatDate value="${game.activateAt.time}" pattern="dd MMMM" var="dateInMonth"/>
    </c:when>
    <c:when test="${mode eq 'active'}">
        <fmt:formatDate value="${game.finishedAt.time}" pattern="E" var="day"/>
        <fmt:formatDate value="${game.finishedAt.time}" pattern="dd MMMM" var="dateInMonth"/>
     </c:when>
</c:choose>

<c:set var="fontsizeSmall" value="100"/>
<c:set var="fontsizeBig" value="100"/>

<c:choose>
    <c:when test="${fn:length(day) eq 3}">
        <c:set var="fontsizeSmall" value="70"/>
        <c:set var="fontsizeBig" value="90"/>
    </c:when>
    <c:when test="${fn:length(day) eq 2}">
        <c:set var="fontsizeSmall" value="80"/>
    </c:when>
    <c:when test="${fn:length(day) eq 1}">
        <c:set var="fontsizeBig" value="150"/>
    </c:when>
</c:choose>

<table class="date">
	<c:choose>
		<c:when test="${type eq 'main'}">
			<tr id="selected">
				<td class="day" style="font-size: ${fontsizeBig}%;">${day}</td>
				<td class="dateInMonth">${dateInMonth}</td>
			</tr>
		</c:when>
		<c:when test="${type eq 'other'}">
			<tr>
				<td>
                    <table class="day" width="100%">
                        <tr>
                            <td width="25px" style="font-size: ${fontsizeSmall}%;">${day}</td>
                            <td width="7px" >&nbsp;</td>
                        </tr>
                    </table>
                </td>
                <td class="dateInMonth">${dateInMonth}</td>
			</tr>
		</c:when>
	</c:choose>
</table>
