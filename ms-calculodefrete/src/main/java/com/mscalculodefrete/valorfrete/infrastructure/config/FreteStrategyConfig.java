package com.mscalculodefrete.valorfrete.infrastructure.config;

import com.mscalculodefrete.valorfrete.infrastructure.enums.Transporte;
import com.mscalculodefrete.valorfrete.infrastructure.interfaces.FreteStrategyInterface;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteExpresso;
import com.mscalculodefrete.valorfrete.infrastructure.models.FreteNormal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class FreteStrategyConfig {
    @Bean
    public Map<Transporte, FreteStrategyInterface> estrategiasDeFrete(
            FreteNormal freteNormal,
            FreteExpresso freteExpresso
    ) {
        return Map.of(
                Transporte.NORMAL, freteNormal,
                Transporte.EXPRESSO, freteExpresso
        );
    }
}