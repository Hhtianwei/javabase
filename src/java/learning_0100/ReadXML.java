package learning_0100;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class ReadXML
{
	public static void main(String[] args) throws DocumentException
	{
		readXML();
	}

	private static void readXML() throws DocumentException
	{
		//解析xml文件有多种方式，最常用的是通过dmo4j这种方式 需要引入dom4j.jar

		SAXReader reader = new SAXReader();
		InputStream in = ReadXML.class.getClass().getResourceAsStream("/stu.xml");
		Document doc = reader.read(in);

		Element root = doc.getRootElement();

		//获取到所有的学生
		List<Element> list = root.elements();
		for (Element e : list)
		{
			//每个学生
			readElement(e);
			System.out.println("=====================================");
		}
	}

	private static void readElement(Element e)
	{
		if (e.elements().isEmpty())
		{
			System.out.println(e.getName() + "-->" + e.getText());
		}
		else
		{
			List<Element> list = e.elements();
			for (Element e2 : list)
			{
				readElement(e2);
			}
		}

	}

}
