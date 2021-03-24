### Excel utils

1. create dog entity

```
package com.isxcode.demo.oxygen;

import com.isxcode.oxygen.core.excel.ExcelType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dog {

    @ExcelType(cellName = "狗狗名称")
    private String name;

    @ExcelType(cellName = "狗狗年龄")
    private Integer age;

    @ExcelType(cellName = "狗狗爱好")
    private String joy;
}

```


2. 编写接口

```
package com.isxcode.demo.oxygen;

import com.isxcode.oxygen.core.excel.ExcelUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @GetMapping("/write")
    public void writeExcel(HttpServletResponse response) throws UnsupportedEncodingException {

        List<Dog> dogList = Arrays.asList(
                new Dog("Tom", 12, "swim"),
                new Dog("Gar", 16, "eat"));

        String fileName = "狗狗Excel";
        ExcelUtils.generate(dogList, fileName, response);
    }

    @GetMapping("/read")
    public void readExcel(@RequestParam("excel") MultipartFile excel) throws IOException {

        List<Dog> dogs = ExcelUtils.parse(excel.getInputStream(), Dog.class);
        System.out.println(dogs);
    }

}
```