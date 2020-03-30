package com.pandaism.application.concurrent;

import com.pandaism.application.Main;
import com.pandaism.application.reference.Messages;
import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicLong;

public class InstallTask implements Runnable {
    private TextArea textArea;
    private ProgressBar progressBar;

    public InstallTask(TextArea textArea, ProgressBar progressBar) {
        this.textArea = textArea;
        this.progressBar = progressBar;
    }

    private double calculateProgess(double size) {
        return (size / (double)Main.MAX_BYTE);
    }

    @Override
    public void run() {
        File minecraftFolder = new File(System.getProperty("user.home") + "/AppData/Roaming/.minecraft/");
        if(minecraftFolder.exists()) {
            this.textArea.appendText(Messages.DETECT_MC_FOLDER);
            System.out.println(Messages.DETECT_MC_FOLDER);

            AtomicLong copySize = new AtomicLong();

            File forgeFiles = new File("./bin/forge-1.12.2/");
            try {
                Files.walk(forgeFiles.toPath()).filter(Files::isRegularFile).forEach(path -> {
                    File file = new File(path.toString().replace(".\\bin\\forge-1.12.2\\", minecraftFolder.getAbsolutePath() + "\\"));
                    File folder = new File(path.toFile().getParentFile().getPath().replace(".\\bin\\forge-1.12.2\\", minecraftFolder.getAbsolutePath() + "\\"));

                    if (!folder.exists()) {
                        if (folder.mkdirs()) {
                            Platform.runLater(() -> this.textArea.appendText(Messages.CREATED + folder.getAbsolutePath() + "\n"));
                        }
                    }

                    if (!file.exists()) {
                        try {
                            Files.copy(path, file.toPath());
                            Platform.runLater(() -> {
                                this.textArea.appendText(Messages.CREATED + file.getAbsolutePath() + "\n");
                                System.out.println(Messages.CREATED + file.getAbsolutePath() + "\n");
                                copySize.addAndGet(file.length());
                                this.progressBar.setProgress(calculateProgess(copySize.get()));
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            File rlcraftFiles = new File("./bin/RLCraft");
            try {
                Files.walk(rlcraftFiles.toPath()).filter(Files::isRegularFile).forEach(path -> {
                    File file = new File(path.toString().replace(".\\bin\\", System.getProperty("user.home") + "\\AppData\\Roaming\\"));
                    File folder = new File(path.toFile().getParentFile().getPath().replace(".\\bin\\", System.getProperty("user.home") + "\\AppData\\Roaming\\"));

                    if (!folder.exists()) {
                        if (folder.mkdirs()) {
                            Platform.runLater(() -> this.textArea.appendText(Messages.CREATED + folder.getAbsolutePath() + "\n"));
                        }
                    }

                    if (!file.exists()) {
                        try {
                            Files.copy(path, file.toPath());
                            Platform.runLater(() -> {
                                this.textArea.appendText(Messages.CREATED + file.getAbsolutePath() + "\n");
                                System.out.println(Messages.CREATED + file.getAbsolutePath() + "\n");
                                copySize.addAndGet(file.length());
                                this.progressBar.setProgress(calculateProgess(copySize.get()));
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Platform.runLater(() -> this.textArea.appendText(Messages.NO_MC_ERROR));
        }
    }
}
