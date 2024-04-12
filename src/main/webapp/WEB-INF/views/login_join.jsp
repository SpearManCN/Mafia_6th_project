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
            height:400px;
            font-size: 35px;
        }
        #inputPw, #inputNick, #inputPw2, #inputId{
            font-size: 35px;
        }
        .bt{
            font-size: 20px;
        }
    </style>
    <script>
        $(function(){
            $("#joinBt").click(function(){val()});
        });
        function goJoin(){
            // ajax로 가입.
        }
        function val(){
            var id = $("#inputId").val();
            var pw = $("#inputPw").val();
            var pw2 = $("#inputPw2").val();
            var nick = $("#inputNick").val();
            if(id.length==0||pw.length==0||pw2.length==0||nick.length==0){
                alert("모든 칸을 채워주세요");
                return;
            }
            if(pw!=pw2){
                alert("pw와 pw확인이 다릅니다");
                return;
            }
            goJoin();
        }
    </script>
</head>
<body>
<%@ include file="home.jsp" %>
<div id="div1">
    <div id="div2">
        <form name="joinForm">
        <div>
            id <input name="id" type="text" placeholder="ID를 입력해주세요" id="inputId">
        </div>
        <div>
            pw <input name="pw" type="password" placeholder="PW를 입력해주세요" id="inputPw">
        </div>
        <div>
            pw확인 <input type="password" placeholder="PW를 재 입력해주세요" id="inputPw2">
        </div>
        <div>
            닉네임 <input name="nick" type="password" placeholder="PW를 입력해주세요" id="inputNick">
        </div>
        </form>
        <div>
            <button class="bt" id="joinBt">가입하기</button>
        </div>
    </div>
</div>
</body>
</html>