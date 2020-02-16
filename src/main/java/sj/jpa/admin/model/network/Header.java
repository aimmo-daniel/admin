package sj.jpa.admin.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {

    // api 통신기간

    private String transactionTime;

    // api 응답 코드
    private String resultCode;

    // api 부가 설명
    private String description;

    private T data;

    // OK
    public static <T> Header<T> OK() {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .resultCode("OK")
                .description("OK")
                .build();
    }

    // DATA OK
    public static <T> Header<T> OK(T data) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    // ERROR
    public static <T> Header<T> ERROR(String description) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .resultCode("ERROR")
                .description(description)
                .build();
    }



}
