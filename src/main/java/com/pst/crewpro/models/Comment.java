package com.pst.crewpro.models;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

@Data
public class Comment implements Serializable {

  /** Serial Version UID. */
  private static final long serialVersionUID = 1L;

  @NotNull(message = "ERR400_REQUIRED")
  private String id;

  private String text;
  private String message;
}
