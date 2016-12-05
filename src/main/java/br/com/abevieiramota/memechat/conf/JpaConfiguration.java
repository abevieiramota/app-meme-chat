package br.com.abevieiramota.memechat.conf;

import java.net.URI;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JpaConfiguration {

	@Bean
	public DriverManagerDataSource dataSource() throws SQLException {
		URI dbUri = URI.create(System.getenv("DATABASE_URL"));

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername(dbUri.getUserInfo().split(":")[0]);
		dataSource.setPassword(dbUri.getUserInfo().split(":")[1]);
		dataSource.setUrl("jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath());
		dataSource.setDriverClassName("org.postgresql.Driver");

		return dataSource;
	}

}
