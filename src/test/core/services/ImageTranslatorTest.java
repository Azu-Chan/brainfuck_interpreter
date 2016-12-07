package test.core.services;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import brainfuck.Instructions;
import brainfuck.core.services.ImageTranslator;

public class ImageTranslatorTest {
	ImageTranslator imageTranslator;
	BufferedImage img;

	@Before
	public void setUp(){
		img = new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB);
	}

	@Test
	public void testIncr(){
		// +
		String prog="+";
		imageTranslator = new ImageTranslator(prog);
		imageTranslator.createImageProg();

		// coord. x
		for(int j = 0; j < 3; j++){
			// coord. y
			for(int k = 0; k < 3; k++){
				img.setRGB(j, k, Instructions.INCR.getRGB());
			}
		}
		assertEquals(img.getRGB(2, 2), imageTranslator.getImg().getRGB(1, 1));
		assertEquals(img.getRGB(2, 2), imageTranslator.getImg().getRGB(0, 0));
	}

	@Test
	public void testDecr(){
		//-
		String prog="-";
		imageTranslator = new ImageTranslator(prog);
		imageTranslator.createImageProg();
		// coord. x
		for(int j = 0; j < 3; j++){
			// coord. y
			for(int k = 0; k < 3; k++){
				img.setRGB(j, k, Instructions.DECR.getRGB());
			}
		}
		assertEquals(img.getRGB(2, 2), imageTranslator.getImg().getRGB(1, 1));
		assertEquals(img.getRGB(2, 2), imageTranslator.getImg().getRGB(1, 0));
	}

	@Test
	public void testRight(){
		//>
		String prog=">";
		imageTranslator = new ImageTranslator(prog);
		imageTranslator.createImageProg();
		// coord. x
		for(int j = 0; j < 3; j++){
			// coord. y
			for(int k = 0; k < 3; k++){
				img.setRGB(j, k, Instructions.RIGHT.getRGB());
			}
		}
		assertEquals(img.getRGB(2, 2), imageTranslator.getImg().getRGB(1, 1));
		assertEquals(img.getRGB(2, 2), imageTranslator.getImg().getRGB(2, 0));
	}

	@Test
	public void testLeft(){
		//<
		String prog="<";
		imageTranslator = new ImageTranslator(prog);
		imageTranslator.createImageProg();
		// coord. x
		for(int j = 0; j < 3; j++){
			// coord. y
			for(int k = 0; k < 3; k++){
				img.setRGB(j, k, Instructions.LEFT.getRGB());
			}
		}
		assertEquals(img.getRGB(2, 2), imageTranslator.getImg().getRGB(1, 1));
		assertEquals(img.getRGB(2, 2), imageTranslator.getImg().getRGB(0, 2));
	}

	@Test
	public void testOut(){
		//.
		String prog=".";
		imageTranslator = new ImageTranslator(prog);
		imageTranslator.createImageProg();
		// coord. x
		for(int j = 0; j < 3; j++){
			// coord. y
			for(int k = 0; k < 3; k++){
				img.setRGB(j, k, Instructions.OUT.getRGB());
			}
		}
		assertEquals(img.getRGB(2, 2), imageTranslator.getImg().getRGB(1, 1));
		assertEquals(img.getRGB(2, 2), imageTranslator.getImg().getRGB(1, 0));
	}

	@Test
	public void testIn(){
		//,
		String prog=",";
		imageTranslator = new ImageTranslator(prog);
		imageTranslator.createImageProg();
		// coord. x
		for(int j = 0; j < 3; j++){
			// coord. y
			for(int k = 0; k < 3; k++){
				img.setRGB(j, k, Instructions.IN.getRGB());
			}
		}
		assertEquals(img.getRGB(2, 2), imageTranslator.getImg().getRGB(1, 1));
		assertEquals(img.getRGB(2, 2), imageTranslator.getImg().getRGB(2, 0));
	}

	@Test
	public void testJump(){
		//[
		String prog="[";
		imageTranslator = new ImageTranslator(prog);
		imageTranslator.createImageProg();
		// coord. x
		for(int j = 0; j < 3; j++){
			// coord. y
			for(int k = 0; k < 3; k++){
				img.setRGB(j, k, Instructions.JUMP.getRGB());
			}
		}
		assertEquals(img.getRGB(2, 2), imageTranslator.getImg().getRGB(1, 1));
		assertEquals(img.getRGB(2, 2), imageTranslator.getImg().getRGB(1, 0));
	}

	@Test
	public void testBack(){
		//]
		String prog="]";
		imageTranslator = new ImageTranslator(prog);
		imageTranslator.createImageProg();
		// coord. x
		for(int j = 0; j < 3; j++){
			// coord. y
			for(int k = 0; k < 3; k++){
				img.setRGB(j, k, Instructions.BACK.getRGB());
			}
		}
		assertEquals(img.getRGB(2, 2), imageTranslator.getImg().getRGB(1, 1));
		assertEquals(img.getRGB(2, 2), imageTranslator.getImg().getRGB(2, 0));
	}

}
