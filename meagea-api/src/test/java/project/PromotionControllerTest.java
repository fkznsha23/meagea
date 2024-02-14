package project;

import entity.Promotion;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import project.dto.PromotionForm;
import project.dto.SimplePromotionDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PromotionControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Test
    public void writePromotionTest() throws IOException {
        String url = "/meagea/promotion";
        List<MultipartFile> multiList = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            String name = "file" + i;
            String type = "jpg";
            String path = "src\\main\\java\\project\\image\\" + name + "." + type;
            // path 경로에 있는 name.type 파일을 File 객체로 생성
            File file = new File(path);
            // 해당 파일을 바이트 단위로 읽어온다
            FileSystemResource resource = new FileSystemResource(file);
            FileInputStream input = new FileInputStream(file);
            byte[] byteArr = input.readAllBytes();
            // 파일 객체를 멀티파일 객체로 변환
            MockMultipartFile mock = new MockMultipartFile(name, name + "." + type, type, byteArr);
            multiList.add(mock);
            input.close();
        }

        PromotionForm form = new PromotionForm("제목", "머핀", multiList, 4, 3.5, true,
                                                "고양이", "삼색이", "인근 슈퍼 앞", 5,
                                                5, 3, 5,
                                                "귀엽습니다.", "집을 많이 비우시는 분은 안됩니다.");
        ResponseEntity<Promotion> responseEntity = testRestTemplate.postForEntity(url, form, Promotion.class);
        Promotion pro = responseEntity.getBody();
        Assertions.assertThat(pro.getTitle()).isEqualTo("제목");
    }

    @Test
    public void getPromotionTest(){
        String url = "/meagea/promotion/" + 10;
        Promotion pro = testRestTemplate.getForObject(url, Promotion.class);
        assertThat(pro.getNo()).isEqualTo(10);
    }

    @Test
    public void getAllPromotionTitleTest(){
        String url = "/meagea/all-promotion-title";
        ResponseEntity<List<SimplePromotionDto>> responseEntity =
                testRestTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<SimplePromotionDto>>() {});
        List<SimplePromotionDto> dtoList = responseEntity.getBody();
        for(int i = 0; i < dtoList.size(); i++) {
            assertThat(dtoList.get(i).getNo()).isEqualTo(i);
        }
    }
}