
import controller.PromotionController;
import dto.PromotionForm;
import dto.SimplePromotionDto;
import entity.Promotion;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = PromotionController.class)
public class PromotionControllerTest {
    PromotionController controller = new PromotionController();
    @Test
    public void writePromotionTest() throws IOException {
        PromotionForm form = new PromotionForm("쫑이를 소개합니다.", "쫑이", new ArrayList<>(), 5,
                                                3.5, true, "고양이", "삼색이", "인근",
                                                5, 5, 5, 5, "설명",
                                                "입양조건");
        Promotion pro = controller.writePromotion(form);
    }

    @Test
    public void getPromotionTest(){
        Promotion pro = controller.getPromotion(10);
        assertThat(pro.getNo()).isEqualTo(10);
    }

    @Test
    public void getAllPromotionTest(){
        List<SimplePromotionDto> list = controller.getAllPromotion();
        SimplePromotionDto simple = list.get(1);
    }
}