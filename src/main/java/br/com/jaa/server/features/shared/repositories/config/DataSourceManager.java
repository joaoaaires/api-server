package br.com.jaa.server.features.shared.repositories.config;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Scope(value = "singleton")
@Component
public class DataSourceManager {

    private final DriverManagerDataSource dataSource;

    public DataSourceManager() {
        this.dataSource = initDefaultDatasource();
    }

    private DriverManagerDataSource initDefaultDatasource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:./data");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }

    public DriverManagerDataSource getDefaultDatasource() {
        if (this.dataSource == null) {
            throw new NullPointerException("DataSource não foi inicializado");
        }

        return this.dataSource;
    }

}
