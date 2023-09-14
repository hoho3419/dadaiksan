package com.eanswer.dadaiksan.Controller;

import com.eanswer.dadaiksan.Dto.EventDto;
import com.eanswer.dadaiksan.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<?> newEvent(@RequestBody EventDto eventDto, HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails) throws ParseException {
        boolean isCreate = eventService.newEvent(eventDto, request, userDetails);
        if (isCreate) return new ResponseEntity<>("이벤트 등록 성공", HttpStatus.OK);
        else return new ResponseEntity<>("이벤트 등록 실패", HttpStatus.OK);
    }
}
