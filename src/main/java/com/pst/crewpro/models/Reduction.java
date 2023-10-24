package com.pst.crewpro.models;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

@Data
public class Reduction implements Serializable {

  /** Serial Version UID. */
  private static final long serialVersionUID = 1L;

  @NotNull(message = "ERR400_REQUIRED")
  private String id;

  private boolean excludeRestDaysFlag;
  private boolean excludeAllEarningsFlag;
  private boolean unavailableExceedFlag;
}
