<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <title>Mafia</title>
</head>
<style>
    tr{
        height:50px;
    }
    td{
        text-align: center;
        font-size: 20px;
        font-family: Arial;
    }
    .rankTd{
        width:10%;
    }
    .nickTd{
        width:40%;
    }
    .scoreTd{
        width:20%
    }
    .winTd .lossTd{
        width:15%;
    }
    #firstTr{
        background-color: grey;
    }
</style>
<body>
<%@ include file="home.jsp" %>
<div align="center" width="500px" height="400px">
    <table>
        <tr id="firstTr">
            <td class="rankTd">
                등수
            </td>
            <td class="nickTd">
                닉네임
            </td>
            <td class="scoreTd">
                점수
            </td>
            <td class="winTd">
                승
            </td>
            <td class="lossTd">
                패
            </td>
        </tr>
    <c:forEach var="ranks" items="${ranking}" varStatus="loopTagStatus">
        <tr>
            <td>
                ${loopTagStatus.count}
            </td>
            <td>
                ${ranks.nick}
            </td>
            <td>
                ${ranks.score}
            </td>
            <td>
                ${ranks.win}
            </td>
            <td>
                ${ranks.loss}
            </td>
        </tr>
    </c:forEach>
    </table>
</div>
</body>
</html>