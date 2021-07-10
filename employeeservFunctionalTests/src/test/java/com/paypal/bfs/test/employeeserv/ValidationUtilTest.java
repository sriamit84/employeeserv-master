package com.paypal.bfs.test.employeeserv;

import com.paypal.bfs.test.employeeserv.exceptions.BadInputException;
import com.paypal.bfs.test.employeeserv.validators.ValidationUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.paypal.bfs.test.employeeserv.exceptions.ErrorMessages.*;


public class ValidationUtilTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testFirstNameValidation() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_FIRSTNAME);
        ValidationUtil.VALIDATE_FIRSTNAME.accept(null);
    }

    @Test
    public void testLastNameValidation() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_LASTNAME);
        ValidationUtil.VALIDATE_LASTNAME.accept(null);
    }
    @Test
    public void testLine1Validation() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_LINE1);
        ValidationUtil.VALIDATE_LINE1.accept(null);
    }

    @Test
    public void testCityValidation() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_CITY);
        ValidationUtil.VALIDATE_CITY.accept(null);
    }

    @Test
    public void testStateValidation() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_STATE);
        ValidationUtil.VALIDATE_STATE.accept(null);
    }

    @Test
    public void testCountryValidation() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_COUNTRY);
        ValidationUtil.VALIDATE_COUNTRY.accept(null);
    }

    @Test
    public void testZipcodeValidation() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_ZIPCODE);
        ValidationUtil.VALIDATE_ZIPCODE.accept(null);
    }

    @Test
    public void testDateOfBirthValidationNull() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_DATE);
        ValidationUtil.VALIDATE_DATE.accept(null);
    }
    @Test
    public void testDateOfBirthValidationInvalidDate() {
        exception.expect(BadInputException.class);
        exception.expectMessage(INVALID_DATE_PATTERN);
        ValidationUtil.VALIDATE_DATE_FORMAT.apply("21/01/2019");
        ValidationUtil.VALIDATE_DATE_FORMAT.apply("21-01-2019");
    }
}