package org.example.machinglearn.Controller;

import org.example.machinglearn.DTO.SystemInfoDTO;
import org.example.machinglearn.Service.SystemInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/system")
public class SystemInfoController {

    private final SystemInfoService systemInfoService;

    public SystemInfoController(SystemInfoService systemInfoService) {
        this.systemInfoService  = systemInfoService;
    }

    @GetMapping("/info")
    public SystemInfoDTO getSystemInfo() {
        return systemInfoService.getSystemInfo();
    }
}
