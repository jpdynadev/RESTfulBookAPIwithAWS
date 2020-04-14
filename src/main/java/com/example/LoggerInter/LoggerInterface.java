package com.example.LoggerInter;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class LoggerInterface {
	
	
	public void debug(String message) {
		appender(" debug: ", message);
				
	}
	public void error(String message) {
		appender(" error: ", message);
				
	}
	public void warn(String message) {
		appender(" warn: ", message);
	}
	public void info(String message) {
		appender(" info: ", message);
				
	}
	
	public static void appender(String type, String message) {
		try(PrintWriter pw = new PrintWriter(new FileOutputStream("LogFile.txt", true))){
			pw.append(new Timestamp(System.currentTimeMillis()) + type + message + '\n');
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println("Stack trace below: ");
			e.printStackTrace();
		}
	}

}
