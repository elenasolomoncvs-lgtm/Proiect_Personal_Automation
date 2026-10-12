package ConfigUtility;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ConfigReader {

    public static Configuration readConfig(String filePath) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();
            Element dbConfigElement = (Element) root.getElementsByTagName("DataBaseConfig").item(0);

            if (dbConfigElement != null) {
                DataBaseConfig dbConfig = new DataBaseConfig();
                dbConfig.setPort(dbConfigElement.getElementsByTagName("port").item(0).getTextContent());
                dbConfig.setDataBase(dbConfigElement.getElementsByTagName("dataBase").item(0).getTextContent());
                dbConfig.setUserName(dbConfigElement.getElementsByTagName("userName").item(0).getTextContent());
                dbConfig.setPassword(dbConfigElement.getElementsByTagName("password").item(0).getTextContent());

                Configuration config = new Configuration();
                config.setDataBaseConfig(dbConfig);

                return config;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
