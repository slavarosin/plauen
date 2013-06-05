<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table width="100%" border="0" cellspacing="26" cellpadding="0">
	<tbody>
		<tr>
			<td align="center" valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="26" bgcolor="#BCC6CA" style="background-image: url(<c:url value='/images/bg_left_column_bot.png' />);background-repeat: repeat-x;background-position: top left;">
					<tr>
						<td align="center" valign="top">
							<table width="70%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td colspan="2" align="center" valign="top">
										<table width="80%" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td height="10"><img src="<c:url value='/images/10px_gray_top_l.png' />" width="10" height="10"/></td>
												<td width="100%" height="5" bgcolor="#687B83"/>
												<td height="10"><img src="<c:url value='/images/10px_gray_top_r.png' />" alt="" width="10" height="10"/></td>
											</tr>
											<tr bgcolor="#687B83">
												<td>&nbsp;</td>
												<td align="center" bgcolor="#687B83" class="HeaderBIG"><fmt:message key="games.empty" /></td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td height="10"><img src="<c:url value='/images/10px_gray_bot_l.png' />" alt="" width="10" height="10"/></td>
												<td height="5" bgcolor="#687B83"/>
												<td height="10"><img src="<c:url value='/images/10px_gray_bot_r.png' />" alt="" width="10" height="10"/></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</tbody>
</table>