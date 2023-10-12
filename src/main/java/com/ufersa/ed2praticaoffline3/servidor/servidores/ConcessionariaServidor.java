package com.ufersa.ed2praticaoffline3.servidor.servidores;

import com.ufersa.ed2praticaoffline3.model.Protocolos.AutomovelResponse;
import com.ufersa.ed2praticaoffline3.model.entities.Automovel;
import com.ufersa.ed2praticaoffline3.model.hashTable.HashTableEncadExt;
import com.ufersa.ed2praticaoffline3.servidor.interfaces.IConcessioanariaServidor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConcessionariaServidor implements IConcessioanariaServidor {

    private static final int CAPACITY = 250;
    private int repetido;
    private HashTableEncadExt<String, Automovel> hashTable;

    public ConcessionariaServidor() throws IOException {
        this.hashTable = new HashTableEncadExt<String, Automovel>(CAPACITY);
        repetido = 0;
    }

    public int addAutomovel(Automovel automovel) {
        var renavam = automovel.getRenavam();
        if(hashTable.containsKey(renavam)){
            System.out.println("REPETIDO!");
            repetido++;
        }
        hashTable.put(automovel.getRenavam(), automovel);
        return repetido;
    }

    public String removerAutomovel(String chave) {
        if(!hashTable.containsKey(chave)){
            return "Chave não existe";
        }

        hashTable.remove(chave);
        //add log
        return "Automóvel removido!";
    }


    public List<AutomovelResponse> buscarTodos() {
        List<AutomovelResponse> list = new ArrayList<>();
        for(var obj : hashTable.entrySet()){
            if(obj != null){
                list.add(new AutomovelResponse(obj.getKey(),obj.getValue()));
            }
        }
        return list;
    }


    public Automovel buscarPorChave(String chave) {
        return hashTable.get(chave);
    }
}
