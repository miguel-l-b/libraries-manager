package core.use_cases.api;

import core.entities.Logradouro;

public interface IGetAddressByCEP {
    Logradouro GetAddressByCEP(String cep);
}