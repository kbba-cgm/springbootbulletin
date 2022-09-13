package com.cgmgl.springbootbulletin.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;

public class PhotoUploadHelper {    
    public static String getImageExtension(String base64String) {
		String[] strings = base64String.split(",");
		String extension;
		switch (strings[0]) {// check image's extension
		case "data:image/jpeg;base64":
			extension = "jpeg";
			break;
		case "data:image/png;base64":
			extension = "png";
			break;
		case "data:image/webp;base64":
			extension = "webp";
			break;
		case "data:image/gif;base64":
			extension = "gif";
			break;
		default:
			extension = "jpg";
			break;
		}
		return extension;
	}

    public static String getRootStoragePath() {
        return new File("src\\main\\resources\\static\\uploads\\").getAbsolutePath();	
	}

	public static String getProfileImgStorePath(String base64String) throws IOException {
		if (base64String == null || base64String.length() <= 0)
			return null;

		String base_file_path = getRootStoragePath() + "\\profiles\\";
		if (!Files.exists(Paths.get(base_file_path)))
			Files.createDirectories(Paths.get(base_file_path));

		String fileExtension = getImageExtension(base64String);
		UUID fileName = UUID.randomUUID();

		String file_path = "profiles\\" + fileName + "." + fileExtension;

		return file_path;
	}

	@SuppressWarnings("resource")
	public static String fileToBase64String(String file_path) throws IOException {
		if (file_path == null || file_path.length() <= 0)
			return null;
		
		String base64String = null;
		File file = new File(file_path);
		if (file.getAbsoluteFile().exists()) {
			FileInputStream fis = new FileInputStream(file);
			byte byteArray[] = new byte[(int) file.length()];
			fis.read(byteArray);
			base64String = "data:image/png;base64," + Base64.encodeBase64String(byteArray);
		}

		return base64String;
	}

    public static void writeImageData(String base64String, String file_path) throws FileNotFoundException, IOException {
		if (base64String == null || base64String.length() <= 0)
			return;

        String full_path = getRootStoragePath() + "\\" + file_path;
		String[] block = base64String.split(",");
		String realData = block[1];
		byte[] data = Base64.decodeBase64(realData);
		try (FileOutputStream stream = new FileOutputStream(full_path)) {
			stream.write(data);
		}
	}
}