package mafia.Controller;

import mafia.Domain.Member;
import mafia.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @Autowired
    private MemberService memberService;
    @RequestMapping("/home")
    public String home(){
        return "home";
    }
    @RequestMapping("/ranking")
    public String ranking(){
        return "ranking";
    }

    // 로그인 관련
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/loginProc")
    @ResponseBody
    public int loginProc(Member member){
        memberService.
        return 1;
    }
    @RequestMapping("/login_join")
    public String login_join(){
        return "login_join";
    }

    @RequestMapping("/joinProc")
    @ResponseBody
    public int joinProc(Member member){
        //member 값 받아와서 중복확인.
        //id 와 nick 중복확인후 id중복시 3, nick중복시 2, 성공적으로 가입시 1
        int result = memberService.join(member);
        return result;
    }
    @RequestMapping("/game")
    public String game(){
        return "game";
    }
    @RequestMapping("/board")
    public String board(){
        return "board";
    }
    @RequestMapping("/findPw")
    public String findPw(){
        return "findPw";
    }
}
