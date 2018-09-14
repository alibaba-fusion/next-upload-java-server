package design.fusion.javauploadserver;

import design.fusion.dto.RetDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@SpringBootApplication
public class JavaUploadServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaUploadServerApplication.class, args);
	}

    @RequestMapping("/")
	public String index() {
	    return "index";
    }

    @ResponseBody
    @RequestMapping(path = "file", method = RequestMethod.POST)
    public RetDto file(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            RetDto re = new RetDto(false, "file empty", null);
            return re;
        }
        try {
            // 获取文件路径
            // Get FilePath
            String workingDir = System.getProperty("user.dir");
            Path path = Paths.get(workingDir + "/files/" + file.getOriginalFilename());

            byte[] bytes = file.getBytes();
            Files.write(path, bytes);

            RetDto re = new RetDto(true, "upload success", "https://dummyimage.com/600x300/#fff&text=Upload%20%E6%88%90%E5%8A%9F");
            return re;
        } catch (IOException e) {
            e.printStackTrace();
            RetDto re = new RetDto(false, "upload fail", null);
            return re;
        }
    }
}