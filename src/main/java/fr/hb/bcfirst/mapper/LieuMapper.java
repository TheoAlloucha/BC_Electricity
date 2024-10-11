package fr.hb.bcfirst.mapper;

import fr.hb.bcfirst.dto.LieuDto;
import fr.hb.bcfirst.model.Lieu;
import jakarta.validation.Valid;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LieuMapper {
    Lieu toEntity(@Valid LieuDto lieuDto);

    LieuDto toDto(Lieu lieu);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Lieu partialUpdate(LieuDto lieuDto, @MappingTarget Lieu lieu);
}