package br.com.multreaders.readers.skipper;

import br.com.multreaders.model.City;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class SkipTestReaderConfig {

    @Bean("skipReader")
    public ItemReader<City> readerException(@Qualifier("springBatchBusinessDataSource")DataSource dataSource){
        return new JdbcCursorItemReaderBuilder<City>()
                .name("skipException")
                .dataSource(dataSource)
                .sql("Select * from City")
               // .beanRowMapper(City.class)
                .rowMapper(rowMapper())
                .build();

    }

    private RowMapper<City> rowMapper() {
        return new RowMapper<City>() {
            @Override
            public City mapRow(ResultSet rs, int rowNum) throws SQLException {
                if (rs.getRow() == 11)
                    throw new SQLException((String.format("End of execution - Bad City %s", rs.getString("name"))));
                else
                    return cityRowMapper(rs);
                }
                private City cityRowMapper(ResultSet rs) throws SQLException {
                    City city = new City();
                    city.setCountry(rs.getString("country"));
                    city.setName(rs.getString("name"));
                    city.setState(rs.getString("state"));
                    city.setZip(rs.getString("zip"));
                    return city;
                }
        };
    }
}
