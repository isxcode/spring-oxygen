## Welcome to contribute Spring Oxygen

Hello! Thank you for taking the time to contribute! If you want to join us, please send email to **ispong@outlook.com**.

#### [How to Contribute to an Open Source Project on GitHub](https://egghead.io/series/how-to-contribute-to-an-open-source-project-on-github)

[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

### Installation Prerequisites

- Git 2.22.+
- Java 1.8.+
- Gradle 6.5.+

### Build and Package

- for Gradle

```
git clone -b latest https://github.com/ispong/spring-oxygen
cd spring-oxygen
gradle publishToMavenLocal
```

- for Maven
```
git clone -b latest https://github.com/ispong/spring-oxygen
cd spring-oxygen
mvn install
```

### Add git signed commits

```
gpg --gen-key
git config commit.gpgsign true
gpg --list-secret-keys
gpg --armor --export F2307DAE
```