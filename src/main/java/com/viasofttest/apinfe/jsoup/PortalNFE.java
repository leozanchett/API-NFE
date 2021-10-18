package com.viasofttest.apinfe.jsoup;

import com.viasofttest.apinfe.entity.NFE;
import com.viasofttest.apinfe.enums.StatusNFE;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PortalNFE {
    private static final String SERVICO_DISPONIVEL = "http://www.nfe.fazenda.gov.br/portal/imagens/bola_verde_P.png";
    private static final String STATUS_SERVICO = "status serviço4";

    private static Integer getColunaStatusServico(Document doc) {
        Elements statusServico = doc.getElementsByTag("th");
        statusServico.remove(0);
        Integer colunaStatusServico = 0;
        for (Element element : statusServico) {
            colunaStatusServico += 1;
            if (element.text().toLowerCase().equals(STATUS_SERVICO)) {
                break;
            }
        }
        return colunaStatusServico;
    }

    private static NFE getDescricaoStatusServico(Element img, Element element) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        if (img.absUrl("src").equals(SERVICO_DISPONIVEL)) {
            System.out.println(String.format("%s, disponivel, %s", element.select("td").first().text(), formatter.format(date)));
            //return "<br>" + String.format("%s, disponivel, %s", element.select("td").first().text(), formatter.format(date));
            return new NFE(element.select("td").first().text(), StatusNFE.DISPONIVEL);
        } else {
            System.out.println(String.format("%s, indisponivel, %s", element.select("td").first().text(), formatter.format(date)));
            //return "<br>" + String.format("%s, indisponível, %s", element.select("td").first().text(), formatter.format(date));
            return new NFE(element.select("td").first().text(), StatusNFE.INDISPONIVEL);
        }
    }

    public static List<NFE> getStatusServicos() throws IOException {
        List<NFE> statusServicos = new ArrayList<>();
        Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
        Elements tr = doc.getElementsByTag("tr");
        Integer colunaStatusServico = getColunaStatusServico(doc);
        Integer colunaImgStatusServico = 0;
        for (Element element : tr) {
            Elements imgs = element.getElementsByTag("img");
            for (Element img : imgs) {
                colunaImgStatusServico += 1;
                if (colunaImgStatusServico == colunaStatusServico) {
                    colunaImgStatusServico = 0;
                    statusServicos.add(getDescricaoStatusServico(img, element));
                    break;
                }
            }
        }
        return statusServicos;
    }

}

