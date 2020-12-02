package classes

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.hibernate.service.ServiceRegistry
import org.hibernate.service.ServiceRegistryBuilder

object SessionFactoryUtil {
	lateinit var sessionFactory: SessionFactory

	init {
		val conf = Configuration().configure()
		conf.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false")
		val reg = ServiceRegistryBuilder()
		reg.applySettings(conf.getProperties())
		val serviceRegistry = reg.buildServiceRegistry()
		sessionFactory = conf.buildSessionFactory(serviceRegistry)
	}
}