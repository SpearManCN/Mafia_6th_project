<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mafia</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
        #inputId{
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
            var id = $("#inputId").val();
            var pw = $("#inputPw").val();
            if(id.length==0||pw.length==0){
                alert("ID 혹은 PW를 다시 확인해주세요");
            }else{
                //ajax로 form 넘겨서 id 확인 해줘야함.
                $.ajax({
                    url:"loginProc"
                    ,type:"post"
                    ,data:$("[name='loginForm']").serialize()
                    ,success:function(data){
                      //비번 틀렸으면 0, 성공시 1
                        if(data==1){
                            alert("환영합니다");
                            window.location.href = "/ranking";
                        }else {
                            alert("계정 정보가 틀렸습니다.");
                            return;
                        }
                    }
                    ,error:function(){
                        alert("오류발생");
                        return;
                    }
                });
            }
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
        <form name="loginForm">
        <div>
            id <input type="text" placeholder="ID를 입력해주세요" id="inputId" name="id">
        </div>
        <div>
            pw <input type="password" placeholder="PW를 입력해주세요" id="inputPw" name="pw">
        </div>
        </form>
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