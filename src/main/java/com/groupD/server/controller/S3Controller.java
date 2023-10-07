package com.groupD.server.controller;

import com.groupD.server.domain.dto.SignUpRequestDto;
import com.groupD.server.domain.entity.Member;
import com.groupD.server.repository.MemberRepository;
import com.groupD.server.security.Auth;
import com.groupD.server.security.AuthInfo;
import com.groupD.server.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class S3Controller {
    private final S3Uploader s3Uploader;
private final MemberRepository memberRepository;
    @PostMapping("/image")
    public ResponseEntity<Void> updateUserImage(@Auth AuthInfo authInfo, @RequestPart("file") MultipartFile multipartFile) {
        try {
            Member member = memberRepository.findByEmail(authInfo.getEmail())
                    .orElseThrow(() -> new RuntimeException("Member not found"));

            String imageUrl = s3Uploader.uploadFiles(multipartFile, "static");

            // 회원의 imageUrl 필드 업데이트
            member.setImageUrl(imageUrl);
            memberRepository.save(member);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
