package com.nm.exam.controller;

import com.nm.exam.model.Annonce;
import com.nm.exam.services.AnnonceService;
import com.nm.exam.services.PdfService;
import com.lowagie.text.DocumentException;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/export")
public class ExportController {

    @Autowired
    private AnnonceService as;

    @Autowired
    private PdfService pdfService;

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public void pdf(HttpServletResponse response) throws IOException, DocumentException {
        List<Annonce> annonces = this.as.getAnnonces();

        this.pdfService.generatePdfFromHtml(annonces);

        InputStream inputStream = new FileInputStream(new File("src/main/resources/static/pdf/Liste_Annonces.pdf"));
        IOUtils.copy(inputStream, response.getOutputStream());

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=export_annonces_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        response.flushBuffer();

    }
}