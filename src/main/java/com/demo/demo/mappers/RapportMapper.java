package com.demo.demo.mappers;

import com.demo.demo.dtos.RapportDTo;
import com.demo.demo.entity.Rapport;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RapportMapper {
    RapportDTo ToRapportDTo(Rapport rapport);
    Rapport ToRapport(RapportDTo rapportDTo);

    void updateRapportFromDTO(RapportDTo rapportDTo, @MappingTarget  Rapport existingRapport);
}
