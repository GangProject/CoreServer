package com.gang.api;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Junwoo on 2017-04-27.
 */
@RestController
@Api(value = "rankedStats", description = "랭크 챔피언 API", basePath= "/api/rankedStats")
@RequestMapping("/api/rankedStats")
public class RankedStatsController {

}
