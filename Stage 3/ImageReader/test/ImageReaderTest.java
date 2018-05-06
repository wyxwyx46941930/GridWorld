
import imagereader.IImageIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.Graphics;

import javax.imageio.ImageIO;

import org.junit.Test;
import static org.junit.Assert.*;

public class ImageReaderTest {
	@Test
	public void testShowChanelR() throws IOException {
		MyImageIO mir = new MyImageIO();
		Image img = mir.myRead("/home/administrator/Desktop/ImageReader/bmptest/1.bmp");
		MyImageProcessor mip = new MyImageProcessor();
		Image redimg = mip.showChanelR(img);
		mir.myWrite(redimg, "/home/administrator/Desktop/ImageReader/result/1_red.bmp");

		Image result = mir.myRead("/home/administrator/Desktop/ImageReader/result/1_red.bmp");
		Image goal = ImageIO.read(new FileInputStream("/home/administrator/Desktop/ImageReader/bmptest/goal/1_red_goal.bmp"));

        assertEquals("The width of result and the goal is different ", result.getWidth(null), goal.getWidth(null));
        assertEquals("The height of result and the goal is different ", result.getHeight(null), goal.getHeight(null));
        
        BufferedImage bufferedResult = new BufferedImage(result.getWidth(null), result.getHeight(null),
                                                             BufferedImage.TYPE_INT_RGB);
        BufferedImage bufferedGoal = new BufferedImage(goal.getWidth(null), goal.getHeight(null),
                                                             BufferedImage.TYPE_INT_RGB);

        int width = goal.getWidth(null);
        int height = goal.getHeight(null);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                assertEquals("Pixel in (" + i + ", " + j + ") is different", bufferedResult.getRGB(i, j), bufferedGoal.getRGB(i, j));
            }
        }
	}
	@Test
	public void testShowChanelG() throws IOException {
		MyImageIO mir = new MyImageIO();
		Image img = mir.myRead("/home/administrator/Desktop/ImageReader/bmptest/2.bmp");
		MyImageProcessor mip = new MyImageProcessor();
		Image redimg = mip.showChanelG(img);
		mir.myWrite(redimg, "/home/administrator/Desktop/ImageReader/result/2_green.bmp");

		Image result = mir.myRead("/home/administrator/Desktop/ImageReader/result/2_green.bmp");
		Image goal = ImageIO.read(new FileInputStream("/home/administrator/Desktop/ImageReader/bmptest/goal/2_green_goal.bmp"));
		
        assertEquals("The width of result and the goal is different ", result.getWidth(null), goal.getWidth(null));
        assertEquals("The height of result and the goal is different ", result.getHeight(null), goal.getHeight(null));
        
        BufferedImage bufferedResult = new BufferedImage(result.getWidth(null), result.getHeight(null),
                                                             BufferedImage.TYPE_INT_RGB);
        BufferedImage bufferedGoal = new BufferedImage(goal.getWidth(null), goal.getHeight(null),
                                                             BufferedImage.TYPE_INT_RGB);

        int width = goal.getWidth(null);
        int height = goal.getHeight(null);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                assertEquals("Pixel in (" + i + ", " + j + ") is different", bufferedResult.getRGB(i, j), bufferedGoal.getRGB(i, j));
            }
        }
	}
    @Test
    public void testShowGray() throws IOException {
    	MyImageIO mir = new MyImageIO();
		Image img = mir.myRead("/home/administrator/Desktop/ImageReader/bmptest/1.bmp");
		MyImageProcessor mip = new MyImageProcessor();
		Image redimg = mip.showGray(img);
		mir.myWrite(redimg, "/home/administrator/Desktop/ImageReader/result/1_gray.bmp");

		Image result = mir.myRead("/home/administrator/Desktop/ImageReader/result/1_gray.bmp");
		Image goal = ImageIO.read(new FileInputStream("/home/administrator/Desktop/ImageReader/bmptest/goal/1_gray_goal.bmp"));
		
		
        assertEquals("The width of result and the goal is different ", result.getWidth(null), goal.getWidth(null));
        assertEquals("The height of result and the goal is different ", result.getHeight(null), goal.getHeight(null));
        
        BufferedImage bufferedResult = new BufferedImage(result.getWidth(null), result.getHeight(null),
                                                             BufferedImage.TYPE_INT_RGB);
        BufferedImage bufferedGoal = new BufferedImage(goal.getWidth(null), goal.getHeight(null),
                                                             BufferedImage.TYPE_INT_RGB);

        int width = goal.getWidth(null);
        int height = goal.getHeight(null);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                assertEquals("Pixel in (" + i + ", " + j + ") is different", bufferedResult.getRGB(i, j), bufferedGoal.getRGB(i, j));
            }
        }

    }
}
