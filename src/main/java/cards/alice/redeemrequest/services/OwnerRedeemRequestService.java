package cards.alice.redeemrequest.services;


import cards.alice.common.models.RedeemRequestDto;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface OwnerRedeemRequestService {
    RedeemRequestDto saveNewRedeemRequest(RedeemRequestDto redeemRequestDto);

    Optional<RedeemRequestDto> getRedeemRequestById(String id);

    Optional<RedeemRequestDto> updateRedeemRequestById(String id, RedeemRequestDto redeemRequestDto);

    Optional<RedeemRequestDto> patchRedeemRequestById(String id, RedeemRequestDto redeemRequestDto);

    Set<RedeemRequestDto> listRedeemRequests(UUID ownerId, Set<String> ids);

    void deleteRedeemRequestById(String id);

    Boolean exists(String id);

}
