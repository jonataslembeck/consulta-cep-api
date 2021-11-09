#language: pt
#encoding: utf-8

Funcionalidade: Consultar CEP

  Cenario: Consulta CEP valido
    Dado que informo um cep valido e realizo a consulta
    Entao deve retornar o CEP, logradouro, complemento, bairro, localidade, uf e ibge

  Cenario: Consulta CEP inexistente
    Dado informo um cep inexistente e realizo a consulta
    Entao deve retornar um badrequest

  Cenario: Consulta CEP com formato invalido
    Dado que o usuario inseri um CEP com formato invalido e consulta o servico
    Entao deve retornar um bad request

  Cenario: Consulta Cidade Gravatai
    Dado que o usuario consulta o servico "https://viacep.com.br/ws/RS/Gravatai/Barroso/json/"
    Entao deve retornar os dados da cidade Gravatai
