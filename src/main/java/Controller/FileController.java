package Controller;

import Entities.SinhVien;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class FileController {

    public List<SinhVien> readFile(String path) throws FileNotFoundException {
        List<SinhVien> beans = new CsvToBeanBuilder(new FileReader(path))
                .withType(SinhVien.class)
                .build()
                .parse();

        return beans;

    }

    public void exportCSV(List<SinhVien> listSv , String path) {
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(path));

                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            String[] headerRecord = {"ID", "Name", "Email", "Bonus", "Report", "App", "LT", "Final"};
            csvWriter.writeNext(headerRecord);
            listSv.forEach(x -> {
                csvWriter.writeNext(new String[]{x.getId(), x.getName(), x.getEmail(), String.valueOf(x.getBonus()), String.valueOf(x.getReport()), String.valueOf(x.getApp()), String.valueOf(x.getLt()), String.valueOf(x.getDiemTongKet())});
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
    }
};
