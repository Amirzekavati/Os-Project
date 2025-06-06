//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ProcessorReader {
    ProcessorReader() {
    }

    public static List<Processor> readFromFile(String filename) {
        List<Processor> processors = new ArrayList();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            try {
                String line = reader.readLine();

                while((line = reader.readLine()) != null) {
                    String[] uv = line.split(",");
                    long id = (long)Integer.parseInt(uv[0].trim());
                    long cache = (long)Integer.parseInt(uv[1].trim());
                    long memory = (long)Integer.parseInt(uv[2].trim());
                    long frequency = (long)Integer.parseInt(uv[3].trim());
                    Processor processor = new Processor(id, cache, memory, frequency);
                    processors.add(processor);
                }
            } catch (Throwable var15) {
                try {
                    reader.close();
                } catch (Throwable var14) {
                    var15.addSuppressed(var14);
                }

                throw var15;
            }

            reader.close();
        } catch (IOException var16) {
            var16.printStackTrace();
        }

        return processors;
    }
}
