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
        String url = "jdbc:sqlite:data.db";
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.sqlite.JDBC");
        ds.setUrl(url);
        ds.setUsername("sa");
        ds.setPassword("sa");
        return ds;
    }

    public DriverManagerDataSource getDefaultDatasource() {
        if (this.dataSource == null) {
            throw new NullPointerException("DataSource não foi inicializado");
        }

        return this.dataSource;
    }

}
