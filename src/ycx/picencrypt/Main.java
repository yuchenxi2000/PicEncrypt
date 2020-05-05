package ycx.picencrypt;

import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Main {
	
	static void image_transform(double key, File fi, File fo, Boolean encrypt) {
		try {
			BufferedImage bi = ImageIO.read(fi);
			int h = bi.getHeight();
			int w = bi.getWidth();
			int[] buffer = new int[w*h];
			
			bi.getRGB(0, 0, w, h, buffer, 0, w);
			
			// 一维转二维
			int[][] buffer_2d = new int[h][w];
			ArrayFunctions af = new ArrayFunctions();
			af.change(buffer, buffer_2d, h, w);
			
			// 解密
			MyAlgorithms ma = new MyAlgorithms();
			if (encrypt) {
				ma.encrypt(buffer_2d, key, h, w);
			} else {
				ma.decrypt(buffer_2d, key, h, w);
			}
			
			// 二维转一维
			af.recovery(buffer_2d, buffer, h, w);
			
			bi.setRGB(0, 0, w, h, buffer, 0, w);
			
			ImageIO.write(bi, "png", fo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Boolean encrypt = true;
		double key = 0.0;
		File fi = null, fo = null;
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
			case "-d":
				encrypt = false;
				break;
			case "-e":
				encrypt = true;
				break;
			case "-k":
				if (++i < args.length) {
					key = Double.parseDouble(args[i]);
				}
				break;
			case "-i":
				if (++i < args.length) {
					fi = new File(args[i]);
				}
				break;
			case "-o":
				if (++i < args.length) {
					fo = new File(args[i]);
				}
				break;
			case "-h":
				System.out.println("安卓App picencript 的 java 移植版");
				System.out.println("命令行参数:");
				System.out.println("-e: 加密");
				System.out.println("-d: 解密");
				System.out.println("-k: 后面跟密钥");
				System.out.println("-i: 后面跟输入图片路径");
				System.out.println("-o: 后面跟输出图片路径");
				System.out.println("-h: 打印帮助然后退出");
				return;
			}
		}
		if (fi == null) {
			System.out.println("输入图片路径为空");
			return;
		}
		if (fo == null) {
			System.out.println("输出图片路径为空");
			return;
		}
		image_transform(key, fi, fo, encrypt);
		return;
	}

}
