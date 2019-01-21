package com.epam.library.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {
    private static final String[] NOT_NULL_AND_NOT_EMPTY_STRINGS = {"Str_1", "Str_2", "Str_3"};
    private static final String[] NOT_EMPTY_STRINGS_WITH_NULL = {"Str_1", null, "Str_3"};
    private static final String[] EMPTY_STRINGS = {"Str_1", "", ""};
    private static final String EMPTY_STRING = "";
    private static final String NOT_EMPTY_STRING = "Str_1";
    private static final String NUMBER_STRING = "123";
    private static final String BOOLEAN_STRING = "true";

    private StringUtil stringUtil = new StringUtil();

    @Test
    public void shouldReturnTrueWhenInputsStringsAreNotNull() {
        // given

        // when
        boolean result = stringUtil.areNotNull(NOT_NULL_AND_NOT_EMPTY_STRINGS);

        // then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenInputStringIsNull() {
        // given

        // when
        boolean result = stringUtil.areNotNull(NOT_EMPTY_STRINGS_WITH_NULL);

        // then
        Assert.assertFalse(result);
    }

    @Test
    public void shouldReturnTrueWhenInputsStringsAreNotNullAndNotEmpty() {
        // given

        // when
        boolean result = stringUtil.areNotNull(NOT_NULL_AND_NOT_EMPTY_STRINGS);

        // then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenInputsStringsAreEmpty() {
        // given

        // when
        boolean result = stringUtil.areNotNull(EMPTY_STRINGS);

        // then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenInputStringIsEmpty() {
        // given

        // when
        boolean result = stringUtil.isNullOrEmpty(EMPTY_STRING);

        // then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenInputStringIsNull() {
        // given

        // when
        boolean result = stringUtil.isNullOrEmpty(null);

        // then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenInputStringIsNotNull() {
        // given

        // when
        boolean result = stringUtil.isNullOrEmpty(NOT_EMPTY_STRING);

        // then
        Assert.assertFalse(result);
    }

    @Test
    public void shouldReturnTrueWhenInputStringMatchesNumberFormat() {
        // given

        // when
        boolean result = stringUtil.isNumber(NUMBER_STRING);

        // then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenInputStringDoesNotMatchesNumberFormat() {
        // given

        // when
        boolean result = stringUtil.isNumber(NOT_EMPTY_STRING);

        // then
        Assert.assertFalse(result);
    }

    @Test
    public void shouldReturnTrueWhenInputStringMatchesBooleanFormat() {
        // given

        // when
        boolean result = stringUtil.isBoolean(BOOLEAN_STRING);

        // then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenInputStringDoesNotMatchesBooleanFormat() {
        // given

        // when
        boolean result = stringUtil.isBoolean(NOT_EMPTY_STRING);

        // then
        Assert.assertFalse(result);
    }
}
