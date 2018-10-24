package org.txttodbservice.file_reader;



import org.txttodbservice.entity.Analysis;
import org.txttodbservice.repository.AnalysisRepository;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyReader {




    public MyReader(String dir, String fileName, AnalysisRepository analysisRepository) {


        Path path = Paths.get(dir);
        FileSystem fileSystem = FileSystems.getDefault();
        WatchService watchService = null;
        try {
            watchService = fileSystem.newWatchService();

            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //if (watchService!=null) {
            while (true) {
                WatchKey watchKey = null;
                try {
                    watchKey = watchService.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
                for (WatchEvent<?> we : watchEvents) {
                    if (we.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                        System.out.println("Created: " + we.context());
                        String fileNameFormSystem = we.context().toString().trim();
                        if (fileNameFormSystem.equals(fileName)) {

                            //System.out.println("1");
                            String content = null;
                            try {
                                content = new String(Files.readAllBytes(Paths.get(dir + fileName)));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            if (writeDataToDb(content, analysisRepository)){
                                System.out.println("Данные записаны");
                            }else{
                                System.out.println("Данные не записаны");
                            }


                            break;

                        }
                    } else if (we.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                        System.out.println("Deleted: " + we.context());
                    } else if (we.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        System.out.println("Modified :" + we.context());
                    }
                }
                if (!watchKey.reset()) {
                    break;
                }
            }
        //}
    }

    private boolean writeDataToDb(String content, AnalysisRepository analysisRepository) {
        Map<String, String> values = new HashMap<>();
        List<String> lines  = Arrays.asList(content.split("\n"));
        for (String line: lines){
            String val0 = line.split("=")[0];
            String val1 = line.split("=")[1];
            values.put(val0,val1);

        }
        Analysis analysis = new Analysis(values.get((Object) "parameter1"), values.get((Object) "parameter2"), values.get((Object) "parameter3"));
        analysisRepository.save(analysis);
        return true;
    }
}
