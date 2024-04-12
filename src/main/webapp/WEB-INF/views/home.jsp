<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mafia</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(function (){
            $("#loginBar").click(function(){
                goLogin();
            });
            $("#title1").click(function(){
                goTitle(1);
            });
            $("#title2").click(function(){
                goTitle(2);
            });
            $("#title3").click(function(){
                goTitle(3);
            });
        });

        function goLogin(){
            window.location.href = "/login";
        }
        function goTitle(n){
            if(n==1){
                window.location.href = "/game";
            }else if(n==2){
                window.location.href = "/ranking";
            }else{
                window.location.href = "/board";
            }

        }


    </script>
    <style>
        #loginBar{
            height:50px;
            width:100px;
            cursor:pointer;
            font-size:20px;
        }
        #Mafia{
            display: flex; font-size:40px;
            justify-content: center;
            align-items: center;
            background-color: grey;
            width:100%; height:300px;
            font-size:50px;
            font-family: Arial
        }
        #titleDiv{
            text-align:center;
            align:center;
            height:80px;
            width:100%;
            font-size:35px;
            font-family: Arial
        }
        .titles{
            display: inline-block;
            width:15%;
            align:center;
            cursor: pointer;
        }
    </style>
</head>

<body>
<div align="right" style="height:50px;">
    <span id ="loginBar" >로그인</span>
</div>
<div id="Mafia">
    Mafia Game
</div>
<div id="titleDiv">
    <span class="titles" id="title1">게임 찾기</span>
    <span class="titles" >&nbsp;</span>
    <span class="titles" id="title2">랭킹</span>
    <span class="titles" >&nbsp;</span>
    <span class="titles" id="title3">게시판</span>
</div>

</body>
</html>