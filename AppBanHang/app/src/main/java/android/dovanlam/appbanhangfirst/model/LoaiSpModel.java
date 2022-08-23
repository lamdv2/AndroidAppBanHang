package android.dovanlam.appbanhangfirst.model;

import java.util.List;

public class LoaiSpModel {
    boolean success;
    String message;
    List<LoaiSp> resutl;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LoaiSp> getResutl() {
        return resutl;
    }

    public void setResutl(List<LoaiSp> resutl) {
        this.resutl = resutl;
    }
}
