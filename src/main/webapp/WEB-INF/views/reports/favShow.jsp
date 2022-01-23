
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />

<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />


<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>いいね一覧</h2>
        <table id="favorite_list">
            <tbody>
                <tr>
                    <th class="favorite_name">氏名</th>
                    <th class="favorite_date">日付</th>
                </tr>
                <c:forEach var="favorite" items="${favorites}" varStatus="status">
                    <fmt:parseDate value="${favorite.createdAt}" pattern="yyyy-MM-dd" var="favoriteDay" type="date" />
                    <tr class="row${status.count % 2}">
                        <td class="report_name"><c:out value="${favorite.employee.name}" /></td>
                        <td class="report_date"><fmt:formatDate value='${favoriteDay}' pattern='yyyy-MM-dd' /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${favorites_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((favorites_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actRep}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>


        <p>
            <a href="<c:url value='?action=${actRep}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>