package com.nm.exam.services;

import com.nm.exam.model.Annonce;
import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class PdfService {

    public static String output = "src/main/resources/static/pdf/rest-with-spring.pdf";

    private String parseThymeleafTemplate(List<Annonce> annonces) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();

        context.setVariable("annonces", annonces);

        return templateEngine.process("templates/annonce/annonce_pdf_template.html", context);
    }

    public void generatePdfFromHtml(List<Annonce> annonces) throws IOException, DocumentException {
        String html = this.parseThymeleafTemplate(annonces);
        OutputStream outputStream = new FileOutputStream(output);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);


        outputStream.close();
    }
}
