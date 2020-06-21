/**
 * Student Name  : 2016312021
 * Student ID No.: Moon Taeui
 */

import cache.Cache;
import cache.CacheTypeOne;
import cache.CacheTypeZero;
import cache.DataInstruction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CacheSimulator {

    private List<DataInstruction> instructionList = new ArrayList<>();
    private Cache cache;
    private int missCount = 0;


    public CacheSimulator(int cacheType) {
        switch (cacheType) {
            case 0 -> cache = new CacheTypeZero();
            case 1 -> cache = new CacheTypeOne();
        }
    }


    public static void main(String... args) {
        CacheSimulator cacheSimulator = new CacheSimulator(Integer.parseInt(args[0]));

        cacheSimulator.simulate("trace" + args[1] + ".txt");
    }

    public void simulate(String fileAddress) {
        try {
            readFile(fileAddress);

            for (DataInstruction dataInstruction : instructionList) {
                runInstruction(dataInstruction);
            }

            System.out.println(missCount + " " + cache.getWritingCount());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile(String fileAddress) throws IOException {
        FileReader fileReader = new FileReader(new File(fileAddress));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";

        while ((line = bufferedReader.readLine()) != null) {
            String[] splitedStr = line.split(" ");
            String instructionStr = splitedStr[0];
            String dataAddress = splitedStr[1];

            if (instructionStr.equals("L")) {
                instructionList.add(new DataInstruction(Long.parseLong(dataAddress, 16), true));
            } else {
                instructionList.add(new DataInstruction(Long.parseLong(dataAddress, 16), false));
            }
        }

        bufferedReader.close();
        fileReader.close();
    }

    private void runInstruction(DataInstruction dataInstruction) {
        if (dataInstruction.isLoad()) {
            if (!cache.load(dataInstruction.getDataAddress())) {
                missCount++;
                System.out.println("L " + Long.toHexString(dataInstruction.getDataAddress()) + " : miss");
            } else {
                System.out.println("L " + Long.toHexString(dataInstruction.getDataAddress()) + " : hit");
            }
        } else {
            if (!cache.save(dataInstruction.getDataAddress())) {
                missCount++;
                System.out.println("S " + Long.toHexString(dataInstruction.getDataAddress()) + " : miss");
            } else {
                System.out.println("S " + Long.toHexString(dataInstruction.getDataAddress()) + " : hit");
            }
        }
    }

}
