package objects;

import org.openqa.selenium.By;

import utils.WebBrowser;

public class PaginaLogin {
	
	public PaginaLogin() {
		WebBrowser.loadURL("https://trabalhador.certponto.com.br/#/");
		WebBrowser.waitForElement(By.className("button-btn-access"));
		WebBrowser.waitPageLoad();
	}
	
	public void clicarAcessar() {
		WebBrowser.findElement(By.className("button-btn-access")).click();
	}
	
	public void preencherCPF(String cpf) {
		WebBrowser.waitPageLoad();
		WebBrowser.findElement(By.xpath("//input[@placeholder='CPF']")).sendKeys(cpf);
	}
	
	public void preencherSenha(String senha) {
		WebBrowser.findElement(By.xpath("//input[@placeholder='Senha']")).sendKeys(senha);
	}
	
	public void clicarEntrar() {
		WebBrowser.findElement(By.xpath("//button[contains(text(),'Entrar')]")).click();
	}

}
