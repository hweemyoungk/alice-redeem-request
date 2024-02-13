package cards.alice.common.web.mappers;

import cards.alice.redeemrequest.domain.RedeemRequest;
import cards.alice.redeemrequest.models.RedeemRequestDto;

public interface RedeemRequestMapper {
    RedeemRequest toEntity(RedeemRequestDto redeemRequestDto);

    RedeemRequestDto toDto(RedeemRequest redeemRequest);

    RedeemRequest partialUpdate(RedeemRequestDto redeemRequestDto, RedeemRequest redeemRequest);
}
