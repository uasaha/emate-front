package me.emate.matefront.main;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@RestController
public class MainRestController {
    @GetMapping("/dGltZXpvbmU=")
    public String getTime() {
        return LocalDateTime.now().toString();
    }

    @GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody byte[] getRobots() throws IOException {
        InputStream txt = getClass().getResourceAsStream("/static/robots.txt");
        if (txt == null) {
            return null;
        }
        return IOUtils.toByteArray(txt);
    }

    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody byte[] getSiteMap() throws IOException {
        InputStream xml = getClass().getResourceAsStream("/static/sitemap.xml");
        if (xml == null) {
            return null;
        }
        return IOUtils.toByteArray(xml);
    }
}
