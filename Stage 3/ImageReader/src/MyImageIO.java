
import imagereader.IImageIO;

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;


public class MyImageIO implements IImageIO {

    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int ALPHA = 0xff;

    public Image myRead(String filepath) {
        try {
            byte fourBytesArr[] = new byte[FOUR];
            byte twoBytesArr[] = new byte[TWO];
            byte rgb[] = new byte[THREE];
            File f=new File(filepath);
            FileInputStream fs = new FileInputStream(f);
            //#0-13 skip the head
            //#14-17 skip the #14-17
            fs.skip(18);
            //#18-21  keep the wide 
            fs.read(fourBytesArr);
            int width = bytes2Int(fourBytesArr);
            //#22-25 keep the hight
            fs.read(fourBytesArr);
            int height = bytes2Int(fourBytesArr);
            //#26-27
            fs.skip(2);
            //#28-29
            fs.read(twoBytesArr);
            // bit per pixel 
            int bbp = bytes2Int(twoBytesArr);
            //#30-33
            fs.skip(4);
            //#34-37
            fs.read(fourBytesArr);
            int size = bytes2Int(fourBytesArr);
            //#38-53
            fs.skip(16);
            // 24 bit color has no color bit
            if (bbp == 24) {
                int pixels[] = new int[width*height];  
                int complement = 0;
                if (width*THREE%FOUR != 0) {
                    complement = FOUR-(width*THREE%FOUR);
                }
                // or can be written as size/height-width*3;
                for (int i = height-1; i >= 0; i--) {
                    for (int j = 0; j < width; j++) {
                        fs.read(rgb);
                        pixels[i*width+j] = (ALPHA << 24) | bytes2Int(rgb);
                    }
                    fs.skip(complement);
                }
                return Toolkit.getDefaultToolkit().createImage(
                    new MemoryImageSource(width, height, pixels, 0, width));
            }
        } catch (FileNotFoundException e) {
            //
        }  catch (IOException e) {
            //
        }
        return null; 
    }
    public Image myWrite(Image img, String filepath) {
        try {

            File imageFile = new File(filepath);
            
            // Creating a BufferedImage from an Image object
            // Because the ImageIO.write method need a ReneredImage as parameter
            BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null),
                                                             BufferedImage.TYPE_INT_RGB);
            Graphics g = bufferedImage.createGraphics();
            g.drawImage(img, 0, 0, null);
            
            // static boolean write(RenderedImage im, String formatName, File output)
            ImageIO.write(bufferedImage, "bmp", imageFile);
            return img;

        } catch (FileNotFoundException e) {
            //
        }  catch (IOException e) {
            //
        }
        return null;
    }
    //convert the byte array to an interger 
    public int bytes2Int(byte[] bytes) {
        int value = 0;
        for (int i = 0; i < bytes.length; i++) {
            int shift = i*8;
            value |= (bytes[i] & 0x0000ff) << shift;
        }
        return value;
    }
}
