import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class StartCLI {
    public File dir;
    public String command = "";
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void start(String arg) throws IOException {
        dir = new File(arg);
        while (!command.equals("stop")) {
            String[] str = reader.readLine().split(" ");
            command = str[0];
            switch (command) {
                case "ls" -> {
                    for (File file : Objects.requireNonNull(dir.listFiles())) {
                        System.out.println(file.getName());
                    }
                }
                case "mkdir" -> {
                    File theCreationgDir = new File(dir + "\\" + str[1]);
                    if(!theCreationgDir.exists()) {
                        theCreationgDir.mkdirs();
                    } else {
                        System.out.println("||--The directory is alredy exist--||");
                    }
                }
                case "cd" -> {
                    if (str[1].equals("..")) {
                        String path = dir.getPath();
                        int lastIndex = path.lastIndexOf("\\");
                        path = path.substring(0, lastIndex);
                        dir = new File(path);
                        System.out.println("||--Your directory is: " + dir.getPath() + "--||");
                    } else {
                        String path = dir + "\\" + str[1];
                        File tst = new File(path);
                        if (tst.exists()) {
                            dir = tst;
                        }
                        System.out.println("||--Your directory is: " + dir.getPath() + "--||");
                    }
                }
            }
        }
    }
}
