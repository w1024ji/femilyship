package com.example.femilyship.controller;

import com.example.femilyship.dto.EssayDto;
import com.example.femilyship.entity.Essay;
import com.example.femilyship.entity.User;
import com.example.femilyship.security.UserDetailsImpl;
import com.example.femilyship.service.EssayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/essays")
public class EssayController {

    private final EssayService essayService;

    // 1. 새로운 에세이 생성
    @PostMapping
    public ResponseEntity<Essay> createEssay(@RequestBody EssayDto.Request requestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User currentUser = userDetails.getUser();
        Essay createdEssay = essayService.createEssay(requestDto, currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEssay);
    }

    // 2. 특정 에세이 상세 조회
    @GetMapping("/{essayId}")
    public ResponseEntity<EssayDto.Response> getEssayById(@PathVariable Long essayId) {
        EssayDto.Response essay = essayService.getEssayById(essayId);
        return ResponseEntity.ok(essay);
    }

    // 3. 특정 에세이 수정
    @PutMapping("/{essayId}")
    public ResponseEntity<Essay> updateEssay(@PathVariable Long essayId,
                                             @RequestBody EssayDto.Request requestDto,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User currentUser = userDetails.getUser();
        Essay updatedEssay = essayService.updateEssay(essayId, requestDto, currentUser);
        return ResponseEntity.ok(updatedEssay);
    }

    // 4. 특정 에세이 삭제
    @DeleteMapping("/{essayId}")
    public ResponseEntity<Void> deleteEssay(@PathVariable Long essayId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User currentUser = userDetails.getUser();
        essayService.deleteEssay(essayId, currentUser);
        return ResponseEntity.noContent().build();
    }
}