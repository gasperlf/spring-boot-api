package co.com.javadevelopercolombia.api.config.orika;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MapperFactoryBean implements FactoryBean<MapperFactory>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public MapperFactory getObject() {

        DefaultMapperFactory build = new DefaultMapperFactory.Builder().build();

        applicationContext.getBeansOfType(CustomConverter.class).values().stream().forEach(converter -> build.getConverterFactory().registerConverter(converter));

        applicationContext.getBeansOfType(Mapper.class).values().stream().forEach(mapper -> build.registerMapper(mapper));

        applicationContext.getBeansOfType(ClassMapBuilder.class).values().stream().forEach(mapper -> build.registerClassMap(mapper));

        return build;
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFactoryBean.class;
    }


    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
