package objects;

import java.time.YearMonth;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utils.WebBrowser;

public class PaginaRegistrosPonto {
	
	private final String horaEntrada = "08:12";
	private final String horaSaida = "18:18";
	private static final int index= 0;
	public PaginaRegistrosPonto() {
		WebBrowser.waitPageLoad();
		WebBrowser.switchToTab(1);
	}
	
	public void selecionar50Registros() {
		WebBrowser.waitForElement(By.xpath("//select[@name='totalRegistryPage']/option[text() = '50']"));
		WebBrowser.scrollDown();
		
		Select comboQtdRegistros = new Select(WebBrowser.findElement(By.name("totalRegistryPage")));
		comboQtdRegistros.selectByIndex(3);
	}
	
	public void preencherDiasCom8Horas(String ano, String mes) {
		YearMonth anoMes = YearMonth.of(Integer.parseInt(ano), Integer.parseInt(mes));
		
		for(int i = 1; i <= anoMes.lengthOfMonth(); i++) {
			WebElement celulaDia =
					WebBrowser.findElement(By.xpath("//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[" + i + "]/td[8]"));

			WebElement celulaHorasEsperadas =
					WebBrowser.findElementNoWait(By.xpath("//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[" + i + "]/td[12]"));

			WebElement celulaHorasRealizadas =
					WebBrowser.findElementNoWait(By.xpath("//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[" + i + "]/td[13]"));

			/*
			Após inserir a primeira linha, a tabela cria duas colunas intermediárias de "Entrada 1" e "Saída 1", então é necessário buscar em duas colunas
			posteriores
			 */
			if("----".equals(celulaHorasEsperadas.getText())) {
				celulaHorasEsperadas =
						WebBrowser.findElementNoWait(By.xpath("//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[" + i + "]/td[14]"));

				celulaHorasRealizadas =
						WebBrowser.findElementNoWait(By.xpath("//div[@class='k-grid-content k-auto-scrollable']/table/tbody/tr[" + i + "]/td[15]"));
			}

			if(!"00:00".equals(celulaHorasEsperadas.getText()) && "00:00".equals(celulaHorasRealizadas.getText())) {
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
		List<WebElement> elementosInserirLinha =
				WebBrowser.findElementsWithoutVisibility(By.xpath("//form[@name='frmInsertDisregard']/input[@class='pull-right']"));
		elementosInserirLinha.get(index).click();

		List<WebElement> elementosHora =
				WebBrowser.findElementsWithoutVisibility(By.xpath("//table[@class='table table-header-color-none']/tbody/tr[1]/td[3]/input"));
		elementosHora.get(index).sendKeys(horaEntrada);

		List<WebElement> elementosCombo =
				WebBrowser.findElementsWithoutVisibility(By.xpath("//table[@class='table table-header-color-none']/tbody/tr[1]/td[5]/select"));
		Select comboJustificativa = new Select(elementosCombo.get(index));
		comboJustificativa.selectByVisibleText("Problemas Relogio");

		List<WebElement> elementosSalvar =
				WebBrowser.findElementsWithoutVisibility(By.xpath("//table[@class='table table-header-color-none']/tbody/tr[1]/td[7]/input[@title='Salvar']"));
		elementosSalvar.get(index).click();
	}
	
	private void lancarHorarioSaida() {
		List<WebElement> elementosInserirLinha =
				WebBrowser.findElementsWithoutVisibility(By.xpath("//form[@name='frmInsertDisregard']/input[@class='pull-right']"));
		elementosInserirLinha.get(index).click();

		List<WebElement> elementosHora =
				WebBrowser.findElementsWithoutVisibility(By.xpath("//table[@class='table table-header-color-none']/tbody/tr[2]/td[3]/input"));
		elementosHora.get(index).sendKeys(horaSaida);

		List<WebElement> elementosCombo =
				WebBrowser.findElementsWithoutVisibility(By.xpath("//table[@class='table table-header-color-none']/tbody/tr[2]/td[5]/select"));

		Select comboJustificativa = new Select(elementosCombo.get(index));
		comboJustificativa.selectByVisibleText("Problemas Relogio");

		List<WebElement> elementosSalvar =
				WebBrowser.findElementsWithoutVisibility(By.xpath("//table[@class='table table-header-color-none']/tbody/tr[2]/td[7]/input[@title='Salvar']"));
		elementosSalvar.get(index).click();
	}
	
	private void fecharJanelaDia() {
		WebBrowser.scrollUp();
		WebBrowser.findElement(By.xpath("//md-toolbar[@class='md-theme-light _md _md-toolbar-transitions']/div[@class='pull-right']")).click();
	}

}
