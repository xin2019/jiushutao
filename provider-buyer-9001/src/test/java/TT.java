import com.xin.jiushutao.pojo.T_BUYER;
import com.xin.jiushutao.service.PersonalService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: jiushutao
 * @Author 陈欣
 * @description
 * @Date 2021/2/25 13:28
 * @Version 1.0
 **/
@SpringBootTest
public class TT {
    @Autowired
    private PersonalService personalService;
    @Test
    public void tt(){
//        T_BUYER t_buyer=new T_BUYER();
//        t_buyer.setT_buyer_id("1");
//        t_buyer.setT_buyer_email("kkk@qq.com");
//        t_buyer.setT_buyer_phone("kk");
//        t_buyer.setT_buyer_address("gaoxin");
//        t_buyer.setT_buyer_name("xin");
//        System.out.println(t_buyer);
//        boolean b = personalService.updateT_Buyer(t_buyer);
//        System.out.println(b);
        System.out.println(personalService.getOrderAndBuyerByorderId("1"));
    }
}
