package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class DigitConvert {

  private static final int MIN_DIGIT = 0;
  private static final int MAX_DIGIT = 99;

  private final Map<Integer, List<String>> mapping =
      Map.of(
          0,
          Collections.emptyList(),
          1,
          Collections.emptyList(),
          2,
          List.of("A", "B", "C"),
          3,
          List.of("D", "E", "F"),
          4,
          List.of("G", "H", "I"),
          5,
          List.of("J", "K", "L"),
          6,
          List.of("M", "N", "O"),
          7,
          List.of("P", "Q", "R", "S"),
          8,
          List.of("T", "U", "V"),
          9,
          List.of("W", "X", "Y", "Z"));

  private final Function<String, String> toLowerCase = String::toLowerCase;

  public List<String> convert(int... digits) {
    Validation validate = validate(digits);

    if (!validate.isValid()) {
      throw new IllegalArgumentException(validate.getAllMsgs());
    }

    return convertDigit(digits);
  }

  private List<String> convertDigit(int[] digits) {
    List<List<String>> container = new ArrayList<>();

    for (int digit : digits) {
      container.add(getDigitMapping(digit));
    }

    for (int i = 0; i < container.size() - 1; i++) {
      List<String> result = combine(container.get(i), container.get(i + 1));
      container.set(i + 1, result);
    }

    return container.get(container.size() - 1);
  }

  private List<String> getDigitMapping(int digit) {
    if (digit >= 0 && digit <= 9) {
      // it is a single digit

      return mapping.get(digit).stream().map(toLowerCase).collect(toList());
    } else {

      return convertDigit(toSingleInteger(digit));
    }
  }

  private int[] toSingleInteger(int digit) {
    char[] chars = String.valueOf(digit).toCharArray();

    int[] digits = new int[chars.length];

    for (int i = 0; i < chars.length; i++) {
      digits[i] = Character.getNumericValue(chars[i]);
    }

    return digits;
  }

  private Validation validate(int[] digits) {

    List<DigitError> errors = new ArrayList<>();

    for (int digit : digits) {
      if (digit < MIN_DIGIT) {
        errors.add(
            new DigitError(
                String.format("This digit %d is less then the min digit %d", digit, MIN_DIGIT)));
      } else if (digit > MAX_DIGIT) {
        errors.add(
            new DigitError(
                String.format("This digit %d is greater then the max digit %d", digit, MAX_DIGIT)));
      }
    }

    return new Validation(errors);
  }

  private List<String> combine(List<String> letterLeft, List<String> letterRight) {
    if (letterLeft.isEmpty()) {
      return letterRight;
    }
    if (letterRight.isEmpty()) {
      return letterLeft;
    }

    List<String> result = new ArrayList<>(letterLeft.size() * letterRight.size());

    for (String left : letterLeft) {
      for (String right : letterRight) {
        result.add(left.concat(right));
      }
    }

    return result;
  }
}
