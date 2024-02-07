import controller.PromotionController;
import dto.PromotionForm;
import dto.SimplePromotionDto;
import entity.Promotion;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = PromotionController.class)
public class PromotionControllerTest {

    private final String URI = "http://localhost:" + 8080;
    TestRestTemplate testRestTemplate = new TestRestTemplate();
    PromotionController controller = new PromotionController();
    @Test
    public void writePromotionTest() throws IOException {
        String url = URI + "meagea/promotion/";
        PromotionForm form = new PromotionForm("쫑이를 소개합니다.", "쫑이", new ArrayList<>(), 5,
                                                3.5, true, "고양이", "삼색이", "인근",
                                                5, 5, 5, 5, "설명",
                                                "입양조건");
        ResponseEntity<Promotion> responseEntity = testRestTemplate.postForEntity(url, form, Promotion.class);
    }

    @Test
    public void getPromotionTest(){
        String url = URI + "meagea/promotion/" + 10;
        ResponseEntity<Promotion> responseEntity = testRestTemplate.getForEntity(url, Promotion.class);
        Promotion pro = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(pro.getNo()).isEqualTo(10);
    }

    @Test
    public void getAllPromotionTitleTest(){
        String url = URI + "meagea/all-promotion-title";
        ResponseEntity<SimplePromotionDto> responseEntity = testRestTemplate.getForEntity(url, SimplePromotionDto.class);
        SimplePromotionDto dto = responseEntity.getBody();
    }
}