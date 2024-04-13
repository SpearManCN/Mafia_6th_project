package com.example.mafia.Repository;

import com.example.mafia.Domain.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    @Autowired
    private SqlSession sqlSession;

    public Member getMemberById(Member input){
        String id = input.getId();
        Member result = sqlSession.selectOne("getMemberById", id);
        return result;
    }
    public Member getMemberByNick(Member input){
        String nick = input.getNick();
        Member result = sqlSession.selectOne("getMemberByNick",nick);
        return result;
    }
    public Member getMemberByPw(Member input){
        String pw = input.getPw();
        Member result = sqlSession.selectOne("getMemberByPw",pw);
        return result;
    }
    public List<Member> getMembers(){
        List<Member> result = sqlSession.selectList("getMembers");
        return result;
    }
    public int insertMember(Member input){
        int result = sqlSession.insert("insertMember", input);
        return result;
    }

    public int updateWinScore(Member input){
        int result = sqlSession.update("updateWinScore", input);
        return result;
    }

    public int updateLossScore(Member input){
        int result = sqlSession.update("updateLossScore", input);
        return result;
    }

    public int updatePw(Member input){
        int result = sqlSession.update("updatePw", input);
        return result;
    }

    public List<Member> getRanking(){
        List<Member> result = sqlSession.selectList("getRanking");
        return result;
    }

    public int getMyRanking(Member member){
        int result =0;
        result = sqlSession.selectOne("getMyRanking", member);
        return result;
    }


}
