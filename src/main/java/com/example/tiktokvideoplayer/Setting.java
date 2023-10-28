package com.example.tiktokvideoplayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Setting {
    private static final Logger LOGGER = LoggerFactory.getLogger(Setting.class);
    private String key;
    private String value;

    public Setting(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static List<Setting> settingKeyValue() {
        try {
            LOGGER.debug("Setting Listesi Parse Ediliyor");
            File xmlFile = new File("son_ayar.xml"); // XML dosyanızın adını ve yolunu belirtin
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            NodeList lastSetting = doc.getElementsByTagName("Setting");
            List<Setting> settings = new ArrayList<>();

            for (int i = 0; i < lastSetting.getLength(); i++) {
                Node node = lastSetting.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String key = element.getElementsByTagName("key").item(0).getTextContent();
                    String value = element.getElementsByTagName("value").item(0).getTextContent();

                    Setting setting = new Setting(key, value);
                    settings.add(setting);
                }
            }
            return settings;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            //throw new RuntimeException("son ayar dosyası parse edilemedi "+e.getMessage());
            return new ArrayList<>();
        }
    }
}
