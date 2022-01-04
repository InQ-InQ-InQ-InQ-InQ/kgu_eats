package kgueats.domain.store.model.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.store.model.entity.BusinessHour;

@Getter
public class BusinessHourDto {

    private final LocalDateTime openTime;
    private final LocalDateTime closeTime;

    @Builder
    public BusinessHourDto(LocalDateTime openTime, LocalDateTime closeTime) {
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
