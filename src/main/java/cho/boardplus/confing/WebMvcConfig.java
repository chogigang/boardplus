package cho.boardplus.confing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Value("${uploadPath}")
//    String uploadPath;
//
//    @Override
//    public void  addResourceHandlers(ResourceHandlerRegistry registry){
//        registry.addResourceHandler("/images/**")
//                .addResourceLocations(uploadPath);
//    }



    private String savePath = "file:///C:/board/"; // 실제 파일 저장 경로(win)
    private String resourcePath = "/upload/**"; //view 에서 접근할 경로

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }
}
