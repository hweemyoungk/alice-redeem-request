package cards.alice.redeemrequest.repositories;

import cards.alice.redeemrequest.domain.RedeemRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RedeemRequestRepository extends CrudRepository<RedeemRequest, String> {
    Optional<RedeemRequest> findByRedeemRuleIdAndCardId(@NonNull Long redeemRuleId, @NonNull Long cardId);

    Set<RedeemRequest> findByOwnerIdAndIdIn(@NonNull UUID ownerId, @NonNull Set<String> ids);

    Set<RedeemRequest> findByIdIn(@NonNull Set<String> ids);

    Set<RedeemRequest> findByOwnerId(@NonNull UUID ownerId);

}
