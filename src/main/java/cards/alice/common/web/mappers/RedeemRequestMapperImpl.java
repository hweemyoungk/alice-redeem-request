package cards.alice.common.web.mappers;

import cards.alice.redeemrequest.domain.RedeemRequest;
import cards.alice.redeemrequest.models.RedeemRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RedeemRequestMapperImpl implements RedeemRequestMapper {

    @Override
    public RedeemRequest toEntity(RedeemRequestDto redeemRequestDto) {
        if (redeemRequestDto == null) {
            return null;
        }

        RedeemRequest.RedeemRequestBuilder redeemRequest = RedeemRequest.builder();

        redeemRequest.id(redeemRequestDto.getId());
        redeemRequest.ttlSeconds(redeemRequestDto.getTtlSeconds());
        redeemRequest.cardId(redeemRequestDto.getCardId());
        redeemRequest.redeemRuleId(redeemRequestDto.getRedeemRuleId());
        redeemRequest.customerDisplayName(redeemRequestDto.getCustomerDisplayName());
        redeemRequest.customerId(redeemRequestDto.getCustomerId());
        redeemRequest.ownerId(redeemRequestDto.getOwnerId());
        redeemRequest.blueprintDisplayName(redeemRequestDto.getBlueprintDisplayName());
        redeemRequest.expMilliseconds(redeemRequestDto.getExpMilliseconds());
        redeemRequest.isRedeemed(redeemRequestDto.getIsRedeemed());

        return redeemRequest.build();
    }

    @Override
    public RedeemRequestDto toDto(RedeemRequest redeemRequest) {
        if (redeemRequest == null) {
            return null;
        }

        RedeemRequestDto.RedeemRequestDtoBuilder redeemRequestDto = RedeemRequestDto.builder();

        redeemRequestDto.id(redeemRequest.getId());
        redeemRequestDto.ttlSeconds(redeemRequest.getTtlSeconds());
        redeemRequestDto.cardId(redeemRequest.getCardId());
        redeemRequestDto.redeemRuleId(redeemRequest.getRedeemRuleId());
        redeemRequestDto.customerDisplayName(redeemRequest.getCustomerDisplayName());
        redeemRequestDto.customerId(redeemRequest.getCustomerId());
        redeemRequestDto.ownerId(redeemRequest.getOwnerId());
        redeemRequestDto.blueprintDisplayName(redeemRequest.getBlueprintDisplayName());
        redeemRequestDto.expMilliseconds(redeemRequest.getExpMilliseconds());
        redeemRequestDto.isRedeemed(redeemRequest.getIsRedeemed());

        return redeemRequestDto.build();
    }

    @Override
    public RedeemRequest partialUpdate(RedeemRequestDto redeemRequestDto, RedeemRequest redeemRequest) {
        if (redeemRequestDto == null) {
            return redeemRequest;
        }

        if (redeemRequestDto.getId() != null) {
            redeemRequest.setId(redeemRequestDto.getId());
        }
        if (redeemRequestDto.getCardId() != null) {
            redeemRequest.setCardId(redeemRequestDto.getCardId());
        }
        if (redeemRequestDto.getTtlSeconds() != null) {
            redeemRequest.setTtlSeconds(redeemRequestDto.getTtlSeconds());
        }
        if (redeemRequestDto.getRedeemRuleId() != null) {
            redeemRequest.setRedeemRuleId(redeemRequestDto.getRedeemRuleId());
        }
        if (redeemRequestDto.getCustomerDisplayName() != null) {
            redeemRequest.setCustomerDisplayName(redeemRequestDto.getCustomerDisplayName());
        }
        if (redeemRequestDto.getCustomerId() != null) {
            redeemRequest.setCustomerId(redeemRequestDto.getCustomerId());
        }
        if (redeemRequestDto.getOwnerId() != null) {
            redeemRequest.setOwnerId(redeemRequestDto.getOwnerId());
        }
        if (redeemRequestDto.getBlueprintDisplayName() != null) {
            redeemRequest.setBlueprintDisplayName(redeemRequestDto.getBlueprintDisplayName());
        }
        if (redeemRequestDto.getExpMilliseconds() != null) {
            redeemRequest.setExpMilliseconds(redeemRequestDto.getExpMilliseconds());
        }
        if (redeemRequestDto.getIsRedeemed() != null) {
            redeemRequest.setIsRedeemed(redeemRequestDto.getIsRedeemed());
        }

        return redeemRequest;
    }
}
