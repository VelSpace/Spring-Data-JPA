package ai.rezo.mshr.ManiHRBOOT.Utils;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SwaggerProperty {
	public static Properties prop = null;

	private static final Logger logger = LoggerFactory.getLogger(SwaggerProperty.class);

	public static String url;
	public static String password;
	public static String search_regex;
	public static String model_name;
	public static String number_id;
	public static String rec_url;

	static {

		try {
			prop = utils.loadPropertyValues(MSHRFilePaths.SWAGGER_FILE_PACKAGE);
			url = prop.getProperty("url").trim();
			password = prop.getProperty("password").trim();
			search_regex = prop.getProperty("search_regex").trim();
			model_name = prop.getProperty("model_name").trim();
			number_id = prop.getProperty("number_id").trim();

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception ", ex.toString());
		}
	}
}
