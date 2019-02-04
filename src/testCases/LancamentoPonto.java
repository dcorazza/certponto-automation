package testCases;

import org.junit.After;
import org.junit.Test;

import objects.PaginaInicial;
import objects.PaginaLogin;
import objects.PaginaRegistrosPonto;
import objects.PaginaTratamentoPonto;
import utils.WebBrowser;

public class LancamentoPonto {

	//CPF sem m�scara, apenas n�meros
	public static final String CPF = "08303377922";
	
	//Senha de acesso a ferramenta
	public static final String SENHA = "iaristotelesm3";
	
	//Ano referente ao qual se deseja realizar os lan�amentos. Formato yyyy
	public static final String ANO = "2019";
	
	//M�s referente ao qual se deseja realizar os lan�amentos. Formato MM
	public static final String MES = "01";
	
	@Test
	public void lancarPonto() {
		PaginaLogin paginaLogin = new PaginaLogin();
		paginaLogin.clicarAcessar();
		paginaLogin.preencherCPF(CPF);
		paginaLogin.preencherSenha(SENHA);
		paginaLogin.clicarEntrar();
		
		PaginaInicial paginaInicial = new PaginaInicial();
		paginaInicial.clicarTratamentoPonto();
		
		PaginaTratamentoPonto paginaTratamentoPonto = new PaginaTratamentoPonto();
		paginaTratamentoPonto.selecionarAnoMes(ANO + "/" + MES);
		paginaTratamentoPonto.clicarGerar();
		paginaTratamentoPonto.clicarTratamentoPonto();
		
		PaginaRegistrosPonto paginaRegistrosPonto = new PaginaRegistrosPonto();
		paginaRegistrosPonto.selecionar50Registros();
		paginaRegistrosPonto.preencherDiasCom8Horas(ANO, MES);
	}
	
	@After
	public void fecharBrowser() {
		WebBrowser.close();
	}

}
