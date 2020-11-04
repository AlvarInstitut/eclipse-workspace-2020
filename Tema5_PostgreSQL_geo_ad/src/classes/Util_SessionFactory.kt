package dades

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.hibernate.service.ServiceRegistry
import org.hibernate.service.ServiceRegistryBuilder

class Util_SessionFactory() {
	var sF:SessionFactory? = null
	
	fun getSessionFactory(): SessionFactory? {
		if (this.sF == null) {
			val conf = Configuration().configure();
			conf.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
			val reg = ServiceRegistryBuilder();
			reg.applySettings(conf.getProperties());
			val serviceRegistry = reg.buildServiceRegistry ();

			sF = conf.buildSessionFactory(serviceRegistry);
		}

		return sF

	}
}