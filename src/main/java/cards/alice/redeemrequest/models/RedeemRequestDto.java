package cards.alice.redeemrequest.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RedeemRequestDto {
    private String id;
    private Long ttlSeconds;
    @NotNull
    @Positive
    private Long cardId;
    @NotNull
    @Positive
    private Long redeemRuleId;
    @NotNull
    @NotBlank
    private String customerDisplayName;
    @NotNull
    private UUID customerId;

    private UUID ownerId;
    private String blueprintDisplayName;
    @Positive
    private Long expMilliseconds;
    private Boolean isRedeemed;

    public void setExpMillisecondsFromNow(long milliseconds) {
        expMilliseconds = Instant.now().toEpochMilli() + milliseconds;
    }
}