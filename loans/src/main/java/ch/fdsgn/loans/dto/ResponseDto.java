package ch.fdsgn.loans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ResponseDto {

    private String code;
    private String message;

    public static ResponseDto create(String code, String message) {
        ResponseDto dto = new ResponseDto();

        dto.code = code;
        dto.message = message;

        return dto;
    }
}
