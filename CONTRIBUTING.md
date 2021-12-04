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

[https://github.com/isxcode/spring-oxygen/compare](https://github.com/isxcode/spring-oxygen/compare)

> Note:  ispong/spring-oxygen/latest  **==squash merge==>** isxcode/spring-oxygen/latest

### Build Docs

```bash
npm i docsify-cli -g
cd spring-oxygen
docsify serve docs
```
[http://localhost:3000](http://localhost:3000)

### Publish

#### publish SNAPSHOT to Sonatype repository

```bash
vim VERSION.md

# -------------- VERSION.md --------------  
1.0.0-SNAPSHOT
# -------------- VERSION.md -------------- 
```

```bash
vim gradle.properties

# ------------- gradle.properties -------------  
sonatypePassword=xxx
# ------------- gradle.properties -------------
```

```bash
gradle publishMavenJavaPublicationToSonatypeRepository
```

#### publish RELEASE to Sonatype repository

```bash
vim VERSION.md

# -------------- VERSION.md --------------  
1.0.0
# -------------- VERSION.md -------------- 
```

```bash
vim gradle.properties

# ------------- gradle.properties -------------  
sonatypePassword=xxx
# ------------- gradle.properties -------------
```

```bash
gradle publishMavenJavaPublicationToSonatypeRepository
```

[https://oss.sonatype.org/](https://oss.sonatype.org/)

#### publish RELEASE to GitHub repository

[https://github.com/isxcode/spring-oxygen/actions/workflows/release-github.yml](https://github.com/isxcode/spring-oxygen/actions/workflows/release-github.yml)

### Release new version

- [ ] Fix Pull Requests
- [ ] Edit version number(0.11.5-SNAPSHOT)
- [ ] Edit SECURITY.md version number
- [ ] Edit CHANGELOG.md version number (## v0.11.5)
- [ ] Copy README.md to /docs/_homepage.md
- [ ] Submit and Fix GitHub Security
- [ ] Merge to isxcode/spring-oxygen/latest
- [ ] Check project badge status
- [ ] Create GitHub new tag (v0.8.1) and new branch (0.8.1)
- [ ] Run GitHub action publish to GitHub repository
- [ ] Run GitHub action publish new version docs
- [ ] Publish to Sonatype repository
