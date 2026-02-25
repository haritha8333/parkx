package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
public class EntryResponseDTO {


    private String slotId;

    private String exitQrToken;
    private String message;


}
