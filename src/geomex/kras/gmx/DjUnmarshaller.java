package geomex.kras.gmx;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;


public class DjUnmarshaller {


	private static HashMap<String, JAXBContext> jcMap = new HashMap<String, JAXBContext>();
	
	
	@SuppressWarnings("rawtypes")
	public Object getUnmarshal(Class cls, String data) {
		
		Object obj = null;

		/*
		System.out.println(cls.getName()+">>>");
		System.out.println(jcMap.get(cls.getName()));
		System.out.println(">>> Now jcMap >>>");
		for( String key : jcMap.keySet() ) {
			System.out.println(cls.getName()+">>>");
			System.out.println(jcMap.get(key));
		}
		System.out.println(">>>>>>");
		*/
		
		try {
			
			if ( jcMap.get(cls.getName()) == null )
				jcMap.put(cls.getName(), JAXBContext.newInstance(cls));

			obj = jcMap.get(cls.getName()).createUnmarshaller().unmarshal( IOUtils.toInputStream(data, "UTF-8") );
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

}
