# 解压 python

# 安装 pip 会自动安装 setuptools 和 wheel

使用 git

```shell script
curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py
python get-pip.py
```

修改文件 python38_pth 文件

```text
python38.zip
.
D:\developer\JetBrains\apps\PyCharm-P\ch-0\193.6494.30\plugins\python\helpers\pydev

# Uncomment to run site.main() automatically
import site
```

# 升级 pip

```shell script
python -m pip install --upgrade pip
```

# 切换阿里镜像

在自己的文件夹下 创建文件夹 pip
创建文件 %APPDATA%\pip\pip.ini

echo %APPDATA%
C:\Users\ispon\AppData\Roaming

touch %APPDATA%\pip\pip.ini

```text
[global]
index-url = https://mirrors.aliyun.com/pypi/simple/

[install]
trusted-host=mirrors.aliyun.com
```
