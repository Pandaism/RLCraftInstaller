package com.pandaism.application;


import com.pandaism.application.concurrent.InstallTask;
import com.pandaism.application.reference.Messages;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {
    public TextArea log_area;
    public ProgressBar progress_bar;

    public void install() {
        ExecutorService downloaderService = Executors.newFixedThreadPool(1);
        downloaderService.execute(new InstallTask(this.log_area, this.progress_bar));
        downloaderService.shutdown();

        ExecutorService finishService = Executors.newFixedThreadPool(1);
        finishService.execute(() -> {
            while(!downloaderService.isTerminated()) {
                System.out.println("[Debug] Awaiting Thread Closure...");
            }

            Platform.runLater(() -> new Alert(Alert.AlertType.INFORMATION, Messages.MC_LAUNCHER_INFO, ButtonType.OK).show());
        });
        finishService.shutdown();
    }
}
