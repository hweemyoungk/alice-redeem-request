package cards.alice.redeemrequest.web.controllers;

import cards.alice.common.models.RedeemRequestDto;
import cards.alice.redeemrequest.services.OwnerRedeemRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("${cards.alice.redeemrequest.web.controllers.path.base}")
@RequiredArgsConstructor
public class OwnerRedeemRequestController {
    @Value("${cards.alice.redeemrequest.server.host}:${cards.alice.redeemrequest.server.port}")
    private String redeemRequestHostname;
    @Value("${cards.alice.redeemrequest.web.controllers.path.base}${cards.alice.redeemrequest.web.controllers.path.owner.redeem-request}")
    private String redeemRequestPath;

    private final OwnerRedeemRequestService redeemRequestService;

    @PostMapping(path = "${cards.alice.redeemrequest.web.controllers.path.owner.redeem-request}")
    //@PreAuthorize("authentication.name == #redeemRequestDto.customerId.toString()")
    public ResponseEntity postRedeemRequest(@Validated @RequestBody RedeemRequestDto redeemRequestDto) {
        RedeemRequestDto savedRedeemRequestDto = redeemRequestService.saveNewRedeemRequest(redeemRequestDto);
        return ResponseEntity.created(URI.create(redeemRequestHostname + redeemRequestPath + "/" + savedRedeemRequestDto.getId())).build();
    }

    @GetMapping(path = "${cards.alice.redeemrequest.web.controllers.path.owner.redeem-request}/{id}/exists")
    public ResponseEntity<Boolean> getRedeemRequestExists(@PathVariable String id) {
        final Boolean exists = redeemRequestService.exists(id);
        return ResponseEntity.ok(exists);
    }

    @GetMapping(path = "${cards.alice.redeemrequest.web.controllers.path.owner.redeem-request}/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<RedeemRequestDto> getRedeemRequest(@PathVariable String id) {
        final RedeemRequestDto redeemRequestDto = redeemRequestService.getRedeemRequestById(id).orElseThrow();
        return ResponseEntity.ok(redeemRequestDto);
    }

    @PutMapping(path = "${cards.alice.redeemrequest.web.controllers.path.owner.redeem-request}/{id}")
    public ResponseEntity putRedeemRequest(@PathVariable String id, @Validated @RequestBody RedeemRequestDto redeemRequestDto) {
        Optional<RedeemRequestDto> updatedRedeemRequestDto = redeemRequestService.updateRedeemRequestById(id, redeemRequestDto);
        updatedRedeemRequestDto.orElseThrow();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "${cards.alice.redeemrequest.web.controllers.path.owner.redeem-request}/{id}")
    public ResponseEntity deleteRedeemRequest(@PathVariable String id) {
        redeemRequestService.deleteRedeemRequestById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(path = "${cards.alice.redeemrequest.web.controllers.path.owner.redeem-request.list}", produces = "application/json;charset=UTF-8")
    //@PreAuthorize("authentication.name == #ownerId.toString()")
    public ResponseEntity<Set<RedeemRequestDto>> listRedeemRequests(@RequestParam UUID ownerId) {
        final Set<RedeemRequestDto> redeemRuleDtos = redeemRequestService
                .listRedeemRequests(ownerId, null);
        return ResponseEntity.ok(redeemRuleDtos);
    }
}
