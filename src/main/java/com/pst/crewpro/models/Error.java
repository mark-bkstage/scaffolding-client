package com.pst.crewpro.models;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

@Data
public class Error implements Serializable {

  /** Serial Version UID. */
  private static final long serialVersionUID = 1L;

  private String id;

  @NotNull(message = "ERR400_REQUIRED")
  private String title;

  @NotNull(message = "ERR400_REQUIRED")
  private String code;

  private String detail;
}
