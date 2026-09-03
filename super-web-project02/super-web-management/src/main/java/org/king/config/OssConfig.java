package org.king.config;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.credentials.CredentialsProvider;
import com.aliyun.sdk.service.oss2.credentials.EnvironmentVariableCredentialsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OSS 客户端配置：把 OSSClient 注册为 Spring 单例 Bean
 * 凭证从环境变量 OSS_ACCESS_KEY_ID / OSS_ACCESS_KEY_SECRET 自动读取
 */
@Configuration
public class OssConfig {

    @Bean
    public OSSClient ossClient(OssProperties properties) {
        CredentialsProvider provider = new EnvironmentVariableCredentialsProvider();
        return OSSClient.newBuilder()
                .credentialsProvider(provider)
                .region(properties.getRegion())
                .build();
    }
}
