package com.golfzonaca.officesharingplatform.config.auth;

import com.golfzonaca.officesharingplatform.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {
    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }
    //권한 한개가 아닐 수 있음 (3개 이상의 권한) 따라서 컬렉션으로 담는다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collector = new ArrayList<>();
        //        collector.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return user.getRole();
//            }
//        });
        // lambda식으로 함수 넣는 작업.
        collector.add(() -> { return user.getRole();});
        return collector;
    }

    @Override
    public String getPassword() {
        return user.getPw();
    }

    @Override
    public String getUsername() {
        return user.getMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //아니
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //아니
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 아니 회사가면 필요할 수 있음 이거 false면 로그인안됨
    }

    @Override
    public boolean isEnabled() {
        return true; //활성화 되어 있음
    }
}
