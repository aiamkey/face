package org.example.machinglearn.DTO;

import lombok.Data;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class SystemInfoDTO {
    private double cpuUsage;      // CPU使用率(%)
    private double memoryUsage;   // 内存使用率(%)
    private double storageUsage;  // 存储使用率(%)
    private long freeMemory;      // 空闲内存(MB)
    private long totalMemory;     // 总内存(MB)
    private long freeStorage;     // 空闲存储(MB)
    private long totalStorage;    // 总存储(MB)

    // 格式化方法确保小数点后两位
    public double getCpuUsage() {
        return formatDouble(cpuUsage);
    }

    public double getMemoryUsage() {
        return formatDouble(memoryUsage);
    }

    public double getStorageUsage() {
        return formatDouble(storageUsage);
    }

    private double formatDouble(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}