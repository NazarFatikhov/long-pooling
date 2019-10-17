package ru.nazarfatichov.transfer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {
    private String login;
    private String text;
}
