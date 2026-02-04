package com.info.configdemo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BuildInfoController {

//    @Value("${build.id}")
//    private String buildId;
//
//    @Value("${build.version}")
//    private String buildVersion;
//
//    @Value("${build.name}")
//    private String buildName;

    private BuildInfo buildInfo;

    @GetMapping("/build-info")
    private String getBuildInfo() {
        return "Build ID: " + buildInfo.getId() + ", Build Version: " + buildInfo.getVersion() + ", Build Name: " + buildInfo.getName();
    }
}
