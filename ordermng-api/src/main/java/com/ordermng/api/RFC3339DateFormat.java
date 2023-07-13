package com.ordermng.api;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.databind.util.ISO8601Utils;

import java.text.FieldPosition;
import java.util.Date;


public class RFC3339DateFormat extends StdDateFormat {

  private static final long serialVersionUID = 1L;

  // Same as ISO8601DateFormat but serializing milliseconds.
  @Override
  public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
    String value = ISO8601Utils.format(date, true);
    toAppendTo.append(value);
    return toAppendTo;
  }

}