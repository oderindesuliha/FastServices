package org.group6.fastservices.dtos.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class QRCodeRequest {
    private String text;
    private int width = 300;
    private int height = 300;
}
