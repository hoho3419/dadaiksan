package com.eanswer.dadaiksan.Controller;

import com.eanswer.dadaiksan.Dto.EventDto;
import com.eanswer.dadaiksan.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/newEvent") // 등록
    public ResponseEntity<?> newEvent(@RequestBody EventDto eventDto, HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails) throws ParseException {
        boolean isCreate = eventService.newEvent(eventDto, request, userDetails);
        if (isCreate) return new ResponseEntity<>("이벤트 등록 성공", HttpStatus.OK);
        else return new ResponseEntity<>("이벤트 등록 실패", HttpStatus.OK);
    }

    @PostMapping("/updateEvent/{id}") // 수정
    public ResponseEntity<?> updateEvent(@PathVariable("id") Long id, @RequestBody EventDto eventDto, HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails) throws ParseException {
        try{
            boolean isUpdated = eventService.updateEvent(id, eventDto, request, userDetails);
            return new ResponseEntity<>(isUpdated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
