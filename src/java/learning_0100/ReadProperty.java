package learning_0100;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;


public class ReadProperty
{
	static Properties properties = null;

	public static void main(String[] args) throws IOException
	{
		//1. 从项目的resource文件夹（他是一个classpath）中读取source.properties文件的内容
		properties = readCityCodeFromFile();

		//2. 获取指定文件的内容（这个文件可能来源于电脑的任意一个文件）
		//readFileFromFile();

		//3. 执行一个任务 每隔一定时间打印出一句话 (时间单位为秒，每隔n秒执行一次任务)
		doSomething();
	}

	private static void doSomething()
	{
		String startTime = properties.getProperty("task.start.time");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try
		{
			date = sdf.parse(startTime);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		while (true)
		{
			System.out.println("3333333333");
			if (new Date().getTime() > date.getTime())
			{
				break;
			}
			if (new Date().getTime() == date.getTime())
			{
				//到点了，就开始执行任务。一般来说，只要是一个独立的任务，都给他一个线程
				task();
				System.out.println("========111111111111111=======");
				//break;
			}
			System.out.println("=======222222222222222222222222=======");
		}
	}

	private static void task()
	{
		Thread t = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				System.out.println("-------------------------------------------------------------");
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("start task , say hello");
			}
		});
		t.start();
	}

	private static void readFileFromFile() throws IOException
	{
		String path = properties.getProperty("file.path");
		if (path == null || "".equals(path))
		{
			System.out.println("path is not exists,can't read");
			return;
		}
		File file = new File(path);
		if (!file.exists() || !file.isFile())
		{
			System.out.println("file is not exists or file");
			return;
		}
		Reader reader = new InputStreamReader(new FileInputStream(file), "gb2312");
		int tempChar;
		while ((tempChar = reader.read()) != -1)
		{
			System.out.print((char) tempChar);
		}
		reader.close();
		//		InputStream input = new FileInputStream(file);
		//		byte[] bytes = new byte[input.available()];
		//		input.read(bytes);
		//		System.out.println(new String(bytes));
		//		input.close();
	}

	private static Properties readCityCodeFromFile() throws IOException
	{
		InputStream input = ReadProperty.class.getClass().getResourceAsStream("/config.properties");
		Properties property = new Properties();
		//property.load(input);
		property.load(new InputStreamReader(input, "UTF-8"));
		Set<Object> set = property.keySet();
		for (Object key : set)
		{
			System.out.println(key + "-->" + property.getProperty((String) key));
		}

		return property;
	}

}
