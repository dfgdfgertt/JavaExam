package App;

import Controller.FileController;
import Entities.SinhVien;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws IOException {
        String fileName = "data.csv";
        String fileOut = "output.csv";
        FileController fileController = new FileController();
        List<SinhVien> listSv = fileController.readFile(fileName);
        listSv.sort((a,b)->{
            Float p1 = a.getLt();
            Float p2 = b.getLt();
            if (p1 > p2) {
                return -1;
            }else if (p1 < p2) {
                return 1;
            }else
                return 0;
        });
        //listSv.forEach(x -> System.out.println(x));

        //Top 10 cao
        for (int i = 0; i<10 ; i++ ){
            System.out.println(listSv.get(i));
        }

        //Diem tong ket
        listSv.forEach(x -> System.out.println(x.getName() +" "+ x.getDiemTongKet()));

        //Top 10 thấp
        listSv.sort((a,b)->{
            Float p1 = a.getDiemTongKet();
            Float p2 = b.getDiemTongKet();
            if (p1 > p2) {
                return 1;
            }else if (p1 < p2) {
                return -1;
            }else
                return 0;
        });
        for (int i = 0; i<10 ; i++ ){
            System.out.println(listSv.get(i));
        }


        //export
        fileController.exportCSV(listSv,fileOut);


        // print all
        thongKe(listSv);

        //logGmail
        logGmail(listSv);


        //export
        String fileOutLook = "outlook.bin";
        List<SinhVien> newList = new ArrayList<>();
        listSv.forEach(x -> {
            if (x.checkMail() == 2) {
                newList.add(x);
            }
        });
        fileController.exportCSV(newList,fileOutLook);


        //
        List<SinhVien> list2_3 = fileController.readFile(fileOutLook);
        list2_3.forEach(x -> System.out.println(x));


}


    public static void thongKe(List<SinhVien> listSv){
        System.out.println("Số lượng sinh viên: " + listSv.size());
        int soLuongDat = (int) listSv.stream().filter(x -> x.dat() == 1).count();
        System.out.println("Số lượng đạt: " + soLuongDat + " \tTỉ lệ: " + soLuongDat/listSv.size()*100 + "%");
        System.out.println("Số lượng không đạt: " + (listSv.size() - soLuongDat) + " \tTỉ lệ: " + (listSv.size() - soLuongDat)/listSv.size()*100+ "%");
        int gioi = (int) listSv.stream().filter(x -> x.rank() == 1).count();
        int kha = (int) listSv.stream().filter(x -> x.rank() == 2).count();
        int trungBinh = (int) listSv.stream().filter(x -> x.rank() == 3).count();
        System.out.println("Số lượng giỏi: " + gioi + " \tTỉ lệ: " + gioi/listSv.size()*100 + "%");
        System.out.println("Số lượng khá: " + kha + " \tTỉ lệ: " + kha/listSv.size()*100 + "%");
        System.out.println("Số lượng trung bình: " + trungBinh + " \tTỉ lệ: " + trungBinh/listSv.size()*100 + "%");
    }

    public static void logGmail(List<SinhVien> listSv){
        listSv.forEach(x ->
        {
            if (x.checkMail() == 1) {
                System.out.println(x);
            }
        });
    }
}
