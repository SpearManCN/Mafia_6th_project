<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mafia</title>
</head>
<script src="/js/sockjs.min.js"></script>
<script src="/js/stomp.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2/dist/stomp.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    var stompClient = null;

    function connect() {
        if(confirm("Are you sure you want to start a new chat?")==false){return;}
        alert("12344t");
        var socket = new WebSocket('ws://' + window.location.host + '/chat');
        alert("prkf");
        $("[name=totUserSpan]").text( "wind.location.host : "+window.location.host);
        alert("f");
        stompClient = Stomp.over(socket);
        alert("over");
        stompClient.connect({}, function () {
            alert( "connected");
        });
        alert("after");
    }
        //     stompClient.subscribe('/topic/public/'+sessionId, function (chatMessage) {
        //         $("[name=brokerName]").val(JSON.parse(chatMessage.body).brokerName);
        //         // $("[name=memberName]").val(JSON.parse(chatMessage.body).receiver);
        //         showChatMessage(JSON.parse(chatMessage.body));
        //     });
        //     stompClient.send("/app/chat/sendMessage/"+sessionId, {}, JSON.stringify({sender:sessionId, content:"", type: 'JOIN' }));


    function updateTotUser() {
        alert("1233323");
        $.ajax({
            url: '/updateUserNo',  // 서버에서 유저 목록을 반환하는 엔드포인트
            success: function(no) {
                alert("123123");
                $("[name=totUserSpan]").text( "현재 접속 인원 : "+no);
                // $("[name=totUserSpan]").text( "Now Users : " +no);
            },
            error:function(){
                alert("error");
            }
        });
    }
    // setInterval(alert("555"), 1000);
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