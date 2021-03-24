## Welcome to contribute Spring Oxygen

Hello! Thank you for taking the time to contribute! If you want to join us, please send email to **ispong@outlook.com**.

#### [How to Contribute to an Open Source Project on GitHub](https://egghead.io/series/how-to-contribute-to-an-open-source-project-on-github)

[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

### Installation Prerequisites

- Git 2.22+
- Java 11+
- Gradle 6.5+

### Contribute (developer : _ispong_)

1. Fork Project (https://github.com/isxcode/spring-oxygen)

your project - https://github.com/ispong/spring-oxygen

2. Clone Project (branch: **latest**)

```
git clone -b latest https://github.com/ispong/spring-oxygen.git
```

3. Build Project and Install Local and Use

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

4. Git Branchs (**Optional**)

 Branch             | Desc
 ---                | ---
 feature-project    | change project base file like (README.md/SECURITY.md e.g)
 feature-starter    | change spring-oxygen starter config
 feature-core       | change core utils in moudle spring-oxygen-core
 feature-flysql     | flysql moudle
 feature-freecode   | freecode moudle

5. Merge conflict (branch: **latest**)

```
git remote add upstream https://github.com/isxcode/spring-oxygen.git
git fetch upstream
git merge upstream/latest
git push origin latest
```

6. Pull Request

- https://github.com/isxcode/spring-oxygen/compare

> Note:  ispong/spring-oxygen/latest  **==squash merge==>** isxcode/spring-oxygen/latest

7. Merge Rule (**Optional**)
 
 Branch flow                                                                  | Operate
 ----                                                                         | ---
 ispong/spring-oxygen/feature-core --> isxcode/spring-oxygen/feature-core     | squash merge
 isxcode/spring-oxygen/feature-core --> ispong/spring-oxygen/feature-core     | merge commits 
 isxcode/spring-oxygen/feature-core --> isxcode/spring-oxygen/release-0.0.x   | merge commits
 isxcode/spring-oxygen/release-0.0.x --> isxcode/spring-oxygen/feature-flysql | squash merge
 isxcode/spring-oxygen/release-0.0.x --> isxcode/spring-oxygen/main           | rebase merge
 isxcode/spring-oxygen/hotfix-0.0.x --> isxcode/spring-oxygen/release-0.0.x   | merge commits

8. Docs Contribute 

```
cd spring-oxygen
docsify serve docs
```

- [local docs](http://localhost:3000)
