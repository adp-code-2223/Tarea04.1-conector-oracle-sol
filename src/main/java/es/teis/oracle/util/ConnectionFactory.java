package es.teis.oracle.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import oracle.jdbc.datasource.impl.OracleDataSource;

public class ConnectionFactory {

	private static OracleDataSource ods = null;
	private static final String RUTA_FICHERO_CONFIG = "src/main/resources/db.properties";
	private static final String URL_KEY = "url";
	private static final String USER_KEY = "user";
	private static final String PWD_KEY = "pwd";

	private ConnectionFactory() {

	}

	public static OracleDataSource getDataSource() {
		if (ods == null) {
			Properties properties = new Properties();
			try (FileInputStream fis = new FileInputStream(RUTA_FICHERO_CONFIG)) {
				properties.load(fis);
				String url = properties.getProperty(URL_KEY);
				String user = properties.getProperty(USER_KEY);
				String pwd = properties.getProperty(PWD_KEY);

				ods = new OracleDataSource();
				ods.setURL(url);
				ods.setUser(user);
				ods.setPassword(pwd);

			} catch (FileNotFoundException e) {
				System.err.println("Ha ocurrido una excepción FileNotFound: " + e.getMessage());
			} catch (IOException e) {
				System.err.println("Ha ocurrido una excepción IOE: " + e.getMessage());
			} catch (Exception e) {
				System.err.println("Ha ocurrido una excepción: " + e.getMessage());

			}
			
		}
		
		return ods;

	}
	
	
}
