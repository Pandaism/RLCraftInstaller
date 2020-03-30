package com.pandaism.application.reference;

public final class Messages {
    //Info
    public static final String DETECT_MC_FOLDER = "MC folder detected...\n";
    public static final String CREATED = "Created folder: ";
    public static final String MC_LAUNCHER_INFO =
            "Sadly this part I can't do systematically yet... Follow these steps to finish the installation:\n" +
            "Run Minecraft launcher\n" +
            "Navigate to installations and create new one\n" +
            "Name it something memorable\n" +
            "Set version to 1.12.2-forge1.12.2-14.23.5.2838\n" +
            "Change game directory from .minecraft to RLCraft\n" +
            "Allocate more ram (4GB or more recommended) by clicking more options and changing JVM argument (-Xmx2G to -Xmx4G ; G represents gigabytes and the number how many)\n" +
            "Click create.";

    //Errors
    public static final String NO_MC_ERROR = "Minecraft folder not found...\nPlease run MC Launcher to generate proper directories first\n";
    public static final String NO_BIN = "Bin folder not detected...\n Please contact Mike (Pandaism)\n";
}
