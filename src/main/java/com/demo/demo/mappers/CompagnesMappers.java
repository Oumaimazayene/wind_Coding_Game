package com.demo.demo.mappers;

import com.demo.demo.dtos.CandidateDTO;
import com.demo.demo.dtos.CompagnesDTo;
import com.demo.demo.entity.Compagnes;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CompagnesMappers {
    Compagnes ToCompagnes(CompagnesDTo compagnesDTo);
    CompagnesDTo ToCompagnesDTo(Compagnes compagnes);

    void updateCompagneFromDTO(CompagnesDTo compagnesDTo, @MappingTarget Compagnes existingCompagne);
}
