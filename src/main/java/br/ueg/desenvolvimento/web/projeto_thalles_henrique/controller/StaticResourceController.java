package br.ueg.desenvolvimento.web.projeto_thalles_henrique.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticResourceController {

    @RequestMapping("/styles/**")
    @ResponseBody
    public String getStyle() {
        return "<link rel=\"stylesheet\" href=\"/styles/bulma.css\">";
    }
}
