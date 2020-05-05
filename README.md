# PicEncrypt
安卓App goldsudo/PicEncryptApp 的 java 移植版

## Motivation
群里发色图，用 PicEncryptApp 加密，结果本人没有安卓手机，看不了，怎么办，不看了吗？
No！
自己写一个不就是了！

（把 App 核心算法非常简单地封装了一下）

## 使用方法
下载 bin/picencrypt.jar

加密：
``` shell
java -jar picencrypt.jar -e -k 0.1 -i test.png -o test-out.png
```

解密：
``` shell
java -jar picencrypt.jar -d -k 0.1 -i test.png -o test-out.png
```

## 命令行参数
* -e: 加密
* -d: 解密
* -k: 后面跟密钥
* -i: 后面跟输入图片路径
* -o: 后面跟输出图片路径
* -h: 打印帮助然后退出

## 致谢

https://github.com/goldsudo/PicEncryptApp
