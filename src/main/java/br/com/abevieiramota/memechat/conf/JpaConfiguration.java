package br.com.abevieiramota.memechat.conf;

import java.net.URI;
import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JpaConfiguration {

	@Bean
	public DriverManagerDataSource connection() throws SQLException {
		URI dbUri = URI.create(System.getenv("DATABASE_URL"));

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername(dbUri.getUserInfo().split(":")[0]);
		dataSource.setPassword(dbUri.getUserInfo().split(":")[1]);
		dataSource.setUrl("jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath());
		dataSource.setDriverClassName("org.postgresql.Driver");

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		URI dbUri = URI.create(System.getenv("DATABASE_URL"));

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(vendorAdapter);

		// necessário jdbc.properties em resources
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername(dbUri.getUserInfo().split(":")[0]);
		dataSource.setPassword(dbUri.getUserInfo().split(":")[1]);
		dataSource.setUrl("jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath());
		dataSource.setDriverClassName("org.postgresql.Driver");

		factoryBean.setDataSource(dataSource);

		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "create");
		factoryBean.setJpaProperties(props);

		factoryBean.setPackagesToScan("br.com.abevieiramota.memechat.model");

		return factoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
