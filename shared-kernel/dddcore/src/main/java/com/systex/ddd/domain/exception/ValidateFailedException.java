package com.systex.ddd.domain.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ValidateFailedException extends RuntimeException {

    private static final long serialVersionUID = -2294054198084347502L;

    @Getter
    private final List<DomainErrorStatus> errors = new ArrayList<>();

    @Getter
    private final List<ParamedError> paramedErrors = new ArrayList<>();

    public ValidateFailedException(DomainErrorType domainErrorType) {
        super(domainErrorType.getType());
    }

    public ValidateFailedException(DomainErrorStatus... errors) {
        super(DomainErrorType.VALIDATE_FAILED.name());
        if (errors != null && errors.length > 0) {
            this.errors.addAll(Arrays.asList(errors));
        }
    }

    public ValidateFailedException(DomainErrorStatus error, String msg) {
        super(DomainErrorType.VALIDATE_FAILED.name());
        error.setMessage(msg);
        errors.add(error);
    }

    public void add(DomainErrorStatus error) {
        errors.add(error);
    }

    public void add(DomainErrorStatus error, String msg) {
        error.setMessage(msg);
        errors.add(error);
    }

    public void add(ValidateFailedException exception) {
        errors.addAll(exception.getErrors());
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void add(ParamedError paramedError) {
        paramedErrors.add(paramedError);
    }

    public boolean hasParamedErrors() {
        return !paramedErrors.isEmpty();
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum DomainErrorType {

        VALIDATE_FAILED("validation-error", "Validation Failed"), USER_CONFIRM("user-confirm", "User Confirm"),
        ;

        @Getter
        private final String type;

        @Getter
        private String description;

        DomainErrorType(String type, String description) {
            this.type = type;
            this.description = description;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum DomainErrorStatus {

        NOT_FOUND("NotFound", "Not Found"), VALIDATE_FAILED("ValidateFailed", "Validate Failed"),

        SAMPLE_SOMETHING_WRONG("SomethingWrong", "Something Wrong");

        @Getter
        private final String code;

        @Getter
        @Setter
        private String message;

        DomainErrorStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    // 支援參數的 error 格式
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class ParamedError {

        String code;
        List<String> params;
    }

}
