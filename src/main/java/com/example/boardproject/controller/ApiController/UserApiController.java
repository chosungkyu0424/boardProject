package com.example.boardproject.controller.ApiController;

import com.example.boardproject.Sevice.UserService;
import com.example.boardproject.config.auth.PrincipalDetail;
import com.example.boardproject.domain.user.User;
import com.example.boardproject.dto.user.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController //html파일이 아니라 data를 리턴해주는 controller = RestController
public class UserApiController {
    private final UserService userService;

    /**
     * 회원가입 API
     */
    @PostMapping("/auth/api/v1/user")
    public Long save(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return userService.save(userSaveRequestDto.toEntity());
    }

    /**
     * 회원수정 API
     */
    @PutMapping("/api/v1/user")
    public Long update(@RequestBody User user, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        userService.update(user, principalDetail);
        return user.getId();
    }

}