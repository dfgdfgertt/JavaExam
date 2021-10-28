package Entities;
import com.opencsv.bean.CsvBindByName;

import java.util.ArrayList;
import java.util.List;

public class SinhVien {
    @CsvBindByName(column  = "ID")
    private String id;
    @CsvBindByName(column  = "Name")
    private String name;
    @CsvBindByName(column  = "Email")
    private String email;
    @CsvBindByName(column  = "Bonus")
    private float bonus;
    @CsvBindByName(column  = "Report")
    private float report;
    @CsvBindByName(column  = "App")
    private float app;
    @CsvBindByName(column  = "LT")
    private float lt;

    private float diemTongKet;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public float getBonus() {
        return bonus;
    }

    public float getReport() {
        return report;
    }

    public float getApp() {
        return app;
    }

    public float getLt() {
        return lt;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", bonus=" + bonus +
                ", report=" + report +
                ", app=" + app +
                ", lt=" + lt +
                ", diemTongKet=" + diemTongKet +
                '}';
    }

    public void setDiemTongKet(float diemTongKet) {
        this.diemTongKet = diemTongKet;
    }

    public List<SinhVien> top10(List<SinhVien> list){
        list.sort(this::compare);
        //list.forEach(x -> System.out.println(x));
        return list;
    }

    public List<SinhVien> listOutLook(List<SinhVien> list){
        List<SinhVien> newList = new ArrayList<>();
        list.forEach(x -> {
            if (x.checkMail() == 2) {
                newList.add(x);
            }
        });
        return newList;
    }

    public  int compare(SinhVien sv1 , SinhVien sv2){
        Float p1 = sv1.getLt();
        Float p2 = sv2.getLt();
        if (p1 > p2) {
            return 1;
        }else if (p1 < p2) {
            return -1;
        }else
            return 0;
    }

    public float getDiemTongKet() {
        float diemTongKet= (float) ((this.bonus * 0.1)+(this.report * 0.3) + (this.app * 0.15) + (this.lt * 0.45));
        diemTongKet = (float) Math.round(diemTongKet * 10) / 10;
        if (diemTongKet % 1 >= 0.75) {
            diemTongKet = (float) (Math.floor(diemTongKet/1) + 1);
        }else if (diemTongKet % 1 >= 0.25) {
            diemTongKet = (float) (Math.floor(diemTongKet/1) + 0.5);
        }else {
            diemTongKet = (float) Math.floor(diemTongKet/1);
        }
        this.setDiemTongKet(diemTongKet);
        return diemTongKet;
    }

    public int dat(){
        if (diemTongKet > 4.5) {
            return 1;
        }else
            return -1;
    }
    public int rank(){
        if (diemTongKet >= 8 ) {
            return 1;
        }else if (diemTongKet >= 6.5 ) {
            return 2;
        }else if (diemTongKet >= 5) {
            return 3;
        };
          return 0;
    }

    public int checkMail() {
        String gmail = "(.*)@gmail.com";
        String outlook = "(.*)@outlook.com";
        if (this.email.matches(gmail)) {
            return 1;
        }else if (this.email.matches(outlook)) {
            return 2;
        }
       return 0;
    }
}
