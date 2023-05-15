package com.coppel.api;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author greyv
 *
 */
@Configuration
@PropertySource(value = "classpath:application.properties")
@ComponentScan(basePackages = "com.coppel.api")
@EnableTransactionManagement
@EnableJpaRepositories
public class ConfigDataSource {
	private static final Logger logger = LoggerFactory.getLogger(ConfigDataSource.class);

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClass;

	@Bean(name = "dataSource")
	@Qualifier("dataSource")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public DataSource dataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(driverClass);
		dataSourceBuilder.url(url);
		dataSourceBuilder.username(username);
		dataSourceBuilder.password(password);
		return dataSourceBuilder.build();
	}
	 
	/*
	@Autowired
	private Environment environment;

	@Bean(name = "dataSource")
	@Qualifier("dataSource")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public JndiObjectFactoryBean oracleDataSource() {
		JndiObjectFactoryBean dataSource = null;
		String nombreJndi = null;
		nombreJndi = environment.getProperty("jdni.datasource.name");
		nombreJndi = StringUtils.trimToEmpty(nombreJndi);
		logger.info("Recupero nombre jndi ORACLE " + nombreJndi);
		dataSource = new JndiObjectFactoryBean();
		dataSource.setCache(true);
		dataSource.setLookupOnStartup(true);
		dataSource.setProxyInterface(DataSource.class);
		dataSource.setExpectedType(DataSource.class);
		dataSource.setResourceRef(true);
		dataSource.setJndiName(nombreJndi);
		logger.info("Creo el dataSource ORACLE " + dataSource);
		return dataSource;
	}
	 */
	@Bean(name = "jdbcTemplate")
	// @Qualifier("jdbcTemplate")
	public JdbcTemplate jdbcTemplate(
			// @Qualifier("oracleDataSource")
			final DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("JdbcTemplate: " + jdbcTemplate);
		return jdbcTemplate;
	}

	@Bean(name = "namedParameterJdbcTemplate")
	// @Qualifier("namedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
		return namedParameterJdbcTemplate;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.coppel.api","com.coppel.api.model","com.coppel.api.model.entity" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

}
