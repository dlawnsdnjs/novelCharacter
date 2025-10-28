package com.example.novelcharacter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * {@code RedisService}는 Redis를 활용한 캐싱, 임시 데이터 저장, 만료 관리 등의 기능을 제공합니다.
 * <p>
 * Redis의 Value(문자열) 및 Hash(맵) 구조를 모두 지원하며,
 * TTL(Time To Live) 설정과 데이터 삭제, 존재 여부 확인 기능을 포함합니다.
 * </p>
 *
 * <ul>
 *   <li>단순 Key-Value 저장 → {@link #setValues(String, String)}</li>
 *   <li>TTL이 설정된 Key-Value 저장 → {@link #setValues(String, String, Duration)}</li>
 * </ul>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * Redis에 단일 문자열 값을 저장합니다.
     *
     * @param key  Redis 키
     * @param data 저장할 데이터 값
     */
    public void setValues(String key, String data) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key, data);
    }

    /**
     * Redis에 만료 시간을 가진 문자열 값을 저장합니다.
     *
     * @param key      Redis 키
     * @param data     저장할 데이터 값
     * @param duration 데이터 만료 시간 (예: {@code Duration.ofMinutes(5)})
     */
    public void setValues(String key, String data, Duration duration) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key, data, duration);
    }

    /**
     * Redis에서 문자열 값을 조회합니다.
     *
     * @param key 조회할 Redis 키
     * @return 저장된 문자열 값이 존재하면 해당 값, 존재하지 않으면 "false"
     */
    @Transactional(readOnly = true)
    public String getValues(String key) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        if (values.get(key) == null) {
            return "false";
        }
        return (String) values.get(key);
    }

    /**
     * Redis에서 특정 키를 삭제합니다.
     *
     * @param key 삭제할 Redis 키
     */
    public void deleteValues(String key) {
        redisTemplate.delete(key);
    }

    /**
     * Redis 값 존재 여부를 확인합니다.
     *
     * @param value {@link #getValues(String)}의 반환 값
     * @return 값이 "false"가 아니면 {@code true}, 그렇지 않으면 {@code false}
     */
    public boolean checkExistsValue(String value) {
        return !value.equals("false");
    }
}
