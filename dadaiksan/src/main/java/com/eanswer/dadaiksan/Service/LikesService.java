package com.eanswer.dadaiksan.Service;

import com.eanswer.dadaiksan.Entity.Member;
import com.eanswer.dadaiksan.Repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {
  @Autowired
  private AuthService authService;
  @Autowired
  private LikesRepository likesRepository;
  @Transactional
  public boolean likesToggle(Long id, HttpServletRequest request, UserDetails userDetails){
    Member member = authService.validateTokenAndGetUser(request,userDetails);
    System.out.println(member.getId());
    int a = likesRepository.likesToggle(id,member.getId());

    return true;
  }
}
