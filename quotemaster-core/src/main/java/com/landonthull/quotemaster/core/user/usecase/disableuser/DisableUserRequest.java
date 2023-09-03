package com.landonthull.quotemaster.core.user.usecase.disableuser;

import java.util.UUID;

public class DisableUserRequest {

  private UUID userId;

  public DisableUserRequest(UUID userId) {
    this.userId = userId;
  }

  public UUID getUserId() {
    return this.userId;
  }

  public void setUserId(final UUID userId) {
    this.userId = userId;
  }
}
