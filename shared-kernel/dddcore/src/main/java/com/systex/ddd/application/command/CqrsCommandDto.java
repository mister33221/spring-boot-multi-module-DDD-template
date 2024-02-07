package com.systex.ddd.application.command;

import com.systex.ddd.application.Dto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CqrsCommandDto<ID> implements Dto {

    private List<ID> ids;
    private String message;
}
