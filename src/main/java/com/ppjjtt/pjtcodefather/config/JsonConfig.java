package com.ppjjtt.pjtcodefather.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Spring MVC Json 配置
 */
// 1. 注解：标记这是 Jackson JSON 组件，SpringBoot 会自动加载生效
@JsonComponent
public class JsonConfig {

    /**
     * 全局配置：解决 Long 转 JSON 精度丢失
     */
    // 2. 注册 Bean：替换 Spring 默认的 JSON 序列化工具 ObjectMapper
    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        // 3. 构建标准的 JSON 序列化对象（关闭XML功能，只用JSON）
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        // 4. 创建自定义模块：用来注册「类型转换规则」
        SimpleModule module = new SimpleModule();

        // 5. 核心配置1：包装类 Long → 转字符串
        module.addSerializer(Long.class, ToStringSerializer.instance);
        // 6. 核心配置2：基本类型 long → 转字符串
        module.addSerializer(Long.TYPE, ToStringSerializer.instance);

        // 7. 把自定义规则注册到序列化工具中
        objectMapper.registerModule(module);

        // 8. 返回全局生效的 ObjectMapper
        return objectMapper;
    }
}
