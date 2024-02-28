package com.demo.demo.mappers;

import com.demo.demo.dtos.SoumetDTo;
import com.demo.demo.entity.Soumet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SoumetMapper {
    Soumet ToSoumet(SoumetDTo soumetDTo);
    SoumetDTo ToSoumetDTo(Soumet soumet);

    void updateFromDTO(SoumetDTo soumetDTo, @MappingTarget  Soumet existingSoumet);
}
