package com.b1a9idps.itextsandbox;

import java.io.File;
import java.io.IOException;

public abstract class AbstractPdfCreator {

    private static final String DEST_DIR = "results";

    private final String destPath;

    public AbstractPdfCreator(String destPath) {
        this.destPath = destPath;
    }

    void generatePdf() throws IOException {
        File file = new File(destPath());
        file.getParentFile().mkdirs();
        createPdf();
    }

    protected String destPath() {
        return DEST_DIR + this.destPath;
    }

    protected abstract void createPdf() throws IOException;
}
