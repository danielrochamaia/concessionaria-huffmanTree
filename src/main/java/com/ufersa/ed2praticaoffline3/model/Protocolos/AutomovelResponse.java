package com.ufersa.ed2praticaoffline3.model.Protocolos;

import com.ufersa.ed2praticaoffline3.model.entities.Automovel;

public class AutomovelResponse {

    public String chave;
    public Automovel automvel;

    public AutomovelResponse(String chave, Automovel automovel){
        this.chave = chave;
        this.automvel = automovel;
    }
}
