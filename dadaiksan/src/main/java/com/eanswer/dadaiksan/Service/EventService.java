package com.eanswer.dadaiksan.Service;

import com.eanswer.dadaiksan.Dto.EventDto;
import com.eanswer.dadaiksan.Entity.Event;
import com.eanswer.dadaiksan.Entity.Member;
import com.eanswer.dadaiksan.Repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    private EventDto eventDto;
    @Autowired
    private AuthService authService;

    public boolean newEvent(EventDto eventDto, HttpServletRequest request, UserDetails userDetails) throws ParseException {

        authService.validateTokenAndGetUser(request, userDetails);

        Event event = new Event();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        Date startDate = dateFormat.parse(eventDto.getStartDate());
        Date finDate = dateFormat.parse(eventDto.getFinDate());

        event.setStartDate(startDate);
        event.setFinDate(finDate);
        event.setEventName(eventDto.getEventName());
        event.setEventContents(eventDto.getEventContents());
        event.setRegDate(LocalDateTime.now());
        event.setEventImg(eventDto.getEventImg());
        Event saveEvent = eventRepository.save(event);
        return saveEvent != null;
    }
    @Transactional
    public boolean updateEvent(Long id, EventDto eventDto, HttpServletRequest request, UserDetails userDetails) throws ParseException {

        authService.validateTokenAndGetUser(request, userDetails);
        Event event = eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        Date startDate = dateFormat.parse(eventDto.getStartDate());
        Date finDate = dateFormat.parse(eventDto.getFinDate());

        event.setStartDate(startDate);
        event.setFinDate(finDate);
        event.setEventName(eventDto.getEventName());
        event.setEventContents(eventDto.getEventContents());
        event.setRegDate(LocalDateTime.now());
        event.setEventImg(eventDto.getEventImg());
        Event updateEvent = eventRepository.save(event);

        return updateEvent != null;
    }
}
