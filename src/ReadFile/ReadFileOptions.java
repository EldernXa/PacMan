package ReadFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ReadFileOptions {
    private String file[];
    private String pathName;
    private ArrayList<String> option;
    private ArrayList<Integer> stateOption;


    public ReadFileOptions(String str) {
        pathName = str;
        option = new ArrayList<>();
        stateOption = new ArrayList<>();


        Path path = Paths.get(str);
        try {
            this.file = Files.readString(path).split("\n");
            read();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void read() {

        for (int i = 0; i < file.length; i++) {

            try {
                String line[] = file[i].split("\\s+");
                option.add(line[0]);
                stateOption.add(Integer.parseInt(line[1]));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }



    public  boolean isState(String state){
        for(int i = 0; i < option.size();i++){
            if(option.get(i).equals(state)){
                if(stateOption.get(i) == 1){
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }
}
