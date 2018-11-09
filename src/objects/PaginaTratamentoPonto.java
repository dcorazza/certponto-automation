package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import utils.InternalErrorException;
import utils.WebBrowser;

public class PaginaTratamentoPonto {
	
	public PaginaTratamentoPonto() {
		WebBrowser.waitForElement(By.id("cycle"));
		WebBrowser.waitPageLoad();
	}
	
	public void selecionarAnoMes(String anoMes) {
		WebBrowser.waitForElement(By.xpath("//select[@name='cycle']/option[text() = '" + anoMes + "']"));
		Select comboAnoMes = new Select(WebBrowser.findElement(By.id("cycle")));
		comboAnoMes.selectByVisibleText(anoMes);
	}
	
	public void clicarGerar() {		
		WebBrowser.findElement(By.id("pesquisar")).click();
	}
	
	public void clicarTratamentoPonto() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new InternalErrorException("Erro ao esperar pelo botão de tratamento de ponto");
		}
		
		WebBrowser.findElement(By.xpath("//input[@title='Tratamento de Ponto']")).click();
	}

}
