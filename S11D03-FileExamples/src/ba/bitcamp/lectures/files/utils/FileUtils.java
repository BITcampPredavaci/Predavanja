package ba.bitcamp.lectures.files.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 * Utility class with methods for working with files.
 * 
 * @author emir
 *
 */
public class FileUtils {
	public static final int KB = 1 << 10;
	public static final int MB = 1 << 20;
	public static final int GB = 1 << 30;
	
	/**
	 * Creates new file of provided size with random values
	 * 
	 * @param path to create file at
	 * @param sizeBytes new file size
	 * @return created file
	 * @throws IOException in case file cannot be created
	 */
	public static File random(String path, long sizeBytes) throws IOException {
		File rand = new File(path);
		random(rand, sizeBytes);
		return rand;
	}
	
	/**
	 * Creates  new file of provided size with random values
	 * @param target file to create new file
	 * @param sizeBytes new file size
	 * @throws IOException in case file cannot be created
	 */
	public static void random(File target, long sizeBytes) throws IOException {
		Random rand = new Random();
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(target));
		try {
			long written = 0;
			while (written++ < sizeBytes) {
				bos.write(rand.nextInt());
			}
		} finally {
			bos.close();
		}
		
	}
	
	/**
	 * Copy bytes from input to output stream and closes both streams
	 * 
	 * @param in stream to read from
	 * @param out stream to write to
	 * @throws IOException in case of read/write error or out close error
	 */
	public static void copy(InputStream in, OutputStream out) throws IOException {
		try {
			int b;
			while ((b = in.read()) > 0) {
				out.write(b);
			}
		} finally {
			// in is closed quietly and output is not
			closeQuietly(in);
			out.close();
		}
	}
	
	/**
	 * Copies file using  {@link BufferedInputStream} and {@link BufferedOutputStream} of requested size.
	 * In case buffer size is set to 0, buffered streams are not used.
	 *  
	 * @param source file to copy from
	 * @param target file to copy to
	 * @param bufferSize to use when copying
	 * @throws IOException in case of read/write exception
	 */
	public static void copy(File source, File target, int bufferSize) throws IOException {
		InputStream in = new FileInputStream(source);
		OutputStream out = new FileOutputStream(target);
		if (bufferSize > 0) {
			in = new BufferedInputStream(in, bufferSize);
			out = new BufferedOutputStream(out, bufferSize);
		}
		copy(in, out);
	}
	
	/**
	 * Copies file using provided buffer.
	 *  
	 * @param source file to copy from
	 * @param target file to copy to
	 * @param buffer to use when copying
	 * @throws IOException in case of read/write exception
	 */
	public static void copy(File source, File target, byte[] buffer) throws IOException {
		FileInputStream fis = new FileInputStream(source);
		FileOutputStream fos = new FileOutputStream(target);
		try {
			int read = 0;
			while ((read = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, read);
			}
		} finally {
			closeQuietly(fis);
			fos.close();
		}
	}
	
	/**
	 * Tries to close provided closable if not null. Ignores errors if fails.
	 *  
	 * @param closeable to close
	 * @return true if close is successfully invoked
	 */
	public static boolean closeQuietly(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
				return true;
			} catch (IOException e) {
				// ignore
			}
		}
		return false;
	}
}
