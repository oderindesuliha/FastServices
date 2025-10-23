package org.group6.fastservices.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QRCodeRequest {
    private String text;
    private int width = 300;   // default size
    private int height = 300;  // default size
}
