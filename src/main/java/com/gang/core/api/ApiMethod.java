package com.gang.core.api;

import com.gang.core.constant.Region;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Junwoo & Seungki on 2017-04-05.
 */
@Getter
@Setter
abstract public class ApiMethod {
    private final ApiConfig config;
    private final String service;
    private Region region = null;

    protected ApiMethod(ApiConfig config, String service){
        this.config = config;
        this.service = service;
    }


}
