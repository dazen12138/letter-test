package com.example;

import java.util.List;
import java.util.StringJoiner;
import lombok.Getter;

@Getter
public class Validation {

  private final boolean isValid;

  private final List<DigitError> digitErrors;

  public Validation(List<DigitError> digitErrors) {
    this.isValid = digitErrors.isEmpty();
    this.digitErrors = digitErrors;
  }

  public String getAllMsgs() {
    StringJoiner joiner = new StringJoiner(", ");

    for (DigitError digitError : digitErrors) {
      joiner.add(digitError.getMsg());
    }

    return joiner.toString();
  }
}
