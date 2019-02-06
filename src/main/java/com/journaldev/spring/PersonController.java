package com.journaldev.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.sf.saxon.xqj.SaxonXQDataSource;

@RestController
public class PersonController {
	
	@RequestMapping("/")
	public String welcome() throws Exception {
		readXml();
		return "Welcome to Spring Boot REST...";
	}
	
	private void readXml() throws Exception {
		  ClassLoader classLoader = this.getClass().getClassLoader();
	      InputStream inputStream = new FileInputStream(new File(classLoader.getResource("books.xqy").getFile()));
	      XQDataSource ds = new SaxonXQDataSource();
	      XQConnection conn = ds.getConnection();
	      XQPreparedExpression exp = conn.prepareExpression(inputStream);
	      XQResultSequence result = exp.executeQuery();
	      
	      while (result.next()) {
	         System.out.println(result.getItemAsString(null));
	      }
	   }
}
