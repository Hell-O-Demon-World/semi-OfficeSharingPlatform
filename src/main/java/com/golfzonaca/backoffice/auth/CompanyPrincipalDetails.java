package com.golfzonaca.backoffice.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class CompanyPrincipalDetails implements UserDetails {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    private final String username;
    private String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final Set<GrantedAuthority> authorities;

    public CompanyPrincipalDetails(String username, String password, Set<GrantedAuthority> authorities) {
        this(username, password, true, true, true, true, authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
