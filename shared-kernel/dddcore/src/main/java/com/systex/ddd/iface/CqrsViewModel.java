package com.systex.ddd.iface;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CqrsViewModel<META> {

    private META metaData;
    private String message;
}
