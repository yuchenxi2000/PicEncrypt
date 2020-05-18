package ycx.picencrypt;

import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Main {
	
	static void image_transform(double key, File fi, File fo, Boolean encrypt, String mode) {
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
				switch (mode) {
				case "rc":
					ma.encrypt(buffer_2d, key, h, w);
					break;
				case "r":
					ma.rowEncrypt_interface(buffer_2d, key, h, w);
					break;
				case "c":
					ma.columnEncrypt_interface(buffer_2d, key, h, w);
					break;
				default:
					System.out.println("未知的模式，可能的模式：r/c/rc");
					return;
				}
			} else {
				switch (mode) {
				case "rc":
					ma.decrypt(buffer_2d, key, h, w);
					break;
				case "r":
					ma.rowDecrypt_interface(buffer_2d, key, h, w);
					break;
				case "c":
					ma.columnDecrypt_interface(buffer_2d, key, h, w);
					break;
				default:
					System.out.println("未知的模式，可能的模式：r/c/rc");
					return;
				}
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
		double key = 0.1;
		File fi = null, fo = null;
		String mode = "rc";
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
			case "-m":
				if (++i < args.length) {
					mode = args[i];
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
				System.out.println("-m: 加密/解密模式，r/c/rc（行/列/行列），默认行列");
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
		image_transform(key, fi, fo, encrypt, mode);
		return;
	}

}
