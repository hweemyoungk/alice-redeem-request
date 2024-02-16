package cards.alice.redeemrequest.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RedisHash("RedeemRequest")
public class RedeemRequest {
    @Id
    private String id;
    @TimeToLive
    private Long ttlSeconds;
    @Indexed
    @NotNull
    @Positive
    private Long cardId;
    @Indexed
    @NotNull
    @Positive
    private Long redeemRuleId;
    @NotNull
    @NotBlank
    private String customerDisplayName;
    @NotNull
    private UUID customerId;

    @NotNull
    @Indexed
    private UUID ownerId;
    //private UUID token;
    @NotNull
    private String blueprintDisplayName;
    @Positive
    private Long expMilliseconds;
    @Indexed
    @NotNull
    private Boolean isRedeemed;
}