package org.king.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * OSS 配置参数（从 application.yml 的 aliyun.oss 前缀读取）
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {
    private String region; //地域ID，如 cn-beijing
    private String bucket; //Bucket名称，如 java-super
}
