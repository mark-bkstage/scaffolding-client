package com.pst.crewpro.client;

import com.pst.crewpro.enums.AgreementTypeEnum;
import com.pst.crewpro.models.Profile;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

@HttpExchange("/crewpro/timekeeping/guarantee")
public interface ProfilesClient {

  @Retryable(
      retryFor = {Exception.class},
      maxAttemptsExpression = "${retry.maxAttempts:3}",
      backoff =
          @Backoff(
              random = true,
              delayExpression = "${retry.delayelay:5000}",
              maxDelayExpression = "${retry.maxDelay:15000}",
              multiplier = 2))
  @GetExchange("/profiles")
  List<Profile> getProfiles(
      @RequestParam(name = "agreementType", required = false) AgreementTypeEnum agreementType,
      @RequestParam(name = "excludeRestDaysFlag", required = false) Boolean excludeRestDaysFlag,
      @RequestParam(name = "commentId", required = false) String commentId,
      @RequestHeader Map<String, String> headers);

  @Retryable(
      retryFor = {Exception.class},
      maxAttemptsExpression = "${retry.maxAttempts:3}",
      backoff =
          @Backoff(
              random = true,
              delayExpression = "${retry.delayelay:5000}",
              maxDelayExpression = "${retry.maxDelay:15000}",
              multiplier = 2))
  @PostExchange("/profiles")
  Profile createAgreementProfile(
      @Valid @RequestBody Profile profile, @RequestHeader Map<String, String> headers);

  @Retryable(
      retryFor = {Exception.class},
      maxAttemptsExpression = "${retry.maxAttempts:3}",
      backoff =
          @Backoff(
              random = true,
              delayExpression = "${retry.delayelay:5000}",
              maxDelayExpression = "${retry.maxDelay:15000}",
              multiplier = 2))
  @GetExchange("/profiles/{oId}")
  Profile getProfileById(@PathVariable Integer oId, @RequestHeader Map<String, String> headers);

  @Retryable(
      retryFor = {Exception.class},
      maxAttemptsExpression = "${retry.maxAttempts:3}",
      backoff =
          @Backoff(
              random = true,
              delayExpression = "${retry.delayelay:5000}",
              maxDelayExpression = "${retry.maxDelay:15000}",
              multiplier = 2))
  @PutExchange("/profiles/{oId}")
  Profile updateProfile(
      @PathVariable Integer oId,
      @Valid @RequestBody Profile profile,
      @RequestHeader Map<String, String> headers);

  @Retryable(
      retryFor = {Exception.class},
      maxAttemptsExpression = "${retry.maxAttempts:3}",
      backoff =
          @Backoff(
              random = true,
              delayExpression = "${retry.delayelay:5000}",
              maxDelayExpression = "${retry.maxDelay:15000}",
              multiplier = 2))
  @DeleteExchange("/profiles/{oId}")
  Void deleteProfile(@PathVariable Integer oId, @RequestHeader Map<String, String> headers);
}
