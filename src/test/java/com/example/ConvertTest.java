package com.example;

import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConvertTest {

  private DigitConvert digitConvert;

  @BeforeEach
  void setup() {
    digitConvert = new DigitConvert();
  }

  @Test
  void combineWithSingleDigitsTest() {
    assertThat(digitConvert.convert(2, 3))
        .containsExactly("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
  }

  @Test
  void combineWithOneDigit() {
    assertThat(digitConvert.convert(9)).containsExactly("w", "x", "y", "z");
  }

  @Test
  void combineWithOneOrZeroTest() {
    assertThat(digitConvert.convert(1, 2, 0, 1, 3, 0))
        .containsExactly("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
  }

  @Test
  void includeNotSupportDigitTest() {
    IllegalArgumentException exception =
        Assertions.assertThrows(
            IllegalArgumentException.class, () -> digitConvert.convert(-1, 99, 100));

    assertThat(exception.getMessage())
        .isEqualTo(
            "This digit -1 is less then the min digit 0, This digit 100 is greater then the max digit 99");
  }

  @Test
  void combineWithDoubleDigit() {
    assertThat(digitConvert.convert(1, 23, 9))
        .containsExactly(
            "adw", "adx", "ady", "adz", "aew", "aex", "aey", "aez", "afw", "afx", "afy", "afz",
            "bdw", "bdx", "bdy", "bdz", "bew", "bex", "bey", "bez", "bfw", "bfx", "bfy", "bfz",
            "cdw", "cdx", "cdy", "cdz", "cew", "cex", "cey", "cez", "cfw", "cfx", "cfy", "cfz");
  }

  @Test
  void validationTest() {
    Validation validation = new Validation(Collections.emptyList());

    assertThat(validation.isValid()).isEqualTo(true);
    assertThat(validation.getDigitErrors()).isEmpty();
    assertThat(validation.getAllMsgs()).isEqualTo("");
  }

  @Test
  void DigitErrorTest() {
    DigitError digitError = new DigitError("");

    assertThat(digitError.getMsg()).isEmpty();
  }
}
