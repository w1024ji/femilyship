package com.example.femilyship.controller;

import com.example.femilyship.dto.EssayDto;
import com.example.femilyship.entity.Essay;
import com.example.femilyship.entity.User;
import com.example.femilyship.security.UserDetailsImpl;
import com.example.femilyship.service.EssayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/essays")
public class EssayController {
    private final EssayService essayService;
    /**
     * 새로운 에세이 생성
     */
    @PostMapping
    public ResponseEntity<Essay> createEssay(@RequestBody EssayDto.Request requestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User currentUser = userDetails.getUser();
        Essay createdEssay = essayService.createEssay(requestDto, currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEssay);
    }
    /**
     * 특정 에세이 상세 조회
     */
    @GetMapping("/{essayId}")
    public ResponseEntity<EssayDto.Response> getEssayById(@PathVariable Long essayId) {
        EssayDto.Response essay = essayService.getEssayById(essayId);
        return ResponseEntity.ok(essay);
    }
    /**
     * 특정 에세이 수정
     */
    @PutMapping("/{essayId}")
    public ResponseEntity<Essay> updateEssay(@PathVariable Long essayId,
                                             @RequestBody EssayDto.Request requestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            log.info("인증되지 않은 사용자 접근. essayId: {}", essayId); //  essayId: 6
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User currentUser = userDetails.getUser();
        Essay updatedEssay = essayService.updateEssay(essayId, requestDto, currentUser);
        return ResponseEntity.ok(updatedEssay);
    }
    /**
     * 특정 에세이 삭제
     */
    @DeleteMapping("/{essayId}")
    public ResponseEntity<Void> deleteEssay(@PathVariable Long essayId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User currentUser = userDetails.getUser();
        essayService.deleteEssay(essayId, currentUser);
        return ResponseEntity.noContent().build();
    }
}

