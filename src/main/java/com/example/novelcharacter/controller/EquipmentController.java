package com.example.novelcharacter.controller;

import com.example.novelcharacter.JWT.JWTUtil;
import com.example.novelcharacter.dto.EquipmentDTO;
import com.example.novelcharacter.dto.EquipmentDataDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.example.novelcharacter.service.EquipmentService;

import javax.naming.NoPermissionException;
import java.util.List;
import java.util.Map;

@RestController
class EquipmentController {
    private final EquipmentService equipmentService;
    private final JWTUtil jwtUtil;

    @Autowired
    public EquipmentController(EquipmentService equipmentService, JWTUtil jwtUtil) {
        this.equipmentService = equipmentService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/getEquipments")
    public List<EquipmentDTO> getEquipments(@RequestHeader String access, @RequestBody Map<String, Long> payload) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        long novelNum = payload.get("novelNum");

        return equipmentService.selectEquipmentsByNovel(novelNum, uuid);
    }

    @PostMapping("/getEquipmentData")
    public EquipmentDataDTO getEquipmentData(@RequestHeader String access, @RequestBody Map<String, Long> payload) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        long equipmentNum = payload.get("equipmentNum");

        return equipmentService.selectEquipmentData(uuid, equipmentNum);
    }

    @PostMapping("/addEquipment")
    public void addEquipment(@RequestHeader String access,@Valid @RequestBody EquipmentDataDTO equipmentDataDTO) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        System.out.println("equipment:"+equipmentDataDTO);

        equipmentService.insertEquipment(equipmentDataDTO, uuid);

    }

    @PostMapping("/updateEquipment")
    public void updateEquipment(@RequestHeader String access,@Valid @RequestBody EquipmentDataDTO equipmentDataDTO) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        System.out.println("equipment:"+equipmentDataDTO);

        equipmentService.updateEquipment(equipmentDataDTO, uuid);
    }

    @PostMapping("/deleteEquipment")
    public ResponseEntity<Void> deleteEpisode(@RequestHeader String access, @RequestBody EquipmentDTO equipmentDTO) throws NoPermissionException {
        long uuid = jwtUtil.getUuid(access);
        equipmentService.deleteEquipment(equipmentDTO, uuid);
        System.out.println("equipment:"+equipmentDTO);

        return ResponseEntity.noContent().build();
    }

}