package com.example.mafia.Service;

import com.example.mafia.Domain.Member;
import com.example.mafia.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //랭킹보드 가져오기


    //내 랭킹 가져오기
}
