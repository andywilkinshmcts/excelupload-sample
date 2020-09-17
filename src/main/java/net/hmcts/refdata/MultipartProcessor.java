package net.hmcts.refdata;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface MultipartProcessor {
    public String[] process (MultipartFile f);
}
