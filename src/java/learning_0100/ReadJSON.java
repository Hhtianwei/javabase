package learning_0100;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.model.Person;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ReadJSON
{
	public static void main(String[] args)
	{

		Person p1 = new Person(123, "tianwei", 25);
		JSONObject obj = JSONObject.fromObject(p1);
		System.out.println(obj.getString("id"));

		//json 数组
		Person p4 = new Person(121, "tianwei4", 21);
		Person p3 = new Person(124, "tianwei2", 24);
		Person p2 = new Person(125, "tianwei3", 25);
		List<Person> list = new ArrayList<Person>();
		list.add(p4);
		list.add(p2);
		list.add(p3);
		JSONArray obj2 = JSONArray.fromObject(list);
		System.out.println(obj2.toString());
		for (int i = 0; i < obj2.size(); i++)
		{
			JSONObject o = obj2.getJSONObject(i);
			Iterator<String> iter = o.keys();
			while (iter.hasNext())
			{
				String key = iter.next();
				System.out.println(key + "-->" + o.getString(key));
			}
		}
	}
}
