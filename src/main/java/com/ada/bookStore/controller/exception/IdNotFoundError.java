package com.ada.bookStore.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IdNotFoundError extends Exception {
    private String description;
}
