package app.ports.providers;

import core.entities.Logradouro;
import core.entities.CEP;

public interface ICEPProvider {
    Logradouro getAddress(CEP cep) throws Exception;
}