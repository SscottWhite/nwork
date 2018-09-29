package com.example.demo.otherstool;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class ZplCnUtil {

	public static BufferedImage source = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
	public static Graphics2D gs = source.createGraphics();

	// public static String getFontZpl(String content, int x, int y, int size,
	// String fontName) {
	// return String.format("^FO%d,%d^A1N,%d,%d^FD%s^FS", x, y, size, size,
	// content);
	// }

	public static String getFontHexWithWidth(String content, int x, int y, int width, int maxHeight, String fontName) {
		if (content == null || "".equals(content))
			return "";
		Font f = null;
		width = (width + 7) / 8 * 8;
		int size = width / content.length();
		int retryFlag = 1;
		if (size > maxHeight) {
			size = maxHeight;
			if ("宋体".equals(fontName)) {
				f = new Font("simsun", Font.PLAIN, size);
			} else if ("黑体".equals(fontName)) {
				f = new Font("simhei", Font.BOLD, size);
			} else {
				f = new Font("simsun", Font.PLAIN, size);
			}
		} else {
			while (true) {
				if ("宋体".equals(fontName)) {
					f = new Font("simsun", Font.PLAIN, size);
				} else if ("黑体".equals(fontName)) {
					f = new Font("simhei", Font.BOLD, size);
				} else {
					f = new Font("simsun", Font.PLAIN, size);
				}
				gs.setFont(f);
				FontMetrics fontMetrics = gs.getFontMetrics();
				Rectangle2D stringBounds = fontMetrics.getStringBounds(content, gs);
				int nw = (int) stringBounds.getWidth();

				if (nw > width) {
					size--;
					if (retryFlag == 1) {
						break;
					}
					retryFlag = 0;

				} else {
					if (size >= maxHeight)
						break;
					size++;
					retryFlag = 1;
				}
			}
		}
		int height = size;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		g2.setFont(f);
		g2.setColor(Color.BLACK);
		g2.drawString(content, 1, (int) (height * 0.88));

		g2.dispose();

		StringBuilder zpl = new StringBuilder("^FO").append(x).append(",").append(y).append(getImage(image))
				.append("^FS");

		return zpl.toString();

	}

	public static String getFontHex(String content, int x, int y, int size, String fontName) {
		String zplStr = getFontHex(content,size,fontName);
		StringBuilder zpl = new StringBuilder("^FO")
				.append(x).append(",")
				.append(y).append(zplStr)
				.append("^FS");
		return zpl.toString();
	}
	
	public static String getFontHex(String content, int size, String fontName) {
		if (content == null || "".equals(content))
			return "";
		Font f = null;
		if ("宋体".equals(fontName)) {
			f = new Font("simsun", Font.PLAIN, size);
		} else if ("宋体BOLD".equals(fontName)) {
			f = new Font("simsun", Font.BOLD, size);
		} else if ("黑体".equals(fontName)) {
			f = new Font("simhei", Font.BOLD, size);
		} else if (fontName.contains("msyh-")){
			f = new Font(fontName.substring(fontName.indexOf("-")+1,fontName.length()), Font.PLAIN, size);
		} else {
			f = new Font("simsun", Font.PLAIN, size);
		}
		gs.setFont(f);
		FontMetrics fontMetrics = gs.getFontMetrics();
		Rectangle2D stringBounds = fontMetrics.getStringBounds(content, gs);
		int width = (int) stringBounds.getWidth();
		int height = size;
		width = (width + 7) / 8 * 8;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		g2.setFont(f);
		g2.setColor(Color.BLACK);
		g2.drawString(content, 1, (int) (height * 0.88));
		g2.dispose();
		StringBuilder zpl = new StringBuilder(getImage(image));
		return zpl.toString();
	}
	

	private static final char[] HEX = "0123456789ABCDEF".toCharArray();

	public static String printImage(BufferedImage image, int x, int y) {
		if (image.getWidth() % 8 != 0) {
			BufferedImage i = new BufferedImage(((image.getWidth() + 7) / 8) * 8, image.getHeight(),
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = i.createGraphics();
			g2.drawImage(image, null, 0, 0);
			g2.dispose();
			image = i;
		}
		StringBuilder zpl = new StringBuilder("^FO").append(x).append(",").append(y).append(getImage(image))
				.append("^FS");
		return zpl.toString();
	}

	public static String getImage(BufferedImage i) {
		int w = i.getWidth();
		int h = i.getHeight();
		boolean black[] = getBlackPixels(i, w, h);
		int hex[] = getHexValues(black);

		String data = ints2Hex(hex);

		int bytes = data.length() / 2;
		int perRow = bytes / h;

		return "^GFA," + bytes + "," + bytes + "," + perRow + "," + data;

	}

	@SuppressWarnings("unused")
	private static String flipRows(String hex, int height) {
		String flipped = "";
		int width = hex.length() / height;

		for (int i = 0; i < height; i++) {
			flipped += new StringBuilder(hex.substring(i * width, (i + 1) * width)).reverse().toString();
		}
		return flipped;
	}

	/**
	 * Returns an array of ones or zeros. boolean is used instead of int for memory
	 * considerations.
	 * 
	 * @param o
	 * @return
	 */
	public static boolean[] getBlackPixels(BufferedImage bi, int w, int h) {
		int[] rgbPixels = ((DataBufferInt) bi.getRaster().getDataBuffer()).getData();
		int i = 0;
		boolean[] pixels = new boolean[rgbPixels.length];
		for (int rgbpixel : rgbPixels) {
			pixels[i++] = isBlack(rgbpixel);
		}

		return pixels;
	}

	public static boolean isBlack(int rgbPixel) {
		int a = (rgbPixel & 0xFF000000) >>> 24;
		if (a < 127) {
			return false; // assume pixels that are less opaque than the luma threshold should be
							// considered to be white
		}
		int r = (rgbPixel & 0xFF0000) >>> 16;
		int g = (rgbPixel & 0xFF00) >>> 8;
		int b = rgbPixel & 0xFF;
		int luma = ((r * 299) + (g * 587) + (b * 114)) / 1000; // luma formula
		return luma < 127;
	}

	public static int[] getHexValues(boolean[] black) {
		int[] hex = new int[(int) (black.length / 8)];
		// Convert every eight zero's to a full byte, in decimal
		for (int i = 0; i < hex.length; i++) {
			for (int k = 0; k < 8; k++) {
				hex[i] += (black[8 * i + k] ? 1 : 0) << 7 - k;
			}
		}
		return hex;
	}

	public static String getHexString(byte[] b) throws Exception {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	public static String ints2Hex(int[] ints) {
		char[] hexChars = new char[ints.length * 2];
		for (int i = 0; i < ints.length; ++i) {
			hexChars[i * 2] = HEX[(ints[i] & 0xF0) >> 4];
			hexChars[i * 2 + 1] = HEX[ints[i] & 0x0F];
		}
		return new String(hexChars);
	}

	public static void main(String[] args) {
		String china = getFontHex("你好， 瀍河，赭山公，（洸河街道123ABC@", 40, 50, 30, "黑体");
		System.out.println(china);
//	        try{
//	            Socket socket = new Socket("172.16.17.241", 9100);
//	            if (socket.isConnected() ) {
//	                //获取服务端的输入流,这里可用可不用，主要看产品
//	                InputStream inputStream = socket.getInputStream();
//	                //获取服务端的输出流，这个就一定要取到，因为这个关系到能不能向服务端发送出消息的操作
//	                OutputStream os = socket.getOutputStream();
//	                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
//	                //向服务器端发送一条消息，在打印机里面，\n的动作是必要的，如果没有\n的动作，打印机是不会打印出任何东西的
//	                //bw.write("^XA^FO100,75^BY3^B3N,N,100,Y,N^FD123ABC^XZ");
//	                bw.write("^XA"+china+"^XZ");
//	                bw.flush();
//	                //读取服务器返回的消息
//	                System.out.println("服务器：命令发送完成");
//	            } else {
//	                System.out.println( "client 连接失败");
//	            }
//	        }catch (Exception ex){
//	            System.out.println(ex.fillInStackTrace());
//	        }
//
	}
}
