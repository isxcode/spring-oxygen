### ✅ [oxygen-freecode]()

- Fast generate java code

- Example

```yaml
oxygen:
  freecode:
    author: ispong
    version: 0.0.1
    table-prefix: leo_
    module-path: com.isxcode.leoday.module
```

```http request
GET http://localhost:8080/freecode/generate?tableName=leo_dogs
```

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