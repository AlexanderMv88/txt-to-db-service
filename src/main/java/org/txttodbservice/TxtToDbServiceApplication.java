package org.txttodbservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.txttodbservice.file_reader.MyReader;
import org.txttodbservice.repository.AnalysisRepository;

import java.io.File;

@SpringBootApplication
public class TxtToDbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TxtToDbServiceApplication.class, args);
	}

	@Component
	public class MyRunner implements CommandLineRunner {

		@Autowired
		AnalysisRepository analysisRepository;

		@Override
		public void run(String... args) throws Exception {
			//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			String dir =  System.getProperty("user.dir") +File.separator;
			String fileName ="fileForReading";
			new MyReader(dir, fileName, analysisRepository);
		}
	}
}
