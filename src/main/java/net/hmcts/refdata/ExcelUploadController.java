package net.hmcts.refdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class ExcelUploadController {

    private final MultipartProcessor processor;

    @Autowired
    public ExcelUploadController(MultipartProcessor p) {
        this.processor = p;
    }

    @GetMapping("/")
    public String handleRoot()
    {
        return ("hello");
    }
    @PostMapping("/upload")
    public String handleExcelUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        String filename = file.getOriginalFilename();
        String fn = filename.toLowerCase();
        String[] result = null;
        if (fn.endsWith("xls") || fn.endsWith("xlsx")) {
            result = processor.process(file);
        }
        if (result == null) {
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + filename);
        }
        else {
            redirectAttributes.addFlashAttribute("message",
                    "There were " + result.length + " errors in " + filename);
        }
        return "redirect:/";
    }
}
