package org.taoding.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.taoding.properties.AliOssProperties;
import org.taoding.utils.AliOssUtil;

/**
 * @Date 6/25/24 17:59
 * @Author Tao Ding
 * @Description: TODO
 */
@Slf4j
@Configuration
public class OssConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public AliOssUtil aliossUtil(AliOssProperties aliossProperties){
        log.info("开始创建阿里云文件上传工具类对象：{}", aliossProperties);
        return new AliOssUtil(aliossProperties.getEndpoint(),
                aliossProperties.getAccessKeyId(),
                aliossProperties.getAccessKeySecret(),
                aliossProperties.getBucketName());
    }
}
