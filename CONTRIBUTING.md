## Welcome to contribute Spring Oxygen

Hello! Thank you for taking the time to contribute! If you want to join us, please send email to **ispong@outlook.com**.

#### [How to Contribute to an Open Source Project on GitHub](https://app.egghead.io/playlists/how-to-contribute-to-an-open-source-project-on-github)

[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

---

### Installation Prerequisites

- Git 2.22+
- Java 11+
- Gradle 6.5+

### Contribute (Example developer:**ispong** )

1. Fork Project (_https://github.com/isxcode/spring-oxygen_)

2. Clone Project (Branch: **latest**)

```bash
git clone -b latest https://github.com/ispong/spring-oxygen.git
```

3. Build Project

```bash
cd spring-oxygen
gradle publishToMavenLocal
```

4. Install Local

- Open local spring project and Change file `build.gradle` 

```groovy
repositories {
    // ...    
    mavenLocal()
}

dependencies {
    // ...
    compile group: 'com.isxcode.oxygen', name: 'oxygen-spring-boot-starter', version: '0.0.1', changing: true
}
```

5. Merge conflict (Branch: **latest**)

```bash
git remote add upstream https://github.com/isxcode/spring-oxygen.git
git fetch upstream
git merge upstream/latest
git add .
git commit -m ":sparkles: add new features"
git push origin latest
```

6. Pull Request

- https://github.com/isxcode/spring-oxygen/compare

> Note:  ispong/spring-oxygen/latest  **==squash merge==>** isxcode/spring-oxygen/latest

7. Build Docs

- http://localhost:3000

```bash
npm i docsify-cli -g
cd spring-oxygen
docsify serve docs
```

8. Publish to maven center

- https://oss.sonatype.org/

> change `gradle.properties` file and set version number and password

```
project.version=xxx

signing.keyId=57CA7F60
signing.password=spring-oxygen
signing.secretKeyRingFile=../.github/secring.gpg

sonatypeUsername=isxcode
sonatypePassword=xxx

gpr.user=isxcode
#gpr.key=xxx

###
```

```bash
gradle publishMavenJavaPublicationToSonatypeRepository
```
