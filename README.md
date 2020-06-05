# PicEncrypt
安卓App goldsudo/PicEncryptApp 的 java 移植版

## Motivation
群里发色图，用 PicEncryptApp 加密，结果本人没有安卓手机，看不了，怎么办，不看了吗？

No！

自己写一个不就是了！

（把 App 核心算法非常简单地封装了一下）

（不得不说，原安卓App代码还有些问题，HashMap 类型错误，莫名其妙的String、int来回转换，。。。直接修了）

## 使用方法
下载 bin/picencrypt.jar

加密（默认行加密，密钥0.1）：
``` shell
java -jar picencrypt.jar -e -i test.png -o test_e.png
```

加密，指定密钥0.2，使用行列加密：
``` shell
java -jar picencrypt.jar -e -k 0.2 -m rc -i test.png -o test_e.png
```

解密（默认解密而且是行解密，密钥0.1）：
``` shell
java -jar picencrypt.jar -i test_e.png -o test_d.png
```

解密，加密方式行列，指定密钥0.3：
``` shell
java -jar picencrypt.jar -d -k 0.3 -i test_e.png -o test_d.png -m rc
```

## 命令行参数
* -e: 加密
* -d: 解密（默认）
* -k: 后面跟密钥，默认0.1
* -i: 后面跟输入图片路径
* -o: 后面跟输出图片路径
* -m: 后面跟加密/解密模式，r/rc（行/行列），默认行
* -h: 打印帮助然后退出

## 原安卓App

https://github.com/goldsudo/PicEncryptApp
