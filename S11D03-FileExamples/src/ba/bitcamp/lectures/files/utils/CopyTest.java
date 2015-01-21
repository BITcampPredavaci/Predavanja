package ba.bitcamp.lectures.files.utils;

import java.io.File;
import java.io.IOException;

// static import makes public static methods and fields available without FileUtils.

import static ba.bitcamp.lectures.files.utils.FileUtils.*;

/**
 * Test for comparing copy speed of different copy methods and different parameters.
 * 
 * @author emir
 *
 */
public class CopyTest {
	public static void main(String[] args) throws IOException {
		// create test file
		File test1MB = random("test1MB.dat", 1 * MB);
		System.out.println("Created random file: " + test1MB.exists());
		System.out.println("Size in bytes: " + test1MB.length());
		// make sure it is deleted when application finishes
		test1MB.deleteOnExit();
		
		testCopyWithBufferedOutputStream(test1MB, 0, 1*KB, 4*KB, 20*KB, 100*KB);
		testCopyWithBuffer(test1MB, 1, 1*KB, 4*KB, 20*KB, 100*KB, 200*KB);
	}

	private static void testCopyWithBufferedOutputStream(File test1mb, int...bufferSizes) {
		System.out.println("\nStarting copy test with BufferedOutputStream");
		for (int bufferSize : bufferSizes) {
			File out = new File("copy" + bufferSize);
			try {
				long time = System.nanoTime();
				copy(test1mb, out , bufferSize);
				time = System.nanoTime() - time;
				System.out.println(String.format("Buffer size %6d \tCopy time: %d", bufferSize, time));
			} catch (IOException e) {
				System.err.println("Error copying file: " + e.getMessage());
			} finally {
				out.delete();
			}
		}
	}
	
	private static void testCopyWithBuffer(File source, int...bufferSizes) {
		System.out.println("\nStarting buffered copy test with buffer");
		for (int bufferSize : bufferSizes) {
			File out = new File("copy" + bufferSize);
			try {
				long time = System.nanoTime();
				copy(source, out , new byte[bufferSize]);
				time = System.nanoTime() - time;
				System.out.println(String.format("Buffer size %6d \tCopy time: %d", bufferSize, time));
			} catch (IOException e) {
				System.err.println("Error copying file: " + e.getMessage());
			} finally {
				out.delete();
			}
		}
	}
}
