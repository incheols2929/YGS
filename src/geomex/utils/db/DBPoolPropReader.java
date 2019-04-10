package geomex.utils.db;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <JDBCPoolDefinition> <br>
 * &nbsp;<JDBCPool><br>
 * &nbsp;&nbsp;<NodeName>42150</NodeName><br>
 * &nbsp;&nbsp;<Driver>org.postgresql.Driver</Driver><br>
 * &nbsp;&nbsp;<Url><![CDATA[jdbc:postgresql://localhost:5432/42150]]></Url><br>
 * &nbsp;&nbsp;<User>postgres</User><br>
 * &nbsp;&nbsp;<Passwd>postgres</Passwd><br>
 * &nbsp;&nbsp;<Encoding>utf-8</Encoding><br>
 * &nbsp;&nbsp;<CheckQuery><![CDATA[select 1]]></CheckQuery><br>
 * &nbsp;&nbsp;<MinCapacity>2</MinCapacity><br>
 * &nbsp;&nbsp;<MaxCapacity>2</MaxCapacity><br>
 * &nbsp;&nbsp;<WaitTimeout>1</WaitTimeout> <br>
 * &nbsp;&nbsp;<CheckTimeout>1</CheckTimeout><br>
 * &nbsp;&nbsp;<LoggerName>server-logger</LoggerName><br>
 * &nbsp;</JDBCPool><br>
 * </JDBCPoolDefinition><br>
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class DBPoolPropReader extends DefaultHandler {

    private static final int DBPOOL_TAG = 1;
    private ArrayList<DBPoolProp> list = null;
    private StringBuilder buffer = null;
    private DBPoolProp prop = null;
    private boolean validTag = false;
    private int status = -1;

    /** Creates a new instance of DBConnPropParser */
    public DBPoolPropReader() {}

    @Override
    public void startDocument() throws SAXException {
        buffer = new StringBuilder();
        list = new ArrayList<DBPoolProp>();
        prop = null;
    }

    @Override
    public void endDocument() throws SAXException {}

    @Override
    public void startElement(String uri, String localName, String qName,
        Attributes attributes) throws SAXException {
        buffer.setLength(0);
        if (qName.equalsIgnoreCase("JDBCPoolDefinition")) {
            validTag = true;
            System.out.println("JDBCPoolDefinition Parsing Start.");
        } else if (qName.equalsIgnoreCase("JDBCPool")) {
            status = DBPOOL_TAG;
            prop = new DBPoolProp();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
        throws SAXException {
        if (!validTag) {
            return;
        }
        switch (status) {
            case DBPOOL_TAG:
                if (qName.equalsIgnoreCase("NodeName")) {
                    prop.setName(buffer.toString().trim());
                } else if (qName.equalsIgnoreCase("Driver")) {
                    prop.setDriver(buffer.toString().trim());
                } else if (qName.equalsIgnoreCase("Url")) {
                    prop.setUrl(buffer.toString().trim());
                } else if (qName.equalsIgnoreCase("User")) {
                    prop.setProperty("user", buffer.toString().trim());
                } else if (qName.equalsIgnoreCase("Passwd")) {
                    prop.setProperty("password", buffer.toString().trim());
                } else if (qName.equalsIgnoreCase("Encoding")) {
                    prop.setProperty("encoding", buffer.toString().trim());
                } else if (qName.equalsIgnoreCase("CheckQuery")) {
                    prop.setCheckQuery(buffer.toString().trim());
                } else if (qName.equalsIgnoreCase("MinCapacity")) {
                    prop.setMinCapacity(Integer.parseInt(buffer.toString().trim()));
                } else if (qName.equalsIgnoreCase("MaxCapacity")) {
                    prop.setMaxCapacity(Integer.parseInt(buffer.toString().trim()));
                } else if (qName.equalsIgnoreCase("WaitTimeout")) {
                    prop.setWaitTimeout(Integer.parseInt(buffer.toString().trim()));
                } else if (qName.equalsIgnoreCase("CheckTimeout")) {
                    prop.setCheckTimeout(Integer.parseInt(buffer.toString().trim()));
                } else if (qName.equalsIgnoreCase("LoggerName")) {
                    prop.setLoggerName(buffer.toString().trim());
                } else if (qName.equalsIgnoreCase("JDBCPool")) {
                    list.add(prop);
                    status = -1;
                }
                break;
        }

        if (qName.equalsIgnoreCase("JDBCPoolDefinition")) {
            validTag = false;
            buffer = null;
            System.out.println("JDBCPoolDefinition Parsing Finished.");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
        throws SAXException {
        buffer.append(ch, start, length);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        super.error(e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        super.fatalError(e);
    }

    public ArrayList<DBPoolProp> getProperty() {
        return list;
    }
}
