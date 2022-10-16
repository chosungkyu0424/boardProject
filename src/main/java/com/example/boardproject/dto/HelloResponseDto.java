package com.example.boardproject.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

    private  String name;
    private  String nickname;

    public HelloResponseDto(String name, String nickname) {
        this.name = "sungkyu";
        this.nickname = "babo";
    }
}
