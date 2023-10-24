package com.pst.crewpro.models;

import com.pst.crewpro.enums.PeriodEnum;
import com.pst.crewpro.enums.RateEnum;
import com.pst.crewpro.enums.TypeEnum;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

@Data
public class Agreement implements Serializable {

  /** Serial Version UID. */
  private static final long serialVersionUID = 1L;

  @NotNull(message = "ERR400_REQUIRED")
  private String id;

  @NotNull(message = "ERR400_REQUIRED")
  private TypeEnum type;

  private String accountingProfileOverride;
  private RateEnum rate;
  private PeriodEnum period;
}
