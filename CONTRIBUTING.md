## Welcome to contribute Spring Oxygen

Hello! Thank you for taking the time to contribute! If you want to join us, please send email to **ispong@outlook.com**.

#### [How to Contribute to an Open Source Project on GitHub](https://egghead.io/series/how-to-contribute-to-an-open-source-project-on-github)

[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

### Installation Prerequisites

- Git 2.22+
- Java 11+
- Gradle 6.5+

### Contribute (developer : _xiao_)

1. Fork Project (https://github.com/xiao/spring-oxygen)

2. Clone Project

```
git clone https://github.com/xiao/spring-oxygen.git
```

3. Build Project and Use

```
cd spring-oxygen
gradle publishToMavenLocal
```

- Open other spring project and Change file `build.gradle`

```groovy
repositories {
    mavenLocal()
}

dependencies {
    compile group: 'com.isxcode.oxygen', name: 'oxygen-spring-boot-starter', version: '0.0.1', changing: true
}
```

4. Git Branchs

 Branch             | Desc
 ---                | ---
 feature-project    | change project base file like (README.md/SECURITY.md e.g)
 feature-starter    | change spring-oxygen starter config
 feature-core       | change core utils in moudle spring-oxygen-core
 feature-flysql     | flysql moudle
 feature-freecode   | freecode moudle

5. Merge conflict

```
git checkout -b feature-core origin/feature-core
git remote add upstream https://github.com/isxcode/spring-oxygen.git
git fetch upstream
git merge upstream/feature-core
git push origin feature-core
```

6. Pull Request

- https://github.com/isxcode/spring-oxygen/compare

> Note:  xiao/spring-oxygen/feature-core  --> isxcode/spring-oxygen/feature-core