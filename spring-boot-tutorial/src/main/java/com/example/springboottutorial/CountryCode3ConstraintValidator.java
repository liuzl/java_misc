package com.example.springboottutorial;

import com.neovisionaries.i18n.CountryCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class CountryCode3ConstraintValidator implements ConstraintValidator<ValidCountryCode3, String> {

    @Override
    public void initialize(final ValidCountryCode3 arg0) {

    }

    @SneakyThrows
    @Override
    public boolean isValid(String iso3, ConstraintValidatorContext context) {
        if (iso3 == null) {
            return true;
        }
        CountryCode code = CountryCode.getByAlpha3Code(iso3);
        if (code == null) {
            return false;
        }
        log.info(String.valueOf(code.getCurrency()));
        return true;
    }
}

