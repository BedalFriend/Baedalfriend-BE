package com.hanghae.baedalfriend.domain;

import com.hanghae.baedalfriend.exception.MemberException.MemberNotFoundException;
import com.hanghae.baedalfriend.exception.MemberException.TokenNotExistException;
import com.hanghae.baedalfriend.jwt.TokenProvider;
import com.hanghae.baedalfriend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Check {
    private final MemberRepository memberRepository;

    private final TokenProvider tokenProvider;


    public void tokenCheck(HttpServletRequest request, Member member) {
        if (request.getHeader("Authorization") == null) throw new TokenNotExistException();
        if (member == null) throw new MemberNotFoundException();
    }

    public Member getMemberById(String id) {
        Optional<Member> optionalMember = memberRepository.findById(Long.valueOf(id));
        return optionalMember.orElse(null);
    }

    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Authorization").substring(7))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }
    public Member getMember() {
        return tokenProvider.getMemberFromAuthentication();
    }
}
