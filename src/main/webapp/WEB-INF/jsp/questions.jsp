<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table width="100%" border="0" cellspacing="26" cellpadding="0">
	<tbody>
		<tr>
			<td align="center" valign="top">
			<table bgcolor="#bcc6ca" border="0" cellpadding="0" cellspacing="0" width="100%" style="padding-bottom: 20pt">
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
												<td><img src="<c:url value='/images/10px_gray_top_r.png'/>" alt="" width="10" height="10"></td>
											</tr>
											<tr bgcolor="#687b83">
												<td>&nbsp;</td>
												<td class="HeaderBIG" align="center" bgcolor="#687b83"><div class="white"><fmt:message key="questions.title" /></div></td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td><img src="<c:url value='/images/10px_gray_bot_l.png'/>" alt="" width="10" height="10"></td>
												<td bgcolor="#687b83"></td>
												<td><img src="<c:url value='/images/10px_gray_bot_r.png'/>" alt="" width="10" height="10"></td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>
													<br>
                                                    <p class="MainText" style="color:black; text-align: justify;">
													    <c:import url="questions/textOfQuestions_${locale}.jsp"/>
													</p>
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