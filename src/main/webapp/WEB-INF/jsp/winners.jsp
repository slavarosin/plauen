<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty winners}">
	<table width="200" border="0" cellpadding="0" cellspacing="0" style="background-image: url(<c:url value='/images/bg_left_column_bot.png'/>); background-repeat: repeat-x; background-position: top left;">
	    <tbody>
	        <tr>
	            <td height="10" align="left" valign="top"><img src="<c:url value='/images/10px_white_top_l_invert.png' />" width="10" height="10" alt="" /></td>
	            <td align="right" valign="top"><img src="<c:url value='/images/10px_white_top_r_invert.png' />" width="10" height="10" alt="" /></td>
	        </tr>
	        <tr>
	            <td colspan="2" align="center" valign="top">
	                <div class="HeaderBIG" style="padding:10pt"><fmt:message key="winners.title"/></div>
	                <c:forEach items="${winners}" var="winner">
		                <table border="0" cellspacing="0" cellpadding="0" style="padding: 20pt">
		                    <tbody>
		                        <tr>
		                            <td align="center" valign="top"><img src="<c:url value='/do/image?id=${winner.game.present.images[0].id}' />" width="60" height="90" class="fadePic" alt="" /></td>
		                        </tr>
		                        <tr>
		                            <td align="center" valign="top">
		                                <table border="0" cellpadding="0" cellspacing="0">
		                                    <tbody>
		                                        <tr>
		                                            <td><img src="<c:url value="/images/5px_gray_top_l.png" />" border="0" alt="" /></td>
		                                            <td bgcolor="#BCC6CA"></td>
		                                            <td><img src="<c:url value="/images/5px_gray_top_r.png" />" alt="" border="0" /></td>
		                                        </tr>
		                                        <tr>
		                                            <td align="center" valign="middle" bgcolor="#BCC6CA">&nbsp;</td>
		                                            <td align="center" valign="middle" bgcolor="#BCC6CA" class="blacktext12 gray">&nbsp;&nbsp;&nbsp;${winner.game.present.name}&nbsp;&nbsp;&nbsp;</td>
		                                            <td align="center" valign="middle" bgcolor="#BCC6CA">&nbsp;</td>
		                                        </tr>
		                                        <tr>
		                                            <td><img src="<c:url value='/images/5px_gray_bot_l.png' />" alt="" border="0" /></td>
		                                            <td bgcolor="#BCC6CA"></td>
		                                            <td><img src="<c:url value='/images/5px_gray_bot_r.png' />" alt="" border="0" /></td>
		                                        </tr>
		                                    </tbody>
		                                </table>
		                                <table border="0" cellpadding="0" cellspacing="0">
		                                    <tbody>
		                                        <tr>
		                                            <td width="100" colspan="3" align="center" valign="middle" bgcolor="#BCC6CA" class="Small_text">
		                                                <fmt:message key="winners.winner"/>
		                                                <br />
		                                                <span class=whitetext10bold>${winner.user.login}</span>
		                                            </td>
		                                        </tr>
		                                        <tr>
		                                            <td height="5" align="center" valign="middle"><img src="<c:url value='/images/5px_gray_bot_l.png' />" alt="" border="0" /></td>
		                                            <td width="90" align="center" valign="middle" bgcolor="#BCC6CA"></td>
		                                            <td align="center" valign="middle"><img src="<c:url value='/images/5px_gray_bot_r.png' />" alt="" border="0" /></td>
		                                        </tr>
		                                    </tbody>
		                                </table>
		                            </td>
		                        </tr>
		                    </tbody>
		                </table>
	                </c:forEach>
	                <table width="200" border="0" cellspacing="0" cellpadding="0" style="padding: 20pt">
	                    <tbody>
	                        <tr>
	                            <td align="center" valign="bottom"><img src="<c:url value="/images/tumba.png" />" alt="" width="150" height="77"></td>
	                        </tr>
	                    </tbody>
	                </table>
	            </td>
	        </tr>
	    </tbody>
	</table>
</c:if>