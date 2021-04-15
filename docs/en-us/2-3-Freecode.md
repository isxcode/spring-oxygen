### Start Freecode

```
package com.isxcode.demo.oxygen;

import com.isxcode.oxygen.freecode.annotation.EnableFreecode;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFreecode
public class AppConfig {
    
}
```

### Config freecode

```yaml
spring:
  freemarker:
    enabled: true                                        # 开启freemarker

server:
  tomcat:
    uri-encoding: UTF-8                                  # 如果报错 Invalid character found in method name

oxygen:
  freecode:
    author: ispong
    version: 0.0.1
    table-prefix: leo_
    module-path: com.isxcode.leoday.module
```

### Generate Code

```http
GET http://localhost:8080/freecode/generate?tableName=leo_dogs
```

### File Construct

```text
📂 com
    📂 isxcode
        📂 leoday
            📂 module
                📂 dogs
                    📄 LeoDogsController
                    📄 LeoDogsEntity
                    📄 LeoDogsRepository
                    📄 LeoDogsService
```
