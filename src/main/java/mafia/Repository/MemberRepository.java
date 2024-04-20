package mafia.Repository;

import mafia.Domain.Member;
import mafia.Mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    @Autowired
    private MemberMapper memberMapper;

    public Member getMemberById(Member input){
        //String result = sqlSession.selectOne("MemberRepositoryInterface.getMemberById", input2);
        return memberMapper.getMemberById(input);
    }
    public Member getMemberByNick(Member input){
        return memberMapper.getMemberByNick(input);
    }
    public Member getMemberByPw(Member input){
        return memberMapper.getMemberByPw(input);
    }
    public List<Member> getMembers(){
        return memberMapper.getMembers();
    }
    public int insertMember(Member input){
        return memberMapper.insertMember(input);
    }

    public int updateWinScore(Member input){
        return memberMapper.updateWinScore(input);
    }

    public int updateLossScore(Member input){
        return memberMapper.updateLossScore(input);
    }

    public int updatePw(Member input){
        return memberMapper.updatePw(input);
    }


    public List<Member> getRanking(){
        return memberMapper.getRanking();
    }

    public int getMyRanking(Member input){
        return memberMapper.getMyRanking(input);
    }


}
