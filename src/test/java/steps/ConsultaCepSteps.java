package steps;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

import org.apache.http.HttpStatus;

import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ConsultaCepSteps {

    Response response;

    @Before
    public static void setUp() {
        baseURI = "https://viacep.com.br/ws/";
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
    }


    // ######### Cenario: Consulta CEP valido #########


    @Dado("que informo um cep valido e realizo a consulta")
    public void queInformoUmCepValidoERealizoAConsulta() {
        response =
                when()
                        .get("91060900/json/");
    }

    @Entao("deve retornar o CEP, logradouro, complemento, bairro, localidade, uf e ibge")
    public void deveRetornarOCEPLogradouroComplementoBairroLocalidadeUfEIbge() {
        response
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("cep", is("91060-900"))
                .body("logradouro", is("Avenida Assis Brasil"))
                .body("complemento", is("3940"))
                .body("bairro", is("São Sebastião"))
                .body("localidade", is("Porto Alegre"))
                .body("uf", is("RS"))
                .body("ibge", is("4314902"));
    }


    // ######### Cenario: Consulta CEP inexistente #########


    @Dado("informo um cep inexistente e realizo a consulta")
    public void informoUmCepInexistenteERealizoAConsulta() {
        response =
                when()
                        .get("9100/json/");
    }

    @Entao("e retornada um atributo erro")
    public void eRetornadaUmAtributoErro() {
        response.
                then()
                .contentType("text/html; charset=utf-8")
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }


    // ######### Cenario: Consulta CEP com formato invalido #########


    @Dado("que o usuario inseri um CEP com formato invalido e consulta o servico")
    public void queOUsuarioInseriUmCEPComFormatoInvalidoEConsultaOServico() {
        response =
                when()
                        .get("80.420-120/json/");
    }

    @Entao("deve retornar um bad request")
    public void deveRetornarUmBadrequest() {
        response
                .then()
                .contentType("text/html; charset=utf-8")
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }


    // ######### Cenario: Consulta Cidade Gravataí #########


    @Dado("que o usuario consulta o servico {string}")
    public void queOUsuarioConsultaOServico(String url) {
        response =
                when()
                        .get(url);
    }

    @Entao("deve retornar os dados da cidade Gravatai")
    public void deveRetornarOsDadosDaCidadeGravatai() {
        response
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("size()", is(2))

                .body("cep[0]", is("94085-170"))
                .body("logradouro[0]", is("Rua Ari Barroso"))
                .body("complemento[0]", is(""))
                .body("bairro[0]", is("Morada do Vale I"))
                .body("localidade[0]", is("Gravataí"))
                .body("uf[0]", is("RS"))
                .body("ibge[0]", is("4309209"))

                .body("cep[1]", is("94175-000"))
                .body("logradouro[1]", is("Rua Almirante Barroso"))
                .body("complemento[1]", is(""))
                .body("bairro[1]", is("Recanto Corcunda"))
                .body("localidade[1]", is("Gravataí"))
                .body("uf[1]", is("RS"))
                .body("ibge[1]", is("4309209"));
    }

}
