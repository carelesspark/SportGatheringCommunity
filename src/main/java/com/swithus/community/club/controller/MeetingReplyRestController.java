package com.swithus.community.club.controller;

import com.swithus.community.club.dto.MeetingReplyDTO;
import com.swithus.community.club.service.ClubMemberService;
import com.swithus.community.club.service.MeetingReplyService;
import com.swithus.community.club.service.MeetingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/meetingReply")
public class MeetingReplyRestController {
    private final MeetingService meetingService;
    private final ClubMemberService clubMemberService;
    private final MeetingReplyService meetingReplyService;

    @GetMapping("{meetingId}/list")
    public ResponseEntity<List<MeetingReplyDTO>> getreplyDTOList(@PathVariable("meetingId") Long meetingId) {
        log.info("/meetingReply/{}/list", meetingId);

        List<MeetingReplyDTO> replyDTOList = meetingReplyService.getMeetingReplyDTOListByMeeting(meetingId);

        return new ResponseEntity<>(replyDTOList, HttpStatus.OK);
    }

    @PostMapping("/{meetingId}")
    public ResponseEntity<Long> insert(@PathVariable("meetingId") Long meetingId,
                                       @RequestBody MeetingReplyDTO meetingReplyDTO) {
        log.info("/meetingReply/{}", meetingId);

        Long meetingReplyId = meetingReplyService.register(meetingReplyDTO);

        return new ResponseEntity<>(meetingReplyId, HttpStatus.OK);
    }
}
