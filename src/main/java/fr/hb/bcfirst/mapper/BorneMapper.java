package fr.hb.bcfirst.mapper;

import fr.hb.bcfirst.dto.BorneDto;
import fr.hb.bcfirst.model.Borne;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component("BorneMapper")
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BorneMapper {
    Borne toEntity(BorneDto borneDto);

    BorneDto toDto(Borne borne);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Borne partialUpdate(BorneDto borneDto, @MappingTarget Borne borne);
}