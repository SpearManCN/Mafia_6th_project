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
<style>
    .chat{
        height:300px;
        width:400px;
        overflow: auto;
    }
    li {
        text-align: left;
    }
</style>
<script>
    var stompClient = null;
    var myNick = "${myNick}";
    var myRole = "";
    var roomNo;
    var userArr = [];
    $(function(){
        $("[name=exit]").hide();
        $("[name=chatDiv]").hide();
        $('#messageForm').on('submit', function (e) {
            e.preventDefault();
            sendMessage();
        });
    });

    function sendMessage() {
        // brokerName = $("[name=brokerName]").val();
        // memberName = $("[name=memberName]").val();
        var messageInput = $('#messageInput').val();
        // alert(roomNo);
        stompClient.send("/app/chat/sendMessage/"+roomNo, {}, JSON.stringify({from:myNick, message: messageInput, type: 'CHAT' }));
        $('#messageInput').val('');
    }

    function connect() {
        // alert(myNick);
        if(confirm("게임 대기열에 등록하시겠습니까?")==false){return;}
        $('div[name="gameDiv"]').empty().append('<div> 게임을 찾는 중입니다. </div>');
        var socket = new WebSocket('ws://' + window.location.host + '/chat');
        disconnect();
        $("[name=test]").text( "wind.location.host : "+window.location.host);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function () {
                stompClient.subscribe('/topic/public/'+myNick, function (Message) {
                    // alert(JSON.parse(Message.body).type);
                    if(JSON.parse(Message.body).type == "START"){
                        gameStart(Message);
                    }else if(JSON.parse(Message.body).type == "CHAT"){
                        chat(Message);
                    }else if(JSON.parse(Message.body).type == "GAME"){
                        game(Message);
                    }
                    // alert(JSON.parse(Message.body).type + JSON.parse(Message.body).role);
                    // $("[name=brokerName]").val(JSON.parse(Message.body).brokerName);
                    // $("[name=memberName]").val(JSON.parse(chatMessage.body).receiver);
                    // showChatMessage(JSON.parse(chatMessage.body));
                });
            stompClient.send("/app/chat/regist/"+myNick, {}, JSON.stringify({sender:myNick, content:"", type: 'SYSTEM' }));

            // alert( "connected");
        });
    }

    function gameStart(Message){
        var roleNo = JSON.parse(Message.body).role;
        roomNo = JSON.parse(Message.body).roomNo;
        stompClient.subscribe('/topic/public/'+roomNo, function (Message2) {
            // alert(JSON.parse(Message2.body).type);
            showChatMessage(Message2);
            // if(JSON.parse(Message.body).type == "START"){
            //     gameStart(Message);
            // }else if(JSON.parse(Message.body).type == "CHAT"){
            //     showChatMessage(Message);
            // }else if(JSON.parse(Message.body).type == "GAME"){
            //     game(Message);
            // }
            // alert(JSON.parse(Message.body).type + JSON.parse(Message.body).role);
            // $("[name=brokerName]").val(JSON.parse(Message.body).brokerName);
            // $("[name=memberName]").val(JSON.parse(chatMessage.body).receiver);
            // showChatMessage(JSON.parse(chatMessage.body));
        });
        // 0-시민 1-의사 2-시민 3-마피아
        if (roleNo==1 ){
            myRole = "의사";
        }else if (roleNo==3){
            myRole = "마피아";
        }else {
            myRole = "시민";
        }
        $("[name=chatDiv]").show();
        $("[name=findGame]").hide();
        $("[name=exit]").show();
        $('div[name="gameDiv"]').empty().append('<span style="font-size: 20px"> GAME START!!</span>');
        sleep(1500).then(() => {
            $('div[name="gameDiv"]').empty().append('<div> 당신의 직업은 '+myRole+ ' 입니다. </div>');
        });
        userList(Message);
        $('#messages').append('<li>      낮이 되었습니다. 30초 후 투표를 진행합니다.</li>');
        var count = 30;
        var countdown = setInterval(function() {
            // count 값을 1씩 감소
            count--;
            document.querySelector('[name=time]').innerText = "지금은 낮입니다. 남은 시간은 "+count + "초 입니다";


            // count 값이 0이 되면 타이머 정지
            if (count <= 28) {
                vote4();
                clearInterval(countdown);
            }

        }, 1000);
        // alert("잘돌아가고있음");
    }

    function vote4(){
        $("[name=chatDiv]").hide();
        $("[name=voteList]")
            .html("<input type='button' onclick="+userArr[0]+" value='"+userArr[0]+"'>"
                +"<input type='button' onclick="+userArr[1]+" value='"+userArr[1]+"'>"
                +"<input type='button' onclick="+userArr[2]+" value='"+userArr[2]+"'>"
            );
    }
    function sendVote(val){
        $.ajax({
            url: '/updateUserNo',  // 서버에서 유저 목록을 반환하는 엔드포인트
            success: function(no) {
                $("[name=totUserSpan]").text( "현재 접속 인원 : "+no);
            },
            error:function(){
            }
        });
    }

    function chat(){

    }
    function game(){

    }
    var chatDiv = document.getElementById('chat');
    function scrollToBottom() {
        chatDiv.scrollTop = chatDiv.scrollHeight;
    }
    function showChatMessage(Message) {
        var sender = JSON.parse(Message.body).from;
        var content = JSON.parse(Message.body).message;
        // alert(sender+content);
        $('#messages').append('<li>'+sender+ ' : ' + content + '</li>');

        scrollToBottom();
    }
    function disconnect() {
        if (stompClient !== null) {
            stompClient.disconnect();
            $("[name=findGame]").show();
            $("[name=exit]").hide();
            $("[name=chatDiv]").hide();
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
    function sleep(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }

    function userList(Message){
        var members = JSON.parse(Message.body).members;
        for(let i=0; i<4; i++){
            if(members[i] != myNick){
                userArr.push(members[i]);
            }
        }
        $("[name=userList]").text("참여자 : " + members[0]+ ", " + members[1]+  ", " +members[2] +  ", " +members[3])
    }
    var time = 0;
    function time(){
        timeNo = 30;
        $("[name=time]").text("지금은 낮 입니다. 남은 시간 : "+timeNo+"초");
        timeNo--;
    }

    setInterval(updateTotUser, 1000);
</script>
<body>
<%@ include file="home.jsp" %>
<div align="center">
    <span name="userList"></span>
</div>
<div name="gameDiv" align="center" width="1000px" height="700px">
    <div>
        <span name="totUserSpan"> </span>
    </div>
    <div>
        <span name="test"></span>
        <%--        내 점수  n 점--%>
    </div>
    <div>n승 n패
    </div>
</div>
<div align="center">
    <span name="time"></span>
</div>
<div name="chatDiv" align="center">
    <div class="chat" id="chat">
        <ul id="messages"></ul>
    </div>
    <div style="height: 15px;"></div>
    <form id="messageForm">
        <input style="width:350px; height:25px;" type="text" id="messageInput" placeholder="Type a message..." />
        <button style="width:50px; height:28px;" type="submit" >Send</button>
    </form>
</div>
<div name="voteList" align="center">
</div>
<div align="center">
    <button name="findGame" onclick="connect()">매칭하기</button>
    <button name="exit" onclick="location.reload()">나가기</button>
</div>




<%--<button onclick="u pdateTotUser()">유저수</button>--%>
</body>
</html>