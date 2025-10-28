package com.example.novelcharacter.ExceptionHandler;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.NoPermissionException;
import java.util.Map;

/**
 * GlobalExceptionHandler
 * ----------------------
 * 애플리케이션 전역에서 발생하는 예외를 일관성 있게 처리하기 위한 클래스입니다.
 *
 * <p>
 * {@link RestControllerAdvice} 어노테이션을 사용하여
 * 모든 @RestController에서 발생하는 예외를 가로채어 처리합니다.
 * </p>
 *
 * <p>
 * 각 예외 유형별로 별도의 @ExceptionHandler 메서드를 두어
 * 예외 발생 시 적절한 HTTP 상태 코드와 메시지를 반환합니다.
 * </p>
 *
 * <h3>주요 처리 예외</h3>
 * <ul>
 *     <li>{@link NoPermissionException} : 접근 권한이 없는 경우 → 403 FORBIDDEN</li>
 *     <li>{@link RuntimeException} : 일반적인 런타임 예외 → 400 BAD REQUEST</li>
 *     <li>{@link MessagingException} : 이메일 전송 실패 등 → 400 BAD REQUEST</li>
 *     <li>{@link DuplicateMemberException} : 중복 데이터 등록 시 → 400 BAD REQUEST</li>
 * </ul>
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 권한이 없는 사용자가 리소스에 접근할 경우 발생하는 예외 처리.
     *
     * @param e NoPermissionException
     * @return ResponseEntity : 403 FORBIDDEN + 에러 메시지
     */
    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity<?> handleNoPermission(NoPermissionException e) {
        log.warn("권한 예외 발생: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(Map.of("error", e.getMessage()));
    }

    /**
     * 일반적인 런타임 예외(RuntimeException) 처리.
     * <p>예상치 못한 오류나 검증 실패 등에 대응합니다.</p>
     *
     * @param e RuntimeException
     * @return ResponseEntity : 400 BAD REQUEST + 에러 메시지
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        log.warn("예외 발생: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", e.getMessage()));
    }

    /**
     * 이메일 전송 과정에서 발생하는 MessagingException 처리.
     *
     * @param e MessagingException
     * @return ResponseEntity : 400 BAD REQUEST + 에러 메시지
     */
    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<?> handleMessagingException(MessagingException e) {
        log.warn("메세지 전송 실패: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", e.getMessage()));
    }

    /**
     * 중복된 데이터가 삽입될 때 발생하는 예외 처리.
     *
     * @param e DuplicateMemberException
     * @return ResponseEntity : 400 BAD REQUEST + 에러 메시지
     */
    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<?> handleDuplicateMemberException(DuplicateMemberException e) {
        log.warn("중복 자료 존재: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", e.getMessage()));
    }
}
