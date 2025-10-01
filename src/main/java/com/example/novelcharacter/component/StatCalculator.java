package com.example.novelcharacter.component;

import com.example.novelcharacter.dto.EquipmentDataDTO;
import com.example.novelcharacter.dto.EquipmentStatInfoDTO;
import com.example.novelcharacter.dto.StatInfoDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StatCalculator {

    public List<StatInfoDTO> calculate(
            List<StatInfoDTO> baseStats,
            List<EquipmentDataDTO> equipmentList
    ) {
        Map<String, Long> finalStats = new HashMap<>();
        for (StatInfoDTO stat : baseStats) {
            finalStats.put(stat.getStatName(), stat.getValue());
        }

        for (EquipmentDataDTO equip : equipmentList) {
            for (EquipmentStatInfoDTO equipStat : equip.getEquipmentStats()) {
                String name = equipStat.getStatName();
                long value = equipStat.getValue();

                finalStats.putIfAbsent(name, 0L);

                if (equipStat.getType() == 0) {
                    finalStats.put(name, finalStats.get(name) + value);
                } else if (equipStat.getType() == 1) {
                    finalStats.put(name, finalStats.get(name) * value);
                }
            }
        }

        return finalStats.entrySet().stream()
                .map(e -> new StatInfoDTO(e.getKey(), e.getValue()))
                .toList();
    }
}