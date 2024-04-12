<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mafia</title>
    <style>
        #div1{
            display: flex;
            text-align: center;
            justify-content: center; /* 가로 방향 가운데 정렬 */
            align-items: center;
            /*flex-direction: column;*/
            /*display: inline-block; text-align: center; vertical-align: middle;*/
            background-color: grey;
        }
        #div2{
            flex-direction: column;
            text-align: center;
            display: flex;
            justify-content: center; /* 가로 방향 가운데 정렬 */
            align-items: center;
            width:700px;
            flex-wrap: wrap;
            /*flex-wrap: wrap;*/
            height:400px;
            font-size: 35px;
        }
        #inputId{
            /*width:300px;*/
            /*height:40px;*/
            font-size: 35px;
        }
        #inputPw{
            font-size: 35px;
        }
        .bt{
            font-size: 20px;
        }
    </style>
    <script>
        $(function(){
            $("#loginBt").click(function(){val()});
            $("#findBt").click(function(){goFind()});
            $("#joinBt").click(function(){goJoin()});
            $("#kakaoBt").click(function(){goKakao()});
            $("#discord").click(function(){goDiscord()});
        });
        function goJoin(){
            window.location.href = "/login_join";
        }
        function goKakao(){
            alert("카카오 로그인");
        }
        function goDiscord(){
            alert("디코 로그인");
        }
        function val(){

        }
        function goFind(){
            window.location.href = "/findPw";
        }
    </script>
</head>
<body>
<%@ include file="home.jsp" %>
<div id="div1">
    <div id="div2">
        <div>
            id <input type="text" placeholder="ID를 입력해주세요" id="inputId">
        </div>
        <div>

        </div>
        <div>
            pw <input type="password" placeholder="PW를 입력해주세요" id="inputPw">
        </div>
        <div>
            <button class="bt" id="loginBt">로그인</button>
            <button class="bt" id="findBt">비밀번호 찾기</button>
        </div>

        <div>
            <button class="bt" id="joinBt">회원가입</button> &ensp;&ensp;
            <button class="bt" id="kakaoBt">kakao</button> &ensp;&ensp;
            <button class="bt" id="discord">discord</button>
        </div>
    </div>
</div>
</body>
</html>