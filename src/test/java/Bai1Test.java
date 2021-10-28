import Entities.SinhVien;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Bai1Test {

    @Test
    public void checkLamTron(){
        SinhVien sv = new SinhVien("123","long","hihi@gmail.com",6,5,3,5);
        Assertions.assertEquals(0,sv.getDiemTongKet()%0.5);
    }

    @Test
    public void checkGmail(){
        SinhVien sv = new SinhVien("123","long","hihi@gmail.com",6,5,3,5);
        Assertions.assertEquals(1,sv.checkMail());
    }

    @Test
    public void checkDat(){
        SinhVien sv = new SinhVien("123","long","hihi@gmail.com",10,10,10,10);
        Assertions.assertEquals( 1,sv.dat());
    }
}
