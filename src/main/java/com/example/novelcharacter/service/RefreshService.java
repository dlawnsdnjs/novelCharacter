package com.example.novelcharacter.service;

import com.example.novelcharacter.dto.RefreshDTO;
import com.example.novelcharacter.mapper.RefreshMapper;
import org.springframework.stereotype.Service;

/**
 * {@code RefreshService}는 JWT 인증 시스템에서 리프레시 토큰(Refresh Token)을 관리하는 서비스 클래스입니다.
 * <p>
 * 사용자의 세션 유지를 위해 발급된 리프레시 토큰을 데이터베이스에 저장, 조회, 삭제하는 기능을 제공합니다.
 * </p>
 *
 * <ul>
 *   <li>리프레시 토큰 등록 → {@link #addRefresh(RefreshDTO)}</li>
 *   <li>리프레시 토큰 존재 여부 확인 → {@link #existsByRefresh(String)}</li>
 *   <li>리프레시 토큰 삭제 → {@link #deleteByRefresh(String)}</li>
 * </ul>
 *
 * @author
 * @since 1.0
 */
@Service
public class RefreshService {

    /** 리프레시 토큰 데이터 접근을 담당하는 MyBatis 매퍼 */
    private final RefreshMapper refreshMapper;

    /**
     * {@code RefreshService}의 생성자입니다.
     *
     * @param refreshMapper 리프레시 토큰 관련 SQL을 수행하는 매퍼 객체
     */
    public RefreshService(RefreshMapper refreshMapper) {
        this.refreshMapper = refreshMapper;
    }

    /**
     * 새로운 리프레시 토큰을 데이터베이스에 저장합니다.
     *
     * @param refreshDTO 저장할 리프레시 토큰 정보를 포함한 DTO
     */
    public void addRefresh(RefreshDTO refreshDTO) {
        refreshMapper.insertRefresh(refreshDTO);
    }

    /**
     * 특정 리프레시 토큰이 데이터베이스에 존재하는지 확인합니다.
     *
     * @param refresh 확인할 리프레시 토큰 문자열
     * @return 토큰이 존재하면 {@code true}, 존재하지 않으면 {@code false}
     */
    public Boolean existsByRefresh(String refresh) {
        return refreshMapper.existsByRefresh(refresh);
    }

    /**
     * 주어진 리프레시 토큰을 데이터베이스에서 삭제합니다.
     *
     * @param refresh 삭제할 리프레시 토큰 문자열
     */
    public void deleteByRefresh(String refresh) {
        refreshMapper.deleteByRefresh(refresh);
    }
}
