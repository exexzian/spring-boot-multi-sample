package com.github.kazuki43zoo.sample;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ScreenApplication.class)
@WebIntegrationTest(randomPort = true)
public class HomeTests {

    @Configuration
    public static class LocalContext {
        @Bean
        RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }

    @Value("http://localhost:${local.server.port}")
    String documentRootUrl;

    @Autowired
    RestOperations restOperations;

    @Test
    public void home() throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);
        HtmlPage page = webClient.getPage(new URL(documentRootUrl));

        assertThat(page.asText(), is("Welcome !!"));

    }

}
