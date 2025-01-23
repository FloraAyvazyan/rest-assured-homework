package models.FileUpload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileUploadMetadata {

    @JsonProperty("additionalMetadata")
    private String additionalMetadata;

    @JsonProperty("fileName")
    private String fileName;

    @JsonProperty("fileLength")
    private long fileLength;

    public String getAdditionalMetadata() {
        return additionalMetadata;
    }

    public void setAdditionalMetadata(String additionalMetadata) {
        this.additionalMetadata = additionalMetadata;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    @Override
    public String toString() {
        return "FileUploadMetadata{" +
                "additionalMetadata='" + additionalMetadata + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileLength=" + fileLength +
                '}';
    }
}