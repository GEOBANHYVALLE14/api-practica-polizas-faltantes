package com.coppel.api.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

public class Utilidades {
	public static String FechaRegistro() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public static Timestamp fechaTimestamp() {
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		System.out.println("Current Time Stamp: " + timestamp);
		return timestamp;
	}
	
	public static String sanitizeInputAndOutput(String untrustedHTML) {

        PolicyFactory policy = new HtmlPolicyBuilder()
                .allowAttributes("src").onElements("img")
                .allowAttributes("href").onElements("a")
                .allowStandardUrlProtocols()
                .allowElements(
                        "a", "img"
                ).toFactory();
        //StringEscapeUtils.unescapeHtml(cleanedHtml)
        String salida = policy.sanitize(untrustedHTML);
        String validaCaracteresEspeciales = salida.
                replaceAll("&amp;", "&").
                replaceAll("&lt;", "<").
                replaceAll("&gt;", ">").
                replaceAll("&#64;", "@").
                replaceAll("&#34;", "\"").
                replaceAll("&#39;", "'").
                replaceAll("&#61;", "=").
                replaceAll("&#43;", "+");
        return validaCaracteresEspeciales;
    }


}
