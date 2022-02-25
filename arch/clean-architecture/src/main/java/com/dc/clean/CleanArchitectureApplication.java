package com.dc.clean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CleanArchitectureApplication {

    public static void main(String[] args) {
        SpringApplication.run(CleanArchitectureApplication.class, args);
    }
/*
    public static void main(String[] args) {
        SpringApplication.run(CleanArchitectureApplication.class);
    }


    @Bean
    BeanFactoryPostProcessor beanFactoryPostProcessor(ApplicationContext beanRegistry) {

        return configBeanFactory -> {

            ConfigurableListableBeanFactory beanFactory = ((AnnotationConfigServletWebServerApplicationContext) beanRegistry).getBeanFactory();
            BeanDefinitionRegistry  beanDefinitionRegistry = (BeanDefinitionRegistry) beanFactory;
            
            genericApplicationContext(beanDefinitionRegistry);
        };

    }

    static TypeFilter removeModelAndEntitiesFilter() {
        return (MetadataReader mr, MetadataReaderFactory mrf) -> !mr.getClassMetadata()
                .getClassName()
                .endsWith("Model");
    }


    void genericApplicationContext(BeanDefinitionRegistry beanRegistry) {
        ClassPathBeanDefinitionScanner beanDefinitionScanner = new ClassPathBeanDefinitionScanner(beanRegistry);
        beanDefinitionScanner.addIncludeFilter(removeModelAndEntitiesFilter());
        beanDefinitionScanner.scan("com.dc.clean");
    }


*/
}
