package objects;

import org.openqa.selenium.By;

import utils.WebBrowser;

public class PaginaInicial {
	
	public PaginaInicial() {
		WebBrowser.waitForElement(By.xpath("//span[contains(text(),'Tratamento de Ponto')]"));
		WebBrowser.waitPageLoad();
	}
	
	public void clicarTratamentoPonto() {
		WebBrowser.findElement(By.xpath("//span[contains(text(),'Tratamento de Ponto')]")).click();
	}

}
