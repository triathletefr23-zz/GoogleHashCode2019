import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

final class Helper {
    final static String OUTPUT_DIR = "output\\";

    final static String getFileName(String fullPath) {
        return OUTPUT_DIR + fullPath.split("\\\\")[1];
    }

    final static boolean exists(String filePath) {
        var file = new File(filePath);
        return file.exists();
    }
}
