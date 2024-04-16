package mafia.Mapper;

import mafia.Domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    public Member getMemberById(Member input);
    public Member getMemberByNick(Member input);
//    public Member getMemberByPw(Member input);
//    public List<Member> getMembers();
//    public int insertMember(Member input);
//
//    public int updateWinScore(Member input);
//
//    public int updateLossScore(Member input);
//
//    public int updatePw(Member input);
//
//    public List<Member> getRanking();
//
//    public int getMyRanking(Member member);
}
