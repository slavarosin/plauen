<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table width="30%" height="10" border="0" cellspacing="0" cellpadding="0">
	<tbody>
		<tr>
			<td align="center" valign="bottom" class="Small_text"><fmt:message key="app.footer" /> | <a class="Small_text" href="<c:url value='/do/terms'/>"><fmt:message key="app.footer.terms" /></a>
			<br>Powered by <a href="http://fortumo.com/" class="Small_text">fortumo.com</a></td>
		</tr>
	</tbody>
</table>