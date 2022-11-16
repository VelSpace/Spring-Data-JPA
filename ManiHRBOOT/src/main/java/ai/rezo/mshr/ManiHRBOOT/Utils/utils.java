package ai.rezo.mshr.ManiHRBOOT.Utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class utils {
    private static final Logger logger =  LoggerFactory.getLogger(utils.class);
    public static Properties loadPropertyValues(String filePath_Name) {

		Properties prop = null;
		try (InputStream in = utils.class.getResourceAsStream(filePath_Name)) {
			prop = new Properties();
			prop.load(new InputStreamReader(in, Charset.forName("UTF-8")));
		} catch (Exception e) {
			logger.error("utils.loadPropertyValues()==>>Exception:", e.toString());
		}
		return prop;
	}
}
