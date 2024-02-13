package cards.alice.redeemrequest.web.controllers;

import cards.alice.common.models.RedeemRequestDto;
import cards.alice.redeemrequest.services.CustomerRedeemRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("${cards.alice.redeemrequest.web.controllers.path.base}")
@RequiredArgsConstructor
public class CustomerRedeemRequestController {
    @Value("${cards.alice.redeemrequest.server.host}:${cards.alice.redeemrequest.server.port}")
    private String redeemRequestHostname;
    @Value("${cards.alice.redeemrequest.web.controllers.path.base}${cards.alice.redeemrequest.web.controllers.path.customer.redeem-request}")
    private String redeemRequestPath;

    private final CustomerRedeemRequestService redeemRequestService;

    @PostMapping(path = "${cards.alice.redeemrequest.web.controllers.path.customer.redeem-request}")
    //@PreAuthorize("authentication.name == #redeemRequestDto.customerId.toString()")
    public ResponseEntity postRedeemRequest(@Validated @RequestBody RedeemRequestDto redeemRequestDto) {
        RedeemRequestDto savedRedeemRequestDto = redeemRequestService.saveNewRedeemRequest(redeemRequestDto);
        return ResponseEntity.created(URI.create(redeemRequestHostname + redeemRequestPath + "/" + savedRedeemRequestDto.getId())).build();
    }

    @GetMapping(path = "${cards.alice.redeemrequest.web.controllers.path.customer.redeem-request}/{id}/exists")
    public ResponseEntity<Boolean> getRedeemRequestExists(@PathVariable String id) {
        final Boolean exists = redeemRequestService.exists(id);
        return ResponseEntity.ok(exists);
    }

    @PutMapping(path = "${cards.alice.redeemrequest.web.controllers.path.customer.redeem-request}/{id}")
    public ResponseEntity putRedeemRequest(@PathVariable String id, @Validated @RequestBody RedeemRequestDto redeemRequestDto) {
        Optional<RedeemRequestDto> updatedRedeemRequestDto = redeemRequestService.updateRedeemRequestById(id, redeemRequestDto);
        updatedRedeemRequestDto.orElseThrow();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "${cards.alice.redeemrequest.web.controllers.path.customer.redeem-request}/{id}")
    public ResponseEntity deleteRedeemRequest(@PathVariable String id) {
        redeemRequestService.deleteRedeemRequestById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(path = "${cards.alice.redeemrequest.web.controllers.path.customer.redeem-request.list}", produces = "application/json;charset=UTF-8")
    //@PreAuthorize("authentication.name == #customerId.toString()")
    public ResponseEntity<Set<RedeemRequestDto>> listRedeemRequests(
            @RequestParam Boolean isRedeemed, @RequestParam Long cardId, @RequestParam Long redeemRuleId) {
        final Set<RedeemRequestDto> redeemRuleDtos = redeemRequestService
                .listRedeemRequests(isRedeemed, cardId, redeemRuleId);
        return ResponseEntity.ok(redeemRuleDtos);
    }
}
