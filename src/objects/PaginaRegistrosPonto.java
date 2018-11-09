package objects;

import java.time.YearMonth;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utils.WebBrowser;

public class PaginaRegistrosPonto {
	
	public PaginaRegistrosPonto() {
		WebBrowser.waitForElement(By.name("totalRegistryPage"));
		WebBrowser.waitPageLoad();
		WebBrowser.switchToTab(1);
	}
	
	public void selecionar50Registros() {
		WebBrowser.waitForElement(By.xpath("//select[@name='totalRegistryPage']/option[text() = '50']"));
		WebBrowser.scrollDown();
		
		Select comboQtdRegistros = new Select(WebBrowser.findElement(By.name("totalRegistryPage")));
		comboQtdRegistros.selectByIndex(2);
	}
	
	public void preencherDiasCom8Horas(String ano, String mes) {
		YearMonth anoMes = YearMonth.of(Integer.parseInt(ano), Integer.parseInt(mes));
		
		for(int i = 1; i <= anoMes.lengthOfMonth(); i++) {
			WebElement celulaDia = WebBrowser.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[" + i + "]/td[8]"));
			
			if(!"Sábado".equals(celulaDia.getText()) && !"Domingo".equals(celulaDia.getText())) {
				celulaDia.click();
				WebBrowser.waitPageLoad();
				lancarHorarioEntrada();
				WebBrowser.waitPageLoad();
				lancarHorarioSaida();
				WebBrowser.waitPageLoad();
				fecharJanelaDia();
				WebBrowser.waitPageLoad();
			}
		}
	}
	
	private void lancarHorarioEntrada() {
		WebBrowser.clickElement(By.xpath("//form[@name='frmInsertDisregard']/input[@class='pull-right']"));
		WebBrowser.findElement(By.xpath("//table[@class='table table-header-color-none']/tbody/tr[1]/td[3]/input")).sendKeys("09:00");
		
		Select comboJustificativa = new Select(WebBrowser.findElement(By.xpath("//table[@class='table table-header-color-none']/tbody/tr[1]/td[5]/select")));
		comboJustificativa.selectByVisibleText("Problemas Relogio");
		
		WebBrowser.clickElement(By.xpath("//table[@class='table table-header-color-none']/tbody/tr[1]/td[7]/input[@title='Salvar']"));
	}
	
	private void lancarHorarioSaida() {
		WebBrowser.clickElement(By.xpath("//form[@name='frmInsertDisregard']/input[@class='pull-right']"));
		WebBrowser.findElement(By.xpath("//table[@class='table table-header-color-none']/tbody/tr[2]/td[3]/input")).sendKeys("18:00");
		
		Select comboJustificativa = new Select(WebBrowser.findElement(By.xpath("//table[@class='table table-header-color-none']/tbody/tr[2]/td[5]/select")));
		comboJustificativa.selectByVisibleText("Problemas Relogio");
		
		WebBrowser.clickElement(By.xpath("//table[@class='table table-header-color-none']/tbody/tr[2]/td[7]/input[@title='Salvar']"));
	}
	
	private void fecharJanelaDia() {
		WebBrowser.scrollUp();
		WebBrowser.findElement(By.xpath("//md-toolbar[@class='md-theme-light _md _md-toolbar-transitions']/div[@class='pull-right']")).click();
	}

}
