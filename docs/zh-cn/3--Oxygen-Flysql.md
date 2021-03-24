### ✅ [oxygen-flysql]()

- Integrate spring jdbc rapid development

- Example

```java
import com.ispong.oxygen.flysql.pojo.enums.OrderType;
import org.springframework.stereotype.Repository;
import com.ispong.oxygen.flysql.core.Flysql;

import java.util.List;

@Repository
public class LeoDogsRepository {

    public List<LeoDogsEntity> customQuery() {

        return Flysql.select(LeoDogsEntity.class)
            .select("name", "age", "color")
            .eq("name", "alen")
            .between("age", 12, 20)
            .like("color", "red")
            .orderBy("userIndex", OrderType.DESC)
            .query();
    }
}
```

### 增

```
@GetMapping("/saveDogs")
    public List<DogsEntity> saveDogs() {

        DogsEntity dogsEntity = DogsEntity.builder().age(12).name("li").build();
        Flysql.insert(DogsEntity.class).save(dogsEntity);
        return Flysql.select(DogsEntity.class).query();
    }
```

### 删

```
    @GetMapping("/deleteDogs")
    public List<DogsEntity> deleteDogs() {

        Flysql.delete(DogsEntity.class)
                .eq("name", "li")
                .doDelete();
        return Flysql.select(DogsEntity.class).query();
    }
```

### 改

```
   @GetMapping("/updateDogs")
    public List<DogsEntity> updateDogs() {

        Flysql.update(DogsEntity.class)
                .eq("name", "li")
                .update("age", 100)
                .doUpdate();
        return Flysql.select(DogsEntity.class).query();
    }
```

### 查

```
package com.isxcode.demo.oxygen.dogs;

import com.isxcode.oxygen.flysql.core.Flysql;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller - 
 *
 * @author ispong
 * @since v0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/dogs")
public class DogsController {

    /**
     * query DogsEntity
     *
     * @return String
     */
    @GetMapping("/queryDogs")
    public List<DogsEntity> queryDogs() {
        
        return Flysql.select(DogsEntity.class).query();
    }

}
```
