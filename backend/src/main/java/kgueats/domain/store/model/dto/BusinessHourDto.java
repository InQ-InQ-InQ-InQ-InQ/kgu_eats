package kgueats.domain.store.model.dto;

import java.time.LocalTime;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.store.model.entity.BusinessHour;

@Getter
public class BusinessHourDto {

    private final LocalTime openTime;
    private final LocalTime closeTime;

    @Builder
    public BusinessHourDto(LocalTime openTime, LocalTime closeTime) {
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public static BusinessHourDto toDto(BusinessHour businessHour) {
        return BusinessHourDto.builder()
                .openTime(businessHour.getOpenTime())
                .closeTime(businessHour.getCloseTime())
                .build();
    }

}
