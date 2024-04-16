package mafia.Repository;

import mafia.Domain.Member;
import mafia.Mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
//    public Member getMemberByPw(Member input){
//        String pw = input.getPw();
//        Member result = sqlSession.selectOne("getMemberByPw",pw);
//        return result;
//    }
//    public List<Member> getMembers(){
//        List<Member> result = sqlSession.selectList("getMembers");
//        return result;
//    }
//    public int insertMember(Member input){
//        int result = sqlSession.insert("insertMember", input);
//        return result;
//    }
//
//    public int updateWinScore(Member input){
//        int result = sqlSession.update("updateWinScore", input);
//        return result;
//    }
//
//    public int updateLossScore(Member input){
//        int result = sqlSession.update("updateLossScore", input);
//        return result;
//    }
//
//    public int updatePw(Member input){
//        int result = sqlSession.update("updatePw", input);
//        return result;
//    }
//
//    public List<Member> getRanking(){
//        List<Member> result = sqlSession.selectList("getRanking");
//        return result;
//    }
//
//    public int getMyRanking(Member member){
//        int result =0;
//        result = sqlSession.selectOne("getMyRanking", member);
//        return result;
//    }


}
