package mafia.Service;

import mafia.Domain.Member;
import mafia.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    //회원가입 로직
    //id 와 nick 중복확인후 id중복시 3, nick중복시 2, 성공적으로 가입시 1
    public int join(Member member){
        int result = 0;
        if(memberRepository.getMemberById(member)!=null){
            result = 3;
        }else if(memberRepository.getMemberByNick(member)!=null){
            result = 2;
        }else{
            result = 1;
        }
        return result;
    }
    public Member getNick ( Member member ){
        return memberRepository.getMemberById(member);
    }

    public int insertMember(Member member){
        return memberRepository.insertMember(member);
    }

    //로그인 계정확인
    //비번 틀렸으면 0, 성공시 1
    public int login(Member member){
        int result =0;
        if(memberRepository.getMemberByPw(member)==null){
            result=0;
        }else{
            result=1;
        }
        return result;
    }
//
//    //랭킹보드 가져오기
    public List<Member> getRanking(){
        List<Member> result = memberRepository.getRanking();
        return result;
    }
//
//    //내 랭킹 가져오기
//    public int getMyRanking(Member member){
//        int result = memberRepository.getMyRanking(member);
//        return result;
//    }
//
//    //게임 이겼을시
//    public void win(Member member){
//        memberRepository.updateWinScore(member);
//    }
//    //게임 졌을시
//    public void loss(Member member){
//        memberRepository.updateLossScore(member);
//    }
//    //비밀번호 변경
//    public void changePw(Member member){
//        memberRepository.updatePw(member);
//    }
}
