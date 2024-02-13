package cards.alice.redeemrequest.services;


import cards.alice.common.models.RedeemRequestDto;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.Set;

public interface CustomerRedeemRequestService {
    RedeemRequestDto saveNewRedeemRequest(RedeemRequestDto redeemRequestDto);

    Set<RedeemRequestDto> listRedeemRequests(
            @NonNull Boolean isRedeemed, @NonNull Long cardId, @NonNull Long redeemRuleId);


    Optional<RedeemRequestDto> updateRedeemRequestById(String id, RedeemRequestDto redeemRequestDto);

    Optional<RedeemRequestDto> patchRedeemRequestById(String id, RedeemRequestDto redeemRequestDto);

    Boolean exists(String id);

    void deleteRedeemRequestById(String id);
}
