package mafia.Service;

import mafia.Domain.Member;
import mafia.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = new Member();

        member.setId(id);
        System.out.println("before id : " + member.getId() + " pw : "+member.getPw() );
        member = memberRepository.getMemberById(member);
        System.out.println("id : "+ member+ );

        List<GrantedAuthority> authorities = new ArrayList<>();
        if(member == null){
            throw new UsernameNotFoundException("User not found with username: " + id);
        }

        return new User(member.getId(), member.getPw(), authorities);
    }
}
