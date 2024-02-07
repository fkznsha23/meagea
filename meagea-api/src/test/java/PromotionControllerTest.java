import controller.PromotionController;
import dto.PromotionForm;
import dto.SimplePromotionDto;
import entity.Promotion;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = PromotionController.class)
public class PromotionControllerTest {

    private final String URI = "http://localhost:" + 8080 + "/";
    TestRestTemplate testRestTemplate;
    PromotionController controller = new PromotionController();
    @Test
    public void writePromotionTest() throws IOException {
        String url = URI + "meagea/promotion/";

        List<MultipartFile> multiList = new ArrayList<>();
        PromotionForm form = new PromotionForm("제목", "머핀", multiList, 4, 3.5, true,
                                                "고양이", "삼색이", "인근 슈퍼 앞", 5,
                                                5, 3, 5,
                                                "귀엽습니다.", "집을 많이 비우시는 분은 안됩니다.");
        ResponseEntity<Promotion> responseEntity = testRestTemplate.postForEntity(url, form, Promotion.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Promotion pro = responseEntity.getBody();
        Assertions.assertThat(pro.getTitle()).isEqualTo("제목");

    }

    @Test
    public void getPromotionTest(){
        String url = URI + "meagea/promotion/" + 10;
        Promotion pro = testRestTemplate.getForObject(url, Promotion.class);
        assertThat(pro.getNo()).isEqualTo(10);
    }

    @Test
    public void getAllPromotionTitleTest(){
        String url = URI + "meagea/all-promotion-title";
        ResponseEntity<List<SimplePromotionDto>> responseEntity =
                testRestTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<SimplePromotionDto>>() {});
        List<SimplePromotionDto> dtoList = responseEntity.getBody();
        for(int i = 0; i < dtoList.size(); i++) {
            assertThat(dtoList.get(i).getNo()).isEqualTo(i);
        }

    }
}