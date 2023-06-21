package com.example.reservation.mainService;

import com.example.reservation.member.data.dto.MemberDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface MainService extends UserDetailsService {
    public boolean save(MemberDTO memberDTO);

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
