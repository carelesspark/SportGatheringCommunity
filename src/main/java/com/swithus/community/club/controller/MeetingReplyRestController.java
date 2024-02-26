package com.swithus.community.club.controller;

import com.swithus.community.club.dto.MeetingReplyDTO;
import com.swithus.community.club.service.MeetingReplyService;
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
    private final MeetingReplyService meetingReplyService;

    // C
    @PostMapping("/{meetingId}")
    public ResponseEntity<Long> createReply(
            @PathVariable("meetingId") Long meetingId,
            @RequestBody MeetingReplyDTO meetingReplyDTO) {
        log.info("/meetingReply/{}", meetingId);

        Long meetingReplyId = meetingReplyService.register(meetingReplyDTO);

        return new ResponseEntity<>(meetingReplyId, HttpStatus.OK);
    }

    // R
    @GetMapping("/{meetingId}/list")
    public ResponseEntity<List<MeetingReplyDTO>> getreplyDTOList(
            @PathVariable("meetingId") Long meetingId) {
        log.info("/meetingReply/{}/list", meetingId);

        List<MeetingReplyDTO> replyDTOList = meetingReplyService
                .getMeetingReplyDTOListByMeeting(meetingId);

        return new ResponseEntity<>(replyDTOList, HttpStatus.OK);
    }

    // U
    @PutMapping("/{meetingId}/{replyId}")
    public ResponseEntity<Long> updateReply(
            @PathVariable("meetingId") Long meetingId,
            @PathVariable("replyId") Long replyId,
            @RequestBody MeetingReplyDTO meetingReplyDTO) {
        log.info("/meetingReply/{}/{}", meetingId, replyId);

        meetingReplyService.updateReply(meetingReplyDTO);

        return new ResponseEntity<>(replyId, HttpStatus.OK);
    }

    // D
    @DeleteMapping("/{meetingId}/{replyId}")
    public ResponseEntity<Long> delete(
            @PathVariable("meetingId") Long meetingId,
            @PathVariable("replyId") Long replyId) {
        log.info("/meetingReply/{}/{}", meetingId, replyId);

        meetingReplyService.deleteReply(replyId);

        return new ResponseEntity<>(replyId, HttpStatus.OK);
    }

}
