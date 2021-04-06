package hackaton.crazy_devs.animals_without_owners.configuration.initalizer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class TestDataInit {

    HashMap<Integer, List<Object>> data;

    private void init() {
        ExcelReader excelReader = new ExcelReader();

        try {
            data = excelReader.read("static\\data\\Data_set.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }

        data.entrySet().stream().skip(1).
    }
}
