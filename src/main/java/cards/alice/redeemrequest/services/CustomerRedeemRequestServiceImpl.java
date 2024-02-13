package cards.alice.redeemrequest.services;

import cards.alice.common.models.RedeemRequestDto;
import cards.alice.common.web.mappers.RedeemRequestMapper;
import cards.alice.redeemrequest.domain.RedeemRequest;
import cards.alice.redeemrequest.repositories.RedeemRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerRedeemRequestServiceImpl implements CustomerRedeemRequestService {
    private final RedeemRequestRepository redeemRequestRepository;
    private final RedeemRequestMapper redeemRequestMapper;

    @Override
    public Set<RedeemRequestDto> listRedeemRequests(
            @NonNull Boolean isRedeemed, @NonNull Long cardId, @NonNull Long redeemRuleId) {
        /*final Set<RedeemRequest> redeemRequests;
        if (cardId != null && redeemRuleId != null) {
            redeemRequests = redeemRequestRepository.findByCardIdAndRedeemRuleId(cardId, redeemRuleId);
        } else if (cardId != null) {
            redeemRequests = redeemRequestRepository.findByCardId(cardId);
        } else if (redeemRuleId != null) {
            redeemRequests = redeemRequestRepository.findByRedeemRuleId(redeemRuleId);
        } else {
            redeemRequests = new HashSet<>();
        }*/
        final Set<RedeemRequest> redeemRequests = redeemRequestRepository.findByIsRedeemedAndCardIdAndRedeemRuleId(isRedeemed, cardId, redeemRuleId);
        return redeemRequests.stream().map(redeemRequestMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public Boolean exists(String id) {
        return redeemRequestRepository.existsById(id);
    }

    @Override
    public void deleteRedeemRequestById(String id) {
        redeemRequestRepository.deleteById(id);
    }

    @Override
    public RedeemRequestDto saveNewRedeemRequest(RedeemRequestDto redeemRequestDto) {
        return redeemRequestMapper.toDto(redeemRequestRepository
                .save(redeemRequestMapper.toEntity(redeemRequestDto)));
    }

    @Override
    public Optional<RedeemRequestDto> updateRedeemRequestById(String id, RedeemRequestDto redeemRequestDto) {
        return patchRedeemRequestById(id, redeemRequestDto);
    }

    @Override
    public Optional<RedeemRequestDto> patchRedeemRequestById(String id, RedeemRequestDto redeemRequestDto) {
        final Optional<RedeemRequest> redeemRequest = redeemRequestRepository.findById(id);
        final var atomicReference = new AtomicReference<Optional<RedeemRequestDto>>();
        redeemRequest.ifPresentOrElse(
                srcRedeemRequest -> {
                    final RedeemRequest patchedRedeemRequest = redeemRequestMapper.partialUpdate(redeemRequestDto, srcRedeemRequest);
                    final RedeemRequest savedRedeemRequest = redeemRequestRepository.save(patchedRedeemRequest);
                    final RedeemRequestDto savedRedeemRequestDto = redeemRequestMapper.toDto(savedRedeemRequest);
                    atomicReference.set(Optional.of(savedRedeemRequestDto));
                },
                () -> {
                    atomicReference.set(Optional.empty());
                }
        );
        return atomicReference.get();
    }
}
