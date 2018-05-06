//date is 2018-05-04
import imagereader.*;

import java.io.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;

public class ImageRunner {
	//add a new main method
	public static void main(String[] args) {
		MyImageIO mir = new MyImageIO();
		//add a new method
		MyImageProcessor mip = new MyImageProcessor();
		// Hava a run
		Runner.run(mir, mip);
	}
}