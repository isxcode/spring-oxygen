<p align="center">
    <img alt="spring-oxygen" width="500" src="https://gitee.com/isxcode/blogs-galaxy-images/raw/master/oxygen/oxygen.png">
</p>

<h1 align="center">
    Spring Oxygen
</h1>

<h5 align="center">
    🦄 Spring rapid development integration framework. 
</h5>


### Why Spring Oxygen?



### Features

🚀 Node & Browser Support
⚡️️ Simple, Powerful, & Intuitive API
💎 First Class Mocha & QUnit Test Helpers
🔥 Intercept, Pass-Through, and Attach Events
📼 Record to Disk or Local Storage
⏱ Slow Down or Speed Up Time

### Getting Started

Check out the Quick Start documentation to get started.

### Usage

```java
public class demo{
    @SneakyThrows
    public void executeFlink(String workId) {
        
        String genFlinkPath = "/data/dehoop-scripts/genFlinkFile.py";

        String[] runArguments = new String[]{"python3", genFlinkPath, workId};
        Process runProcess = Runtime.getRuntime().exec(runArguments);
        BufferedReader in = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println("flink==>" + line);
        }
        in.close();
        runProcess.waitFor();
    }
}

```
