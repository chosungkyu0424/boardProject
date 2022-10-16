package com.example.boardproject.config.auth;

import com.example.boardproject.domain.user.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
//스프링 시큐리티의 고유한 세션저장소에 저장을 해준다
@Getter
public class PrincipalDetail implements UserDetails {

    private User user;
    private Map<String, Object> attributes;

    //일반 사용자
    public PrincipalDetail(User user) {
        this.user = user;
    }

    //OAuth 사용자
    public PrincipalDetail(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //계정이 갖고있는 권한 목록을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> user.getRoleKey());

        return collection;
    }

    //사용자 패스워드
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //사용자 아이디
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //사용자 이메일
    public String getEmail() {
        return user.getEmail();
    }

    //사용자 닉네임
    public String getNickname() {
        return user.getNickname();
    }

    //사용자 pk
    public Long getId() {
        return user.getId();
    }

    //계정이 만료되었는지 리턴 (true: 만료되지 않음)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있는지 리턴 (true: 잠겨있지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //패스워드가 만료되지 않았는지 리턴 (true: 만료되지 않음)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화되어 있는지 리턴 (true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }


}
