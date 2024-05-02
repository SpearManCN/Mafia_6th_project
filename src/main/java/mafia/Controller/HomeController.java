package mafia.Controller;

import mafia.Domain.Member;
import mafia.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private MemberService memberService;
    @ModelAttribute
    public void addCommonAttributes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails){
            UserDetails principal = (UserDetails) authentication.getPrincipal();
            String id = principal.getUsername();
            Member member = new Member();
            member.setId(id);
            member = memberService.getNick(member);
            model.addAttribute("myNick", member.getNick());
        }


    }


    @RequestMapping("/home")
    public String home(){
        return "home";
    }
    @RequestMapping("/ranking")
    public String ranking(Model model){
        List<Member> result = memberService.getRanking();
        model.addAttribute("ranking", result);
        return "ranking";
    }

    // 로그인 관련
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/loginError")
    public String loginError(Model model){
        model.addAttribute("error", 1);
        return "login";
    }
    @RequestMapping("/loginProc")
    @ResponseBody
    public int loginProc(Member member){
        return memberService.login(member);
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

        String encodedPassword = passwordEncoder.encode(member.getPw());
        System.out.println(encodedPassword);
        member.setPw(encodedPassword);
        int result = memberService.join(member);
        switch (result){
            case 3 : return result;
            case 2 : return result;
            case 1 : memberService.insertMember(member);
                return result;
            default: return result;
        }
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
