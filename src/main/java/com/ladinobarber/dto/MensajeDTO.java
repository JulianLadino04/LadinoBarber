package com.ladinobarber.dto;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}
