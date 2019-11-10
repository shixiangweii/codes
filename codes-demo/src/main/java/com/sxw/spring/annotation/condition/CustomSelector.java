package com.sxw.spring.annotation.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/**
 * @author shixi
 */
public class CustomSelector implements ImportSelector {
    /**
     * 收集需要导入的类
     * 注意这里不是“筛选”，就是类似"@Bean"的功能，往IOC中加入bean
     * @param importingClassMetadata 当前标注@Import注解的类的所有注解信息
     * @return 计算后导入到容器中的bean全类名
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Set<String> annotationTypes = importingClassMetadata.getAnnotationTypes();
        return new String[]{"com.sxw.spring.annotation.bean.Yellow"};
    }
}
