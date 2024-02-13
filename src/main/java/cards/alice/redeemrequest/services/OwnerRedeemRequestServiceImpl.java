package cards.alice.redeemrequest.services;

import cards.alice.common.web.mappers.RedeemRequestMapper;
import cards.alice.redeemrequest.domain.RedeemRequest;
import cards.alice.common.models.RedeemRequestDto;
import cards.alice.redeemrequest.repositories.RedeemRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerRedeemRequestServiceImpl implements OwnerRedeemRequestService {
    private final RedeemRequestRepository redeemRequestRepository;
    private final RedeemRequestMapper redeemRequestMapper;

    @Override
    public RedeemRequestDto saveNewRedeemRequest(RedeemRequestDto redeemRequestDto) {
        return redeemRequestMapper.toDto(redeemRequestRepository
                .save(redeemRequestMapper.toEntity(redeemRequestDto)));
    }

    @Override
    public Optional<RedeemRequestDto> getRedeemRequestById(String id) {
        return Optional.ofNullable(redeemRequestMapper.toDto(redeemRequestRepository.findById(id).orElse(null)));
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

    @Override
    public Set<RedeemRequestDto> listRedeemRequests(UUID ownerId, Set<String> ids) {
        final Set<RedeemRequest> redeemRequests;
        if (ownerId != null && ids != null) {
            redeemRequests = redeemRequestRepository.findByOwnerIdAndIdIn(ownerId, ids);
        } else if (ownerId != null) {
            redeemRequests = redeemRequestRepository.findByOwnerId(ownerId);
        } else if (ids != null) {
            redeemRequests = redeemRequestRepository.findByIdIn(ids);
        } else {
            redeemRequests = new HashSet<>();
        }

        return redeemRequests.stream().map(redeemRequestMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    public void deleteRedeemRequestById(String id) {
        redeemRequestRepository.deleteById(id);
    }

    @Override
    public Boolean exists(String id) {
        return redeemRequestRepository.existsById(id);
    }
}
