package org.example.machinglearn.Service.Impl;

import org.example.machinglearn.DTO.SystemInfoDTO;
import org.example.machinglearn.Service.SystemInfoService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Arrays;

@Service
public class SystemInfoServiceImpl implements SystemInfoService {

    @Override
    public SystemInfoDTO getSystemInfo() {
        SystemInfoDTO info = new SystemInfoDTO();
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();

        // 改进的CPU监控
        if (osBean instanceof com.sun.management.OperatingSystemMXBean) {
            com.sun.management.OperatingSystemMXBean sunOsBean =
                    (com.sun.management.OperatingSystemMXBean) osBean;

            // 采用最近5次采样的平均值
            double[] samples = new double[5];
            for (int i = 0; i < 5; i++) {
                samples[i] = sunOsBean.getSystemCpuLoad() * 100;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            }
            info.setCpuUsage(Arrays.stream(samples).average().orElse(0));
        }

        // 改进的内存监控（包含系统级内存）
        if (osBean instanceof com.sun.management.OperatingSystemMXBean) {
            com.sun.management.OperatingSystemMXBean sunOsBean =
                    (com.sun.management.OperatingSystemMXBean) osBean;
            info.setTotalMemory(sunOsBean.getTotalPhysicalMemorySize() / (1024 * 1024));
            info.setFreeMemory(sunOsBean.getFreePhysicalMemorySize() / (1024 * 1024));
            info.setMemoryUsage((1 - (double) sunOsBean.getFreePhysicalMemorySize() /
                    sunOsBean.getTotalPhysicalMemorySize()) * 100);
        }

        // 改进的存储监控（多路径检查）
        File[] roots = File.listRoots();
        long total = 0, free = 0;
        for (File root : roots) {
            total += root.getTotalSpace();
            free += root.getFreeSpace();
        }
        info.setTotalStorage(total / (1024 * 1024));
        info.setFreeStorage(free / (1024 * 1024));
        info.setStorageUsage((1 - (double) free / total) * 100);

        return info;
    }
}