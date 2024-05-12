<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mafia</title>
</head>
<script src="/js/sockjs.min.js"></script>
<script src="/js/stomp.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>
    var stompClient = null;
    var myNick = "${myNick}";
    function connect() {
        alert(myNick);
        if(confirm("게임 대기열에 등록하시겠습니까?")==false){return;}

        var socket = new WebSocket('ws://' + window.location.host + '/chat');
        disconnect();
        $("[name=test]").text( "wind.location.host : "+window.location.host);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function () {
                stompClient.subscribe('/topic/public/'+myNick, function (Message) {
                    alert(JSON.parse(Message.body).message);
                    // $("[name=brokerName]").val(JSON.parse(Message.body).brokerName);
                    // $("[name=memberName]").val(JSON.parse(chatMessage.body).receiver);
                    // showChatMessage(JSON.parse(chatMessage.body));
                });
            stompClient.send("/app/chat/regist/"+myNick, {}, JSON.stringify({sender:myNick, content:"", type: 'SYSTEM' }));

            alert( "connected");
        });
    }
    function disconnect() {
        if (stompClient !== null) {
            stompClient.disconnect();
        }
    }
        //     stompClient.subscribe('/topic/public/'+sessionId, function (chatMessage) {
        //         $("[name=brokerName]").val(JSON.parse(chatMessage.body).brokerName);
        //         // $("[name=memberName]").val(JSON.parse(chatMessage.body).receiver);
        //         showChatMessage(JSON.parse(chatMessage.body));
        //     });
        //     stompClient.send("/app/chat/sendMessage/"+sessionId, {}, JSON.stringify({sender:sessionId, content:"", type: 'JOIN' }));


    function updateTotUser() {
        $.ajax({
            url: '/updateUserNo',  // 서버에서 유저 목록을 반환하는 엔드포인트
            success: function(no) {
                $("[name=totUserSpan]").text( "현재 접속 인원 : "+no);
            },
            error:function(){
            }
        });
    }
    setInterval(updateTotUser, 1000);
</script>
<body>
<%@ include file="home.jsp" %>
<div align="center" width="1000px" height="700px">
    <div>
        <span name="totUserSpan"> </span>
    </div>
    <div>
        <span name="test"></span>
<%--        내 점수  n 점--%>
    </div>
    <div>n승 n패
    </div>

    <button onclick="connect()">매칭하기</button>
    <button onclick="updateTotUser()">유저수</button>
</div>

</body>
</html>